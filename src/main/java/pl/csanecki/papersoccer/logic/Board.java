package pl.csanecki.papersoccer.logic;

public class Board {
    private final static int MIN_DIMENSION = 4;

    public Board(int width, int height) {
        checkDimension(width);
        checkDimension(height);
    }

    private void checkDimension(int dimension) {
        if(dimension < MIN_DIMENSION) {
            throw new RuntimeException("Dimension is to small");
        } else if(dimension % 2 == 1) {
            throw new RuntimeException("Dimension cannot be odd");
        }
    }
}
