package EffectiveJava.item52;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Lambda {
    public static void main(String[] args) {

        new Thread(System.out::println).start();

        ExecutorService es = Executors.newCachedThreadPool();

//        es.submit(System.out::println);
    }
}

/*
// T run();
@FunctionalInterface
public interface Callable<V> {
    V call() throws Exception;
}

// void run();
@FunctionalInterface
public interface Runnable {
    public abstract void run();
}
 */
