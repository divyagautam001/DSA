/*
O(n) time & spacecomplexity 
We can use HashMap to store the first and last occurrence of each element in the array.
calculates the prefix sum of the array and stores the first and last occurrence of each element in the array. 
It then iterates over the first occurrence map and for each element, it checks if the element occurs more than once in the array. 
If so, it calculates the sum of the subarray from the first occurrence to the last occurrence of the element and checks 
  If this sum is greater than maxSum. If it is, it updates maxSum and records the indices i and j. 
Finally, it returns maxSum, i, and j.
*/

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 2, 1, 4, 5, 4, 6};
        int[] result = maxSumSubarray(arr);
        System.out.println("Maximum sum: " + result[0]);
        System.out.println("i: " + result[1]);
        System.out.println("j: " + result[2]);
    }

    public static int[] maxSumSubarray(int[] arr) {
        int maxSum = Integer.MIN_VALUE;
        int start = 0;
        int end = 0;
        int[] prefixSum = new int[arr.length + 1];
        Map<Integer, Integer> firstOccurrence = new HashMap<>();
        Map<Integer, Integer> lastOccurrence = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            prefixSum[i + 1] = prefixSum[i] + arr[i];
            lastOccurrence.put(arr[i], i);
            if (!firstOccurrence.containsKey(arr[i])) {
                firstOccurrence.put(arr[i], i);
            }
        }

        for (Map.Entry<Integer, Integer> entry : firstOccurrence.entrySet()) {
            int i = entry.getValue();
            int j = lastOccurrence.get(entry.getKey());
            if (i != j) {
                int sum = prefixSum[j + 1] - prefixSum[i];
                if (sum > maxSum) {
                    maxSum = sum;
                    start = i;
                    end = j;
                }
            }
        }

        return new int[]{maxSum, start, end};
    }
}
