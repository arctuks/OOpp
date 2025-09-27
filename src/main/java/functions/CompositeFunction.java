package functions;

public class CompositeFunction implements MathFunction {
    private final MathFunction firstMathFunction;
    private final MathFunction secondMathFunction;

    CompositeFunction(MathFunction first, MathFunction second) {
        firstMathFunction = first;
        secondMathFunction = second;
    }
    @Override
    public double apply(double x) { return secondMathFunction.apply(firstMathFunction.apply(x)); }
}
