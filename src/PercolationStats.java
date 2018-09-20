import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private final double[] fractions;
    private final int trials;
    private static final double confidence95 = 1.96;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        this.trials = trials;
        fractions = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                percolation.open(row, col);
            }
            fractions[i] = ((double) percolation.numberOfOpenSites()) / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(fractions);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(fractions);
    }


    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (confidence95 * stddev() / Math.sqrt(trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (confidence95 * stddev() / Math.sqrt(trials));
    }

    public static void main(String[] args) {
        int size = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats percolationStats = new PercolationStats(size, trials);

        double mean = percolationStats.mean();
        double stddev = percolationStats.stddev();
        double[] interval = {percolationStats.confidenceLo(), percolationStats.confidenceHi()};
        System.out.printf("%-23s = %f%n", "mean", mean);
        System.out.printf("%-23s = %f%n", "stddev", stddev);
        System.out.printf("%-23s = [%f, %f]%n", "95% confidence interval", interval[0], interval[1]);

    }
}
