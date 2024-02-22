package jmwdev.j8.concurrency;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorServices {
    static Logger logger = LogManager.getLogger(ForkJoin.class);

    static ExecutorService es = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
//    static ExecutorService es = Executors.newCachedThreadPool(); // Expandable ThreadPool
//    static ExecutorService es = Executors.newSingleThreadExecutor();

    /*
     * blocks execution until one is completed. Other tasks are cancelled
     */
    static void invokeAnySync(List<Callable<String>> callables) throws ExecutionException, InterruptedException {
        logger.info("invokeAny Answer is {}", es.invokeAny(callables));
    }

    static void invokeAll(List<Callable<String>> callables) throws ExecutionException, InterruptedException {
         var futures = es.invokeAll(callables);
         for(Future<String> future: futures) {
             logger.info("invokeAll Answer is {}",future.get()); // always in order
         }
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        Future<String> future = es.submit(() -> "hello world");
        List<Callable<String>> callables = new ArrayList<>();
        callables.add(() -> "A");
        callables.add(() -> "B");
        callables.add(() -> "C");
        callables.add(() -> "D");
        invokeAnySync(callables);
        invokeAll(callables);
        es.shutdown();
    }
}
