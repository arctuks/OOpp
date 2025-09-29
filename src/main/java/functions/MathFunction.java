package functions;

public interface MathFunction {
    double apply(double x);

    default CompositeFunction andThen(MathFunction afterFunction) {
        CompositeFunction object = new CompositeFunction(this, afterFunction);
        return object;
    }
}

