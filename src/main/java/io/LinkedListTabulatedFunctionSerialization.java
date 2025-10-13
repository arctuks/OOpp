package io;

import functions.LinkedListTabulatedFunction;
import functions.TabulatedFunction;
import functions.factory.LinkedListTabulatedFunctionFactory;
import operations.TabulatedDifferentialOperator;

import java.io.*;

//хз вроде с моей реализацией сериализации работает
public class LinkedListTabulatedFunctionSerialization {
    public static void main(String[] args) {
        double[] xValues = {0.0, 1.0, 2.0, 3.0};
        double[] yValues = {1.0, 3.0, 5.0, 7.0};
        LinkedListTabulatedFunctionFactory factory = new LinkedListTabulatedFunctionFactory();

        try (FileOutputStream fileOutputStream = new FileOutputStream("output/serialized linked list functions.bin"); BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)) {
            LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);
            TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator(factory);

            TabulatedFunction firstDerivative = operator.derive(function);
            TabulatedFunction secondDerivative = operator.derive(firstDerivative);

            FunctionsIO.serialize(bufferedOutputStream, function);
            FunctionsIO.serialize(bufferedOutputStream, firstDerivative);
            FunctionsIO.serialize(bufferedOutputStream, secondDerivative);
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }

        try (FileInputStream fileInputStream = new FileInputStream("output/serialized linked list functions.bin"); BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream)) {

            TabulatedFunction deserializedOriginal = FunctionsIO.deserialize(bufferedInputStream);
            TabulatedFunction deserializedFirstDerivative = FunctionsIO.deserialize(bufferedInputStream);
            TabulatedFunction deserializedSecondDerivative = FunctionsIO.deserialize(bufferedInputStream);

            System.out.println(deserializedOriginal.toString());
            System.out.println(deserializedFirstDerivative.toString());
            System.out.println(deserializedSecondDerivative.toString());

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace(System.err);
        }
    }
}
