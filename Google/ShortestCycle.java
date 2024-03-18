//Minimum path or the number of minimum steps -> BFS.
//BFS uses Queue data structure
//HashSet does not accept duplicate values. If you try to insert a duplicate value else it is a value already seen, so it's a cycle.

import java.util.*;

public class ShortestCycleInDirectedGraph {

    public static List<Integer> shortestCycle(int[][] graph, int start) {
        
        int n = graph.length;
        
        // Queue for BFS traversal
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        
        // HashSet to track visited nodes
        Set<Integer> visited = new HashSet<>();
        visited.add(start);
        
        // Array to store the parent of each node in the shortest cycle
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        
        // Perform BFS
        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int neighbor : graph[current]) {
                if (neighbor == start) {
                    // Found a cycle
                    parent[neighbor] = current;
                    return buildPath(parent, start);
                }
                if (!visited.contains(neighbor)) {
                    queue.offer(neighbor);
                    visited.add(neighbor);
                    parent[neighbor] = current;
                }
            }
        }
        
        // If no cycle found
        return new ArrayList<>();
    }
    
    // Helper method to build path from parent array
    private static List<Integer> buildPath(int[] parent, int start) {
        List<Integer> path = new ArrayList<>();
        int current = start;
        while (current != -1) {
            path.add(current);
            current = parent[current];
        }
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        // Example graph represented as an adjacency list
        int[][] graph = {
            {1},     // Node 0 points to Node 1
            {2},     // Node 1 points to Node 2
            {0}      // Node 2 points back to Node 0 (forming a cycle)
        };

        int startNode = 0;
        List<Integer> shortestCyclePath = shortestCycle(graph, startNode);
        
        // Output the shortest cycle path
        if (shortestCyclePath.isEmpty()) {
            System.out.println("No cycle found.");
        } else {
            System.out.println("Shortest cycle path: " + shortestCyclePath);
        }
    }
}
