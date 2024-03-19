/*
O(n) 
"remove" from t instead of adding to s.
do fake reversals; keep a boolean flag reversed which you toggle whenever the character at current index is b.
remember to reverse the substring at the end if reversed is true, since that piece of state needs to be propagated.
this solution handles cases like s = "x", t = "xb" correctly (since from reading the question, its not forbidden that s and t are only made up of 'a' or 'b').
*/

public class Main {

    public static boolean solve(String s, String t) {
        int m = s.length(), n = t.length();
        if (n < m) {
            return false;
        }
        int l = 0, r = n - 1, reversed = 0;
        while (r - l + 1 > m) {
            int i = reversed == 0 ? l : r;
            int di = reversed == 0 ? 1 : -1;

            if (t.charAt(i) == 'a') {
                i += di;
            } else if (t.charAt(i) == 'b') {
                i += di;
                reversed ^= 1;
            } else {
                return false;
            }
            if (reversed == 0) {
                l = i;
            } else {
                r = i;
            }
        }
        String remain = t.substring(l, r + 1);
        if (reversed == 1) {
            StringBuilder sb = new StringBuilder(remain);
            remain = sb.reverse().toString();
        }
        return s.equals(remain);
    }

    public static void main(String[] args) {
        String s1 = "ab";
        String s2 = "abc";

        if (solve(s1, s2)) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
    }
}
