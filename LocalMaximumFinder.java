public class LocalMaximumFinder {

    // Function to find the index of a local maximum in an array
    public static int findIndexOfLocalMaximum(int[] nums) {
        int currentIndex = 0; // Start from the first element
        int neighborIndex = 0; // Initialize the neighbor index

        while (true) {
            neighborIndex = currentIndex; // Initialize the neighbor as the current index

            // Check if the left neighbor is greater
            if (currentIndex > 0 && nums[currentIndex - 1] > nums[currentIndex]) {
                neighborIndex = currentIndex - 1; // Update neighbor index
            }
            // Check if the right neighbor is greater
            if (currentIndex < nums.length - 1 && nums[currentIndex + 1] > nums[neighborIndex]) {
                neighborIndex = currentIndex + 1; // Update neighbor index
            }

            // If the neighbor index hasn't changed, a local maximum is found
            if (neighborIndex == currentIndex) {
                break;
            }
            currentIndex = neighborIndex; // Move to the next position
        }
        return currentIndex; // Return the index of the local maximum
    }

    public static void main(String[] args) {
        int[] nums = { 1, 3, 5, 4, 2, 6 };

        // Find the index of the local maximum using the peak-finding algorithm
        int localMaximumIndex = findIndexOfLocalMaximum(nums);

        // Print the index and value of the local maximum
        System.out.println("Index of Local Maximum: " + localMaximumIndex);
        System.out.println("Value of Local Maximum: " + nums[localMaximumIndex]);
    }
}
