/*

O(N)

Initialize an empty list to store the substrings.
Iterate over the characters of the string.
For each character, check if the current substring is in the HashSet list.
If it is, append the character to the current substring and continue to the next character.
If it’s not, add the current substring to the list and start a new substring with the current character.
After the iteration, add the last substring to the list if it’s not empty.
*/

import java.util.*;

public class SubstringGenerator {
    public static List<String> generateSubstrings(String s) {
        List<String> substrings = new ArrayList<>();
        Set<String> set = new HashSet<>();
        StringBuilder currentSubstring = new StringBuilder();

        for (char c : s.toCharArray()) {
            currentSubstring.append(c);
            String current = currentSubstring.toString();
            if (!set.contains(current)) {
                set.add(current);
                substrings.add(current);
                currentSubstring = new StringBuilder();
            }
        }

        // Add the last substring if it's not empty
        if (currentSubstring.length() > 0) {
            substrings.add(currentSubstring.toString());
        }

        return substrings;
    }

    public static void main(String[] args) {
        System.out.println(generateSubstrings("GOOOOOOGLE")); // ["G", "O", "OO", "OOO", "GL", "E"]
        System.out.println(generateSubstrings("GOOOOOOGLEG")); // ["G", "O", "OO", "OOO", "GL", "E", "G"]
    }
}
