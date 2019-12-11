package pl.csanecki.papersoccer.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.springframework.test.util.AssertionErrors.*;

public class NodeTest {
    private Node node;

    @BeforeEach
    void setUp() {
        node = new Node();
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