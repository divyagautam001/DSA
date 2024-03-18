import java.util.*;

public class Main {
    // Global variable storing the binary string
    static String s = "01110010001";

    // Function to query whether there's a '1' in the range [l, r] of the string
    static int query(int l, int r) {
        int cnt = 0;
        for (int i = l; i <= r; i++)
            if (s.charAt(i) == '1')
                return 1;

        return 0;
    }

    // Main solution function
    static void solution() {
        int n = s.length(); // Length of the string
        if (query(0, n - 1) == 0) return; // If there are no '1's, exit function
        int i = 0;
        while (i < n && query(i, n - 1) != 0) {
            int l = i;
            int r = n - 1;
            int ans = -1;
            while (l <= r) { // Binary search for the leftmost '1'
                int mid = (l + r) / 2;
                if (query(l, mid) == 1) {
                    ans = mid;
                    r = mid - 1;
                } else
                    l = mid + 1;
            }
            System.out.println(ans + " "); // Print the index of the leftmost '1'
            i = ans + 1;
        }
    }

    public static void main(String[] args) {
        solution();
    }
}

// divide and conquer approach, leveraging binary search and recursion to locate the positions of 1s. Here's how it can be done:
//Divide the array into two halves.
//Recursively search each half.
//Merge the results obtained from both halves.
// Not exacly log but better than linear search
