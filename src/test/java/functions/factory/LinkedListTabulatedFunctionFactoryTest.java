package functions.factory;

import functions.LinkedListTabulatedFunction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTabulatedFunctionFactoryTest {
    @Test
    void create() {
        TabulatedFunctionFactory factory = new LinkedListTabulatedFunctionFactory();
        double[] xValues = {0, 1, 2, 3};
        double[] yValues = {0, 11, 22, 33};
        functions.TabulatedFunction func = factory.create(xValues, yValues);
        assertInstanceOf(LinkedListTabulatedFunction.class, func);
    }
}