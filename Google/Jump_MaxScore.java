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
