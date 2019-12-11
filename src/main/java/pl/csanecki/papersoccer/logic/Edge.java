package pl.csanecki.papersoccer.logic;

import java.util.Objects;

public class Edge {
    private Coordinate firstCoordinate;
    private Coordinate secondCoordinate;

    public Edge(Coordinate firstCoordinate, Coordinate secondCoordinate) {
        this.firstCoordinate = firstCoordinate;
        this.secondCoordinate = secondCoordinate;
    }

    public Coordinate getFirstCoordinate() {
        return firstCoordinate;
    }

    public Coordinate getSecondCoordinate() {
        return secondCoordinate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return (Objects.equals(firstCoordinate, edge.firstCoordinate) &&
                Objects.equals(secondCoordinate, edge.secondCoordinate)) ||
                (Objects.equals(firstCoordinate, edge.secondCoordinate) &&
                        Objects.equals(secondCoordinate, edge.firstCoordinate));
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstCoordinate, secondCoordinate);
    }
}
