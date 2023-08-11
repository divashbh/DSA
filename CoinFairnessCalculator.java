import java.util.Arrays;

public class CoinFairnessCalculator {

    // Constant representing a single coin
    static int COIN = 1;

    // Function to calculate the minimum coins required for fair distribution
    public static int calculateMinimumCoins(int[] playerScores) {
        int n = playerScores.length;
        int[] coinCounts = new int[n];
        int totalCoins = 0;

        // Initialize the array of coin counts with all 1s
        Arrays.fill(coinCounts, COIN);

        // Traverse from left to right and update coin counts based on increasing scores
        for (int i = 1; i < n; i++) {
            if (playerScores[i] > playerScores[i - 1]) {
                coinCounts[i] = coinCounts[i - 1] + 1;
            }
        }

        // Traverse from right to left and update coin counts based on decreasing scores
        for (int i = n - 2; i >= 0; i--) {
            if (playerScores[i] > playerScores[i + 1] && coinCounts[i] <= coinCounts[i + 1]) {
                coinCounts[i] = coinCounts[i + 1] + 1;
            }
        }

        // Calculate the total number of coins required
        for (int i = 0; i < n; i++) {
            totalCoins += coinCounts[i];
        }

        return totalCoins;
    }

    public static void main(String[] args) {
        int[] playerScores = { 1, 0, 2 };

        // Calculate the minimum coins required for fair distribution
        int minimumCoinsRequired = calculateMinimumCoins(playerScores);

        // Print the result indicating the minimum coins required
        System.out.println("Minimum Coins Required for Fair Distribution: " + minimumCoinsRequired);
    }
}
