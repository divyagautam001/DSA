/*
O(n) Decreasing Monotonic Stack

We will use stack to keep track of the indices of the stones in decreasing order of their values,
which allows us to efficiently find the stone from which we can get the maximum score when jumping to the current stone.

Create a dp array of the same size as the input array, stones.
Initialize all elements of dp to 0.
This dp array will be used to store the maximum score that can be obtained at each stone.
Stack keeps track of the indices of the stones.

Iterate over the stones array from the second stone to the last stone. 
For each stone, while the stack is not empty and the stone at the top of the stack is less than or equal to the current stone, pop the stack. 
Then, calculate the score if we jump from the stone at the top of the stack to the current stone,
	which is dp[top of the stack] + (current stone value * number of jump positions). 
 
Update the dp value of the current stone with this score. Finally, push the current stone index onto the stack.
Result: The maximum score that can be obtained starting from the beginning to end is stored in the last element of the dp array
*/


import java.util.*;

class Main
{
	public static int maxScore(int[] stones) {
        int n = stones.length;
        long[] dp = new long[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && stones[stack.peek()] <= stones[i]) {
                stack.pop();
            }
            dp[i] = (stack.isEmpty() ? 0 : dp[stack.peek()]) + (long) stones[i] * (i - (stack.isEmpty() ? 0 : stack.peek()));
            stack.push(i);
        }
        return (int) dp[n - 1];
    }
    public static void main(String[] args) {
        int[] stones = {1, 1, 1, 1, 1};
        System.out.println(maxScore(stones));
    }
}
