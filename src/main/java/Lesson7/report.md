### Задание 7

1. Синхронизированные методы

В банковском приложении, где несколько потоков могут одновременно пытаться изменить баланс
   одного и того же счета, синхронизированные методы помогают избежать состояния гонки.
  ```java
public class BankAccount {
    private int balance = 0;

    public synchronized void deposit(int amount) {
        balance += amount;
    }

    public synchronized void withdraw(int amount) {
        if (balance >= amount) {
            balance -= amount;
        }
    }

    public synchronized int getBalance() {
        return balance;
    }
}
```
В этом примере методы deposit, withdraw и getBalance защищены от одновременного доступа,
что предотвращает повреждение данных.

2. Синхронизированные блоки
   
В многопоточном веб-приложении, где разные потоки обрабатывают запросы к общему ресурсу (например, к кэшу), синхронизированные блоки позволяют более гибко управлять доступом.
   ````java
public class Cache {
   private final Map<String, Object> cache = new HashMap<>();

   public void put(String key, Object value) {
      synchronized (cache) {
         cache.put(key, value);
      }
   }

   public Object get(String key) {
      synchronized (cache) {
         return cache.get(key);
      }
   }
}
````
Здесь блокировка применяется только к критическим секциям, что повышает производительность.

3. Использование класса Lock
   
В приложении для обработки заказов, где необходимо гарантировать, что только один поток может
обрабатывать определенный заказ в одно и то же время, используется ReentrantLock.
```java
public class OrderProcessor {
   private final Lock lock = new ReentrantLock();

   public void processOrder(Order order) {
      lock.lock();
      try {
         // Логика обработки заказа
      } finally {
         lock.unlock();
      }
   }
}
```   
Этот подход позволяет избежать взаимоблокировок и предоставляет больше контроля над блокировками.

4. CountDownLatch
   
В распределенной системе, где несколько сервисов должны завершить свою работу перед выполнением следующего шага, используется CountDownLatch.
  ```java
public class TaskCoordinator {
   private static final int SERVICE_COUNT = 3;
   private static CountDownLatch latch = new CountDownLatch(SERVICE_COUNT);

   public void serviceCompleted() {
      latch.countDown();
   }

   public void awaitCompletion() throws InterruptedException {
      latch.await(); // Ждем завершения всех сервисов
      // Выполняем следующий шаг
   }
}
```  

Это позволяет координировать выполнение между несколькими потоками.

5. Semaphore

В приложении для управления доступом к ограниченным ресурсам (например, к пулам соединений с базой данных)
используется семафор.
   ```java
public class ConnectionPool {
   private final Semaphore semaphore = new Semaphore(5); // Максимум 5 соединений

   public void getConnection() throws InterruptedException {
      semaphore.acquire(); // Ожидание доступного соединения
      try {
         // Логика работы с соединением
      } finally {
         semaphore.release(); // Освобождение соединения
      }
   }
}
```

Семафор управляет количеством одновременно активных соединений, предотвращая перегрузку системы.
Эти примеры демонстрируют применение различных механизмов синхронизации в реальных проектах на Java
для обеспечения потокобезопасности и предотвращения конфликтов при работе с общими ресурсами.