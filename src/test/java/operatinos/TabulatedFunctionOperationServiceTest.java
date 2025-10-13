package operatinos;

import functions.ArrayTabulatedFunction;
import functions.LinkedListTabulatedFunction;
import functions.TabulatedFunction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TabulatedFunctionOperationServiceTest {
    double[] xValues = {0.0, 1.0, 2.0, 3.5};
    double[] yValues1 = {1.0, 3.0, 5.0, 8.0};
    double[] yValues2 = {2.0, 2.0, 3.0, 3.0};
    TabulatedFunctionOperationService service = new TabulatedFunctionOperationService();

    @Test
    void MultiplyWithDifferentTypes() {
        ArrayTabulatedFunction function1 = new ArrayTabulatedFunction(xValues, yValues1);
        LinkedListTabulatedFunction function2 = new LinkedListTabulatedFunction(xValues, yValues2);
        TabulatedFunction result = service.multiply(function1, function2);


        for (int i = 0; i<xValues.length; i++) {
            assertEquals(yValues1[i] * yValues2[i], result.getY(i));
        }
    }

    @Test
    void multiplyWithArrays() {
        ArrayTabulatedFunction function1 = new ArrayTabulatedFunction(xValues, yValues1);
        ArrayTabulatedFunction function2 = new ArrayTabulatedFunction(xValues, yValues2);
        TabulatedFunction result = service.multiply(function1, function2);


        for (int i = 0; i<xValues.length; i++) {
            assertEquals(yValues1[i] * yValues2[i], result.getY(i));
        }
    }

    @Test
    void multiplyWithLists() {
        LinkedListTabulatedFunction function1 = new LinkedListTabulatedFunction(xValues, yValues1);
        LinkedListTabulatedFunction function2 = new LinkedListTabulatedFunction(xValues, yValues2);
        TabulatedFunction result = service.multiply(function1, function2);


        for (int i = 0; i<xValues.length; i++) {
            assertEquals(yValues1[i] * yValues2[i], result.getY(i));
        }
    }

    @Test
    void divideWithLists() {
        LinkedListTabulatedFunction function1 = new LinkedListTabulatedFunction(xValues, yValues1);
        LinkedListTabulatedFunction function2 = new LinkedListTabulatedFunction(xValues, yValues2);
        TabulatedFunction result = service.divide(function1, function2);


        for (int i = 0; i<xValues.length; i++) {
            assertEquals(yValues1[i] / yValues2[i], result.getY(i));
        }
    }

    @Test
    void divideWithArrays() {
        ArrayTabulatedFunction function1 = new ArrayTabulatedFunction(xValues, yValues1);
        LinkedListTabulatedFunction function2 = new LinkedListTabulatedFunction(xValues, yValues2);
        TabulatedFunction result = service.divide(function1, function2);


        for (int i = 0; i<xValues.length; i++) {
            assertEquals(yValues1[i] / yValues2[i], result.getY(i));
        }
    }

    @Test
    void divideWithDifferentTypes() {
        ArrayTabulatedFunction function1 = new ArrayTabulatedFunction(xValues, yValues1);
        LinkedListTabulatedFunction function2 = new LinkedListTabulatedFunction(xValues, yValues2);
        TabulatedFunction result = service.divide(function1, function2);


        for (int i = 0; i<xValues.length; i++) {
            assertEquals(yValues1[i] / yValues2[i], result.getY(i));
        }
    }

    @Test
    void divideByZero() {
        double[] yZero = {0.0, 0.0, 0.0, 0.0};

        ArrayTabulatedFunction function1 = new ArrayTabulatedFunction(xValues, yValues1);
        LinkedListTabulatedFunction function2 = new LinkedListTabulatedFunction(xValues, yZero);

        assertThrows(IllegalArgumentException.class, () -> service.divide(function1, function2));
    }
}