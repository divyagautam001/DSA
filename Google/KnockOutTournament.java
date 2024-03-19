//The time complexity of this algorithm is O(log n) where n is the number of players.
//This is because, in each recursion, the number of players is halved, leading to a logarithmic number of recursive calls.

import java.util.*;

public class Main {

    // Function to check if the draw is valid
    public static boolean isValidDraw(List<Integer> draw) {
        int n = draw.size();

        // Base case: if there is only one player left, the draw is valid
        if (n == 1) {
            return true;
        }

        // Check if the draw is valid for the current round
        for (int i = 0; i < n; i += 2) {
            // If the sum of ranks of players in each match doesn't add up to n+1, it's not a valid draw
            if (draw.get(i) + draw.get(i + 1) != n + 1) {
                return false;
            }
        }

        // Create a new list for the next round
        List<Integer> nextRound = new ArrayList<>();

        // Construct the next round by taking the minimum of each pair
        for (int i = 0; i < n; i += 2) {
            nextRound.add(Math.min(draw.get(i), draw.get(i + 1)));
        }

        // Continue checking recursively for the next round
        return isValidDraw(nextRound);
    }

    public static void main(String[] args) {
        // Input: ranks of players in the tournament
        List<Integer> playerRanks = Arrays.asList(1, 8, 4, 5, 3, 6, 2, 7);

        // Check if the number of players is a power of 2
        int numPlayers = playerRanks.size();
        boolean isPowerOfTwo = (numPlayers & (numPlayers - 1)) == 0;

        // If the number of players is not a power of 2, the draw is not possible
        if (!isPowerOfTwo) {
            System.out.println("Not possible");
            return;
        }

        // Check if the draw is valid
        boolean isValid = isValidDraw(playerRanks);
        System.out.println(isValid);
    }
}
