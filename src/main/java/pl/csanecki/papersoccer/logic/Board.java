package pl.csanecki.papersoccer.logic;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private int boardWidth;
    private int boardHeight;

    private Map<Coordinate, Node> boardNodes;
    private Coordinate ballCoordinates;

    public Board(int width, int height) {
        this.boardWidth = width;
        this.boardHeight = height;

        initializeNodesNetwork(width, height);
        setBallInTheMiddle();
    }

    private void initializeNodesNetwork(int width, int height) {
        boardNodes = new HashMap<>();

        for (int x = 0; x < width + 1; x++) {
            for (int y = 0; y < height + 1; y++) {
                boardNodes.put(new Coordinate(x, y), Node.PLAIN_FIELD);
            }
        }
    }

    private void setBallInTheMiddle() {
        int middleOfWidth = (boardWidth) / 2;
        int middleOfHeight = (boardHeight) / 2;

        ballCoordinates = new Coordinate(middleOfWidth, middleOfHeight);
    }

    public String play(int move) {
        if(ballCoordinates.getX() == getBoardWidth() / 2 && ballCoordinates.getY() == 0 && move == 8) {
            return "Player2 Wins";
        }

        if(ballCoordinates.getX() == getBoardWidth() / 2 - 1 && ballCoordinates.getY() == 0 && move == 9) {
            return "Player2 Wins";
        }

        if(ballCoordinates.getX() == getBoardWidth() / 2 + 1 && ballCoordinates.getY() == 0 && move == 7) {
            return "Player2 Wins";
        }

        moveBall(move);

        return "Game Underway";
    }

    private void moveBall(int move) {
        int currentBallX = ballCoordinates.getX();
        int currentBallY = ballCoordinates.getY();

        switch (move) {
            case 1:
                ballCoordinates = new Coordinate( currentBallX - 1, currentBallY + 1);
                break;
            case 2:
                ballCoordinates = new Coordinate( currentBallX, currentBallY + 1);
                break;
            case 3:
                ballCoordinates = new Coordinate( currentBallX + 1, currentBallY + 1);
                break;
            case 4:
                if(currentBallX - 1 < 0) {
                    throw new RuntimeException("Not allowed to move out of board");
                }

                ballCoordinates = new Coordinate( currentBallX - 1, currentBallY);
                break;
            case 6:
                if(currentBallX + 1 > getBoardWidth()) {
                    throw new RuntimeException("Not allowed to move out of board");
                }

                ballCoordinates = new Coordinate( currentBallX + 1, currentBallY);
                break;
            case 7:
                ballCoordinates = new Coordinate( currentBallX - 1, currentBallY - 1);
                break;
            case 8:
                ballCoordinates = new Coordinate( currentBallX, currentBallY - 1);
                break;
            case 9:
                ballCoordinates = new Coordinate( currentBallX + 1, currentBallY - 1);
                break;
            default:
                throw new RuntimeException("Not allowed movement of ball");
        }
    }

    public int getBoardWidth() {
        return this.boardWidth;
    }

    public Coordinate getBallCoordinates() {
        return this.ballCoordinates;
    }
}
