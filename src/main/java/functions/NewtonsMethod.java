package functions;

public class NewtonsMethod implements MathFunction {
    private final MathFunction func;
    private static final double EPSILON = 1e-10;    // Допустимая погрешность значения корня
    private static final double DELTA = 1e-5;       // Приращение x для вычисления производной в точке
    private static final int MAX_ITERATIONS = 1000;  // Максимальное число итераций

    NewtonsMethod(MathFunction f) {
        func = f;
    }

    @Override
    public double apply(double firstX) {
        double df = differentiation(firstX);
        double nextX = firstX - (func.apply(firstX) / df);
        int count = 0;

        while ((Math.abs(nextX - firstX) > EPSILON) && (count++ < MAX_ITERATIONS)) {
            firstX = nextX;

            df = differentiation(firstX);
            if (df < EPSILON) df += 0.1;

            nextX = firstX - (func.apply(firstX) / df);
        }

        return nextX;
    }

    // Вычисление производной в точке
    private double differentiation(double x) {
        return ((func.apply(x + DELTA) - func.apply(x - DELTA)) / (2 * DELTA));
    }
}
