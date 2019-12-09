package pl.csanecki.papersoccer.logic;

public class Board {
    private final static int MIN_DIMENSION = 4;

    private final int width;
    private final int height;

    private int ballX;
    private int ballY;

    public Board(int width, int height) {
        checkDimension(width);
        checkDimension(height);

        this.width = width;
        this.height = height;

        this.ballX = width / 2;
        this.ballY = height / 2;
    }

    private void checkDimension(int dimension) {
        if(dimension < MIN_DIMENSION) {
            throw new RuntimeException("Dimension is to small");
        } else if(dimension % 2 == 1) {
            throw new RuntimeException("Dimension cannot be odd");
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getBallX() {
        return ballX;
    }

    public int getBallY() {
        return ballY;
    }

    public void play(int move) {
        switch (move) {
            case 1:
                moveSouthWest();
                break;
            case 2:
                if(ballY + 1 > height) {
                    throw new RuntimeException("Cannot move ball outside of board");
                }

                moveSouth();
                break;
            case 3:
                moveSouthEast();
                break;
            case 4:
                if(ballX - 1 < 0) {
                    throw new RuntimeException("Cannot move ball outside of board");
                }

                moveWest();
                break;
            case 6:
                if(ballX + 1 > width) {
                    throw new RuntimeException("Cannot move ball outside of board");
                }

                moveEast();
                break;
            case 7:
                moveNorthWest();
                break;
            case 8:
                if(ballY - 1 < 0) {
                    throw new RuntimeException("Cannot move ball outside of board");
                }

                moveNorth();
                break;
            case 9:
                moveNorthEast();
                break;
        }
    }

    private void moveNorthWest() {
        moveNorth();
        moveWest();
    }

    private void moveNorthEast() {
        moveNorth();
        moveEast();
    }

    private void moveSouthWest() {
        moveSouth();
        moveWest();
    }

    private void moveSouthEast() {
        moveSouth();
        moveEast();
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
}
