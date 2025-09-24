package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ConstantFunctionTest {

    @Test
    void apply() {
        MathFunction constant = new ConstantFunction(9);
        MathFunction constantZero = new ZeroFunction();
        MathFunction constantUnit = new UnitFunction();

        assertEquals(9, constant.apply(4));
        assertEquals(0, constantZero.apply(4));
        assertEquals(1, constantUnit.apply(90));
    }
}