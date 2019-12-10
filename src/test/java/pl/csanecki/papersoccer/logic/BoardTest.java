package pl.csanecki.papersoccer.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.springframework.test.util.AssertionErrors.assertEquals;

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

        assertEquals("Nodes network is wrongly setted", nodesNetwork[3][4], expected);
    }
}
