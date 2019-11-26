package pl.csanecki.papersoccer.logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BoardTest {
    @Test
    void createBoardWhichWidthAndHeightIsTwiceBigger() {
        int halfWidth = 5;
        int halfHeight = 10;

        Board board = new Board(halfWidth, halfHeight);

        Assertions.assertTrue(board.boardWidth == halfWidth * 2, "Plansza nie jest dwa razy szersza niż podano");
        Assertions.assertTrue(board.boardHeight == halfHeight * 2, "Plansza nie jest dwa razy wyższa niż podano");
    }
}
