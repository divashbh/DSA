import java.util.ArrayList;
import java.util.List;

public class GraphReorientation {

    public static int calculateMinimumReorientations(int numNodes, int[][] edges) {
        List<Integer>[] adjacencyList = new ArrayList[numNodes];
        for (int i = 0; i < numNodes; i++) {
            adjacencyList[i] = new ArrayList<>();
        }

        // Build the adjacency list based on the given edges
        for (int[] edge : edges) {
            int fromNode = edge[0];
            int toNode = edge[1];
            adjacencyList[fromNode].add(toNode);
        }

        int[] visited = new int[numNodes];
        int[] reorientationCount = new int[1];
        depthFirstSearch(0, adjacencyList, visited, reorientationCount);

        return reorientationCount[0];
    }

    private static void depthFirstSearch(int currentNode, List<Integer>[] adjacencyList, int[] visited,
            int[] reorientationCount) {
        visited[currentNode] = 1; // Mark the current node as visited

        for (int neighbor : adjacencyList[currentNode]) {
            if (visited[neighbor] == 0) {
                depthFirstSearch(neighbor, adjacencyList, visited, reorientationCount);
            } else if (visited[neighbor] == 1) {
                reorientationCount[0]++; // Increment reorientation count
            }
        }

        visited[currentNode] = 2; // Mark the current node as processed
    }

    public static void main(String[] args) {
        int numNodes = 6;
        int[][] edges = { { 0, 1 }, { 1, 3 }, { 2, 3 }, { 4, 0 }, { 4, 5 } };
        int minReorientations = calculateMinimumReorientations(numNodes, edges);
        System.out.println("Minimum Reorientations: " + minReorientations);
    }
}
