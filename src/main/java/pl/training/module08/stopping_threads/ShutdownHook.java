package pl.training.module08.stopping_threads;

public class ShutdownHook implements Runnable {

    @Override
    public void run() {
        System.out.println("Performing tasks before shutdown...");
    }

}
