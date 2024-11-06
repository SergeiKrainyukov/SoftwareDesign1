```java
public class MathUtils {
    public static int abs(int x) {
        return (x < 0) ? -x : x;
    }

    public static int max(int a, int b) {
        return (a > b) ? a : b;
    }

    public static int maxAbs(int a, int b) {
        return max(abs(a), abs(b));
    }
}

public class MaxAbsCalculator {
    public static void main(String[] args) {
        int a = -5;
        int b = 3;
        int result = maxAbs(a, b);
        System.out.println("Максимум модуля: " + result); // Должно вывести 5
    }
}
```

### Доказательство

Для функции maxAbs мы можем записать тройку Хоара следующим образом:

{a,b} maxAbs(a,b) {r=max(∣a∣,∣b∣)}

Предусловие (P): P: a b

**Вызов abs:**

По предположению P, вызов MathUtils.abs(a) возвращает ∣a∣.

Аналогично, вызов MathUtils.abs(b) возвращает ∣b∣.

**Вызов max:**

Функция MathUtils.max() сравнивает значения ∣a∣ и ∣b∣ и возвращает максимальное из них.

**Постусловие (Q):**

Результатом должно быть максимальное значение модуля из двух чисел, что соответствует Q:r=max(∣a∣,∣b∣).