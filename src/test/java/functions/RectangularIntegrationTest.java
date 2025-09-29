package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangularIntegrationTest {

    @Test
    void apply() {
        ConstantFunction func = new ConstantFunction(3);
        RectangularIntegration rectangularIntegration= new RectangularIntegration(func,1,5);

        assertEquals(12, rectangularIntegration.apply(4));
    }
}