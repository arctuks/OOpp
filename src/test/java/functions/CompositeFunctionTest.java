package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompositeFunctionTest {
    private static final double EPSILON = 1e-5;

    @Test
    void apply() {
        MathFunction sqr = new SqrFunction();
        MathFunction sin = new IdentityFunction() {
            @Override
            public double apply(double x) {
                return Math.sin(x);
            }
        };
        MathFunction exp = new IdentityFunction() {
            @Override
            public double apply(double x) {
                return Math.exp(x);
            }
        };
        MathFunction log = new IdentityFunction() {
            @Override
            public double apply(double x) {
                return Math.log(x);
            }
        };

        double x = 3;

        MathFunction f1 = new CompositeFunction(sin, sqr); // sin²x
        assertEquals(0.01991486, f1.apply(x), EPSILON);

        MathFunction f2 = new CompositeFunction(sqr, f1); // sin²(x²)
        assertEquals(0.169842, f2.apply(x), EPSILON);

        MathFunction f3 = new CompositeFunction(f2, log); // ln(sin²(x²))
        assertEquals(-1.772889, f3.apply(x), EPSILON);

        MathFunction f4 = new CompositeFunction(f3, exp); // e^(ln(sin²(x²)))
        assertEquals(0.169842, f4.apply(x), EPSILON);

        MathFunction f5 = new CompositeFunction(log, log);
        MathFunction f6 = new CompositeFunction(f5, log);
        MathFunction f7 = new CompositeFunction(f6, sin);
        MathFunction f8 = new CompositeFunction(f7, sqr); // sin(ln(ln(ln(x))))²
        assertEquals(0.492243, f8.apply(x), EPSILON);
    }
}