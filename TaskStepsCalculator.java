import java.util.*;

public class TaskStepsCalculator {

    // Function to calculate the minimum number of steps to complete tasks
    public int calculateMinSteps(int numTasks, int[][] taskDependencies) {
        List<Integer>[] dependencyGraph = new ArrayList[numTasks + 1];

        // Initialize the dependency graph
        for (int i = 1; i <= numTasks; i++) {
            dependencyGraph[i] = new ArrayList<>();
        }

        // Populate the dependency graph with task dependencies
        for (int[] dependency : taskDependencies) {
            int prerequisiteTask = dependency[0];
            int dependentTask = dependency[1];
            dependencyGraph[dependentTask].add(prerequisiteTask);
        }

        // Create an array to memoize the steps required for each task
        int[] memoSteps = new int[numTasks + 1];

        int minSteps = 0;
        for (int i = 1; i <= numTasks; i++) {
            if (memoSteps[i] == 0) {
                minSteps = Math.max(minSteps, dfs(i, dependencyGraph, memoSteps));
            }
        }
        return minSteps;
    }

    // Depth First Search to calculate steps required for a task
    private int dfs(int task, List<Integer>[] dependencyGraph, int[] memoSteps) {
        if (memoSteps[task] > 0) {
            return memoSteps[task];
        }

        int maxSteps = 0;
        for (int prerequisite : dependencyGraph[task]) {
            maxSteps = Math.max(maxSteps, dfs(prerequisite, dependencyGraph, memoSteps));
        }

        memoSteps[task] = maxSteps + 1;
        return memoSteps[task];
    }

    public static void main(String[] args) {
        int numTasks = 3;
        int[][] taskDependencies = { { 1, 3 }, { 2, 3 } };
        TaskStepsCalculator solver = new TaskStepsCalculator();
        int result = solver.calculateMinSteps(numTasks, taskDependencies);
        System.out.println("Minimum number of steps to complete tasks: " + result);
    }
}
