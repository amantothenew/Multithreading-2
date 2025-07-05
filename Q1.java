// WAP to show usage of Callable and demonstrate how it is different from Runnable

import java.util.concurrent.*;

class MyRunnable implements Runnable {
    public void run() {
        System.out.println("This is Using Runnable");
    }
}

class MyCallable implements Callable<String> {
    public String call() throws Exception {
        return "This is using callable";
    }
}

public class Main {
    public static void main(String[] args) {
        MyRunnable runnable = new MyRunnable();
        Thread thread = new Thread(runnable);
        thread.start();

        ExecutorService service = Executors.newSingleThreadExecutor();
        MyCallable callable = new MyCallable();


        Future<String> future = service.submit(callable);

        try {
            String result = future.get();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        service.shutdown();
    }
}
