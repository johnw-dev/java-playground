package jmwdev;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureExample {

    private static final Logger logger = LogManager.getLogger(CompletableFutureExample.class);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(2);
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> 42, es).thenApplyAsync(i -> i + 1, es).thenApplyAsync(
                i -> {
                    logger.info("result: {} thread: {}", i, Thread.currentThread().getName());
                    return i;
                }, es);
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> "42", es).thenApplyAsync(
                i -> {
                    logger.info("result: {} thread: {}", i, Thread.currentThread().getName());
                    return i;
                }, es);
        CompletableFuture<Void> all = CompletableFuture.allOf(f1, f2);
        Thread.sleep(5000);
        all.join();
        es.shutdown();
    }
}
