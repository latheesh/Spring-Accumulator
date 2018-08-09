package accumulator.validator;

import static accumulator.util.NegativeNumberThreadLocal.getNegativeNumberThreadLocal;
import static accumulator.util.StringAccumulatorConstants.MAX_ALLOWED_NUMBER;

public class NumberValidator {

    public static boolean isValidNumber(int number) {
        if (number < 0) {
            getNegativeNumberThreadLocal().add(String.valueOf(number));
            return false;
        }

        return number <= MAX_ALLOWED_NUMBER;
    }

    public static void checkErrorMessages() {
        if (!getNegativeNumberThreadLocal().isEmpty()) {
            String errorMessage = "negative numbers not allowed :" + String.join(",", getNegativeNumberThreadLocal());
            throw new RuntimeException(errorMessage);
        }
    }
}
