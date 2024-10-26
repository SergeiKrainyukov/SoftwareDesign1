package Lesson4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AverageCalculator {
    public double calculateAverage(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return 0.0;
        }

        double sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum / numbers.length;
    }
}

class AverageCalculatorTest {

    @Test
    public void testCalculateAverage_withPositiveNumbers() {
        AverageCalculator calculator = new AverageCalculator();
        int[] numbers = {1, 2, 3, 4, 5};
        assertEquals(3.0, calculator.calculateAverage(numbers), 0.001);
    }

    @Test
    public void testCalculateAverage_withNegativeNumbers() {
        AverageCalculator calculator = new AverageCalculator();
        int[] numbers = {-1, -2, -3, -4, -5};
        assertEquals(-3.0, calculator.calculateAverage(numbers), 0.001);
    }

    @Test
    public void testCalculateAverage_withMixedNumbers() {
        AverageCalculator calculator = new AverageCalculator();
        int[] numbers = {-1, 0, 1};
        assertEquals(0.0, calculator.calculateAverage(numbers), 0.001);
    }

    @Test
    public void testCalculateAverage_withSingleElement() {
        AverageCalculator calculator = new AverageCalculator();
        int[] numbers = {5};
        assertEquals(5.0, calculator.calculateAverage(numbers), 0.001);
    }
}