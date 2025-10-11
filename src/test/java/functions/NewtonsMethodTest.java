package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NewtonsMethodTest {
    private static final double EPSILON = 1e-10; // Допустимая погрешность вычислений

    @Test
    void apply() {
        // Test №1
        MathFunction function = new MathFunction() {
            @Override
            public double apply(double x) {
                return x * x * x * x - 5 * x * x + 4; // x⁴ + 5x² + 4, имеет 4 корня: ±2, ±1
            }
        };
        MathFunction solveOne = new NewtonsMethod(function);
        assertEquals(-2, solveOne.apply(-4), EPSILON);
        assertEquals(2, solveOne.apply(4), EPSILON);
        assertEquals(-1, solveOne.apply(-0.8), EPSILON);
        assertEquals(1, solveOne.apply(0.8), EPSILON);

        // Test №2
        MathFunction trigonometryFunction = new MathFunction() {
            @Override
            public double apply(double x) {
                return (Math.sin(x) * x * x); // Корни πk, где k - целое
            }
        };
        MathFunction solveTwo = new NewtonsMethod(trigonometryFunction);
        assertEquals(Math.PI, solveTwo.apply(3.0001), EPSILON);

        // Test №3
        MathFunction expFunction = new MathFunction() {
            @Override
            public double apply(double x) {
                return Math.exp(x) + Math.sin(x);
            }
        };
        MathFunction sqrFunction = new SqrFunction();
        MathFunction composition = new CompositeFunction(expFunction, sqrFunction);

        MathFunction solveThree = new NewtonsMethod(composition);
        assertEquals(-0.5885327438823686, solveThree.apply(-1), EPSILON);
    }
}