package pl.csanecki.papersoccer.game.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EdgeTest {
    @Test
    void givenEdgeInitiatedThenTwoCoordinatesAreConnected() {
        Coordinate firstCoordinate = new Coordinate(1,1);
        Coordinate secondCoordinate = new Coordinate(1,2);

        Edge edge = new Edge(firstCoordinate, secondCoordinate);

        assertEquals(firstCoordinate, edge.getFirstCoordinate());
        assertEquals(secondCoordinate, edge.getSecondCoordinate());
    }

    @Test
    void givenTwoEdgesInitiatedWithChangedOrderOfCoordinatesThenTheyEquals() {
        Coordinate firstCoordinate = new Coordinate(1,1);
        Coordinate secondCoordinate = new Coordinate(1,2);

        Edge firstEdge = new Edge(firstCoordinate, secondCoordinate);
        Edge secondEdge = new Edge(secondCoordinate, firstCoordinate);

        assertEquals(firstEdge, secondEdge, "They are not the same edges");
    }
}
