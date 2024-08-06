package MultiThreadings;

import java.util.concurrent.locks.ReentrantLock;

class Increments implements Runnable {

    ReentrantLock lck = new ReentrantLock(true);
    private int count = 0;

//    @Override
//    public void run() { // Race condition
//        for (int i = 0; i < 10; i++) {
//            // Introducing a delay to increase the likelihood of a race condition
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
//            count++;
//        }
//    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            // Introducing a delay to increase the likelihood of a race condition
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            lck.lock();
            count++;
            lck.unlock();
        }
    }

    public int getCount() {
        return count;
    }
}

public class ReentrantLockExample {
    private int counter = 0;
    private final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
//        ReentrantLockExample example = new ReentrantLockExample();
//        example.startThreads();


        Increments inc = new Increments();

        Thread t1 = new Thread(inc);
        Thread t2 = new Thread(inc);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(inc.getCount());

    }

    public void startThreads() {
        Thread thread1 = new Thread(this::incrementCounter);
        Thread thread2 = new Thread(this::incrementCounter);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final counter value: " + counter);
    }

    private void incrementCounter() {
        for (int i = 0; i < 1000; i++) {
            lock.lock();
            try {
                counter++;
            } finally {
                lock.unlock();
            }
        }
    }
}
