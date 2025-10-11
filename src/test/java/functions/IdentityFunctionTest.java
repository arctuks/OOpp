package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdentityFunctionTest {

    @Test
    void apply() {
        MathFunction identity = new IdentityFunction();

        for (double i = 1; i < 2; i += 0.26) {
            assertEquals(i, identity.apply(i));
        }
    }
}
