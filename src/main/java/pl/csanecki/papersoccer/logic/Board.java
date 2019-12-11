package pl.csanecki.papersoccer.logic;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private Map<Coordinate, Node> boardNodes;
    private Coordinate ballCoordinates;

    public Board(int width, int height) {
        initializeNodesNetwork(width, height);
        setBallInTheMiddle(width, height);
    }

    private void initializeNodesNetwork(int width, int height) {
        boardNodes = new HashMap<>();

        for (int x = 0; x < width + 1; x++) {
            for (int y = 0; y < height + 1; y++) {
                boardNodes.put(new Coordinate(x, y), Node.PLAIN_FIELD);
            }
        }

        boardNodes.put(new Coordinate(width / 2, -1), Node.PLAYER_ONE_GOAL);
        boardNodes.put(new Coordinate(width / 2, height + 1), Node.PLAYER_ONE_GOAL);
    }

    private void setBallInTheMiddle(int width, int height) {
        int middleOfWidth = (width) / 2;
        int middleOfHeight = (height) / 2;

        ballCoordinates = new Coordinate(middleOfWidth, middleOfHeight);
    }

    public String moveBall(int move) {
        int currentBallX = ballCoordinates.getX();
        int currentBallY = ballCoordinates.getY();

        Coordinate checkCoordinate;

        switch (move) {
            case 1:
                checkCoordinate = new Coordinate(currentBallX - 1, currentBallY + 1);
                break;
            case 2:
                checkCoordinate = new Coordinate(currentBallX, currentBallY + 1);
                break;
            case 3:
                checkCoordinate = new Coordinate(currentBallX + 1, currentBallY + 1);
                break;
            case 4:
                checkCoordinate = new Coordinate(currentBallX - 1, currentBallY);
                break;
            case 6:
                checkCoordinate = new Coordinate(currentBallX + 1, currentBallY);
                break;
            case 7:
                checkCoordinate = new Coordinate(currentBallX - 1, currentBallY - 1);
                break;
            case 8:
                checkCoordinate = new Coordinate(currentBallX, currentBallY - 1);
                break;
            case 9:
                checkCoordinate = new Coordinate(currentBallX + 1, currentBallY - 1);
                break;
            default:
                throw new RuntimeException("Not allowed movement of ball");
        }

        return checkResultOfMovement(checkCoordinate);
    }

    private String checkResultOfMovement(Coordinate checkCoordinate) {
        Node checkNode = boardNodes.get(checkCoordinate);

        if(checkNode == Node.PLAIN_FIELD) {
            ballCoordinates = checkCoordinate;
            return "Game Underway";
        } else if(checkNode == Node.PLAYER_ONE_GOAL) {
            return "Player2 Wins";
        } else if(checkNode == Node.PLAYER_TWO_GOAL) {
            return "Player1 Wins";
        } else {
            throw new RuntimeException("Not allowed to move out of board");
        }
    }

    public Coordinate getBallCoordinates() {
        return this.ballCoordinates;
    }
}
