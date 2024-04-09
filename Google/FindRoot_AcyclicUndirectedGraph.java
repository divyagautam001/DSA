/*
O(n)

Initialize: Start by initializing a boolean array visited of size n (where n is the number of nodes in the graph) 
  to keep track of the visited nodes.
All elements are initially set to false.
DFS: Perform a Depth-First Search (DFS) on the graph. The DFS function should take the current node and its parent as parameters. 
  For each neighbor of the current node, if the neighbor is not visited and is not the parent,
  recursively call the DFS function for the neighbor. If you encounter a node that has been visited and is not the parent, 
  it means there is a cycle, and the graph cannot be a tree.
Check Neighbors: After the DFS, check if any node has more than two neighbors. 
  If so, the graph cannot be a binary tree.
Find Root: If the graph passes the above checks, it can be a binary tree. 
  The root of the tree can be any node.
We can start the transformation of graph to binary tree, from any node.
*/

import java.util.*;

public class BinaryTreeCheck {
    private List<Integer>[] graph;
    private boolean[] visited;

    public boolean isValidBinaryTree(int n, int[][] edges) {
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        visited = new boolean[n];
        if (hasCycle(-1, 0)) {
            return false;
        }

        for (int i = 0; i < n; i++) {
            if (graph[i].size() > 2) {
                return false;
            }
        }

        return true;
    }

    private boolean hasCycle(int parent, int node) {
        visited[node] = true;
        for (int neighbor : graph[node]) {
            if (!visited[neighbor]) {
                if (hasCycle(node, neighbor)) {
                    return true;
                }
            } else if (neighbor != parent) {
                return true;
            }
        }
        return false;
    }
}
