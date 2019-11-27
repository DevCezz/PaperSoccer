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

    public void moveBall(int direction) {
        switch (direction) {
            case 8:
                moveBallNorth();
                break;
            case 9:
                moveBallNorthEast();
                break;
            case 6:
                moveBallEast();
                break;
            case 3:
                moveBallSouthEast();
                break;
            case 2:
                moveBallSouth();
                break;
            case 1:
                moveBallSouthWest();
                break;
            case 4:
                moveBallWest();
                break;
            case 7:
                moveBallNorthWest();
                break;
            default:
                throw new RuntimeException("Niedozwolone przemieszczenie piłki");
        }
    }

    private void moveBallNorthEast() {
        if(ballY - 1 >= 1 || ballX + 1 <= boardWidth) {
            moveBallNorth();
            moveBallEast();
        } else {
            throw new RuntimeException("Nie można przesunąć piłkę poza planszę");
        }
    }

    private void moveBallNorthWest() {
        if(ballY - 1 >= 1 || ballX - 1 >= 1) {
            moveBallNorth();
            moveBallWest();
        } else {
            throw new RuntimeException("Nie można przesunąć piłkę poza planszę");
        }
    }

    private void moveBallSouthWest() {
        if(ballY + 1 <= boardHeight || ballX - 1 >= 1) {
            moveBallSouth();
            moveBallWest();
        } else {
            throw new RuntimeException("Nie można przesunąć piłkę poza planszę");
        }
    }

    private void moveBallSouthEast() {
        if(ballY + 1 <= boardHeight || ballX + 1 <= boardWidth) {
            moveBallSouth();
            moveBallEast();
        } else {
            throw new RuntimeException("Nie można przesunąć piłkę poza planszę");
        }
    }

    private void moveBallNorth() {
        if(ballY - 1 >= 1) {
            ballY--;
        } else {
            throw new RuntimeException("Nie można przesunąć piłkę poza planszę");
        }
    }

    private void moveBallEast() {
        if(ballX + 1 <= boardWidth) {
            ballX++;
        } else {
            throw new RuntimeException("Nie można przesunąć piłkę poza planszę");
        }
    }

    private void moveBallSouth() {
        if(ballY + 1 <= boardHeight) {
            ballY++;
        } else {
            throw new RuntimeException("Nie można przesunąć piłkę poza planszę");
        }
    }

    private void moveBallWest() {
        if(ballX - 1 >= 1) {
            ballX--;
        } else {
            throw new RuntimeException("Nie można przesunąć piłkę poza planszę");
        }
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
