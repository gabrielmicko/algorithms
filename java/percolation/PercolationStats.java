import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double[] results;
    private final int gridSize;
    private final int trials;
    private final double cf;

    public PercolationStats(int s, int t) {
        if (s <= 0 || t <= 0) {
            throw new IllegalArgumentException();
        }
        cf = 1.96;
        trials = t;
        gridSize = s;
        results = runTest();
    }

    private double[] runTest() {
        double[] collection = new double[trials];
        for (int trial = 0; trial < trials; trial++) {
            Percolation percolation = new Percolation(gridSize);
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(1, gridSize + 1);
                int col = StdRandom.uniform(1, gridSize + 1);
                percolation.open(row, col);
            }
            int openSites = percolation.numberOfOpenSites();
            double result = (double) openSites / (gridSize * gridSize);
            collection[trial] = result;
        }
        return collection;
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(results);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(results);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - ((cf * stddev()) / Math.sqrt(trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + ((cf * stddev()) / Math.sqrt(trials));
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, trials);


        String confidence = ps.confidenceLo() + ", " + ps
                .confidenceHi();
        StdOut.println("mean                    = " + ps.mean());
        StdOut.println("stddev                  = " + ps.stddev());
        StdOut.println("95% confidence interval = [" + confidence + "]");
    }

}
