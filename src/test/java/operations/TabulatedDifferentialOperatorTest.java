package operations;

import functions.factory.TabulatedFunctionFactory;
import functions.TabulatedFunction;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.LinkedListTabulatedFunctionFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TabulatedDifferentialOperatorTest {
    double[] x = {1, 2, 3}; // 2x + 1
    double[] y = {3, 5, 7};
    double[] dy = {2, 2, 2}; // Массив численных производных

    @Test
    void testSetAndGetFactory() {
        TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator();

        TabulatedFunctionFactory newFactory = new LinkedListTabulatedFunctionFactory();
        operator.setFactory(newFactory);

        assertEquals(newFactory, operator.getFactory());
    }

    @Test
    void deriveWithArrayTabulatedFunction() {
        ArrayTabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
        TabulatedFunction function = factory.create(x, y);
        TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator(factory);

        TabulatedFunction derivatives = operator.derive(function);
        for (int i = 0; i < 3; i++) {
            assertEquals(x[i], derivatives.getX(i));
            assertEquals(dy[i], derivatives.getY(i));
        }
    }
}