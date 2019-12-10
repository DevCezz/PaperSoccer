package pl.csanecki.papersoccer.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GameEngineTest {
    private int width = 8;
    private int height = 10;

    private GameEngine gameEngine;

    @BeforeEach
    void setUp() {
        gameEngine = new GameEngine(width, height);
    }

    @Test
    void givenWidthTooSmallThenRuntimeException() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            new GameEngine(2, height);
        }, "The width is to small");

        assertEquals("Dimension is to small", exception.getMessage());
    }

    @Test
    void givenOddHeightThenRuntimeException() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            new GameEngine(width, 7);
        }, "The height cannot be odd");

        assertEquals("Dimension cannot be odd", exception.getMessage());
    }

    @Test
    void givenProperWidthThenBoardWidthIsSet() {
        assertEquals(width, gameEngine.getWidth());
    }

    @Test
    void givenProperHeightThenBoardHeightIsSet() {
        assertEquals(height, gameEngine.getHeight());
    }

    @Test
    void givenWidthAndHeightWhenBoardIsInitiatedThenBallIsCenteredHorizontally() {
        assertEquals(width / 2, gameEngine.getBallX());
    }

    @Test
    void givenWidthAndHeightWhenBoardIsInitiatedThenBallIsCenteredVertically() {
        assertEquals(height / 2, gameEngine.getBallY());
    }

    @Test
    void given8ThenBallMoveNorth() {
        gameEngine.play(8);
        assertEquals((height / 2) - 1, gameEngine.getBallY());
    }

    @Test
    void given2ThenBallMoveSouth() {
        gameEngine.play(2);
        assertEquals((height / 2) + 1, gameEngine.getBallY());
    }

    @Test
    void given4ThenBallMoveWest() {
        gameEngine.play(4);
        assertEquals((width / 2) - 1, gameEngine.getBallX());
    }

    @Test
    void given6ThenBallMoveEast() {
        gameEngine.play(6);
        assertEquals((width / 2) + 1, gameEngine.getBallX());
    }

    @Test
    void given7ThenBallMoveNorthWest() {
        gameEngine.play(7);
        assertEquals((height / 2) - 1, gameEngine.getBallY());
        assertEquals((width / 2) - 1, gameEngine.getBallX());
    }

    @Test
    void given9ThenBallMoveNorthEast() {
        gameEngine.play(9);
        assertEquals((height / 2) - 1, gameEngine.getBallY());
        assertEquals((width / 2) + 1, gameEngine.getBallX());
    }

    @Test
    void given1ThenBallMoveSouthWest() {
        gameEngine.play(1);
        assertEquals((height / 2) + 1, gameEngine.getBallY());
        assertEquals((width / 2) - 1, gameEngine.getBallX());
    }

    @Test
    void given3ThenBallMoveSouthEast() {
        gameEngine.play(3);
        assertEquals((height / 2) + 1, gameEngine.getBallY());
        assertEquals((width / 2) + 1, gameEngine.getBallX());
    }

    @Test
    void givenBallOnLeftEdgeOfBoardWhenMoveWestThenRuntimeException() {
        for(int i = 0; i < width / 2; i++) {
            gameEngine.play(4);
        }

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            gameEngine.play(4);
        }, "The ball is not outside of board");

        assertEquals("Cannot move ball outside of board", exception.getMessage());
    }

    @Test
    void givenBallOnRightEdgeOfBoardWhenMoveEastThenRuntimeException() {
        for(int i = 0; i < width / 2; i++) {
            gameEngine.play(6);
        }

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            gameEngine.play(6);
        }, "The ball is not outside of board");

        assertEquals("Cannot move ball outside of board", exception.getMessage());
    }

    @Test
    void givenBallOnTopLeftCornerOfBoardWhenMoveNorthThenRuntimeException() {
        for(int i = 0; i < width / 2; i++) {
            gameEngine.play(4);
        }

        for(int i = 0; i < height / 2; i++) {
            gameEngine.play(8);
        }

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            gameEngine.play(8);
        }, "The ball is not outside of board");

        assertEquals("Cannot move ball outside of board", exception.getMessage());
    }

    @Test
    void givenBallOnBottomLeftCornerOfBoardWhenMoveSouthThenRuntimeException() {
        for(int i = 0; i < width / 2; i++) {
            gameEngine.play(4);
        }

        for(int i = 0; i < height / 2; i++) {
            gameEngine.play(2);
        }

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            gameEngine.play(2);
        }, "The ball is not outside of board");

        assertEquals("Cannot move ball outside of board", exception.getMessage());
    }
}
