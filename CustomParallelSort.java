import java.util.Arrays;

public class CustomParallelSort {

    public static void main(String[] args) {
        int[] data = { 5, 2, 9, 1, 5, 6 };
        customParallelSort(data);
        System.out.println(Arrays.toString(data));
    }

    public static void customParallelSort(int[] data) {
        customParallelSort(data, 0, data.length - 1);
    }

    private static void customParallelSort(int[] data, int low, int high) {
        // If the array size is small, perform a simple merge sort
        if (low < high) {
            if (high - low <= 1000) {
                simpleMergeSort(data, low, high);
            } else {
                int mid = (low + high) / 2;

                // Create threads for sorting left and right segments in parallel
                Thread leftSort = new Thread(() -> customParallelSort(data, low, mid));
                Thread rightSort = new Thread(() -> customParallelSort(data, mid + 1, high));
                leftSort.start();
                rightSort.start();

                // Wait for both threads to finish before merging
                try {
                    leftSort.join();
                    rightSort.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Merge the sorted segments
                merge(data, low, mid, high);
            }
        }
    }

    private static void simpleMergeSort(int[] data, int low, int high) {
        // Perform a simple merge sort on the given segment of the array
        if (low < high) {
            int mid = (low + high) / 2;
            simpleMergeSort(data, low, mid);
            simpleMergeSort(data, mid + 1, high);
            merge(data, low, mid, high);
        }
    }

    private static void merge(int[] data, int low, int mid, int high) {
        // Merge two sorted segments of the array
        int[] temp = new int[high - low + 1];
        int i = low;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= high) {
            if (data[i] <= data[j]) {
                temp[k++] = data[i++];
            } else {
                temp[k++] = data[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = data[i++];
        }

        while (j <= high) {
            temp[k++] = data[j++];
        }

        // Copy the merged segment back to the original array
        System.arraycopy(temp, 0, data, low, temp.length);
    }
}
