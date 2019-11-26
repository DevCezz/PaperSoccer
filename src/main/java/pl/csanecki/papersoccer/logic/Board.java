package pl.csanecki.papersoccer.logic;

public class Board {
    final int boardWidth;
    final int boardHeight;

    public Board(int halfWidth, int halfHeight) {
        this.boardWidth = halfWidth * 2 + 1;
        this.boardHeight = halfHeight * 2 + 1;
    }
}
