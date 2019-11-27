package pl.csanecki.papersoccer.logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardTest {
    private int halfWidth = 4;
    private int halfHeight = 5;

    private Board board;

    @BeforeEach
    void initializeBoard() {
        board = new Board(halfWidth, halfHeight);
    }

    @Test
    void givenHalfWidthThenBoardWidthIsTwiceWiderPlusOne() {
        Assertions.assertEquals(board.getBoardWidth(), halfWidth * 2 + 1, "Board should be twice wider plus one that it was passed");
    }

    @Test
    void givenHalfHeightThenBoardHeightIsTwiceHigherPlusOne() {
        Assertions.assertEquals(board.getBoardHeight(), halfHeight * 2 + 1, "Board should be twice higher plus one that it was passed");
    }

    @Test
    void givenHalfWidthAndHalfHeightThenBallIsInTheMiddleOfBoard() {
        Assertions.assertEquals(board.getBallX(), halfWidth + 1, "Ball should be in middle of board's width");
        Assertions.assertEquals(board.getBallY(), halfHeight + 1, "Ball should be in middle of board's height");
    }

    @Test
    void givenMoveBallToSouthThenBallIsOneDownFromMiddleOfBoard() {
        board.moveBall(2);

        Assertions.assertEquals(board.getBallY(), halfHeight + 2, "Ball should be one down from middle of board");
    }

    @Test
    void givenMoveBallToEastThenBallIsOneRightFromMiddleOfBoard() {
        board.moveBall(6);

        Assertions.assertEquals(board.getBallX(), halfWidth + 2, "Ball should be one right from middle of board");
    }

    @Test
    void givenWrongDirectionOfBallThenRuntimeException() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            board.moveBall(5);
        });
    }

    @Test
    void givenMoveBallOutsideOfBoardThenRuntimeException() {
        board.moveBall(4);
        board.moveBall(4);
        board.moveBall(4);
        board.moveBall(4);

        Assertions.assertThrows(RuntimeException.class, () -> {
            board.moveBall(4);
        });
    }
}
