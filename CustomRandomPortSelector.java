import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class CustomRandomPortSelector {

    private int totalRange; // Total range of available ports
    private Set<Integer> blacklistedPorts; // Set of blacklisted ports
    private Random randomGenerator; // Random number generator

    public CustomRandomPortSelector(int totalRange, int[] blacklistedPorts) {
        this.totalRange = totalRange;
        this.blacklistedPorts = new HashSet<>();
        for (int port : blacklistedPorts) {
            this.blacklistedPorts.add(port);
        }
        this.randomGenerator = new Random();
    }

    public int getRandomPort() {
        int availablePorts = totalRange - blacklistedPorts.size();
        int randomPort = randomGenerator.nextInt(availablePorts);
        int count = 0;

        // Iterate through all possible ports to find the randomly selected one
        for (int port = 0; port < totalRange; port++) {
            if (!blacklistedPorts.contains(port)) {
                if (count == randomPort) {
                    return port;
                }
                count++;
            }
        }
        return -1; // Return -1 if no available port is found
    }

    public static void main(String[] args) {
        int[] blacklistedPorts = { 2, 3, 5 };
        CustomRandomPortSelector portSelector = new CustomRandomPortSelector(7, blacklistedPorts);

        // Generate and print random ports
        System.out.println("Random Port: " + portSelector.getRandomPort());
        System.out.println("Random Port: " + portSelector.getRandomPort());
        System.out.println("Random Port: " + portSelector.getRandomPort());
        System.out.println("Random Port: " + portSelector.getRandomPort());
        System.out.println("Random Port: " + portSelector.getRandomPort());
    }
}
