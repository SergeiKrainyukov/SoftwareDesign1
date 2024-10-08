package Lesson5;

import java.util.List;

public class GradeCalculator {

    public double calculateAverage(List<Double> grades) {
        if (grades == null || grades.isEmpty()) {
            throw new IllegalArgumentException("Список оценок не может быть пустым или равным null.");
        }

        double sum = 0.0;
        for (Double grade : grades) {
            if (grade < 0 || grade > 100) {
                throw new IllegalArgumentException("Оценки должны быть в диапазоне от 0 до 100.");
            }
            sum += grade;
        }

        return sum / grades.size();
    }
}

class GradeCalculatorTest {

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyList() {
        GradeCalculator calculator = new GradeCalculator();
        calculator.calculateAverage(new ArrayList<>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullList() {
        GradeCalculator calculator = new GradeCalculator();
        calculator.calculateAverage(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidGrades() {
        GradeCalculator calculator = new GradeCalculator();
        List<Double> grades = Arrays.asList(90.0, -5.0, 105.0);
        calculator.calculateAverage(grades);
    }

    @Test
    public void testValidGrades() {
        GradeCalculator calculator = new GradeCalculator();
        List<Double> grades = Arrays.asList(80.0, 90.0, 100.0);
        double average = calculator.calculateAverage(grades);

        assertEquals(90.0, average, 0.001);
    }

    @Test
    public void testSameGrades() {
        GradeCalculator calculator = new GradeCalculator();
        List<Double> grades = Arrays.asList(75.0, 75.0, 75.0);
        double average = calculator.calculateAverage(grades);

        assertEquals(75.0, average, 0.001);
    }
}