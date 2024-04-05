/*
O(n) Sliding window
SUB-SEQUENCE - so sub-array size 2
*/

public class Main {
    public static void main(String[] args) {
        int[] arr = {6,1,2,8};
        System.out.println(maxMinPlusMax(arr));
    }

    public static int maxMinPlusMax(int[] arr) {
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length - 1; i++) {
            int min = Math.min(arr[i], arr[i + 1]);
            int max = Math.max(arr[i], arr[i + 1]);
            maxSum = Math.max(maxSum, min + max);
        }
        return maxSum;
    }
}

/*
SUB-ARRAY, consider all sub-arrays
O(n^2)
*/

public class Main {
    public static void main(String[] args) {
        int[] arr = {6, 1, 2, 8};
        System.out.println(maxMinPlusMax(arr));
    }

    public static int maxMinPlusMax(int[] arr) {
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int min = arr[i], max = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                min = Math.min(min, arr[j]);
                max = Math.max(max, arr[j]);
                maxSum = Math.max(maxSum, min + max);
            }
        }
        return maxSum;
    }
}
