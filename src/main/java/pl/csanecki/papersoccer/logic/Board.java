package pl.csanecki.papersoccer.logic;

public class Board {
    private final int boardWidth;
    private final int boardHeight;

    private int ballX;
    private int ballY;

    public Board(int halfWidth, int halfHeight) {
        this.boardWidth = halfWidth * 2 + 1;
        this.boardHeight = halfHeight * 2 + 1;

        this.ballX = halfWidth + 1;
        this.ballY = halfHeight + 1;
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

    public void moveBall(int direction) {
        switch (direction) {
            case 8:
                ballY++;
                break;
            case 9:
                ballY++;
                ballX++;
                break;
            case 6:
                ballX++;
                break;
            case 3:
                ballY--;
                ballX++;
                break;
            case 2:
                ballY--;
                break;
            case 1:
                ballY--;
                ballX--;
                break;
            case 4:
                ballX--;
                break;
            case 7:
                ballY++;
                ballX--;
                break;
            default:
                throw new RuntimeException("Niedozwolone przemieszczenie piłki");
        }
    }
}
