import java.util.Arrays;

public class ShortestPaths {

    // Inner class to represent edges in the graph
    static class Edge {
        int source;
        int destination;
        int weight;

        Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    // Function to find the shortest paths from a source vertex using Bellman-Ford algorithm
    public static int[] findShortestPaths(int[][] graph, int source) {
        int numVertices = graph.length;
        
        // Initialize an array to store the shortest distances from the source vertex
        int[] distance = new int[numVertices];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        // Relax edges repeatedly to find shortest paths
        for (int i = 0; i < numVertices - 1; i++) {
            for (Edge edge : getEdges(graph)) {
                int u = edge.source;
                int v = edge.destination;
                int weight = edge.weight;
                if (distance[u] != Integer.MAX_VALUE && distance[u] + weight < distance[v]) {
                    // Update distance[v] if a shorter path is found from source to v
                    distance[v] = distance[u] + weight;
                }
            }
        }

        // Check for negative cycles
        for (Edge edge : getEdges(graph)) {
            int u = edge.source;
            int v = edge.destination;
            int weight = edge.weight;
            if (distance[u] != Integer.MAX_VALUE && distance[u] + weight < distance[v]) {
                // If distance[u] + weight < distance[v], a negative cycle is detected
                throw new RuntimeException("Graph contains negative cycle.");
            }
        }

        return distance; // Return the array of shortest distances
    }

    // Utility function to convert the graph matrix into an array of edges
    private static Edge[] getEdges(int[][] graph) {
        int numVertices = graph.length;
        int edgeCount = 0;
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (graph[i][j] != 0) {
                    edgeCount++;
                }
            }
        }

        // Create an array to store the edges and populate it
        Edge[] edges = new Edge[edgeCount];
        int idx = 0;
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (graph[i][j] != 0) {
                    edges[idx++] = new Edge(i, j, graph[i][j]);
                }
            }
        }
        return edges;
    }

    public static void main(String[] args) {
        // Define the weighted adjacency matrix representing the graph
        int[][] graph = {
            {0, 6, 0, 0, 0, 0, 0, 0, 0},
            {6, 0, 5, -4, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, -1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 8, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, -2, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 9, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 2},
            {0, 0, 0, 0, 0, 0, 0, 0, 4},
            {0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        // Calculate shortest paths from source vertex 0
        int[] shortestPaths = findShortestPaths(graph, 0);
        System.out.println(Arrays.toString(shortestPaths));
    }
}
