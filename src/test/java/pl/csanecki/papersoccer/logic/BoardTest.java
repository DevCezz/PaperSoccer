package pl.csanecki.papersoccer.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    private int width = 8;
    private int height = 10;

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board(width, height);
    }

    @Test
    void givenBoardInitiatedWhenWidthAndHeightIsSetThenBallIsInTheMiddleOfBoard() {
        Coordinate expected = new Coordinate(width / 2, height / 2);

        assertEquals(expected, board.getBallCoordinates(), "Middle node does not contain ball");
    }

    @Test
    void whenMoveBallDownLeftThenNodeOnTheDownLeftFromMiddleHasBall() {
        board.moveBall(1);

        Coordinate expected = new Coordinate(width / 2 - 1, height / 2 + 1);

        assertEquals(expected, board.getBallCoordinates(), "Ball was not moved down and left");
    }

    @Test
    void whenMoveBallDownThenNodeOnTheDownFromMiddleHasBall() {
        board.moveBall(2);

        Coordinate expected = new Coordinate(width / 2, height / 2 + 1);

        assertEquals(expected, board.getBallCoordinates(), "Ball was not moved down");
    }

    @Test
    void whenMoveBallDownRightThenNodeOnTheDownRightFromMiddleHasBall() {
        board.moveBall(3);

        Coordinate expected = new Coordinate(width / 2 + 1, height / 2 + 1);

        assertEquals(expected, board.getBallCoordinates(), "Ball was not moved down and right");
    }

    @Test
    void whenMoveBallLeftThenNodeOnTheLeftFromMiddleHasBall() {
        board.moveBall(4);

        Coordinate expected = new Coordinate(width / 2 - 1, height / 2);

        assertEquals(expected, board.getBallCoordinates(), "Ball was not moved left");
    }

    @Test
    void whenMoveBallRightThenNodeOnTheRightFromMiddleHasBall() {
        board.moveBall(6);

        Coordinate expected = new Coordinate(width / 2 + 1, height / 2);

        assertEquals(expected, board.getBallCoordinates(), "Ball was not moved right");
    }

    @Test
    void whenMoveBallTopLeftThenNodeOnTheTopLeftFromMiddleHasBall() {
        board.moveBall(7);

        Coordinate expected = new Coordinate(width / 2 - 1, height / 2 - 1);

        assertEquals(expected, board.getBallCoordinates(), "Ball was not moved up and left");
    }

    @Test
    void whenMoveBallTopThenNodeOnTheTopFromMiddleHasBall() {
        board.moveBall(8);

        Coordinate expected = new Coordinate(width / 2, height / 2 - 1);

        assertEquals(expected, board.getBallCoordinates(), "Ball was not moved up");
    }

    @Test
    void whenMoveBallTopRightThenNodeOnTheTopRightFromMiddleHasBall() {
        board.moveBall(9);

        Coordinate expected = new Coordinate(width / 2 + 1, height / 2 - 1);

        assertEquals(expected, board.getBallCoordinates(), "Ball was not moved up and right");
    }

    @Test
    void whenNotAllowedMovementThenRuntimeException() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            board.moveBall(5);
        }, "The movement is incorrectly allowed");

        assertEquals("Not allowed movement of ball", exception.getMessage());
    }

    @Test
    void givenMoveBallLeftToBorderWhenMoveBallLeftThenRuntimeException() {
        for (int i = 0; i < width / 2; i++) {
            board.moveBall(4);
        }

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            board.moveBall(4);
        }, "The movement is incorrectly allowed");

        assertEquals("Not allowed to move out of board", exception.getMessage());
    }

    @Test
    void givenMoveBallRightToBorderWhenMoveBallRightThenRuntimeException() {
        for (int i = 0; i < width / 2; i++) {
            board.moveBall(6);
        }

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            board.moveBall(6);
        }, "The movement is incorrectly allowed");

        assertEquals("Not allowed to move out of board", exception.getMessage());
    }

    @Test
    void givenMoveBallTopToBorderEdgeWhenMoveBallTopThenWinner() {
        for (int i = 0; i < height / 2; i++) {
            board.moveBall(8);
        }

        assertEquals("Player2 Wins", board.moveBall(8));
    }

    @Test
    void givenMoveBallTopToBorderAndOneLeftWhenMoveBallTopRightThenWinner() {
        for (int i = 0; i < height / 2; i++) {
            board.moveBall(8);
        }

        board.moveBall(4);

        assertEquals("Player2 Wins", board.moveBall(9));
    }

    @Test
    void givenMoveBallTopToBorderAndOneRightWhenMoveBallTopLeftThenWinner() {
        for (int i = 0; i < height / 2; i++) {
            board.moveBall(8);
        }

        board.moveBall(6);

        assertEquals("Player2 Wins", board.moveBall(7));
    }

    @Test
    void givenMoveBallDownToBorderEdgeWhenMoveBallDownThenWinner() {
        for (int i = 0; i < height / 2; i++) {
            board.moveBall(2);
        }

        assertEquals("Player1 Wins", board.moveBall(2));
    }

    @Test
    void givenMoveBallDownToBorderAndOneLeftWhenMoveBallDowRightThenWinner() {
        for (int i = 0; i < height / 2; i++) {
            board.moveBall(2);
        }

        board.moveBall(4);

        assertEquals("Player1 Wins", board.moveBall(3));
    }

    @Test
    void givenMoveBallDownToBorderAndOneRightWhenMoveBallDownLeftThenWinner() {
        for (int i = 0; i < height / 2; i++) {
            board.moveBall(2);
        }

        board.moveBall(6);

        assertEquals("Player1 Wins", board.moveBall(1));
    }

    @Test
    void whenMoveBallAndNoWinnerThenGameUnderway() {
        assertEquals("Game Underway", board.moveBall(4));
    }

    @Test
    void givenMoveBallLeftWhenMoveBallRightThenRuntimeException() {
        board.moveBall(4);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            board.moveBall(6);
        }, "The edge is not set as used");

        assertEquals("Not allowed to move on the edge", exception.getMessage());
    }

    @Test
    void givenMoveBallLeftToBorderWhenMoveBallTopThenRuntimeException() {
        for (int i = 0; i < width / 2; i++) {
            board.moveBall(4);
        }

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            board.moveBall(8);
        }, "The edge of border is not set");

        assertEquals("Not allowed to move on the edge", exception.getMessage());
    }

    @Test
    void givenMoveBallRightToBorderWhenMoveBallTopThenRuntimeException() {
        for (int i = 0; i < width / 2; i++) {
            board.moveBall(6);
        }

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            board.moveBall(8);
        }, "The edge of border is not set");

        assertEquals("Not allowed to move on the edge", exception.getMessage());
    }



    @Test
    void givenMoveBallLeftCornerOfPlayer2GoalWhenMoveBallLeftThenRuntimeException() {
        for (int i = 0; i < height / 2; i++) {
            board.moveBall(8);
        }

        board.moveBall(4);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            board.moveBall(4);
        }, "The edge of border is not set");

        assertEquals("Not allowed to move on the edge", exception.getMessage());
    }

    @Test
    void givenMoveBallLeftCornerOfPlayer1GoalWhenMoveBallLeftThenRuntimeException() {
        for (int i = 0; i < height / 2; i++) {
            board.moveBall(2);
        }

        board.moveBall(4);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            board.moveBall(4);
        }, "The edge of border is not set");

        assertEquals("Not allowed to move on the edge", exception.getMessage());
    }

    @Test
    void givenNoMoreAvailableMovementThenMoveBallToStartingPosition() {
        for (int i = 0; i < height / 2 - 1; i++) {
            board.moveBall(2);
        }
        for (int i = 0; i < width / 2 - 1; i++) {
            board.moveBall(6);
        }
        board.moveBall(3);

        Coordinate expected = new Coordinate(width / 2, height / 2);

        assertEquals(expected, board.getBallCoordinates(), "Ball was not restarted");
    }

    @Test
    void givenBallAtStartAndNoMoreMovesMovementThenDraw() {
        board.moveBall(7);
        board.moveBall(2);
        board.moveBall(6);
        board.moveBall(1);
        board.moveBall(6);
        board.moveBall(8);
        board.moveBall(3);
        board.moveBall(8);
        board.moveBall(4);
        board.moveBall(9);
        board.moveBall(4);

        assertEquals("Draw", board.moveBall(2), "There is no expected draw");
    }
}
