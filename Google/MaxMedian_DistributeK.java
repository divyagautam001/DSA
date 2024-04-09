/*
Binary Search
Initialize low as the median element and high as the maximum element in the array.
While low is less than high:
  Calculate mid as the average of low and high.
If it’s possible to make the median at least mid by adding k or fewer numbers, set low to mid + 1.
Otherwise, set high to mid.
Return low - 1 as the maximum possible median.

O(n logm) where n is size of array and m is max possible median.
*/

public class Main {
    public static void main(String[] args) {
        // Initialize array and k
        int[] arr = {1, 2, 3, 4, 5};
        int k = 10;

        // Print the maximum possible median
        System.out.println(maxMedian(arr, k));
    }

    public static int maxMedian(int[] arr, int k) {
        // Initialize low as the median element and high as the maximum possible median
        int low = arr[arr.length / 2], high = (int)1e9;

        // Perform binary search for the maximum possible median
        while (low < high) {
            int mid = (low + high) / 2;

            // If it's possible to make the median at least mid by adding k or fewer numbers, set low to mid + 1
            if (check(arr, mid, k)) {
                low = mid + 1;
            } else {
                // Otherwise, set high to mid
                high = mid;
            }
        }

        // Return low - 1 as the maximum possible median
        return low - 1;
    }

    private static boolean check(int[] arr, int mid, int k) {
        // Initialize extra as the number of additional numbers needed to make the median at least mid
        int extra = 0;

        // Iterate over the second half of the array
        for (int i = arr.length / 2; i < arr.length; i++) {
            // Add the number of additional numbers needed for the current element to extra
            extra += Math.max(0, mid - arr[i]);

            // If extra exceeds k, return false
            if (extra > k) {
                return false;
            }
        }

        // If extra does not exceed k, return true
        return true;
    }
}
