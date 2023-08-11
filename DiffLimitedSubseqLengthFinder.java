import java.util.Arrays;

public class DiffLimitedSubseqLengthFinder {

    // Constant to represent an increment of one
    static int INCREMENT = 1;

    // Function to find the length of the longest subsequence
    // where the difference between elements is less than or equal to a given value
    // k
    public static int findMaxLength(int[] elements, int maxDifference) {
        int n = elements.length;
        int[] dp = new int[n];

        Arrays.fill(dp, INCREMENT);

        // Compute the length of the longest subsequence for each element
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (elements[i] < elements[j] && Math.abs(elements[i] - elements[j]) <= maxDifference) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        // Find the maximum length in the dp array
        int maxLength = 0;
        for (int length : dp) {
            maxLength = Math.max(maxLength, length);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        int[] elements = { 8, 5, 4, 2, 1, 4, 3, 4, 3, 1, 15 };
        int maxDifference = 3;

        // Find the length of the longest subsequence with given maximum difference
        int length = findMaxLength(elements, maxDifference);
        System.out.println("Length of Longest Subsequence: " + length);
    }
}
