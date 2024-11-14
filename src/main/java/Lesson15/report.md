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
        if (array.length <= 1) return;
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSort(array, low, pivotIndex - 1); 
            quickSort(array, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = (low - 1);

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

База индукции (n = 0 или n = 1)

Если массив пустой (n = 0), он уже отсортирован.

Если массив содержит один элемент (n = 1), он также уже отсортирован.

Таким образом, база индукции выполняется.

##### Индуктивное предположение

Предположим, что алгоритм работает корректно для всех массивов длиной меньше n.

##### Индуктивный шаг

Рассмотрим массив длиной n с индексами от low до high.

Выбор опорного элемента: Опорный элемент выбирается как последний элемент массива (array[high]).

Разбиение массива: Метод partition разбивает массив на две части:

Элементы меньше или равные опорному.

Элементы больше опорного.

После выполнения partition, все элементы в диапазоне от low до pivotIndex - 1 будут меньше или равны 
опорному элементу, а все элементы в диапазоне от pivotIndex + 1 до high будут больше опорного.

Рекурсивные вызовы: Рекурсивно вызывается функция quickSort для левой и правой частей массива:

`quickSort(array, low, pivotIndex - 1)`

`quickSort(array, pivotIndex + 1, high)`

По индукционному предположению обе части будут отсортированы после рекурсивных вызовов.

##### Инварианты цикла

Во время выполнения метода partition поддерживается инвариант:

Все элементы в диапазоне от low до i меньше или равны опорному элементу.

Все элементы в диапазоне от i + 2 до high больше опорного элемента.

Этот инвариант сохраняется на каждом шаге выполнения метода.