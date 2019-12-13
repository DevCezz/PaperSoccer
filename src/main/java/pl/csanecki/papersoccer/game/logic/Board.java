package pl.csanecki.papersoccer.game.logic;

import pl.csanecki.papersoccer.game.model.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static pl.csanecki.papersoccer.game.model.GameStatus.*;
import static pl.csanecki.papersoccer.game.model.Player.*;

public class Board {
    private final int boardWidth;
    private final int boardHeight;

    private Player currentPlayer = PLAYER_ONE;

    private Map<Coordinate, Node> boardNodes;
    private Set<Edge> usedEdges;

    private Coordinate ballStatingCoordinate;
    private Coordinate ballCoordinate;

    public Board(int width, int height) {
        this.boardWidth = width;
        this.boardHeight = height;

        this.boardNodes = new HashMap<>();
        this.usedEdges = new HashSet<>();

        initializeBoardSetUp();

    }

    private void initializeBoardSetUp() {
        setUpNodesNetwork();
        setUpBordersOfBoard();
        setUpBallInTheMiddle();
    }

    private void setUpNodesNetwork() {
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
            usedEdges.add(new Edge(firstCoordinateLeft, secondCoordinateLeft));

            Coordinate firstCoordinateRight = new Coordinate(boardWidth, i);
            Coordinate secondCoordinateRight = new Coordinate(boardWidth, i + 1);
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

    private void setUpBallInTheMiddle() {
        int middleOfWidth = (boardWidth) / 2;
        int middleOfHeight = (boardHeight) / 2;

        ballStatingCoordinate = new Coordinate(middleOfWidth, middleOfHeight);
        moveBallToStartPosition();
    }

    private void moveBallToStartPosition() {
        ballCoordinate = ballStatingCoordinate;
    }

    public GameStatus play(int move) {
        Coordinate destinationCoordinateOfBall = getDestinationCoordinateOfBall(move);

        return returnResultOfMovement(destinationCoordinateOfBall);
    }

    private Coordinate getDestinationCoordinateOfBall(int move) {
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

    private GameStatus returnResultOfMovement(Coordinate checkCoordinate) {
        Node checkNode = boardNodes.get(checkCoordinate);

        if(checkNode == Node.PLAIN_FIELD) {
            decideIfChangePlayerBy(checkCoordinate);

            Edge edgeSetByBall = new Edge(checkCoordinate, ballCoordinate);

            checkEdgeUsage(edgeSetByBall);

            setNewBallCoordinates(checkCoordinate);
            addToEdgesNew(edgeSetByBall);

            if(isNoMoreMovementForBall()) {
                moveBallToStartPosition();

                if(isNoMoreMovementForBall()) {
                    return DRAW;
                }
            }

            return UNDERWAY;
        } else if(checkNode == Node.PLAYER_ONE_GOAL) {
            return PLAYER_TWO_WINS;
        } else if(checkNode == Node.PLAYER_TWO_GOAL) {
            return PLAYER_ONE_WINS;
        } else {
            throw new RuntimeException("Not allowed to move out of board");
        }
    }

    private void decideIfChangePlayerBy(Coordinate newCoordinate) {
        if(!isNeighborEdges(newCoordinate)) {
            changeCurrentPlayer();
        }
    }

    private boolean isNeighborEdges(Coordinate coordinate) {
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                Coordinate neighbourCoordinate = new Coordinate(coordinate.getX() + dx, coordinate.getY() + dy);

                if(usedEdges.contains(new Edge(coordinate, neighbourCoordinate))) {
                    return true;
                }
            }
        }

        return false;
    }

    private void changeCurrentPlayer() {
        this.currentPlayer = this.currentPlayer == PLAYER_ONE ? PLAYER_TWO : PLAYER_ONE;
    }

    private void checkEdgeUsage(Edge edge) {
        if(usedEdges.contains(edge)) {
            throw new RuntimeException("Not allowed to move on the edge");
        }
    }

    private void setNewBallCoordinates(Coordinate coordinate) {
        this.ballCoordinate = coordinate;
    }

    private void addToEdgesNew(Edge edge) {
        usedEdges.add(edge);
    }

    private boolean isNoMoreMovementForBall() {
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                Coordinate neighbourCoordinate = new Coordinate(ballCoordinate.getX() + dx, ballCoordinate.getY() + dy);

                if(neighbourCoordinate.equals(ballCoordinate)) {
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

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }
}
