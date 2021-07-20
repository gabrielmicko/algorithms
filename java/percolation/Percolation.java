import edu.princeton.cs.algs4.WeightedQuickUnionUF;

class Grid {
    private boolean[] gridState;
    public final int gridSize;
    private final int gridMaxIndex;
    private final int gridLength;

    public Grid(int n) {
        /* 5 */
        gridSize = n;
        /* 24 */
        gridMaxIndex = (n * n) - 1;
        gridState = new boolean[n * n];
        for (int i = 0; i < n; i++) {
            this.block(i);
        }
        /* 25 */
        gridLength = gridState.length;
    }

    public boolean isSiteWithInBarrier(int n) {
        if (n >= 0 && n <= gridMaxIndex) {
            return true;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public int getNumberFromColRow(int row, int col) {
        int rowLog = (row - 1) * gridSize;
        int colLog = col - 1;
        return rowLog + colLog;
    }


    private boolean isBorderLeft(int n) {
        return n % gridSize == 0;
    }

    private boolean isBorderRight(int n) {
        return (n + 1) % gridSize == 0;
    }

    private boolean isBorderTop(int n) {
        return n >= 0 && n < gridSize;
    }

    private boolean isBorderBottom(int n) {
        return n <= gridMaxIndex && n > (gridMaxIndex - gridSize);
    }

    private int getNumberLeft(int n) {
        return n - 1;
    }

    private int getNumberRight(int n) {
        return n + 1;
    }

    private int getNumberTop(int n) {
        return n - gridSize;
    }

    private int getNumberBottom(int n) {
        return n + gridSize;
    }

    public boolean isOpen(int n) {
        if (isSiteWithInBarrier(n)) {
            return gridState[n];
        }
        return false;
    }

    public boolean isNumberTop(int x) {
        return x >= 0 && x < gridSize;
    }

    //  0  1  2  3  4
    //  5  6  7  8  9
    // 10 11 12 13 14
    // 15 16 17 18 19

    public boolean isNumberBottom(int x) {
        return x >= (gridLength - gridSize) && x < gridLength;
    }

    public int getNumberOfOpenSites() {
        int openSitesCount = 0;
        for (int i = 0; i < gridState.length; i++) {
            if (isOpen(i)) {
                openSitesCount = openSitesCount + 1;
            }
        }
        return openSitesCount;
    }

    public int[] getOpenBorders(int n) {
        int[] x = new int[4];
        x[0] = -1;
        x[1] = -1;
        x[2] = -1;
        x[3] = -1;
        if (isSiteWithInBarrier(n)) {
            int numberLeft = getNumberLeft(n);
            int numberRight = getNumberRight(n);
            int numberTop = getNumberTop(n);
            int numberBottom = getNumberBottom(n);
            if (!isBorderLeft(n) && isOpen(numberLeft)) {
                x[0] = numberLeft;
            }
            if (!isBorderRight(n) && isOpen(numberRight)) {
                x[1] = numberRight;
            }
            if (!isBorderTop(n) && isOpen(numberTop)) {
                x[2] = numberTop;
            }
            if (!isBorderBottom(n) && isOpen(numberBottom)) {
                x[3] = numberBottom;
            }
        }
        return x;
    }

    public void open(int n) {
        if (isSiteWithInBarrier(n)) {
            gridState[n] = true;
        }
    }

    public void block(int n) {
        if (isSiteWithInBarrier(n)) {
            gridState[n] = false;
        }
    }


}


public class Percolation {

    private final int gridSizeSqr;
    private final int virtualTop;
    private final int virtualBottom;
    private final Grid grid;
    private final WeightedQuickUnionUF quickUnionUF;
    private final WeightedQuickUnionUF quickUnionUFForFull;

    public Percolation(int n) {
        if (n < 1) {
            throw new IllegalArgumentException();
        }
        gridSizeSqr = (n * n);
        grid = new Grid(n);
        virtualTop = 0;
        virtualBottom = gridSizeSqr + 1;
        quickUnionUF = new WeightedQuickUnionUF(gridSizeSqr + 2);
        quickUnionUFForFull = new WeightedQuickUnionUF(gridSizeSqr + 1);
    }

    private int getQUNumberFromGridNumber(int x) {
        return x + 1;
    }

    private void validateRowCol(int row, int col) {
        if (row < 1 || col < 1 || row > grid.gridSize || col > grid.gridSize) {
            throw new IllegalArgumentException();
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validateRowCol(row, col);
        int num = grid.getNumberFromColRow(row, col);

        // System.out.println("Open " + num);
        if (!grid.isOpen(num)) {
            grid.open(num);
            int[] openBorders = grid.getOpenBorders(num);
            for (int i = 0; i < openBorders.length; i++) {
                if (openBorders[i] != -1) {
                    quickUnionUF.union(
                            getQUNumberFromGridNumber(openBorders[i]),
                            getQUNumberFromGridNumber(num));
                    quickUnionUFForFull.union(
                            getQUNumberFromGridNumber(openBorders[i]),
                            getQUNumberFromGridNumber(num));
                }
            }
            if (grid.isNumberTop(num)) {
                // System.out.println("Top connect " + num);
                quickUnionUF.union(getQUNumberFromGridNumber(num), virtualTop);
                quickUnionUFForFull.union(getQUNumberFromGridNumber(num), virtualTop);
            }

            if (grid.isNumberBottom(num)) {
                // System.out.println("Bottom connect " + num);
                quickUnionUF.union(getQUNumberFromGridNumber(num), virtualBottom);
            }
        }


    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validateRowCol(row, col);
        return grid.isOpen(grid.getNumberFromColRow(row, col));

    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validateRowCol(row, col);
        return isConnected(quickUnionUFForFull, virtualTop,
                           getQUNumberFromGridNumber(grid.getNumberFromColRow(row, col)));

    }


    // returns the number of open sites
    public int numberOfOpenSites() {
        return grid.getNumberOfOpenSites();
    }

    // does the system percolate?
    public boolean percolates() {
        return isConnected(quickUnionUF, virtualTop, virtualBottom);
    }

    private boolean isConnected(WeightedQuickUnionUF qu, int x, int y) {
        return qu.find(x) == qu.find(y);
    }

    // test client (optional)
    public static void main(String[] args) {
    }
}

