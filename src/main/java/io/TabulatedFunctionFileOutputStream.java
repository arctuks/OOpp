package io;

import functions.ArrayTabulatedFunction;
import functions.LinkedListTabulatedFunction;
import functions.TabulatedFunction;

import java.io.*;

public class TabulatedFunctionFileOutputStream {
    public static void main(String[] args) {
        double[] xValues = {0.0, 0.5, 1.0, 1.5, 2.0};
        double[] yValues = {0.0, 0.25, 1.0, 2.25, 4.0};

        // Нет реализации итератора, пока не работает с ArrayFunction
        TabulatedFunction arrayFunction = new ArrayTabulatedFunction(xValues, yValues);
        TabulatedFunction linkedListFunction = new LinkedListTabulatedFunction(xValues, yValues);

        try (FileOutputStream arrayStream = new FileOutputStream("output/array function.bin");
             FileOutputStream linkedListStream = new FileOutputStream("output/linked list function.bin");
//             BufferedOutputStream bufferedArrayStream = new BufferedOutputStream(arrayStream);
             BufferedOutputStream bufferedLinkedListStream = new BufferedOutputStream(linkedListStream)) {

//            FunctionsIO.writeTabulatedFunction(bufferedArrayStream, arrayFunction);
            FunctionsIO.writeTabulatedFunction(bufferedLinkedListStream, linkedListFunction);

        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }
}
