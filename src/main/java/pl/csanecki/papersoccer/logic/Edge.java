package pl.csanecki.papersoccer.logic;

import java.util.Objects;

public class Edge {
    private Node firstNode;
    private Node secondNode;
    private boolean used;

    public Edge(Node firstNode, Node secondNode) {
        this.firstNode = firstNode;
        this.secondNode = secondNode;
        this.used = false;
    }

    public void setAsUsed() {
        this.used = true;
    }

    public boolean isUsed() {
        return this.used;
    }

    public Node getFirstNode() {
        return firstNode;
    }

    public Node getSecondNode() {
        return secondNode;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return Objects.equals(firstNode, edge.firstNode) && Objects.equals(secondNode, edge.secondNode);
    }
}
