/*
O(N)
It calculates the total weight of all items and then iterates through each item, 
checking if removing that item would result in splitting the remaining weight equally.
*/

import java.util.*;
public class GoldChain {
    // Method to check if a solution is possible
    public static boolean isSolutionPossible(int[] weights) {
        // Calculate the total weight of all items
        int totalWeight = Arrays.stream(weights).sum();
        // Initialize a variable to keep track of the running sum
        int runningSum = 0;
        // Loop through each weight
        for (int i = 0; i < weights.length; i++) {
            // If the running sum equals half of the total weight minus the current weight
            if (runningSum == (totalWeight - weights[i]) / 2.0) {
                return true; // Solution is possible
            }
            // Add the current weight to the running sum
            runningSum += weights[i];
        }
        // If no solution is found, return false
        return false;
    }

    // Main method
    public static void main(String[] args) {
        // Test case: weights of items
        int[] weights = {1, 2, 3, 4, 6};
        // Call the isSolutionPossible method and print the result
        System.out.println(isSolutionPossible(weights) ? "Solution is possible" : "Solution is not possible");
    }
}
