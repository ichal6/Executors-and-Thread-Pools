import java.util.concurrent.*;
public class FutureExample {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Callable<Integer> callable = () -> {
            Thread.sleep(5000);
            return 42;
        };

        Future<Integer> future = executor.submit(callable);
        System.out.println("Other tasks running...");
        int result = future.get();
        System.out.println("Computation result: " + result);
        executor.shutdown();

        executor = Executors.newSingleThreadExecutor();

        Future<Integer> futureTask = executor.submit(callable);

        // Try to cancel the task after 2 seconds
        boolean cancelled = futureTask.cancel(true);
        if (cancelled) {
            System.out.println("Task cancelled successfully.");
        } else {
            System.out.println("Task could not be cancelled.");
        }

        executor.shutdown();
    }
}