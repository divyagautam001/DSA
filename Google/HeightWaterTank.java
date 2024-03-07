import java.util.*;

public class Main {
    static int[] rowDir = new int[]{-1, 0, 1, 0};
    static int[] colDir = new int[]{0, 1, 0, -1};

    public static void main(String[] args) {
        int[][] heights = {
                {9, 0, 1, 0},
                {0, 0, 1, 0},
                {1, 1, 1, 0}
        };

        int[] town1 = new int[]{1, 3};
        int[] town2 = new int[]{2, 3};

        findCommonHighest(heights, town1, town2);
    }

    public static void findCommonHighest(int[][] heights, int[] town1, int[] town2) {
        int numRows = heights.length;
        int numCols = heights[0].length;
        ArrayList<ArrayList<Integer>> results = new ArrayList<>();

        int[][] FromTown1 = new int[numRows][numCols];
        int[][] FromTown2 = new int[numRows][numCols];

        dfs(town1[0], town1[1], heights, FromTown1, numRows, numCols);
        dfs(town2[0], town2[1], heights, FromTown2, numRows, numCols);

        int max = 0;
        int maxRow = 0;
        int maxCol = 0;
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                if (FromTown1[row][col] == 1 && FromTown2[row][col] == 1 && max < heights[row][col]) {
                    max = heights[row][col];
                    maxRow = row;
                    maxCol = col;
                }
            }
        }
        System.out.println( maxRow + ", " + maxCol);
    }

    public static void dfs(int row, int col, int[][] heights, int[][] ocean, int numRows, int numCols) {
        ocean[row][col] = 1;

        for (int i = 0; i < 4; i++) {
            int newRow = row + rowDir[i];
            int newCol = col + colDir[i];

            if (newRow >= 0 && newRow < numRows && newCol >= 0 && newCol < numCols &&
                    heights[row][col] <= heights[newRow][newCol] &&
                    ocean[newRow][newCol] == 0) {
                dfs(newRow, newCol, heights, ocean, numRows, numCols);
            }
        }
    }
}
