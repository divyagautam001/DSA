/* 
 O(n+m), where n is number of digits, m is the amount of money
Greedy algo used

Find max digit with min cost
Keep adding the digit with the minimum cost to the result until the money is not enough to buy another one
*/

public class Main {
    public static void main(String[] args) {
        // Initialize the cost array and the amount of money
        int[] cost = {1, 2, 3, 4, 5, 6, 7, 8, 1};
        int money = 10;

        // Call the maxNumber function and print the result
        System.out.println(maxNumber(cost, money));
    }

    public static String maxNumber(int[] cost, int money) {
        // Initialize the result as an empty string
        StringBuilder result = new StringBuilder();

        // Find the digit with the minimum cost
        int minCostIndex = 0;
        for (int i = 1; i < cost.length; i++) {
            if (cost[i] <= cost[minCostIndex]) {
                minCostIndex = i;
            }
        }

        // Keep adding the digit with the minimum cost to the result until the money is not enough to buy another one
        while (money >= cost[minCostIndex]) {
            result.append(minCostIndex + 1);
            money -= cost[minCostIndex];
        }

        // Return the result
        return result.toString();
    }
}
