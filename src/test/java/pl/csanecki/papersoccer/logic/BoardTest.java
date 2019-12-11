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
    void givenBoardInitiatedWhenWidthAndHeightIsSetThenNodesNetworkIsSet() {
        Node[][] nodesNetwork = board.getNodesNetwork();

        Node expected = new Node(3, 4);

        assertEquals(nodesNetwork[3][4], expected, "Nodes network is wrongly set");
    }

    @Test
    void givenBoardInitiatedWhenWidthAndHeightIsSetThenBallIsInTheMiddleOfBoard() {
        Node[][] nodesNetwork = board.getNodesNetwork();

        Node nodeWithBall = nodesNetwork[width / 2][height / 2];

        assertTrue(nodeWithBall.containsBall(), "Middle node does not contain ball");
    }

    @Test
    void whenMoveBallLeftThenNodeOnTheLeftFromMiddleHasBall() {
        board.moveBall(4);

        Node[][] nodesNetwork = board.getNodesNetwork();
        Node nodeWithBall = nodesNetwork[(width / 2) - 1][height / 2];

        assertTrue(nodeWithBall.containsBall(), "Node does not contain ball");
    }

    @Test
    void whenMoveBallRightThenNodeOnTheRightFromMiddleHasBall() {
        board.moveBall(6);

        Node[][] nodesNetwork = board.getNodesNetwork();
        Node nodeWithBall = nodesNetwork[(width / 2) + 1][height / 2];

        assertTrue(nodeWithBall.containsBall(), "Node does not contain ball");
    }

    @Test
    void whenMoveBallTopThenNodeOnTheTopFromMiddleHasBall() {
        board.moveBall(8);

        Node[][] nodesNetwork = board.getNodesNetwork();
        Node nodeWithBall = nodesNetwork[width / 2][(height / 2) - 1];

        assertTrue(nodeWithBall.containsBall(), "Node does not contain ball");
    }

    @Test
    void whenMoveBallDownThenNodeOnTheDownFromMiddleHasBall() {
        board.moveBall(2);

        Node[][] nodesNetwork = board.getNodesNetwork();
        Node nodeWithBall = nodesNetwork[width / 2][(height / 2) + 1];

        assertTrue(nodeWithBall.containsBall(), "Node does not contain ball");
    }

    @Test
    void whenNotAllowedMovementThenRuntimeException() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            board.moveBall(5);
        }, "The movement is incorrectly allowed");

        assertEquals("Not allowed movement of ball", exception.getMessage());
    }

    @Test
    void whenMoveBallTopLeftThenNodeOnTheTopLeftFromMiddleHasBall() {
        board.moveBall(7);

        Node[][] nodesNetwork = board.getNodesNetwork();
        Node nodeWithBall = nodesNetwork[(width / 2) - 1][(height / 2) - 1];

        assertTrue(nodeWithBall.containsBall(), "Node does not contain ball");
    }

    @Test
    void whenMoveBallTopRightThenNodeOnTheTopRightFromMiddleHasBall() {
        board.moveBall(9);

        Node[][] nodesNetwork = board.getNodesNetwork();
        Node nodeWithBall = nodesNetwork[(width / 2) + 1][(height / 2) - 1];

        assertTrue(nodeWithBall.containsBall(), "Node does not contain ball");
    }

    @Test
    void whenMoveBallDownLeftThenNodeOnTheDownLeftFromMiddleHasBall() {
        board.moveBall(1);

        Node[][] nodesNetwork = board.getNodesNetwork();
        Node nodeWithBall = nodesNetwork[(width / 2) - 1][(height / 2) + 1];

        assertTrue(nodeWithBall.containsBall(), "Node does not contain ball");
    }

    @Test
    void whenMoveBallDownRightThenNodeOnTheDownRightFromMiddleHasBall() {
        board.moveBall(3);

        Node[][] nodesNetwork = board.getNodesNetwork();
        Node nodeWithBall = nodesNetwork[(width / 2) + 1][(height / 2) + 1];

        assertTrue(nodeWithBall.containsBall(), "Node does not contain ball");
    }

    @Test
    void givenMoveBallLeftToBoardWhenMoveBallLeftThenRuntimeException() {
        for (int i = 0; i < width / 2; i++) {
            board.moveBall(4);
        }

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            board.moveBall(4);
        }, "The movement is incorrectly allowed");

        assertEquals("Not allowed to move out of board", exception.getMessage());
    }

    @Test
    void givenMoveBallRightToBoardWhenMoveBallRightThenRuntimeException() {
        for (int i = 0; i < width / 2; i++) {
            board.moveBall(6);
        }

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            board.moveBall(6);
        }, "The movement is incorrectly allowed");

        assertEquals("Not allowed to move out of board", exception.getMessage());
    }

    @Test
    void givenMoveBallTopToBoardEdgeWhenMoveBallTopThenWinner() {
        for (int i = 0; i < height / 2; i++) {
            board.moveBall(8);
        }

        assertEquals("Player2 Wins", board.moveBall(8));
    }

    @Test
    void givenMoveBallTopToBoardAndOneLeftWhenMoveBallTopRightThenWinner() {
        for (int i = 0; i < height / 2; i++) {
            board.moveBall(8);
        }

        board.moveBall(4);

        assertEquals("Player2 Wins", board.moveBall(9));
    }

    @Test
    void givenMoveBallTopToBoardAndOneRightWhenMoveBallTopLeftThenWinner() {
        for (int i = 0; i < height / 2; i++) {
            board.moveBall(8);
        }

        board.moveBall(6);

        assertEquals("Player2 Wins", board.moveBall(7));
    }

    @Test
    void whenMoveBallAndNoWinnerThenGameUnderway() {
        assertEquals("Game Underway", board.moveBall(4));
    }
}
