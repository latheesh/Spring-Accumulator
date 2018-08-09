package accumulator.validator;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static accumulator.util.NegativeNumberThreadLocal.clearThreadLocal;
import static accumulator.validator.NumberValidator.isValidNumber;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NumberValidatorTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void init() {
        clearThreadLocal();
    }

    @Test
    public void validNumberLessThanMax() {
        assertTrue(isValidNumber(10));
    }

    @Test
    public void validNumberEqualsMax() {
        assertTrue(isValidNumber(1000));
    }

    @Test
    public void inValidNumber() {
        assertFalse(isValidNumber(1001));
    }

    @Test
    public void checkErrorMessages() {
        isValidNumber(1);
        isValidNumber(2);
        NumberValidator.checkErrorMessages();
    }

    @Test
    public void checkErrorMessage_ExceptionIsThrown() {
        isValidNumber(1);
        isValidNumber(-3);
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("negative numbers not allowed :-3");
        NumberValidator.checkErrorMessages();
    }

    @Test
    public void checkErrorMessage_ExceptionIsThrown2() {
        isValidNumber(1);
        isValidNumber(-1);
        isValidNumber(-5);
        isValidNumber(-7);
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("negative numbers not allowed :-1,-5,-7");
        NumberValidator.checkErrorMessages();
    }
}