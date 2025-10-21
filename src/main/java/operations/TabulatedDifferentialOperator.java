package operations;

import functions.Point;
import functions.TabulatedFunction;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;
import concurrent.SynchronizedTabulatedFunction;

public class TabulatedDifferentialOperator implements DifferentialOperator<TabulatedFunction> {
    private TabulatedFunctionFactory factory;

    public TabulatedDifferentialOperator() {
        this.factory = new ArrayTabulatedFunctionFactory();
    }

    public TabulatedDifferentialOperator(TabulatedFunctionFactory f) {
        this.factory = f;
    }

    void setFactory(TabulatedFunctionFactory f) {
        this.factory = f;
    }

    TabulatedFunctionFactory getFactory() {
        return factory;
    }

    @Override
    public TabulatedFunction derive(TabulatedFunction function) {
        TabulatedFunctionOperationService p = new TabulatedFunctionOperationService();
        Point[] points = p.asPoints(function);
        int countPoints = points.length;

        double[] xValues = new double[countPoints];
        double[] yValues = new double[countPoints];

        for (int i = 0; i < countPoints; i++) {
            xValues[i] = points[i].x;
        }

        countPoints--;
        for (int i = 0; i < countPoints; i++) {
            yValues[i] = (double) (points[i + 1].y - points[i].y) / (points[i + 1].x - points[i].x);
        }
        yValues[countPoints] = (double) (points[countPoints].y - points[countPoints - 1].y) / (points[countPoints].x - points[countPoints - 1].x);

        return factory.create(xValues, yValues);
    }

    public  synchronized TabulatedFunction deriveSynchronously(TabulatedFunction function) {
        if (!(function instanceof SynchronizedTabulatedFunction)) {
            function = new SynchronizedTabulatedFunction(function);
        }

        SynchronizedTabulatedFunction syncFunction = (SynchronizedTabulatedFunction) function;
        return syncFunction.doSynchronously(this::derive);
    }
}
