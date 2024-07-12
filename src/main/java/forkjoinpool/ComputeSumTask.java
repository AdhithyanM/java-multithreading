package forkjoinpool;

import java.util.concurrent.RecursiveTask;

// This class represents a task that can be performed by fork join pool executor
// It should either extend the RecursiveTask<V> class or RecursiveAction class
// RecursiveTask<V> - use this class for extending when the task, subtask returns some value
// RecursiveAction - use this class for extending when task, subtask doesn't return any value.
public class ComputeSumTask extends RecursiveTask<Integer> {

    int start;
    int end;

    public ComputeSumTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        // base case for when further forks shouldn't happen
        // It represents the smallest subtask a thread can work on.
        if(end - start <= 4) {
            int totalSum = 0;
            for(int i = start; i <= end; i++) {
                totalSum += i;
            }
            return totalSum;
        }
        // Logic of forking the given task into smaller subtask
        // Divide and Conquer
        else {
            int mid = start + (end - start) / 2;

            ComputeSumTask leftTask = new ComputeSumTask(start, mid);
            ComputeSumTask rightTask = new ComputeSumTask(mid+1, end);

            // Fork the subtasks for parallel execution
            // Forking will put the task in work stealing queues
            leftTask.fork();
            rightTask.fork();

            // Combine the results of subtasks
            int leftResult = leftTask.join();  // wait and get the result
            int rightResult = rightTask.join();

            return leftResult + rightResult;
        }
    }
}
