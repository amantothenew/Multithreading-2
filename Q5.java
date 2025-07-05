// WAP to showcase the difference between shutdown() and shutdownNow().

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {

        ExecutorService executor1 = Executors.newFixedThreadPool(2);
        ExecutorService executor2 = Executors.newFixedThreadPool(2);

        System.out.println("Using shutdown()");
        for (int i = 1; i <= 5; i++) {
            int taskId = i;
            executor1.submit(() -> {
                System.out.println("Task " + taskId + " is starting (shutdown)");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Task " + taskId + " was interrupted!");
                }
                System.out.println("Task " + taskId + " is completed (shutdown)");
            });
        }

        executor1.shutdown();

        System.out.println("\nUsing shutdownNow()");
        for (int i = 1; i <= 5; i++) {
            int taskId = i;
            executor2.submit(() -> {
                System.out.println("Task " + taskId + " is starting (shutdownNow)");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println("Task " + taskId + " was interrupted! (shutdownNow)");
                }
                System.out.println("Task " + taskId + " is completed (shutdownNow)");
            });
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        java.util.List<Runnable> notStartedTasks = executor2.shutdownNow();
        System.out.println("shutdownNow(): Not started tasks: " + notStartedTasks.size());
    }
}

