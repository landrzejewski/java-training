package pl.training.module08.creating_threads;

public class PrintTimeThread extends Thread {

    @Override
    public void run() {
        while (true) {
            System.out.printf("Current time: %d ns (current thread: %s)\n", System.nanoTime(), Thread.currentThread().getName());
        }
    }

}
