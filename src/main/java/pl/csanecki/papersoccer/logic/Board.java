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
                ballY++;
                ballX--;
                break;
            case 2:
                ballY++;
                break;
            case 3:
                ballY++;
                ballX++;
                break;
            case 4:
                ballX--;
                break;
            case 6:
                ballX++;
                break;
            case 7:
                ballY--;
                ballX--;
                break;
            case 8:
                ballY--;
                break;
            case 9:
                ballY--;
                ballX++;
                break;
        }
    }
}
