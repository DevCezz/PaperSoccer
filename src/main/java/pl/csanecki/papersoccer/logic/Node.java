package pl.csanecki.papersoccer.logic;

import java.util.Objects;

public class Node {
    private Coordinate coordinate;
    private boolean containsBall;
    private boolean goal;

    public Node(int x, int y, boolean containsBall) {
        this.coordinate = new Coordinate(x, y);
        this.containsBall = containsBall;
        this.goal = false;
    }

    public Node(int x, int y) {
        this(x, y, false);
    }

    public void setContainsBall(boolean containsBall) {
        this.containsBall = containsBall;
    }

    public boolean containsBall() {
        return this.containsBall;
    }

    public void setIsGoal(boolean goal) {
        this.goal = goal;
    }

    public boolean isGoal() {
        return this.goal;
    }

    public int getX() {
        return this.coordinate.getX();
    }

    public int getY() {
        return this.coordinate.getY();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(coordinate, node.coordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinate, containsBall);
    }
}
