package jmwdev.j8.concurrency;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Locks {

    static Logger logger = LogManager.getLogger(Locks.class);

    private static final Lock lock = new ReentrantLock();
    private static int count=0;

    private static void unsafe(String context) {
        int c = count;
        logger.info("Before: {}} - {} - {}", c,Thread.currentThread().getId(), context);
        count = c + 1;
        logger.info("After: {}} - {} - {}", c,Thread.currentThread().getId(), context);
    }

    private static void blockingLock() {
        try {
            lock.lock(); // blocking call
            //critical section
            unsafe("blocking");
        } finally {
            lock.unlock(); // return the lock
        }
    }

    private static void nonblockingLock() {
        if(lock.tryLock()) {
            try {
                // critical section
                unsafe("nonblocking");
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("Failed to acquire lock - " + Thread.currentThread().getId());
        }
    }

    public static void runNonBlocking() throws InterruptedException {
        for (int i = 0; i <= 10; i++) {
            new Thread(Locks::nonblockingLock).start(); // All fail except one
        }
        Thread.sleep(2000);
        logger.info("non-blocking complete {}", count);
    }

    public static void runBlocking() throws InterruptedException {
        for (int i = 0; i <= 10; i++) {
            new Thread(Locks::blockingLock).start(); // All execute in
        }
        Thread.sleep(2000);
        logger.info("blocking complete {}", count);
    }

    public static void main(String[] args) throws InterruptedException {
        runNonBlocking();
        runBlocking();
        logger.info("final {}", count);
    }
}
