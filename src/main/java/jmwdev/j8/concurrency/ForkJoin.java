package jmwdev.j8.concurrency;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoin extends RecursiveTask<List<String>> {
    private List<String> inputList;
    static Logger logger = LogManager.getLogger(ForkJoin.class);

    public ForkJoin(List<String> inputList) {
        this.inputList = inputList;
    }

    public static void main(String[] args) {
        var names = Arrays.asList("Bob", "James", "Jill", "jack");
        ForkJoinPool pool = new ForkJoinPool();
        var stopWatch = new StopWatch();
        stopWatch.start();
        ForkJoin task = new ForkJoin(names);
        var results = pool.invoke(task);
        stopWatch.stop();
        logger.info("{} - {}", stopWatch.getTime(), results);
        pool.shutdown();
    }



    private static String addNameLengthTransform(String name) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return name.length()+" - "+name;
    }

    @Override
    protected List<String> compute() {
        if(inputList.size() <=1) {
            List<String> resultList = new ArrayList<>();
            inputList.forEach(name -> resultList.add(addNameLengthTransform(name)));
            return resultList;
        }
        int midpoint = inputList.size()/2;
        var subTask = new ForkJoin(inputList.subList(0,midpoint)).fork();
        inputList = inputList.subList(midpoint, inputList.size());
        var b = compute();
        List<String> a;
        try {
            a = subTask.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        a.addAll(b);
        return a;
    }
}
