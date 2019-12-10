package pl.csanecki.papersoccer.logic;

public class Board {
    private Node[][] nodesNetwork;

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
    }

    public Node[][] getNodesNetwork() {
        return nodesNetwork;
    }
}
