/*This problem can be solved using the Bipartite Matching or Maximum Flow algorithm. The idea is to treat each boy and girl as nodes in a graph, and an edge between a boy and a girl exists if the boy can invite the girl (i.e., grid[i][j] == 1). The problem then becomes finding the maximum number of edges that do not share a node, which is a classic application of the Bipartite Matching algorithm.

The time complexity of the algorithm is O(m * n * (m + n)), where m is the number of boys and n is the number of girls.

Here is the step-by-step algorithm:

Create a graph where each boy and girl is a node.
Add an edge between a boy i and a girl j if grid[i][j] == 1.
Find the maximum bipartite matching in the graph

*/

import java.util.*;

public class Solution {
    // Array to store the match (girl assigned to boy)
    int[] match;
    // Array to store whether a node has been visited
    boolean[] visited;

    public int maximumInvitations(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        match = new int[n];
        visited = new boolean[n];
        Arrays.fill(match, -1);

        int result = 0;
        for (int i = 0; i < m; i++) {
            Arrays.fill(visited, false);
            if (bpm(grid, i)) result++;
        }
        return result;
    }

    // Returns true if a match is possible for u
    boolean bpm(int[][] grid, int u) {
        int n = grid[0].length;
        for (int v = 0; v < n; v++) {
            // If boy u can invite girl v and v is not visited
            if (grid[u][v] == 1 && !visited[v]) {
                visited[v] = true; // Mark v as visited

                // If girl 'v' is not invited by any boy OR she is invited but the boy previously inviting her can invite other girl
                if (match[v] < 0 || bpm(grid, match[v])) {
                    match[v] = u;
                    return true;
                }
            }
        }
        return false;
    }
}
