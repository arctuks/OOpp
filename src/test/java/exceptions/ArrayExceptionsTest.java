package exceptions;

import functions.AbstractTabulatedFunction;
import functions.ArrayTabulatedFunction;
import functions.LinkedListTabulatedFunction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayExceptionsTest {
    double[] xSorted = {1.0, 2.0, 3.0};
    double[] xUnsorted = {1.0, 7.0, 3.0};
    double[] yLen2 = {10.0, 20.0};
    double[] yLen3 = {10.0, 20.0, 30.0};


    @Test
    void checkLengthIsTheSameTest() {
        assertThrows(DifferentLengthOfArraysException.class, () -> {
            AbstractTabulatedFunction.checkLengthIsTheSame(xSorted, yLen2);
        });
    }

    @Test
    void checkSorted() {
        assertThrows(ArrayIsNotSortedException.class, () -> {
            AbstractTabulatedFunction.checkSorted(xUnsorted);
        });
    }

    @Test
    void checkSortedInConstructorOfArrayTabulatedFunction(){
        assertThrows(ArrayIsNotSortedException.class, () -> {
            new ArrayTabulatedFunction(xUnsorted, yLen3);
        });
    }

    @Test
    void checkTheSameLengthInConstructorOfArrayTabulatedFunction(){
        assertThrows(DifferentLengthOfArraysException.class, () -> {
            new ArrayTabulatedFunction(xSorted, yLen2);
        });
    }

    @Test
    void checkSortedInConstructorOfLinkedListTabulatedFunction(){
        assertThrows(ArrayIsNotSortedException.class, () -> {
            new LinkedListTabulatedFunction(xUnsorted, yLen3);
        });
    }

    @Test
    void checkTheSameLengthInConstructorOfLinkedListTabulatedFunction(){
        assertThrows(DifferentLengthOfArraysException.class, () -> {
            new LinkedListTabulatedFunction(xSorted, yLen2);
        });
    }
}