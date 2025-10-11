package functions;

public class ConstantFunction implements MathFunction {
    private final double pole;

    ConstantFunction(double pole) {
        this.pole = pole;
    }

    @Override
    public double apply(double x) {
        return pole;
    }

    public double getPole() {
        return pole;
    }
}
