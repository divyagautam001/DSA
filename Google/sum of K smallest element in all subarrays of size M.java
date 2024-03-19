// O(n * max(arr))
/* NOT UNDERSTOOD 
Initialization (Paradigm: Greedy / Dynamic Programming):

Initialize an empty list to store the sums of the smallest K elements in all subarrays.
Initialize an array counts to store the counts of elements in the current window.
Calculate the maximum element in the input array to determine the size of the counts array.
Process the First Window (Paradigm: Greedy):

Iterate through the first window of size M.
Update the counts of elements in the window.
Calculate the Sum of Smallest K Elements in the First Window (Paradigm: Greedy):

Calculate the sum of the smallest K elements based on the counts of elements in the window.
Keep track of the sum.
Store the Sum of Smallest K Elements of the First Window (Paradigm: Data Structure Manipulation):

Add the sum calculated in step 3 to the list of sums.
Slide the Window and Update Counts and Sum (Paradigm: Greedy):

Iterate through the array starting from index M.
Slide the window by removing the oldest element and adding the next element.
Update the counts of elements in the window.
Update the sum of the smallest K elements based on the updated counts.
Repeat this step until the end of the array is reached.
Store the Sum of Smallest K Elements for Each Subarray (Paradigm: Data Structure Manipulation):

Add the updated sum of the smallest K elements to the list of sums for each subarray.
Return the List of Sums (Paradigm: Output):

Return the list containing the sums of the smallest K elements in all subarrays

*/

import java.util.ArrayList;
import java.util.List;

public class SumOfKSmallestElements {

    public static List<Integer> sumOfKSmallestElements(int[] arr, int M, int K) {
        int n = arr.length;
        List<Integer> sums = new ArrayList<>();

        // Initialize counts array to store the occurrences of elements
        int[] counts = new int[getMax(arr) + 1];

        // Calculate counts for the first window
        for (int i = 0; i < M; i++) {
            counts[arr[i]]++;
        }

        // Calculate the sum of the smallest K elements in the first window
        int smallestSum = 0;
        int count = 0;
        for (int num = 0; num < counts.length; num++) {
            count += counts[num];
            smallestSum += num * counts[num];
            if (count >= K) {
                smallestSum -= (count - K) * num;
                break;
            }
        }
        sums.add(smallestSum);

        // Slide the window and update counts and sum
        for (int i = M; i < n; i++) {
            counts[arr[i - M]]--;
            counts[arr[i]]++;

            // Update smallest sum
            smallestSum = updateSum(counts, K, smallestSum);
            sums.add(smallestSum);
        }

        return sums;
    }

    // Helper method to find the maximum element in the array
    private static int getMax(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int num : arr) {
            max = Math.max(max, num);
        }
        return max;
    }

    // Helper method to update the sum of the smallest K elements based on counts
    private static int updateSum(int[] counts, int K, int currentSum) {
        int smallestSum = currentSum;
        int count = 0;
        for (int num = 0; num < counts.length; num++) {
            count += counts[num];
            smallestSum += num * counts[num];
            if (count >= K) {
                smallestSum -= (count - K) * num;
                break;
            }
        }
        return smallestSum;
    }

    public static void main(String[] args) {
       int[] arr = {3, 5, 2, 8, 9, 1, 4, 6, 7}; 
       int M = 3; 
       int K = 2; 

        List<Integer> result = sumOfKSmallestElements(arr, M, K);
        System.out.println("Sum = " + result);
    }
}
