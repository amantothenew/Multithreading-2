// Improve the code written in Basics of Multi Threading Part 1 exercise question 4 to handle the deadlock using reentract lock.

import java.util.concurrent.locks.ReentrantLock;

public class Main {
    static ReentrantLock lock1 = new ReentrantLock();
    static ReentrantLock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        Thread t1 = new Thread(new FirstTask());
        Thread t2 = new Thread(new SecondTask());

        t1.start();
        t2.start();
    }
}


class FirstTask implements Runnable {
    public void run() {
        Main.lock1.lock();
        System.out.println("Thread 1: locked resource 1");

        try {
            Thread.sleep(100);

            Main.lock2.lock();
            System.out.println("Thread 1: locked resource 2");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            Main.lock2.unlock();
            Main.lock1.unlock();
        }
    }
}

class SecondTask implements Runnable {
    public void run() {
        Main.lock1.lock();
        System.out.println("Thread 2: locked resource 1");

        try {
            Thread.sleep(100);
            Main.lock2.lock();
            System.out.println("Thread 2: locked resource 2");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            Main.lock2.unlock();
            Main.lock1.unlock();
        }
    }
}
