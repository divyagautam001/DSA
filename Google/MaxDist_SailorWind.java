/*
Initialize a 2D array dp:
Create a 2D array dp of size (n+1) x (n+1), where n is the number of days.
This array will be used to store the maximum distance that can be covered for each day and each possible power level. 
Initialize all elements of dp to -1.

Define a recursive function maxDistanceUtil:
This function takes the array arr, the current power K, the current day i, and the dp array as input. 
It returns the maximum distance that can be covered from day i onwards with K power.
Base case: If i is equal to the length of arr (i.e., all days have been considered), return 0.

Check memoization: If dp[i][K] is not -1, return dp[i][K]. 
This means we have already computed the maximum distance for day i with K power.

Calculate maximum distance without sailing: 
Call maxDistanceUtil for the next day (i+1) with K+1 power (since the power increases by 1 if the sailor does not sail). Store the result in a variable res.

Calculate maximum distance with sailing (if possible): If K > 0 (i.e., the sailor has power to sail), call maxDistanceUtil for the next day (i+1) with K-1 power (since the power decreases by 1 if the sailor sails). Add arr[i] (the distance covered by sailing on day i) to the result. If this value is greater than res, update res.

Update memoization: Set dp[i][K] to res.

Return result: Return res.
Call the recursive function: Call maxDistanceUtil with arr, K, 0 (since we start from day 0), and dp. This will compute the maximum distance that can be covered.
This algorithm ensures that all possible states (day and power level) are considered, and the maximum distance is found. The use of memoization (the dp array) avoids redundant computations, making the algorithm efficient. The time complexity is O(n^2), and the space complexity is also O(n^2), where n is the number of days
*/

public class Main {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int K = 2;
        System.out.println(maxDistance(arr, K));
    }

    public static int maxDistance(int[] arr, int K) {
        int n = arr.length;
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = -1;
            }
        }
        return maxDistanceUtil(arr, K, 0, dp);
    }

    private static int maxDistanceUtil(int[] arr, int K, int i, int[][] dp) {
        if (i == arr.length) {
            return 0;
        }
        if (dp[i][K] != -1) {
            return dp[i][K];
        }
        int res = maxDistanceUtil(arr, Math.min(arr.length, K + 1), i + 1, dp);
        if (K > 0) {
            res = Math.max(res, arr[i] + maxDistanceUtil(arr, K - 1, i + 1, dp));
        }
        dp[i][K] = res;
        return res;
    }
}
