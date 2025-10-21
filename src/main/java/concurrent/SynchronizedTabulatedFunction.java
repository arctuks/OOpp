package concurrent;

import functions.Point;
import functions.TabulatedFunction;
import operations.TabulatedFunctionOperationService;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SynchronizedTabulatedFunction implements TabulatedFunction {
    private final TabulatedFunction function;
    private final Object lock;

    public SynchronizedTabulatedFunction(TabulatedFunction function) {
        this.function = function;
        lock = this;
    }

    @Override
    public int getCount() {
        int count;
        synchronized (lock) {
            count = function.getCount();
        }
        return count;
    }

    @Override
    public double getX(int index) {
        double x;
        synchronized (lock) {
            x = function.getX(index);
        }
        return x;
    }

    @Override
    public double getY(int index) {
        double y;
        synchronized (lock) {
            y = function.getY(index);
        }
        return y;
    }

    @Override
    public void setY(int index, double value) {
        synchronized (lock) {
            function.setY(index, value);
        }
    }

    @Override
    public int indexOfX(double x) {
        int index;
        synchronized (lock) {
            index = function.indexOfX(x);
        }
        return index;
    }

    @Override
    public int indexOfY(double y) {
        int index;
        synchronized (lock) {
            index = function.indexOfY(y);
        }
        return index;
    }

    @Override
    public double leftBound() {
        double left;
        synchronized (lock) {
            left = function.leftBound();
        }
        return left;
    }

    @Override
    public double rightBound() {
        double right;
        synchronized (lock) {
            right = function.rightBound();
        }
        return right;
    }

    @Override
    public double apply(double x) {
        double result;
        synchronized (lock) {
            result = function.apply(x);
        }
        return result;
    }

    @Override
    public Iterator<Point> iterator() {
        Point[] points = TabulatedFunctionOperationService.asPoints(function);

        return new Iterator<>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < points.length;
            }

            @Override
            public Point next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                return points[index++];
            }
        };
    }


    public interface Operation<T> {
        T apply(SynchronizedTabulatedFunction function);
    }

    public <T> T doSynchronously(Operation<? extends T> operation) {
        synchronized (function) {
            return operation.apply(this);
        }
    }
}
