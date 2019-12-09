package pl.csanecki.papersoccer.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BoardTest {
    private int width = 8;
    private int height = 10;

    @Test
    void givenWidthTooSmallThenRuntimeException() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            Board board = new Board(2, height);
        }, "The width is to small");

        assertEquals("Dimension is to small", exception.getMessage());
    }

    @Test
    void givenOddHeightThenRuntimeException() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            Board board = new Board(width, 7);
        }, "The height cannot be odd");

        assertEquals("Dimension cannot be odd", exception.getMessage());
    }
}
