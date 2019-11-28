package pl.csanecki.papersoccer.logic;

public class Board {
    private final int boardWidth;
    private final int boardHeight;

    private int ballX;
    private int ballY;

    public Board(int halfWidth, int halfHeight) {
        checkPassedDimension(halfWidth);
        checkPassedDimension(halfHeight);

        this.boardWidth = halfWidth * 2 + 1;
        this.boardHeight = halfHeight * 2 + 1;

        this.ballX = halfWidth;
        this.ballY = halfHeight;
    }

    private void checkPassedDimension(int dimension) {
        if(dimension < 2) {
            throw new RuntimeException("Passed dimension is too small");
        }
    }

    public void moveBall(int direction) {
        switch (direction) {
            case 8:
                moveNorth();
                break;
            case 2:
                moveSouth();
                break;
            case 4:
                moveWest();
                break;
            case 6:
                moveEast();
                break;
            case 7:
                moveNorth();
                moveWest();
                break;
            case 9:
                moveNorth();
                moveEast();
                break;
            case 3:
                moveSouth();
                moveEast();
                break;
            case 1:
                moveSouth();
                moveWest();
                break;
            default:
                throw new RuntimeException("Not supported operation on ball");
        }
    }

    private void moveNorth() {
        if(checkYAxisMovementValidation()) {
            ballY--;
        }
    }

    private void moveSouth() {
        if(checkYAxisMovementValidation()) {
            ballY++;
        }
    }

    private void moveWest() {
        if(checkXAxisMovementValidation()) {
            ballX--;
        }
    }

    private void moveEast() {
        if(checkXAxisMovementValidation()) {
            ballX++;
        }
    }

    private boolean checkYAxisMovementValidation() {
        if(ballY - 1 >= 0 && ballY + 1 < boardHeight) {
            return true;
        }

        throw new RuntimeException("Ball cannot be outside of board");
    }

    private boolean checkXAxisMovementValidation() {
        if(ballX - 1 >= 0 && ballX + 1 < boardWidth) {
            return true;
        }

        throw new RuntimeException("Ball cannot be outside of board");
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public int getBallX() {
        return ballX;
    }

    public int getBallY() {
        return ballY;
    }
}
