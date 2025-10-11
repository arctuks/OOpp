package functions;

import exceptions.InterpolationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IllegalArgumentExceptionInterpolateTest {
    @Test
    void LinkedListInterpolateTest() {
        double[] x = {1.0, 2.0, 3.0};
        double[] y = {10.0, 20.0, 30.0};
        LinkedListTabulatedFunction function1 = new LinkedListTabulatedFunction(x, y);

        assertThrows(IllegalArgumentException.class, () -> {
            function1.interpolate(2.5, 2);
        });
    }

    @Test
    void ArrayInterpolateTest() {
        double[] x = {1.0, 2.0, 3.0};
        double[] y = {10.0, 20.0, 30.0};
        ArrayTabulatedFunction function1 = new ArrayTabulatedFunction(x, y);

        assertThrows(IllegalArgumentException.class, () -> {
            function1.interpolate(2.5, 2);
        });
    }
}