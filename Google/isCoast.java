import java.util.HashSet;
import java.util.Set;

public class Main {

    public static Set<String> vis = new HashSet<>();

    public static boolean isCoast(char[][] grid, int[] point) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dirs = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};
        Set<String> adjacentWater = new HashSet<>();
        int x = point[0];
        int y = point[1];

        for (int[] dir : dirs) {
            int nr = dir[0] + x;
            int nc = dir[1] + y;
            if (isValid(grid, nr, nc) && grid[nr][nc] == '.') {
                adjacentWater.add(nr + "," + nc);
            }
        }
         for (String water : adjacentWater) {
            String[] parts = water.split(",");
            int row = Integer.parseInt(parts[0]);
            int col = Integer.parseInt(parts[1]);
            if (!vis.contains(row + "," + col) && dfs(grid, vis, row, col, m, n)) {
                return true;
            }
        }
        
        return false;
        
    }

    public static boolean isValid(char[][] grid, int row, int col) {
        int m = grid.length;
        int n = grid[0].length;
        return row >= 0 && row < m && col >= 0 && col < n;
    }

    public static boolean dfs(char[][] grid, Set<String> vis, int row, int col, int m, int n) {
        if (vis.contains(row + "," + col) || grid[row][col] == 'X') {
            return false;
        }
        
        if (row + 1 >= m || row - 1 < 0 || col + 1 >= n || col - 1 < 0) {
            return true;
        }
        
        vis.add(row + "," + col);
        
        return dfs(grid, vis, row + 1, col, m, n) ||
            dfs(grid, vis, row - 1, col, m, n) ||
            dfs(grid, vis, row, col + 1, m, n) ||
            dfs(grid, vis, row, col - 1, m, n);
    }
   

    public static void main(String[] args) {
       char[][] grid = {
            {'X', 'X', 'X', 'X', 'X', 'X','X','X'},
            {'.', '.', '.', 'X', '.', 'X', '.','.'},
            {'.', '.', '.', 'X', 'X', '.', 'X','.'}
        };
        int[] coordinate = {1, 3};
        System.out.println(isCoast(grid, coordinate)); // Output: true
    }
}
