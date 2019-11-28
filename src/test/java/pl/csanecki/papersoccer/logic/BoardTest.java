package pl.csanecki.papersoccer.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BoardTest {
    private int halfWidth = 4;
    private int halfHeight = 5;

    private Board board;

    @BeforeEach
    void initializeBoard(TestInfo testInfo) {
        if(!testInfo.getTags().contains("SkipSetup")) {
            board = new Board(halfWidth, halfHeight);
        }
    }

    @Test
    @Tag("SkipSetup")
    void givenHalfWidthWhenBoardWidthTooSmallThenRuntimeException() {
        assertThrows(RuntimeException.class, () -> {
            board = new Board(1, halfHeight);
        });
    }

    @Test
    @Tag("SkipSetup")
    void givenHalfHeightWhenBoardHeightTooSmallThenRuntimeException() {
        assertThrows(RuntimeException.class, () -> {
            board = new Board(halfWidth, 1);
        });
    }

    @Test
    void givenHalfWidthThenBoardWidthIsTwiceWiderPlusOne() {
        assertEquals(halfWidth * 2 + 1, board.getBoardWidth(), "Board should be twice wider plus one that it was passed");
    }

    @Test
    void givenHalfHeightThenBoardHeightIsTwiceHigherPlusOne() {
        assertEquals(halfHeight * 2 + 1, board.getBoardHeight(), "Board should be twice higher plus one that it was passed");
    }

    @Test
    void givenHalfWidthAndHalfHeightThenBallIsInTheMiddleOfBoard() {
        assertEquals(halfWidth, board.getBallX(), "Ball should be in middle of board's width");
        assertEquals(halfHeight, board.getBallY(), "Ball should be in middle of board's height");
    }

    @Test
    void given8WhenMoveBallThenBallMovesToNorth() {
        board.moveBall(8);

        assertEquals(halfHeight - 1, board.getBallY(), "Ball should be one top from middle of board");
    }

    @Test
    void given2WhenMoveBallThenBallMovesToSouth() {
        board.moveBall(2);

        assertEquals(halfHeight + 1, board.getBallY(), "Ball should be one bottom from middle of board");
    }

    @Test
    void given4WhenMoveBallThenBallMovesToWest() {
        board.moveBall(4);

        assertEquals(halfWidth - 1, board.getBallX(), "Ball should be one left from middle of board");
    }

    @Test
    void given6WhenMoveBallThenBallMovesToEast() {
        board.moveBall(6);

        assertEquals(halfWidth + 1, board.getBallX(), "Ball should be one right from middle of board");
    }

    @Test
    void given7WhenMoveBallThenBallMovesToNorthEast() {
        board.moveBall(7);

        assertEquals(halfWidth - 1, board.getBallX(), "Ball should be one left from middle of board");
        assertEquals(halfHeight - 1, board.getBallY(), "Ball should be one top from middle of board");
    }

    @Test
    void given9WhenMoveBallThenBallMovesToNorthWest() {
        board.moveBall(9);

        assertEquals(halfWidth + 1, board.getBallX(), "Ball should be one right from middle of board");
        assertEquals(halfHeight - 1, board.getBallY(), "Ball should be one top from middle of board");
    }

    @Test
    void given3WhenMoveBallThenBallMovesToSouthWest() {
        board.moveBall(3);

        assertEquals(halfWidth + 1, board.getBallX(), "Ball should be one right from middle of board");
        assertEquals(halfHeight + 1, board.getBallY(), "Ball should be one bottom from middle of board");
    }

    @Test
    void given1WhenMoveBallThenBallMovesToSouthEast() {
        board.moveBall(1);

        assertEquals(halfWidth - 1, board.getBallX(), "Ball should be one left from middle of board");
        assertEquals(halfHeight + 1, board.getBallY(), "Ball should be one bottom from middle of board");
    }

    @Test
    void givenNotSupportNumberWhenMoveBallThenRuntimeException() {
        assertThrows(RuntimeException.class, () -> {
            board.moveBall(5);
        });
    }

    @Test
    void givenMovingEastWhenMoveOutOfBoardThenRuntimeException() {
        for (int i = 0; i < halfWidth; i++) {
            board.moveBall(6);
        }

        assertThrows(RuntimeException.class, () -> {
            board.moveBall(6);
        });
    }

    @Test
    void givenMovingWestWhenMoveOutOfBoardThenRuntimeException() {
        for (int i = 0; i < halfWidth; i++) {
            board.moveBall(4);
        }

        assertThrows(RuntimeException.class, () -> {
            board.moveBall(4);
        });
    }
}
