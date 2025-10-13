package operations;

import functions.TabulatedFunction;
public class TabulatedFunctionOperationService {
    public TabulatedFunction multiply(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, new BiOperation() {
            @Override
            public double apply(double u, double v) {
                return u * v;
            }
        });
    }

    // Метод деления
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
