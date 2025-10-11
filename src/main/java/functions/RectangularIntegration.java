package functions;

public class RectangularIntegration {
    private MathFunction func;

    private double A;
    private double B;

    RectangularIntegration(MathFunction f, double a, double b) {
        func = f;
        A = a;
        B = b;
    }

    double apply(int n) {
        double sum = 0;
        double length = (B - A) / n;
        for (int i = 1; i <= n; i++) {
            sum += length * func.apply(A + i * length);
        }
        return sum;
    }

    public double getA() {
        return A;
    }

    public double getB() {
        return B;
    }

    public void setA(double A) {
        this.A = A;
    }

    public void setB(double B) {
        this.B = B;
    }


}
