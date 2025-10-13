package io;

import functions.TabulatedFunction;
import functions.Point;
import functions.factory.TabulatedFunctionFactory;
import java.io.*;

public final class FunctionsIO {
    public static void writeTabulatedFunction(BufferedOutputStream outputStream, TabulatedFunction function) throws IOException {
        DataOutputStream os = new DataOutputStream(outputStream);
        os.writeInt(function.getCount());

        for (Point point : function) {
            os.writeDouble(point.x);
            os.writeDouble(point.y);
        }

        os.flush();
    }

    public static TabulatedFunction readTabulatedFunction(BufferedInputStream inputStream, TabulatedFunctionFactory factory) throws IOException {
        DataInputStream is = new DataInputStream(inputStream);

        int size = is.readInt();

        double[] xValues = new double[size];
        double[] yValues = new double[size];

        for (int i = 0; i < size; i++) {
            xValues[i] = is.readDouble();
            yValues[i] = is.readDouble();
        }

        return factory.create(xValues, yValues);
    }
}
