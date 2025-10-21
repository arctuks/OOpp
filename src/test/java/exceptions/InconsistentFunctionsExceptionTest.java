package exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InconsistentFunctionsExceptionTest {

    @Test
    void testInconsistentFunctionsExceptionWithoutMessage() {
        InconsistentFunctionsException exception = assertThrows(
                InconsistentFunctionsException.class,
                () -> { throw new InconsistentFunctionsException(); }
        );
        assertNull(exception.getMessage(), "Сообщение должно быть null для конструктора без параметров.");
    }


    @Test
    void testInconsistentFunctionsExceptionWithMessage() {
        String expectedMessage = "Functions are inconsistent!";
        InconsistentFunctionsException exception = assertThrows(
                InconsistentFunctionsException.class,
                () -> { throw new InconsistentFunctionsException(expectedMessage); }
        );
        assertEquals(expectedMessage, exception.getMessage(), "Сообщение должно совпадать с переданным.");
    }
}