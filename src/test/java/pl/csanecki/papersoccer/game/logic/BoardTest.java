package pl.csanecki.papersoccer.game.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.csanecki.papersoccer.game.model.Coordinate;

import static org.junit.jupiter.api.Assertions.*;
import static pl.csanecki.papersoccer.game.model.GameStatus.*;
import static pl.csanecki.papersoccer.game.model.Player.*;

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
        board.play(1);

        Coordinate expected = new Coordinate(width / 2 - 1, height / 2 + 1);

        assertEquals(expected, board.getBallCoordinates(), "Ball was not moved down and left");
    }

    @Test
    void whenMoveBallDownThenNodeOnTheDownFromMiddleHasBall() {
        board.play(2);

        Coordinate expected = new Coordinate(width / 2, height / 2 + 1);

        assertEquals(expected, board.getBallCoordinates(), "Ball was not moved down");
    }

    @Test
    void whenMoveBallDownRightThenNodeOnTheDownRightFromMiddleHasBall() {
        board.play(3);

        Coordinate expected = new Coordinate(width / 2 + 1, height / 2 + 1);

        assertEquals(expected, board.getBallCoordinates(), "Ball was not moved down and right");
    }

    @Test
    void whenMoveBallLeftThenNodeOnTheLeftFromMiddleHasBall() {
        board.play(4);

        Coordinate expected = new Coordinate(width / 2 - 1, height / 2);

        assertEquals(expected, board.getBallCoordinates(), "Ball was not moved left");
    }

    @Test
    void whenMoveBallRightThenNodeOnTheRightFromMiddleHasBall() {
        board.play(6);

        Coordinate expected = new Coordinate(width / 2 + 1, height / 2);

        assertEquals(expected, board.getBallCoordinates(), "Ball was not moved right");
    }

    @Test
    void whenMoveBallTopLeftThenNodeOnTheTopLeftFromMiddleHasBall() {
        board.play(7);

        Coordinate expected = new Coordinate(width / 2 - 1, height / 2 - 1);

        assertEquals(expected, board.getBallCoordinates(), "Ball was not moved up and left");
    }

    @Test
    void whenMoveBallTopThenNodeOnTheTopFromMiddleHasBall() {
        board.play(8);

        Coordinate expected = new Coordinate(width / 2, height / 2 - 1);

        assertEquals(expected, board.getBallCoordinates(), "Ball was not moved up");
    }

    @Test
    void whenMoveBallTopRightThenNodeOnTheTopRightFromMiddleHasBall() {
        board.play(9);

        Coordinate expected = new Coordinate(width / 2 + 1, height / 2 - 1);

        assertEquals(expected, board.getBallCoordinates(), "Ball was not moved up and right");
    }

    @Test
    void whenNotAllowedMovementThenRuntimeException() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            board.play(5);
        }, "The movement is incorrectly allowed");

        assertEquals("Not allowed movement of ball", exception.getMessage());
    }

    @Test
    void givenMoveBallLeftToBorderWhenMoveBallLeftThenRuntimeException() {
        for (int i = 0; i < width / 2; i++) {
            board.play(4);
        }

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            board.play(4);
        }, "The movement is incorrectly allowed");

        assertEquals("Not allowed to move out of board", exception.getMessage());
    }

    @Test
    void givenMoveBallRightToBorderWhenMoveBallRightThenRuntimeException() {
        for (int i = 0; i < width / 2; i++) {
            board.play(6);
        }

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            board.play(6);
        }, "The movement is incorrectly allowed");

        assertEquals("Not allowed to move out of board", exception.getMessage());
    }

    @Test
    void givenMoveBallTopToBorderEdgeWhenMoveBallTopThenWinner() {
        for (int i = 0; i < height / 2; i++) {
            board.play(8);
        }

        assertEquals(PLAYER_TWO_WINS, board.play(8));
    }

    @Test
    void givenMoveBallTopToBorderAndOneLeftWhenMoveBallTopRightThenWinner() {
        for (int i = 0; i < height / 2; i++) {
            board.play(8);
        }

        board.play(4);

        assertEquals(PLAYER_TWO_WINS, board.play(9));
    }

    @Test
    void givenMoveBallTopToBorderAndOneRightWhenMoveBallTopLeftThenWinner() {
        for (int i = 0; i < height / 2; i++) {
            board.play(8);
        }

        board.play(6);

        assertEquals(PLAYER_TWO_WINS, board.play(7));
    }

    @Test
    void givenMoveBallDownToBorderEdgeWhenMoveBallDownThenWinner() {
        for (int i = 0; i < height / 2; i++) {
            board.play(2);
        }

        assertEquals(PLAYER_ONE_WINS, board.play(2));
    }

    @Test
    void givenMoveBallDownToBorderAndOneLeftWhenMoveBallDowRightThenWinner() {
        for (int i = 0; i < height / 2; i++) {
            board.play(2);
        }

        board.play(4);

        assertEquals(PLAYER_ONE_WINS, board.play(3));
    }

    @Test
    void givenMoveBallDownToBorderAndOneRightWhenMoveBallDownLeftThenWinner() {
        for (int i = 0; i < height / 2; i++) {
            board.play(2);
        }

        board.play(6);

        assertEquals(PLAYER_ONE_WINS, board.play(1));
    }

    @Test
    void whenMoveBallAndNoWinnerThenGameUnderway() {
        assertEquals(UNDERWAY, board.play(4));
    }

    @Test
    void givenMoveBallLeftWhenMoveBallRightThenRuntimeException() {
        board.play(4);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            board.play(6);
        }, "The edge is not set as used");

        assertEquals("Not allowed to move on the edge", exception.getMessage());
    }

    @Test
    void givenMoveBallLeftToBorderWhenMoveBallTopThenRuntimeException() {
        for (int i = 0; i < width / 2; i++) {
            board.play(4);
        }

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            board.play(8);
        }, "The edge of border is not set");

        assertEquals("Not allowed to move on the edge", exception.getMessage());
    }

    @Test
    void givenMoveBallRightToBorderWhenMoveBallTopThenRuntimeException() {
        for (int i = 0; i < width / 2; i++) {
            board.play(6);
        }

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            board.play(8);
        }, "The edge of border is not set");

        assertEquals("Not allowed to move on the edge", exception.getMessage());
    }



    @Test
    void givenMoveBallLeftCornerOfPlayer2GoalWhenMoveBallLeftThenRuntimeException() {
        for (int i = 0; i < height / 2; i++) {
            board.play(8);
        }

        board.play(4);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            board.play(4);
        }, "The edge of border is not set");

        assertEquals("Not allowed to move on the edge", exception.getMessage());
    }

    @Test
    void givenMoveBallLeftCornerOfPlayer1GoalWhenMoveBallLeftThenRuntimeException() {
        for (int i = 0; i < height / 2; i++) {
            board.play(2);
        }

        board.play(4);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            board.play(4);
        }, "The edge of border is not set");

        assertEquals("Not allowed to move on the edge", exception.getMessage());
    }

    @Test
    void givenNoMoreAvailableMovementThenMoveBallToStartingPosition() {
        for (int i = 0; i < height / 2 - 1; i++) {
            board.play(2);
        }
        for (int i = 0; i < width / 2 - 1; i++) {
            board.play(6);
        }
        board.play(3);

        Coordinate expected = new Coordinate(width / 2, height / 2);

        assertEquals(expected, board.getBallCoordinates(), "Ball was not restarted");
    }

    @Test
    void givenBallAtStartAndNoMoreMovesMovementThenDraw() {
        board.play(7);
        board.play(2);
        board.play(6);
        board.play(1);
        board.play(6);
        board.play(8);
        board.play(3);
        board.play(8);
        board.play(4);
        board.play(9);
        board.play(4);

        assertEquals(DRAW, board.play(2), "There is no expected draw");
    }

    @Test
    void givenBoardInitializedThenBoardWidthIsSet() {
        assertEquals(width, board.getBoardWidth(), "There is different width of board then expected");
    }

    @Test
    void givenBoardInitializedThenBoardHeightIsSet() {
        assertEquals(height, board.getBoardHeight(), "There is different height of board then expected");
    }

    @Test
    void givenBoardInitializedThenCurrentPlayerIsPlayer1() {
        assertEquals(PLAYER_ONE, board.getCurrentPlayer(), "The current player is not Player1");
    }

    @Test
    void whenFirstMoveThenPlayerChangedToPlayer2() {
        board.play(2);
        assertEquals(PLAYER_TWO, board.getCurrentPlayer(), "The current player is not Player2");
    }

    @Test
    void whenMoveBallOnCoordinateWhichWasUsedThenTheSamePlayer() {
        board.play(2); //Player1
        board.play(9); //Player2
        board.play(4); //Player1
        assertEquals(PLAYER_ONE, board.getCurrentPlayer(), "The current player is not Player1");
    }
}
