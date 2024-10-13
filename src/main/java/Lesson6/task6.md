### Ошибка "Состояние гонки"

В примере кода возникает состояние гонки из-за того, что несколько потоков 
одновременно обращаются к общему ресурсу — переменной counter. 
Поскольку инкремент (counter++) не является атомарной операцией, 
то есть он состоит из нескольких шагов (чтение значения, увеличение на 1, запись нового значения), 
в результате может произойти ситуация, когда два или более потока читают одно и то же значение 
counter одновременно и записывают его обратно, тем самым теряя изменения.

### Правильный вариант с использованием синхронизации
Чтобы избежать состояния гонки, можно использовать синхронизацию. 
Для этого добавим ключевое слово synchronized для метода или блока кода, 
который изменяет общий ресурс.

```java
public class RaceConditionExample {

    private static int counter = 0;

    public static synchronized void incrementCounter() {
        counter++;
    }

    public static void main(String[] args) {
        int numberOfThreads = 10;
        Thread[] threads = new Thread[numberOfThreads];

        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    incrementCounter();
                }
            });
            threads[i].start();
        }

        for (int i = 0; i < numberOfThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Final counter value: " + counter);
    }
}
```

### Ошибка "Deadlock" (взаимная блокировка)
В примере кода возникает взаимная блокировка (deadlock) из-за того, 
что два потока пытаются захватить два разных ресурса в противоположном порядке. 
Это приводит к ситуации, когда каждый поток удерживает один ресурс и ожидает другой, 
что приводит к бесконечному ожиданию.

Порядок захвата ресурсов:
Поток 1 захватывает lock1 и затем пытается захватить lock2.
Поток 2 захватывает lock2 и затем пытается захватить lock1.

Ожидание:
Поток 1 ждет освобождения lock2, который удерживается Потоком 2.
Поток 2 ждет освобождения lock1, который удерживается Потоком 1.

В результате оба потока находятся в состоянии ожидания друг друга, и ни один из них не может продолжить выполнение.

### Правильный вариант для предотвращения взаимной блокировки


```java
public class DeadlockExample {

    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            // Сначала захватываем lock1, затем lock2
            synchronized (lock1) {
                System.out.println("Thread 1 acquired lock1");

                try { Thread.sleep(50); } 
                catch (InterruptedException e) { e.printStackTrace(); }

                synchronized (lock2) {
                    System.out.println("Thread 1 acquired lock2");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            // Сначала захватываем lock1, затем lock2
            synchronized (lock1) {
                System.out.println("Thread 2 acquired lock1");

                try { Thread.sleep(50); } 
                catch (InterruptedException e) { e.printStackTrace(); }

                synchronized (lock2) {
                    System.out.println("Thread 2 acquired lock2");
                }
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Finished");
    }
}
```

Оба потока теперь пытаются сначала захватить lock1, а затем lock2. 
Это устраняет возможность взаимной блокировки, 
так как ни один поток не сможет заблокировать другой.