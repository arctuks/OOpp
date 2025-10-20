package io;

import functions.LinkedListTabulatedFunction;
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

    public static TabulatedFunction deserialize(BufferedInputStream stream) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(stream);
        return (TabulatedFunction) objectInputStream.readObject();
    }

    public static void serialize(BufferedOutputStream stream, TabulatedFunction function) throws IOException {
        // Оборачиваем поток в ObjectOutputStream для записи объекта
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(stream);

        // Записываем объект
        objectOutputStream.writeObject(function);

        // Пробрасываем данные из буфера
        objectOutputStream.flush();
    }
}
