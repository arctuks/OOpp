package concurrent;

import functions.*;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class SynchronizedTabulatedFunctionTest {
    TabulatedFunction function = new LinkedListTabulatedFunction(new SqrFunction(), 1, 10, 10);
    SynchronizedTabulatedFunction synchronizedTabulatedFunction;
    int count = function.getCount();

    @Test
    public void testDoSynchronously() {
        // Создаем табулированную функцию
        TabulatedFunction function = new LinkedListTabulatedFunction(new UnitFunction(), 1, 10, 10);
        SynchronizedTabulatedFunction synchronizedFunction = new SynchronizedTabulatedFunction(function);

        // Операция получения значения y по индексу
        double yValue = synchronizedFunction.doSynchronously(f -> f.getY(5));

        assertEquals(1.0, yValue, 0.001);
    }

    @Test
    void getCount() {
        synchronizedTabulatedFunction = new SynchronizedTabulatedFunction(function);
        assertEquals(count, synchronizedTabulatedFunction.getCount());
    }

    @Test
    void getX() {
        synchronizedTabulatedFunction = new SynchronizedTabulatedFunction(function);

        for (int i = 0; i < count; i++) {
            assertEquals(function.getX(i), synchronizedTabulatedFunction.getX(i));
        }
    }

    @Test
    void getY() {
        synchronizedTabulatedFunction = new SynchronizedTabulatedFunction(function);

        for (int i = 0; i < count; i++) {
            assertEquals(function.getY(i), synchronizedTabulatedFunction.getY(i));
        }
    }

    @Test
    void setY() {
        synchronizedTabulatedFunction = new SynchronizedTabulatedFunction(function);

        for (int i = 0; i < count; i++) {
            synchronizedTabulatedFunction.setY(i, 100);
            assertEquals(100, synchronizedTabulatedFunction.getY(i));
        }
    }

    @Test
    void indexOfX() {
        synchronizedTabulatedFunction = new SynchronizedTabulatedFunction(function);

        for (int i = 0; i < count; i++) {
            assertEquals(i, synchronizedTabulatedFunction.indexOfX(function.getX(i)));
        }
    }

    @Test
    void indexOfY() {
        synchronizedTabulatedFunction = new SynchronizedTabulatedFunction(function);

        for (int i = 0; i < count; i++) {
            assertEquals(i, synchronizedTabulatedFunction.indexOfY(function.getY(i)));
        }
    }

    @Test
    void leftBound() {
        synchronizedTabulatedFunction = new SynchronizedTabulatedFunction(function);

        assertEquals(function.leftBound(), synchronizedTabulatedFunction.leftBound());
    }

    @Test
    void rightBound() {
        synchronizedTabulatedFunction = new SynchronizedTabulatedFunction(function);

        assertEquals(function.rightBound(), synchronizedTabulatedFunction.rightBound());
    }

    @Test
    void apply() {
        synchronizedTabulatedFunction = new SynchronizedTabulatedFunction(function);

        assertEquals(72.5, synchronizedTabulatedFunction.apply(8.5));
        assertEquals(119, synchronizedTabulatedFunction.apply(11));
    }

    @Test
    void iteratorInWhile() {
        synchronizedTabulatedFunction = new SynchronizedTabulatedFunction(function);

        Iterator<Point> iterator = synchronizedTabulatedFunction.iterator();
        Point point;
        int position = 0;

        while (iterator.hasNext()) {
            point = iterator.next();
            assertEquals(synchronizedTabulatedFunction.getY(position), point.y);
            assertEquals(synchronizedTabulatedFunction.getX(position), point.x);
            position++;
        }

        assertEquals(10, position);
    }

    @Test
    void iteratorInForEach() {
        synchronizedTabulatedFunction = new SynchronizedTabulatedFunction(function);
        int position = 0;

        for (Point point : synchronizedTabulatedFunction) {
            assertEquals(synchronizedTabulatedFunction.getY(position), point.y);
            assertEquals(synchronizedTabulatedFunction.getX(position), point.x);
            position++;
        }

        assertEquals(10, position);
    }

    @Test
    void iteratorException() {
        synchronizedTabulatedFunction = new SynchronizedTabulatedFunction(function);
        Iterator<Point> iterator = synchronizedTabulatedFunction.iterator();

        while (iterator.hasNext()) {
            iterator.next();
        }

        assertThrows(NoSuchElementException.class, iterator::next);
        assertFalse(iterator.hasNext());
    }
}