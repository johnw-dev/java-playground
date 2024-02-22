package jmwdev.j8.concurrency;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.*;

public class ScheduledExecutors {
    static ScheduledExecutorService es = Executors.newScheduledThreadPool(10);
    static Logger logger = LogManager.getLogger(ScheduledExecutors.class);

    static void schedule() throws ExecutionException, InterruptedException {
        logger.info("start");
        Future<String> future = es.schedule(() -> "A", 2, TimeUnit.SECONDS);
        logger.info(future.get());
        logger.info("finish");
    }

    static void scheduleFixedDelay() {
        logger.info("start");
        long initDelay=2000, waitPeriod=300;
        es.scheduleWithFixedDelay(() -> logger.info("Thread id: {}", Thread.currentThread().getId()), initDelay, waitPeriod, TimeUnit.MILLISECONDS);
        logger.info("finish");
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        schedule();
        scheduleFixedDelay();
//        es.shutdown();
    }

}
