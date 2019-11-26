package pl.csanecki.papersoccer.logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BoardTest {
    @Test
    void createBoardWhichWidthAndHeightIsTwiceBiggerPlusOneAsPassed() {
        int halfWidth = 5;
        int halfHeight = 10;

        Board board = new Board(halfWidth, halfHeight);

        Assertions.assertEquals(board.boardWidth, halfWidth * 2 + 1, "Board should be twice wider plus one that it was passed");
        Assertions.assertEquals(board.boardHeight, halfHeight * 2 + 1, "Board should be twice higher plus one that it was passed");
    }
}
