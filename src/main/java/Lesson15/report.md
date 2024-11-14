```java
import java.util.Arrays;

public class QuickSort {

    // Предусловия:
    // Входной массив содержит хотя бы 2 элемента
    // low >= 0
    // high >= low
    // high < array.length

    // Постусловия:
    // Массив array отсортирован по возрастанию.
    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
```

### Доказательство корректности

##### Предусловие

Для функции `quickSort(int[] array, int low, int high):`

• P: Массив array является массивом целых чисел. 
Индексы low и high являются допустимыми индексами массива array, и

0 <= low <= high < array.length.

##### Постусловие (Postcondition)

• Q: Подмассив array[low..high] отсортирован по возрастанию.

##### Инвариант цикла

Для функции partition(int[] array, int low, int high):

• Инициализация: В начале выполнения цикла переменная i равна low - 1, а подмассив array[low..i] пуст.

• Инвариант: В любой момент времени в цикле:

• Все элементы в подмассиве array[low..i] меньше или равны опорному элементу (pivot).

• Все элементы в подмассиве array[i+1..j-1] больше опорного элемента.

• Завершение: После завершения цикла переменная i указывает на последний элемент, который меньше или равен опорному элементу. 
Элементы слева от i+1 меньше или равны опорному элементу, а справа — больше.

##### Корректность рекурсивных вызовов

Для каждого рекурсивного вызова quickSort(array, low, pivotIndex - 1) и quickSort(array, pivotIndex + 1, high):

• Предусловие: Подмассивы находятся в границах массива и могут быть отсортированы.

• Постусловие: Каждый из подмассивов будет отсортирован после завершения рекурсивного вызова.

##### Доказательство завершения

Алгоритм завершается, так как каждый рекурсивный вызов уменьшает размер подмассива. 
Базовый случай достигается, когда low >= high, что останавливает дальнейшую рекурсию.

Таким образом, мы можем заключить, что алгоритм QuickSort корректно сортирует массив.