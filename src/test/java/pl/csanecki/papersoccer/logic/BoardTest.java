package pl.csanecki.papersoccer.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BoardTest {
    private int width = 8;
    private int height = 10;

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board(width, height);
    }

    @Test
    void givenWidthTooSmallThenRuntimeException() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            new Board(2, height);
        }, "The width is to small");

        assertEquals("Dimension is to small", exception.getMessage());
    }

    @Test
    void givenOddHeightThenRuntimeException() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            new Board(width, 7);
        }, "The height cannot be odd");

        assertEquals("Dimension cannot be odd", exception.getMessage());
    }

    @Test
    void givenProperWidthThenBoardWidthIsSet() {
        assertEquals(width, board.getWidth());
    }

    @Test
    void givenProperHeightThenBoardHeightIsSet() {
        assertEquals(height, board.getHeight());
    }

    @Test
    void givenWidthAndHeightWhenBoardIsInitiatedThenBallIsCenteredHorizontally() {
        assertEquals(width / 2, board.getBallX());
    }

    @Test
    void givenWidthAndHeightWhenBoardIsInitiatedThenBallIsCenteredVertically() {
        assertEquals(height / 2, board.getBallY());
    }

    @Test
    void given8ThenBallMoveNorth() {
        board.play(8);
        assertEquals((height / 2) - 1, board.getBallY());
    }

    @Test
    void given2ThenBallMoveSouth() {
        board.play(2);
        assertEquals((height / 2) + 1, board.getBallY());
    }

    @Test
    void given4ThenBallMoveWest() {
        board.play(4);
        assertEquals((width / 2) - 1, board.getBallX());
    }

    @Test
    void given6ThenBallMoveEast() {
        board.play(6);
        assertEquals((width / 2) + 1, board.getBallX());
    }

    @Test
    void given7ThenBallMoveNorthWest() {
        board.play(7);
        assertEquals((height / 2) - 1, board.getBallY());
        assertEquals((width / 2) - 1, board.getBallX());
    }

    @Test
    void given9ThenBallMoveNorthEast() {
        board.play(9);
        assertEquals((height / 2) - 1, board.getBallY());
        assertEquals((width / 2) + 1, board.getBallX());
    }

    @Test
    void given1ThenBallMoveSouthWest() {
        board.play(1);
        assertEquals((height / 2) + 1, board.getBallY());
        assertEquals((width / 2) - 1, board.getBallX());
    }

    @Test
    void given3ThenBallMoveSouthEast() {
        board.play(3);
        assertEquals((height / 2) + 1, board.getBallY());
        assertEquals((width / 2) + 1, board.getBallX());
    }

    @Test
    void givenBallOnLeftEdgeOfBoardWhenMoveWestThenRuntimeException() {
        for(int i = 0; i < width / 2; i++) {
            board.play(4);
        }

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            board.play(4);
        }, "The ball is not outside of board");

        assertEquals("Cannot move ball outside of board", exception.getMessage());
    }

    @Test
    void givenBallOnRightEdgeOfBoardWhenMoveEastThenRuntimeException() {
        for(int i = 0; i < width / 2; i++) {
            board.play(6);
        }

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            board.play(6);
        }, "The ball is not outside of board");

        assertEquals("Cannot move ball outside of board", exception.getMessage());
    }

    @Test
    void givenBallOnTopLeftCornerOfBoardWhenMoveNorthThenRuntimeException() {
        for(int i = 0; i < width / 2; i++) {
            board.play(4);
        }

        for(int i = 0; i < height / 2; i++) {
            board.play(8);
        }

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            board.play(8);
        }, "The ball is not outside of board");

        assertEquals("Cannot move ball outside of board", exception.getMessage());
    }

    @Test
    void givenBallOnBottomLeftCornerOfBoardWhenMoveSouthThenRuntimeException() {
        for(int i = 0; i < width / 2; i++) {
            board.play(4);
        }

        for(int i = 0; i < height / 2; i++) {
            board.play(2);
        }

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            board.play(2);
        }, "The ball is not outside of board");

        assertEquals("Cannot move ball outside of board", exception.getMessage());
    }
}
