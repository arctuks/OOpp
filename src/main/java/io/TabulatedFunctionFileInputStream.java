package io;

import functions.TabulatedFunction;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.LinkedListTabulatedFunctionFactory;
import operatinos.TabulatedDifferentialOperator;

import java.io.*;

public class TabulatedFunctionFileInputStream {
    public static void main(String[] args) {

        try (FileInputStream fileInputStream = new FileInputStream("input/binary function.bin");
             BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream)) {

            TabulatedFunction fileFunction = FunctionsIO.readTabulatedFunction(bufferedInputStream, new ArrayTabulatedFunctionFactory());
            System.out.println(fileFunction);
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }

        System.out.println("Введите размер и значения функции");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            String countLine = bufferedReader.readLine();
            int count = Integer.parseInt(countLine.trim());

            double[] xValues = new double[count];
            double[] yValues = new double[count];

            for (int i = 0; i < count; i++) {
                String line = bufferedReader.readLine();
                String[] values = line.trim().split("\\s+");

                xValues[i] = Double.parseDouble(values[0]);
                yValues[i] = Double.parseDouble(values[1]);
            }

            TabulatedFunction consoleFunction = new LinkedListTabulatedFunctionFactory().create(xValues, yValues);
            LinkedListTabulatedFunctionFactory factory = new LinkedListTabulatedFunctionFactory();
            TabulatedFunction derivative = new TabulatedDifferentialOperator(factory).derive(consoleFunction);

            System.out.println("Производные функции: ");
            System.out.println(derivative.toString());

        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }
}
