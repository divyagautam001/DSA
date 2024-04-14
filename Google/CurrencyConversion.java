/*
BFS + HashSet O(V+E)
Create a graph: Each currency is a node in the graph. 
  The edge between two currencies represents the conversion rate between them. 
  For each conversion rate in the input, add an edge between the two currencies in both directions. 
  The weight of the edge from currency A to currency B is the conversion rate from A to B, 
  and the weight of the edge from B to A is the reciprocal of that conversion rate.
Initialize a queue and a visited set: Add the ‘from’ currency to the queue and the visited set.
Initialize a map to store the conversion values: The key is a currency, 
  and the value is the conversion rate from the ‘from’ currency to this currency. Initialize the conversion value of the ‘from’ currency to 1.
Perform BFS: While the queue is not empty, remove a currency from the queue. 
  For each neighbor of this currency that has not been visited, 
  calculate the conversion value from the ‘from’ currency to this neighbor by multiplying the conversion value of the current currency 
  and the conversion rate from the current currency to the neighbor.
  If the neighbor is the ‘to’ currency, return its conversion value. Add the neighbor to the queue and the visited set.
Return -1 if no conversion rate can be found: If the BFS ends without finding the ‘to’ currency, return -1.

*/

import java.util.*;

public class CurrencyConverter {
    private Map<String, Map<String, Double>> graph = new HashMap<>();

    public CurrencyConverter(String[][] rates) {
        // Build the graph
        for (String[] rate : rates) {
            graph.putIfAbsent(rate[0], new HashMap<>());
            graph.get(rate[0]).put(rate[1], Double.parseDouble(rate[2]));
            graph.putIfAbsent(rate[1], new HashMap<>());
            graph.get(rate[1]).put(rate[0], 1 / Double.parseDouble(rate[2]));
        }
    }

    public double convert(String from, String to) {
        if (!graph.containsKey(from) || !graph.containsKey(to)) return -1;
        if (from.equals(to)) return 1;

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Map<String, Double> values = new HashMap<>();

        queue.offer(from);
        visited.add(from);
        values.put(from, 1.0);

        while (!queue.isEmpty()) {
            String curr = queue.poll();
            double value = values.get(curr);
            Map<String, Double> neighbors = graph.get(curr);

            for (String neighbor : neighbors.keySet()) {
                if (!visited.contains(neighbor)) {
                    double neighborValue = value * neighbors.get(neighbor);
                    if (neighbor.equals(to)) return neighborValue;

                    queue.offer(neighbor);
                    visited.add(neighbor);
                    values.put(neighbor, neighborValue);
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        String[][] rates = {{"USD", "JPY", "110"}, {"USD", "AUD", "1.45"}, {"JPY", "GBP", "0.0070"}};
        CurrencyConverter converter = new CurrencyConverter(rates);
        System.out.println(converter.convert("GBP", "AUD"));
    }
}
