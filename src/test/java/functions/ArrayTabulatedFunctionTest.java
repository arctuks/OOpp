package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayTabulatedFunctionTest {

    @Test
    void apply(){
        MathFunction sqr = new SqrFunction();
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(sqr, 1, 10, 10);
        assertEquals(72.5, function.apply(8.5));
        assertEquals(119, function.apply(11));
    }

    @Test
    void insert() {
        MathFunction sqr = new SqrFunction();
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(sqr, 1, 10, 10);

        function.insert(6, 40);
        assertEquals(40, function.getY(5));

        function.insert(6.5, 42.25);
        assertEquals(42.25, function.getY(6));
        assertEquals(100, function.getY(10));
    }

    @Test
    void extrapolateLeft() {
        MathFunction sqr = new SqrFunction();
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(sqr, 1, 10, 10);
        assertEquals(2.5, function.extrapolateLeft(1.5));

        double[] x1 = {1};
        double[] y1 = {10};
        ArrayTabulatedFunction function2 = new ArrayTabulatedFunction(x1, y1);
        assertEquals(10, function2.extrapolateLeft(12));
    }

    @Test
    void extrapolateRight() {
        MathFunction sqr = new SqrFunction();
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(sqr, 1, 10, 10);
        assertEquals(90.5, function.extrapolateRight(9.5));

        double[] x1 = {1};
        double[] y1 = {10};
        ArrayTabulatedFunction function2 = new ArrayTabulatedFunction(x1, y1);
        assertEquals(10, function2.extrapolateRight(12));
    }

    @Test
    void interpolate() {
        MathFunction sqr = new SqrFunction();
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(sqr, 1, 10, 10);
        assertEquals(72.5, function.interpolate(8.5, function.floorIndexOfX(8.5)));

        double[] x1 = {1};
        double[] y1 = {10};
        ArrayTabulatedFunction function2 = new ArrayTabulatedFunction(x1, y1);
        assertEquals(10, function2.interpolate(12, 10));
    }

    @Test
    void floorIndexOfX() {
        double[] xVal = {3, 4, 5};
        double[] yVal = {20, 21, 22};
        ArrayTabulatedFunction f = new ArrayTabulatedFunction(xVal, yVal);

        assertEquals(1, f.floorIndexOfX(4.5));
        assertEquals(0, f.floorIndexOfX(2));
        assertEquals(3, f.floorIndexOfX(1000));
    }

    @Test
    void indexOfX() {
        MathFunction sqr = new SqrFunction();
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(sqr, 1, 10, 10);

        assertEquals(0, function.indexOfX(1));
        assertEquals(5, function.indexOfX(6));
        assertEquals(9, function.indexOfX(10));
    }

    @Test
    void indexOfY() {
        MathFunction sqr = new SqrFunction();
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(sqr, 1, 10, 10);

        assertEquals(0, function.indexOfY(1));
        assertEquals(5, function.indexOfY(36));
        assertEquals(9, function.indexOfY(100));
    }

    @Test
    void getCount() {
        MathFunction sqr = new SqrFunction();
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(sqr, 1, 10, 10);

        double[] arrX = {1, 1.5, 44, 35, 46.001};
        double[] arrY = {10, 11, 12, 36, 47.034};
        ArrayTabulatedFunction function2 = new ArrayTabulatedFunction(arrX, arrY);

        assertEquals(10, function.getCount());
        assertEquals(5, function2.getCount());
    }

    @Test
    void leftBound() {
        MathFunction sqr = new SqrFunction();
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(sqr, 1, 10, 10);

        double[] arrX = {1, 1.5, 44, 35, 46.001};
        double[] arrY = {10, 11, 12, 36, 47.034};
        ArrayTabulatedFunction function2 = new ArrayTabulatedFunction(arrX, arrY);

        assertEquals(1, function.leftBound());
        assertEquals(1, function2.leftBound());
    }

    @Test
    void rightBound() {
        MathFunction sqr = new SqrFunction();
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(sqr, 1, 10, 10);

        double[] arrX = {1, 1.5, 44, 35, 46.001};
        double[] arrY = {10, 11, 12, 36, 47.034};
        ArrayTabulatedFunction function2 = new ArrayTabulatedFunction(arrX, arrY);

        assertEquals(10, function.rightBound());
        assertEquals(46.001, function2.rightBound());
    }

    @Test
    void getXandY() {
        MathFunction sqr = new SqrFunction();
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(sqr, 1, 10, 10);

        double[] arrX = {1, 1.5, 44, 35, 46.001};
        double[] arrY = {10, 11, 12, 36, 47.034};
        ArrayTabulatedFunction function2 = new ArrayTabulatedFunction(arrX, arrY);

        assertEquals(7, function.getX(6));
        assertEquals(44, function2.getX(2));

        assertEquals(100, function.getY(9));
        assertEquals(1, function.getY(0));

        assertEquals(10, function.getX(9));
        assertEquals(1, function.getX(0));

        assertEquals(49, function.getY(6));
        assertEquals(12, function2.getY(2));
    }

    @Test
    void setY() {
        MathFunction sqr = new SqrFunction();
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(sqr, 1, 10, 10);

        function.setY(4, 23);
        assertEquals(23, function.getY(4));

    }
}
