/*
We can use heap and queue to solve this problem.

We can get the houses with colors and put them in a min heap.
Then we start grouping them in neighbourhoods.
As we use a house number & color, we put all same numbers it in discarded queue with a neighbourhood number when it will be available again to be put.
As we start filling in next neighbourhood, we clean up the queues and make data available again. This should be the first step in iteration.
*/

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[][] houseNumbers = {{8,2,9}, {4,6,4}, {4,5,1}};
        char[][] houseColors = {{'r','g','b'}, {'w','c','b'}, {'x','y','b'}};
        System.out.println(Arrays.deepToString(rearrangeNeighbourhood(houseNumbers, houseColors)));
    }

    public static String[][] rearrangeNeighbourhood(int[][] houseNumbers, char[][] houseColors) {
        PriorityQueue<int[]> houses = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        int m = houseNumbers.length, n = houseNumbers[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                houses.offer(new int[]{houseNumbers[i][j], houseColors[i][j], i, j});
            }
        }

        String[][] ans = new String[m][n];
        Queue<int[]> discarded = new LinkedList<>();

        for (int neighborhood = 0; neighborhood < m; neighborhood++) {
            while (!discarded.isEmpty() && discarded.peek()[2] <= neighborhood) {
                int[] d = discarded.poll();
                houses.offer(new int[]{d[0], d[1], neighborhood + 1, d[3]});
            }

            List<String> temp = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (houses.isEmpty()) break;
                int[] house = houses.poll();
                temp.add(house[0] + "" + (char)house[1]);

                while (!houses.isEmpty() && (houses.peek()[0] == house[0] || houses.peek()[1] == house[1])) {
                    int[] d = houses.poll();
                    discarded.offer(new int[]{d[0], d[1], neighborhood + 1, d[3]});
                }
            }

            ans[neighborhood] = temp.toArray(new String[0]);
        }

        return ans;
    }
}
