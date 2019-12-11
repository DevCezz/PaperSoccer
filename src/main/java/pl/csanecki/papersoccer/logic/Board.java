package pl.csanecki.papersoccer.logic;

public class Board {
    private Node[][] nodesNetwork;
    private Node ballNode;

    public Board(int width, int height) {
        nodesNetwork = new Node[width + 1][height + 1];
        initializeNodesNetwork(width, height);
    }

    private void initializeNodesNetwork(int width, int height) {
        for (int x = 0; x < width + 1; x++) {
            for (int y = 0; y < height + 1; y++) {
                nodesNetwork[x][y] = new Node(x, y);
            }
        }

        setBallInTheMiddle();
    }

    private void setBallInTheMiddle() {
        int middleOfWidth = (nodesNetwork.length - 1) / 2;
        int middleOfHeight = (nodesNetwork[0].length - 1) / 2;

        ballNode = nodesNetwork[middleOfWidth][middleOfHeight];
        ballNode.setContainsBall(true);
    }

    public void moveBall(int move) {
        ballNode.setContainsBall(false);
        ballNode = getNodeAfterMove(move);
        ballNode.setContainsBall(true);
    }

    private Node getNodeAfterMove(int move) {
        switch (move) {
            case 1:
                return nodesNetwork[ballNode.getX() - 1][ballNode.getY() + 1];
            case 2:
                return nodesNetwork[ballNode.getX()][ballNode.getY() + 1];
            case 3:
                return nodesNetwork[ballNode.getX() + 1][ballNode.getY() + 1];
            case 4:
                return nodesNetwork[ballNode.getX() - 1][ballNode.getY()];
            case 6:
                return nodesNetwork[ballNode.getX() + 1][ballNode.getY()];
            case 7:
                return nodesNetwork[ballNode.getX() - 1][ballNode.getY() - 1];
            case 8:
                return nodesNetwork[ballNode.getX()][ballNode.getY() - 1];
            case 9:
                return nodesNetwork[ballNode.getX() + 1][ballNode.getY() - 1];
        }

        throw new RuntimeException("Not allowed movement of ball");
    }

    public Node[][] getNodesNetwork() {
        return nodesNetwork;
    }
}
