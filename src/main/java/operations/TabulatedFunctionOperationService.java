package operations;

import functions.Point;
import exceptions.InconsistentFunctionsException;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;

import functions.TabulatedFunction;

public class TabulatedFunctionOperationService {
    public static Point[] asPoints(TabulatedFunction tabulatedFunction) {

        Point[] points = new Point[tabulatedFunction.getCount()];

        int i = 0;


        for (Point point : tabulatedFunction) {
            points[i] = point;
            i++;
        }

        return points;
    }
    private TabulatedFunctionFactory factory;

    public TabulatedFunctionOperationService(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedFunctionOperationService() {
        this.factory = new ArrayTabulatedFunctionFactory();
    }

    public TabulatedFunctionFactory getFactory() {
        return factory;
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    private interface BiOperation {
        double apply(double u, double v);
    }

    private TabulatedFunction doOperation(TabulatedFunction a, TabulatedFunction b, BiOperation operation) {

        if (a.getCount() != b.getCount()) {
            throw new InconsistentFunctionsException("У функций разное количество точек");
        }

        Point[] pointsA = asPoints(a);
        Point[] pointsB = asPoints(b);

        double[] xValues = new double[a.getCount()];
        double[] yValues = new double[a.getCount()];

        for (int i = 0; i < a.getCount(); i++) {
            if (pointsA[i].x != pointsB[i].x) {
                throw new InconsistentFunctionsException("У функций разные значения X");
            }
            xValues[i] = pointsA[i].x;
            yValues[i] = operation.apply(pointsA[i].y, pointsB[i].y);
        }

        return factory.create(xValues, yValues);
    }

    // Метод для сложения двух функций
    public TabulatedFunction add(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, (u, v) -> u + v);
    }

    // Метод для вычитания двух функций
    public TabulatedFunction subtract(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, (u, v) -> u - v);
    }

    public TabulatedFunction multiply(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, new BiOperation() {
            @Override
            public double apply(double u, double v) {
                return u * v;
            }
        });
    }

    public TabulatedFunction divide(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, new BiOperation() {
            @Override
            public double apply(double u, double v) {
                if (v == 0.0) {
                    throw new IllegalArgumentException("Divide by Zero");
                }
                return u / v;
            }
        });
    }
}