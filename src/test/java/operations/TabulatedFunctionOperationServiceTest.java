package operations;

import org.junit.jupiter.api.Test;

import functions.ArrayTabulatedFunction;
import functions.Point;
import functions.LinkedListTabulatedFunction;
import functions.factory.*;
import functions.TabulatedFunction;
import exceptions.InconsistentFunctionsException;

import static org.junit.jupiter.api.Assertions.*;

class TabulatedFunctionOperationServiceTest {
    double[] xValues = {0.0, 1.0, 2.0, 3.5};
    double[] yValues1 = {1.0, 3.0, 5.0, 8.0};
    double[] yValues2 = {2.0, 2.0, 3.0, 3.0};
    TabulatedFunctionOperationService service = new TabulatedFunctionOperationService();

    @Test
    public void testAsPointsWithArrayTabulatedFunction() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {4.0, 5.0, 6.0};
        ArrayTabulatedFunction arrayFunction = new ArrayTabulatedFunction(xValues, yValues);

        Point[] points = TabulatedFunctionOperationService.asPoints(arrayFunction);

        // Проверка длины массива
        assertEquals(3, points.length);

        // Проверка каждой точки
        assertEquals(1.0, points[0].x, 0.0001);
        assertEquals(4.0, points[0].y, 0.0001);

        assertEquals(2.0, points[1].x, 0.0001);
        assertEquals(5.0, points[1].y, 0.0001);

        assertEquals(3.0, points[2].x, 0.0001);
        assertEquals(6.0, points[2].y, 0.0001);
    }

    @Test
    void testAsPointsWithLinkedListTabulatedFunction() {
        double[] xArray = {1.0, 2.0, 3.0};
        double[] yArray = {0.5, 1.0, 4.0};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xArray, yArray);

        Point[] points = TabulatedFunctionOperationService.asPoints(function);

        assertNotNull(points);
        assertEquals(xArray.length, points.length);
        for (int i = 0; i < points.length; i++) {
            assertNotNull(points[i]);
            assertEquals(xArray[i], points[i].x);
            assertEquals(yArray[i], points[i].y);
        }
    }

    @Test
    public void testAdditionWithSameFunctionTypes() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValuesA = {4.0, 5.0, 6.0};
        double[] yValuesB = {7.0, 8.0, 9.0};

        TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService(factory);

        ArrayTabulatedFunction functionA = new ArrayTabulatedFunction(xValues, yValuesA);
        ArrayTabulatedFunction functionB = new ArrayTabulatedFunction(xValues, yValuesB);

        TabulatedFunction result = service.add(functionA, functionB);

        assertEquals(11.0, result.apply(1.0), 0.0001);
        assertEquals(13.0, result.apply(2.0), 0.0001);
        assertEquals(15.0, result.apply(3.0), 0.0001);
    }

    @Test
    public void testSubtractionWithDifferentFunctionTypes() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValuesA = {10.0, 20.0, 30.0};
        double[] yValuesB = {5.0, 15.0, 25.0};

        TabulatedFunctionFactory factory = new LinkedListTabulatedFunctionFactory();
        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService(factory);

        LinkedListTabulatedFunction functionA = new LinkedListTabulatedFunction(xValues, yValuesA);
        ArrayTabulatedFunction functionB = new ArrayTabulatedFunction(xValues, yValuesB);

        TabulatedFunction result = service.subtract(functionA, functionB);

        assertEquals(5.0, result.apply(1.0), 0.0001);
        assertEquals(5.0, result.apply(2.0), 0.0001);
        assertEquals(5.0, result.apply(3.0), 0.0001);
    }

    @Test
    public void testInconsistentFunctionsExceptionByDifferentLength() {
        double[] xValuesA = {1.0, 2.0, 3.0};
        double[] xValuesB = {1.0, 2.0};
        double[] yValuesA = {4.0, 5.0, 6.0};
        double[] yValuesB = {7.0, 8.0};

        TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService(factory);

        ArrayTabulatedFunction functionA = new ArrayTabulatedFunction(xValuesA, yValuesA);
        ArrayTabulatedFunction functionB = new ArrayTabulatedFunction(xValuesB, yValuesB);

        assertThrows(InconsistentFunctionsException.class, () -> service.add(functionA, functionB));
    }

    @Test
    public void testInconsistentFunctionsExceptionByDifferentXValues() {
        double[] xValuesA = {1.0, 2.0, 3.0};
        double[] xValuesB = {1.0, 2.5, 3.0};
        double[] yValuesA = {4.0, 5.0, 6.0};
        double[] yValuesB = {7.0, 8.0, 9.0};

        TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService(factory);

        ArrayTabulatedFunction functionA = new ArrayTabulatedFunction(xValuesA, yValuesA);
        ArrayTabulatedFunction functionB = new ArrayTabulatedFunction(xValuesB, yValuesB);

        assertThrows(InconsistentFunctionsException.class, () -> service.add(functionA, functionB));
    }

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

    @Test
    public void getSetTest(){
        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService();

        assertInstanceOf(ArrayTabulatedFunctionFactory.class,service.getFactory());

        service.setFactory(new LinkedListTabulatedFunctionFactory());

        assertInstanceOf(LinkedListTabulatedFunctionFactory.class,service.getFactory());
    }
}