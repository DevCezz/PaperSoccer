package pl.csanecki.papersoccer.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.springframework.test.util.AssertionErrors.*;

public class EdgeTest {
    private Node firstNode;
    private Node secondNode;
    private Edge edge;

    @BeforeEach
    void setUp() {
        firstNode = new Node();
        secondNode = new Node();

        edge = new Edge(firstNode, secondNode);
    }

    @Test
    void givenEdgeInitiatedThenFirstNodeIsSet() {
        assertEquals("First node is wrongly set", firstNode, edge.getFirstNode());
    }

    @Test
    void givenEdgeInitiatedThenSecondNodeIsSet() {
        assertEquals("Second node is wrongly set", secondNode, edge.getSecondNode());
    }

    @Test
    void givenNodeInstantiatedThenNotContainsBall() {
        assertFalse("Edge cannot be used at start", edge.isUsed());
    }

    @Test
    void givenSetUsedTheEdgeIsUsed() {
        edge.setAsUsed();
        assertTrue("Edge should be used", edge.isUsed());
    }
}
