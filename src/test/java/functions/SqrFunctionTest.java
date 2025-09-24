package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SqrFunctionTest {

    @Test
    void apply() {
        MathFunction squareX = new SqrFunction();

        for (double i = 0; i < 564; i+=4.06768){
            assertEquals(i*i, squareX.apply(i));
        }
    }
}