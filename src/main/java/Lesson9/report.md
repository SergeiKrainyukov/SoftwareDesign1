## Задание 9
### Улучшенное решение

Используем класс AtomicInteger, который предоставляет методы, безопасные для потоков, для выполнения атомарных операций, 
таких как инкремент.

```java
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadExample {
    private static AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) {
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                counter.incrementAndGet();
            }
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Counter: " + counter.get());
    }
}
```