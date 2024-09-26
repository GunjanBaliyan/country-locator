package com.example;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Tests the performance of the CountryLocator by making multiple requests in parallel.
 */

public class PerformanceTest {
    private static final int REQUESTS_PER_SECOND = 100;
    private static final int TEST_DURATION_SECONDS = 10;

    
    
    
    /**
     * Runs performance testing for the CountryLocator class to measure average execution time
     * of the getCountryCode method under simulated load.
     *
     * The method simulates multiple concurrent requests per second and calculates the average
     * execution time over a specified test duration.
     *
     * @param args Command-line arguments (not used in this implementation).
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */
    
    
    
    public static void main(String[] args) throws InterruptedException {
        CountryLocator locator = new CountryLocator();
        ExecutorService executor = Executors.newFixedThreadPool(REQUESTS_PER_SECOND);
        long totalExecutionTime = 0;

        for (int i = 0; i < TEST_DURATION_SECONDS; i++) {
            long start = System.currentTimeMillis();

            List<Future<Void>> futures = IntStream.range(0, REQUESTS_PER_SECOND)
                    .mapToObj(n -> CompletableFuture.runAsync(() -> {
                        locator.getCountryCode(37.7749, -122.4194);
                    }, executor))
                    .collect(Collectors.toList());

            // Wait for all futures to complete
            futures.forEach(future -> {
                try {
                    future.get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            long end = System.currentTimeMillis();
            totalExecutionTime += (end - start);
            Thread.sleep(1000); // Sleep for a second to maintain 100 requests per second
        }

        executor.shutdown();
        double averageExecutionTime = (double) totalExecutionTime / (REQUESTS_PER_SECOND * TEST_DURATION_SECONDS);
        System.out.println("Average execution time: " + averageExecutionTime + " ms");
    }
}
