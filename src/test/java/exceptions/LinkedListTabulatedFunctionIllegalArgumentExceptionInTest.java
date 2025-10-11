package exceptions;

import functions.LinkedListTabulatedFunction;
import functions.SqrFunction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedListTabulatedFunctionIllegalArgumentExceptionInTest {
    double[] xValues = {1, 2, 3, 4, 6};
    double[] yValues = {10, 20, 30, 40, 60};
    LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);

    @Test
    void constructorOfArrayTabulatedFunction() {
        assertThrows(IllegalArgumentException.class, () -> new LinkedListTabulatedFunction( new double[]{1}, new double[]{2}));
        assertThrows(IllegalArgumentException.class, () -> new LinkedListTabulatedFunction(new SqrFunction(), 1, 1, 1));
    }

    @Test
    void getXandY() {
        assertThrows(IllegalArgumentException.class, () -> function.getY(-1));
        assertThrows(IllegalArgumentException.class, () -> function.getX(-1));
    }

    @Test
    void setY() {
        assertThrows(IllegalArgumentException.class, () -> function.setY(-1, 10));
    }

    @Test
    void indexOfX() {
        assertThrows(IllegalArgumentException.class, () -> function.indexOfX(1000));
        assertThrows(IllegalArgumentException.class, () -> function.indexOfX(-10));
    }

    @Test
    void floorIndexOfX() {
        assertThrows(IllegalArgumentException.class, () -> function.floorIndexOfX(0));
    }

    @Test
    void remove() {
        assertThrows(IllegalArgumentException.class, () -> function.remove(-1));
        assertThrows(IllegalArgumentException.class, () -> function.remove(7));
    }
}
