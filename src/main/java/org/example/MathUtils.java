package org.example;

import static org.example.MathUtils.maxAbs;

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

class MaxAbsCalculator {
    public static void main(String[] args) {
        int a = -5;
        int b = 3;
        int result = maxAbs(a, b);
        System.out.println("Максимум модуля: " + result); // Должно вывести 5
    }
}