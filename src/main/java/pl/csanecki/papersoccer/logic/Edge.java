package pl.csanecki.papersoccer.logic;

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
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }
        if (!(obj instanceof Edge)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        Edge edge = (Edge) obj;

        return this.firstNode.equals(edge.getFirstNode()) &&
                this.secondNode.equals(edge.getSecondNode());
    }
}
