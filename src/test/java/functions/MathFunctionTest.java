package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MathFunctionTest {

    @Test
    void andThen() {
        MathFunction sin = new MathFunction() {
            @Override
            public double apply(double x) {
                return Math.sin(x);
            }
        };
        SqrFunction xSqr = new SqrFunction();

        MathFunction f1 = xSqr.andThen(xSqr);
        assertEquals(16, f1.apply(2));

        MathFunction f2 = sin.andThen(xSqr);
        assertEquals(0.5, f2.apply(Math.PI / 4), 1e-5);

    }
}