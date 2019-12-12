package pl.csanecki.papersoccer.logic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Board {
    private final int boardWidth;
    private final int boardHeight;

    private Map<Coordinate, Node> boardNodes;
    private Set<Edge> usedEdges;

    private Coordinate ballStatingCoordinate;
    private Coordinate ballCoordinate;

    public Board(int width, int height) {
        this.boardWidth = width;
        this.boardHeight = height;

        this.boardNodes = new HashMap<>();
        this.usedEdges = new HashSet<>();

        initializeNodesNetwork();

        setUpBordersOfBoard();
        setBallInTheMiddle();
    }

    private void initializeNodesNetwork() {
        for (int x = 0; x < boardWidth + 1; x++) {
            for (int y = 0; y < boardHeight + 1; y++) {
                boardNodes.put(new Coordinate(x, y), Node.PLAIN_FIELD);
            }
        }

        setUpPlayersGoalsNodes();
    }

    private void setUpPlayersGoalsNodes() {
        boardNodes.put(new Coordinate(boardWidth / 2, -1), Node.PLAYER_ONE_GOAL);
        boardNodes.put(new Coordinate(boardWidth / 2, boardHeight + 1), Node.PLAYER_TWO_GOAL);
    }

    private void setUpBordersOfBoard() {
        setUpSideBordersOfBoard();
        setUpTopAndBottomBordersOfBoard();
    }

    private void setUpSideBordersOfBoard() {
        for (int i = 0; i < boardHeight; i++) {
            Coordinate firstCoordinateLeft = new Coordinate(0, i);
            Coordinate secondCoordinateLeft = new Coordinate(0, i + 1);

            Coordinate firstCoordinateRight = new Coordinate(boardWidth, i);
            Coordinate secondCoordinateRight = new Coordinate(boardWidth, i + 1);

            usedEdges.add(new Edge(firstCoordinateLeft, secondCoordinateLeft));
            usedEdges.add(new Edge(firstCoordinateRight, secondCoordinateRight));
        }
    }

    private void setUpTopAndBottomBordersOfBoard() {
        for (int i = 0; i < boardWidth; i++) {
            Coordinate firstCoordinateUp = new Coordinate(i, 0);
            Coordinate secondCoordinateUp = new Coordinate(i + 1, 0);

            Coordinate firstCoordinateDown = new Coordinate(i, boardHeight);
            Coordinate secondCoordinateDown = new Coordinate(i + 1, boardHeight);

            if(isCoveringGoal(firstCoordinateUp, secondCoordinateUp)) {
                continue;
            }

            usedEdges.add(new Edge(firstCoordinateUp, secondCoordinateUp));
            usedEdges.add(new Edge(firstCoordinateDown, secondCoordinateDown));
        }
    }

    private boolean isCoveringGoal(Coordinate firstCoordinate, Coordinate secondCoordinate) {
        return firstCoordinate.getX() == boardWidth / 2 || secondCoordinate.getX() == boardWidth / 2;
    }

    private void setBallInTheMiddle() {
        int middleOfWidth = (boardWidth) / 2;
        int middleOfHeight = (boardHeight) / 2;

        ballStatingCoordinate = new Coordinate(middleOfWidth, middleOfHeight);
        moveBallToStartPosition();
    }

    private void moveBallToStartPosition() {
        ballCoordinate = ballStatingCoordinate;
    }

    public String moveBall(int move) {
        Coordinate checkCoordinate = getFutureMovementCoordinateByMove(move);

        return checkResultOfMovement(checkCoordinate);
    }

    private Coordinate getFutureMovementCoordinateByMove(int move) {
        int currentBallX = ballCoordinate.getX();
        int currentBallY = ballCoordinate.getY();

        switch (move) {
            case 1:
                return new Coordinate(currentBallX - 1, currentBallY + 1);
            case 2:
                return new Coordinate(currentBallX, currentBallY + 1);
            case 3:
                return new Coordinate(currentBallX + 1, currentBallY + 1);
            case 4:
                return new Coordinate(currentBallX - 1, currentBallY);
            case 6:
                return new Coordinate(currentBallX + 1, currentBallY);
            case 7:
                return new Coordinate(currentBallX - 1, currentBallY - 1);
            case 8:
                return new Coordinate(currentBallX, currentBallY - 1);
            case 9:
                return new Coordinate(currentBallX + 1, currentBallY - 1);
            default:
                throw new RuntimeException("Not allowed movement of ball");
        }
    }

    private String checkResultOfMovement(Coordinate checkCoordinate) {
        Node checkNode = boardNodes.get(checkCoordinate);

        if(checkNode == Node.PLAIN_FIELD) {
            Edge edge = new Edge(checkCoordinate, ballCoordinate);
            checkEdgeUsage(edge);

            setNewBallCoordinates(checkCoordinate);

            if(isNoMoreMovementForBall()) {
                moveBallToStartPosition();

                if(isNoMoreMovementForBall()) {
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
                    return false;
                }
            }
        }

        return true;
    }

    public Coordinate getBallCoordinates() {
        return this.ballCoordinate;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public int getBoardHeight() {
        return boardHeight;
    }
}
