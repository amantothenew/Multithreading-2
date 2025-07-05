// WAP to return a random integert value from a thread execution using Future.

import java.util.Random;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Callable<Integer> task = () -> {
            Random random = new Random();
            int number = random.nextInt(100);
            System.out.println("Generated number inside thread: " + number);
            return number;
        };

        Future<Integer> future = executor.submit(task);

        try {
            int result = future.get();
            System.out.println("Result received from thread: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdown();
    }
}
