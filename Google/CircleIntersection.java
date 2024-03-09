// Follow up: Find largest group also if groups are disconnected.

import java.util.*;

public class myproj {   
    static boolean overlap(int[] circle1, int[] circle2) {
        double dist = Math.sqrt(Math.pow(circle2[0] - circle1[0], 2) + Math.pow(circle2[1] - circle1[1], 2));
        return dist <= circle1[2] + circle2[2];
    }
    
    // Main function to check if circles belong to a single circle group
    static boolean circleGroup(int[][] circles) {
        // Create a graph with nodes representing circles and edges representing overlap
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < circles.length; i++) {
            graph.put(i, new HashSet<>());
            for (int j = i + 1; j < circles.length; j++) {
                if (overlap(circles[i], circles[j])) {
                    graph.get(i).add(j);
                    if (!graph.containsKey(j)) {
                        graph.put(j, new HashSet<>());
                    }
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }

        // Use depth-first search to check if all nodes are connected
        Set<Integer> visited = new HashSet<>();
        dfs(0, graph, visited);
        return visited.size() == circles.length;
    }

    // Depth-first search implementation
    static void dfs(int node, Map<Integer, Set<Integer>> graph, Set<Integer> visited) {
        visited.add(node);
        for (int neighbor : graph.get(node)) {
            if (!visited.contains(neighbor)) {
                dfs(neighbor, graph, visited);
            }
        }
    }

    // Example usage
    public static void main(String[] args) {
        int[][] circles1 = {{2, 1, 3}, {6, 1, 4}};
        System.out.println(circleGroup(circles1)); // Output: true (the two circles belong to a single circle group)

        int[][] circles2 = {{2, 1, 3}, {6, 1, 4}, {8, 1, 5}};
        System.out.println(circleGroup(circles2)); // Output: true (all three circles overlap)

        int[][] circles3 = {{2, 1, 3}, {6, 1, 4}, {10, 1, 6}};
        System.out.println(circleGroup(circles3)); // Output: false (the third circle does not overlap with the other two circles)

        int[][] circles4 = {{2, 1, 3}, {2, 2, 3}, {2, 3, 3}};
        System.out.println(circleGroup(circles4)); // Output: true (all three circles are the same circle)
    }
}
