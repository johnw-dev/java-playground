package jmwdev.async;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class AsyncStream {

    static Logger logger = LogManager.getLogger("AsyncStream");
    static Long start = System.currentTimeMillis();
    static ExecutorService es = Executors.newFixedThreadPool(5);

    public static void simulateDelay(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) { // NOSONAR
            // swallowing for the sake of simplicity in simulation
            logger.error(e);
        }
    }

    public static void output(String message) {
        logger.info("{} {}", (System.currentTimeMillis() - start), message);
    }

    public static CompletionStage<String> getResource(String id) {
        return CompletableFuture.supplyAsync(() -> id, es).thenApplyAsync(res -> {
            simulateDelay(3000);
            output("resource retrieved: " + res);
            return res;
        }, es);
    }

    public static List<CompletionStage<String>> getResources() {
        List<CompletionStage<String>> retrievedResources = new ArrayList<>();
        int[] ids = IntStream.rangeClosed(1, 10).toArray();
        Arrays.stream(ids).mapToObj(Integer::toString).forEach(id -> retrievedResources.add(getResource(id)));
        output("all resources requested");
        return retrievedResources;
    }

    public static CompletionStage<String> decorateResource(String resource) {
        simulateDelay(500);
        output("decoration retrieved: " + resource);

        return CompletableFuture.supplyAsync(() -> "Complete resource:" + resource, es);
    }

    public static void main(String[] args) {
        List<CompletionStage<String>> decoratedResources =
                getResources().stream()
                        .map(cs -> cs.thenComposeAsync(AsyncStream::decorateResource, es))
                        .toList();
        output("stream defined");

        for (CompletionStage<String> cs : decoratedResources) {
            cs.whenCompleteAsync((result, e) -> {
                        if (e != null) output(e.getMessage());
                    }, es)
                    .thenAcceptAsync(AsyncStream::output, es);
        }

        output("finished");
    }
}
