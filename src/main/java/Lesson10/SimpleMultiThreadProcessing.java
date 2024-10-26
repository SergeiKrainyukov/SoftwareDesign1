package Lesson10;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleMultiThreadProcessing {
    private static final int SIZE = 1000000;
    private static final int THREADS = 4;
    private static final int[] data = new int[SIZE];
    private static volatile int sum = 0;

    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < SIZE; i++) {
            data[i] = random.nextInt(100);
        }

        // Создание пула потоков
        ExecutorService executorService = Executors.newFixedThreadPool(THREADS);
        int chunkSize = SIZE / THREADS;

        for (int i = 0; i < THREADS; i++) {
            final int start = i * chunkSize;
            final int end = (i + 1) * chunkSize;

            executorService.execute(() -> {
                synchronized (SimpleMultiThreadProcessing.class) {
                    sum += Arrays.stream(data, start, end).sum();
                }
            });
        }

        // Завершение работы пула потоков
        executorService.shutdown();
        while (!executorService.isTerminated()) {
            continue;
        }

        System.out.println("Sum of all elements: " + sum);
    }
}