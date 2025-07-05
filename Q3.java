// Use a singleThreadExecutor, newCachedThreadPool() and newFixedThreadPool() to submit a list of tasks and wait for completion of all tasks.

import java.util.*;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<Callable<String>> tasks = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            final int taskId = i;
            tasks.add(() -> {
                Thread.sleep(500);
                return "Task " + taskId + " executed by " + Thread.currentThread().getName();
            });
        }


        System.out.println("Using SingleThreadExecutor");
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        try {
            List<Future<String>> futures = singleThreadExecutor.invokeAll(tasks);

            for (Future<String> future : futures) {
                try {
                    System.out.println(future.get());
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            singleThreadExecutor.shutdown();
        }

        System.out.println("\nUsing FixedThreadPool (3 threads)");
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        try {
            List<Future<String>> futures = fixedThreadPool.invokeAll(tasks);

            for (Future<String> future : futures) {
                try {
                    System.out.println(future.get());
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            fixedThreadPool.shutdown();
        }


        System.out.println("\nUsing CachedThreadPool");
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        try {
            List<Future<String>> futures = cachedThreadPool.invokeAll(tasks);

            for (Future<String> future : futures) {
                try {
                    System.out.println(future.get());
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            cachedThreadPool.shutdown();
        }
    }
}
