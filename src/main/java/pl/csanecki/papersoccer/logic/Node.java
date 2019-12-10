package pl.csanecki.papersoccer.logic;

public class Node {
    private int x;
    private int y;
    private boolean containsBall;

    public Node(int x, int y, boolean containsBall) {
        this.x = x;
        this.y = y;
        this.containsBall = containsBall;
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

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }
        if (!(obj instanceof Node)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        Node node = (Node) obj;

        return this.x == node.getX() && this.y == node.getY();
    }
}
