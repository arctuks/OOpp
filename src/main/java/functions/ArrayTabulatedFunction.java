package functions;

import java.util.Arrays;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction implements Insertable, Removable {
    private double[] xValues;
    private double[] yValues;
    private int count;

    public ArrayTabulatedFunction(double[] xValues, double[] yValues) {
        count = xValues.length;
        if (count < 2) throw new IllegalArgumentException("The length lower than minimum");

        this.xValues = Arrays.copyOf(xValues, count);
        this.yValues = Arrays.copyOf(yValues, count);
    }

    ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (count < 2) throw new IllegalArgumentException("The length lower than minimum");

        this.xValues = new double[count];
        this.yValues = new double[count];
        this.count = count;
        if (xFrom > xTo) {
            double temp = xFrom;
            xFrom = xTo;
            xTo = temp;
        }

        if (xFrom == xTo) {
            for (int i = 0; i < count + 1; i++) {
                xValues[i] = xFrom;
                yValues[i] = source.apply(xFrom);
            }
        } else {
            this.xValues[0] = xFrom;
            this.yValues[0] = source.apply(xFrom);

            double lengthXY = xTo - xFrom;
            double step = lengthXY / (count - 1);

            for (int i = 1; i < count; i++) {
                xValues[i] = xFrom + i * step;
                yValues[i] = source.apply(xFrom + i * step);
            }
        }
    }

    public int getCount() {
        return count;
    }

    public double getX(int indexX) {
        if (indexX < 0 || indexX >= count) throw new IllegalArgumentException("Not in range of acceptable values");
        return xValues[indexX];
    }

    public double getY(int indexY) {
        if (indexY < 0 || indexY >= count) throw new IllegalArgumentException("Not in range of acceptable values");
        return yValues[indexY];
    }

    public void setY(int index, double value) {
        if (index < 0 || index >= count) throw new IllegalArgumentException("Not in range of acceptable values");
        this.yValues[index] = value;
    }

    public double leftBound() {
        return getX(0);
    }

    public double rightBound() {
        return getX(count - 1);
    }

    public int indexOfX(double x) {
        if (x < leftBound() || x > rightBound()) throw new IllegalArgumentException("Not in range of acceptable values");
        for (int i = 0; i < count; i++) {
            if (x == xValues[i]) {
                return i;
            }
        }
        return -1;
    }

    public int indexOfY(double y) {
        for (int i = 0; i < count + 1; i++) {
            if (y == yValues[i]) {
                return i;
            }
        }
        return -1;
    }

    public int floorIndexOfX(double x) {
        if (x <= xValues[0]) {
            throw new IllegalArgumentException();
        }
        if (x >= xValues[count - 1]) {
            return count;
        }

        for (int i = 1; i < count; i++) {
            if (x == xValues[i]) {
                return i;
            }
            if (x < xValues[i] && x > xValues[i - 1]) {
                return i - 1;
            }
        }
        return count;
    }

    protected double extrapolateLeft(double x) {
        double x0 = getX(0);
        double x1 = getX(1);
        double y0 = getY(0);
        double y1 = getY(1);

        return y0 + ((y1 - y0) / (x1 - x0)) * (x - x0);
    }

    protected double extrapolateRight(double x) {
        double x0 = getX(count - 2);
        double x1 = getX(count - 1);
        double y0 = getY(count - 2);
        double y1 = getY(count - 1);

        return y0 + (y1 - y0) * (x - x0) / (x1 - x0);
    }

    protected double interpolate(double x, int floorIndex) {
        double x0 = getX(floorIndex);
        double x1 = getX(floorIndex + 1);
        double y0 = getY(floorIndex);
        double y1 = getY(floorIndex + 1);

        return interpolate(x, x0, x1, y0, y1);
    }

    public void insert(double x, double y) {
        if (count == 0) {
            count++;
            double[] newX = {x};
            double[] newY = {y};

            xValues = newX;
            yValues = newY;
        } else {
            int index = floorIndexOfX(x);
            if (getX(index) == x) {
                setY(index, y);
            } else {
                index++;
                double[] newX = new double[count + 1];
                double[] newY = new double[count + 1];

                System.arraycopy(xValues, 0, newX, 0, index);
                System.arraycopy(yValues, 0, newY, 0, index);

                newX[index] = x;
                newY[index] = y;

                System.arraycopy(xValues, index, newX, index + 1, count - index);
                System.arraycopy(yValues, index, newY, index + 1, count - index);

                xValues = newX;
                yValues = newY;
                count++;
            }
        }
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= count) throw new IllegalArgumentException("Not in range of acceptable values");

        double[] newX = new double[count - 1];
        double[] newY = new double[count - 1];
        if (index != 0) {
            System.arraycopy(xValues, 0, newX, 0, index);
            System.arraycopy(yValues, 0, newY, 0, index);
        }

        if (count != index + 1) {
            System.arraycopy(xValues, index + 1, newX, index + 1, count - index - 1);
            System.arraycopy(yValues, index + 1, newY, index + 1, count - index - 1);
        }

        xValues = newX;
        yValues = newY;
        count--;
    }
}