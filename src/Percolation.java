import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int size;
    private int openSites;
    private static final int topId = 0;
    private final int bottomId;
    private boolean[][] grid;
    private final WeightedQuickUnionUF model;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        size = n;
        bottomId = n * n + 1;
        openSites = 0;
        grid = new boolean[n][n];
        model = new WeightedQuickUnionUF(bottomId + 1);
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        int modelId = getId(row, col);
        if (!isOpen(row, col)) {
            grid[row - 1][col - 1] = true;
            openSites++;


            if (row == 1) {
                model.union(modelId, topId);
            } else if (isOpen(row - 1, col)) {
                model.union(modelId, modelId - size);
            }

            if (row == size) {
                model.union(modelId, bottomId);
            } else if (isOpen(row + 1, col)) {
                model.union(modelId, modelId + size);
            }

            if (col > 1 && isOpen(row, col - 1)) {
                model.union(modelId, modelId - 1);
            }

            if (col < size && isOpen(row, col + 1)) {
                model.union(modelId, modelId + 1);
            }
        }
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row - 1][col - 1];

    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        int id = getId(row, col);
        return model.connected(topId, id);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return model.connected(topId, bottomId);
    }

    private void validate(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size) {
            throw new java.lang.IllegalArgumentException();
        }
    }

    private int getId(int row, int col) {
        return (row - 1) * size + col;
    }

}
