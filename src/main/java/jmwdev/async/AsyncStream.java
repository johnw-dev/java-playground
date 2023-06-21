package jmwdev.async;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AsyncStream {
    static Long start = System.currentTimeMillis();

    static ExecutorService es = Executors.newFixedThreadPool(5);

    public static void simulateDelay(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void output(String message) {
        System.out.printf("%d %s  -  %s%n", (System.currentTimeMillis() - start), Thread.currentThread().getName(), message);
    }

    public static CompletionStage<String> getResource(String id) {
        return CompletableFuture.supplyAsync(() -> id, es).thenApplyAsync((res) -> {
            simulateDelay(3000);
            output("resource retrieved: " + res);
            return res;
        }, es);
    }

    public static List<CompletionStage<String>> getResources() throws InterruptedException {
        List<CompletionStage<String>> retrievedResources = new ArrayList<>();
        int[] ids = IntStream.rangeClosed(1, 10).toArray();
        Arrays.stream(ids).mapToObj((i) -> Integer.toString(i)).forEach((id) -> retrievedResources.add(getResource(id)));
        output("all resources requested");
        return retrievedResources;
    }

    public static CompletionStage<String> decorateResource(String resource) {
        simulateDelay(500);
        output("decoration retrieved: "+resource);

        return CompletableFuture.supplyAsync(() -> "Complete resource:" + resource, es);
    }

    public static void main(String[] args) throws InterruptedException {
        List<CompletionStage<String>> decoratedResources =
                getResources().stream()
                        .map((cs) -> cs.thenComposeAsync((resource) -> {
                            return decorateResource(resource);
                        }, es))
                        .collect(Collectors.toList());
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
