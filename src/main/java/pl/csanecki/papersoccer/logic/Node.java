package pl.csanecki.papersoccer.logic;

public class Node {
    private boolean goal;

    public Node() {
        this(false);
    }

    public Node(boolean isGoal) {
        this.goal = isGoal;
    }

    public void setIsGoal(boolean goal) {
        this.goal = goal;
    }

    public boolean isGoal() {
        return this.goal;
    }
}
