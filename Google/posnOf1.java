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

/* 

Divide and conquer approach, leveraging binary search principles locate the positions of 1s. Here's how it can be done:
Initialize an empty list to store the positions of 1s.
Start with the entire range [0, M-1].
Divide the range into two halves and query each half.
If the left half contains at least one 1, update the right boundary to mid - 1.
Otherwise, update the left boundary to mid + 1.
Repeat steps 3-5 until the left boundary exceeds the right boundary.
Return the list of positions of 1s.
    */
