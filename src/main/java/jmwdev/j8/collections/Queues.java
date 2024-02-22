package jmwdev.j8.collections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class Queues {

    static Logger logger = LogManager.getLogger(Queues.class);


    static void queueDemo() {
        Queue<Integer> q = new LinkedList<>();
        q.add(1); // Head -> [1]
        q.offer(2); // Head -> [1,2]
        q.add(3); // Head -> [1,2,3]
        q.offer(4); // Head -> [1,2,3,4]

        logger.info("q element: {}",q.element());
        logger.info("q peek: {}",q.peek());
        logger.info("q queue: {}",q);

        logger.info("q peek: {}",q.remove()); // 1, Head -> [2, 3,4]
        logger.info("q peek: {}",q.poll()); // 2, Head -> [3,4]
        logger.info("q queue: {}",q); // Head -> [3,4]
    }

    static void dequeDemo() {
        Deque<Integer> dq = new ArrayDeque<>();
        dq.add(1); // Head -> [1]
        dq.addFirst(2); // Head -> [2,1]
        dq.offerFirst(3); // Head -> [3,2,1]
        dq.addLast(4); // Head -> [3,2,1,4]
        dq.offerLast(5); // Head -> [3,2,1,4,5]

        logger.info("dq element: {}",dq.removeFirst());
        logger.info("dq peek: {}",dq.getFirst());
        logger.info("dq queue: {}",dq);

        logger.info("dq peek: {}", dq.getLast());
        logger.info("dq peek: {}", dq.removeFirst());
        logger.info("dq queue: {}",dq);
    }

    static void naturalOrderPriortyQueueDemo() {
        Queue<String> q = new PriorityQueue<>();
        q.add("V");
        q.add("C");
        q.add("A");
        q.add("B");
        logger.info("natural priority q queue: {}",q);
    }

    static void customOrderPriortyQueueDemo() {
        Queue<String> q = new PriorityQueue<>( Comparator.comparing(String::length));
        q.add("Victor");
        q.add("Charlie");
        q.add("Alpha");
        q.add("Beta");
        logger.info("custom priority q queue: {}",q);
    }

    public static void main(String[] args) {
        queueDemo();
        dequeDemo();
        naturalOrderPriortyQueueDemo();
        customOrderPriortyQueueDemo();
    }
}
