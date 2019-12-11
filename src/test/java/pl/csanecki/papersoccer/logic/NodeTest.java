package pl.csanecki.papersoccer.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.springframework.test.util.AssertionErrors.*;

public class NodeTest {
    private int x = 3;
    private int y = 4;

    private Node node;

    @BeforeEach
    void setUp() {
        node = new Node(x, y);
    }

    @Test
    void givenNodeInstantiatedThenXIsSet() {
        assertEquals("Node X is set wrongly", x, node.getX());
    }

    @Test
    void givenNodeInstantiatedThenYIsSet() {
        assertEquals("Node Y is set wrongly", y, node.getY());
    }

    @Test
    void givenNodeInstantiatedThenNotContainsBall() {
        assertFalse("Node cannot contains ball at start", node.containsBall());
    }

    @Test
    void givenSetContainsBallThenNodeContainsBall() {
        node.setContainsBall(true);
        assertTrue("Node should contain ball", node.containsBall());
    }

    @Test
    void givenSetContainsBallThenNodeIsNotGoal() {
        assertFalse("Node cannot contains ball at start", node.isGoal());
    }

    @Test
    void givenSetIsGoalThenNodeIsGoal() {
        node.setIsGoal(true);
        assertTrue("Node cannot contains ball at start", node.isGoal());
    }
}