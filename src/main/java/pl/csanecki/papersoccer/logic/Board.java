package pl.csanecki.papersoccer.logic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Board {
    private Map<Coordinate, Node> boardNodes;
    private Set<Edge> usedEdges;

    private Coordinate ballStatingCoordinate;
    private Coordinate ballCoordinate;

    public Board(int width, int height) {
        this.boardNodes = new HashMap<>();
        this.usedEdges = new HashSet<>();

        initializeNodesNetwork(width, height);
        setBallInTheMiddle(width, height);
    }

    private void initializeNodesNetwork(int width, int height) {
        for (int x = 0; x < width + 1; x++) {
            for (int y = 0; y < height + 1; y++) {
                boardNodes.put(new Coordinate(x, y), Node.PLAIN_FIELD);
            }
        }

        setUpPlayersGoals(width, height);
        setUpBorderOfBoard(width, height);
    }

    private void setUpPlayersGoals(int width, int height) {
        boardNodes.put(new Coordinate(width / 2, -1), Node.PLAYER_ONE_GOAL);
        boardNodes.put(new Coordinate(width / 2, height + 1), Node.PLAYER_TWO_GOAL);
    }

    private void setUpBorderOfBoard(int width, int height) {
        for (int i = 0; i < height; i++) {
            Coordinate firstCoordinate = new Coordinate(0, i);
            Coordinate secondCoordinate = new Coordinate(0, i + 1);

            usedEdges.add(new Edge(firstCoordinate, secondCoordinate));

            firstCoordinate = new Coordinate(width, i);
            secondCoordinate = new Coordinate(width, i + 1);

            usedEdges.add(new Edge(firstCoordinate, secondCoordinate));
        }

        for (int i = 0; i < width; i++) {
            Coordinate firstCoordinateUp = new Coordinate(i, 0);
            Coordinate secondCoordinateUp = new Coordinate(i + 1, 0);

            Coordinate firstCoordinateDown = new Coordinate(i, height);
            Coordinate secondCoordinateDown = new Coordinate(i + 1, height);

            if(firstCoordinateUp.getX() == width / 2 || secondCoordinateUp.getX() == width / 2) {
                continue;
            }

            usedEdges.add(new Edge(firstCoordinateUp, secondCoordinateUp));
            usedEdges.add(new Edge(firstCoordinateDown, secondCoordinateDown));
        }
    }

    private void setBallInTheMiddle(int width, int height) {
        int middleOfWidth = (width) / 2;
        int middleOfHeight = (height) / 2;

        ballStatingCoordinate = new Coordinate(middleOfWidth, middleOfHeight);
        moveBallToStartPosition();
    }

    private void moveBallToStartPosition() {
        ballCoordinate = ballStatingCoordinate;
    }

    public String moveBall(int move) {
        int currentBallX = ballCoordinate.getX();
        int currentBallY = ballCoordinate.getY();

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
            Edge edge = new Edge(checkCoordinate, ballCoordinate);
            checkEdgeUsage(edge);

            setNewBallCoordinates(checkCoordinate);

            if(!isNoMoreMovementForBall()) {
                moveBallToStartPosition();

                if(!isNoMoreMovementForBall()) {
                    return "Draw";
                }
            }

            return "Game Underway";
        } else if(checkNode == Node.PLAYER_ONE_GOAL) {
            return "Player2 Wins";
        } else if(checkNode == Node.PLAYER_TWO_GOAL) {
            return "Player1 Wins";
        } else {
            throw new RuntimeException("Not allowed to move out of board");
        }
    }

    private void checkEdgeUsage(Edge edge) {
        if(usedEdges.contains(edge)) {
            throw new RuntimeException("Not allowed to move on the edge");
        } else {
            usedEdges.add(edge);
        }
    }

    private void setNewBallCoordinates(Coordinate coordinate) {
        this.ballCoordinate = coordinate;
    }

    private boolean isNoMoreMovementForBall() {
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                Coordinate neighbourCoordinate = new Coordinate(ballCoordinate.getX() + dx, ballCoordinate.getY() + dy);

                if(dx == 0 && dy == 0) {
                    continue;
                }

                if(boardNodes.containsKey(neighbourCoordinate) && !usedEdges.contains(new Edge(ballCoordinate, neighbourCoordinate))) {
                    return true;
                }
            }
        }

        return false;
    }

    public Coordinate getBallCoordinates() {
        return this.ballCoordinate;
    }
}
