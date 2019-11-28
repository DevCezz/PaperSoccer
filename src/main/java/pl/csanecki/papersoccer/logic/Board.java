package pl.csanecki.papersoccer.logic;

public class Board {
    private final int boardWidth;
    private final int boardHeight;

    private int ballX;
    private int ballY;

    public Board(int halfWidth, int halfHeight) {
        this.boardWidth = halfWidth * 2 + 1;
        this.boardHeight = halfHeight * 2 + 1;

        this.ballX = halfWidth;
        this.ballY = halfHeight;
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
        ballY--;
    }

    private void moveSouth() {
        ballY++;
    }

    private void moveWest() {
        ballX--;
    }

    private void moveEast() {
        ballX++;
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
