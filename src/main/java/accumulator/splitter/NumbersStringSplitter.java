package accumulator.splitter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static accumulator.util.NegativeNumberThreadLocal.clearThreadLocal;
import static accumulator.util.StringAccumulatorConstants.NUMBER_STRING_PATTERN;
import static accumulator.validator.NumberValidator.checkErrorMessages;
import static accumulator.validator.NumberValidator.isValidNumber;
import static java.lang.Integer.parseInt;

public class NumbersStringSplitter {

    private Pattern pattern = Pattern.compile(NUMBER_STRING_PATTERN);

    public List<Integer> splitNumberString(String numbersStr) {
        Matcher matcher = pattern.matcher(numbersStr);
        List<Integer> numbers = new ArrayList<>();
        clearThreadLocal();
        while (matcher.find()) {
            int number = parseInt(matcher.group());
            if (isValidNumber(number)) {
                numbers.add(number);
            }
        }
        checkErrorMessages();
        return numbers;
    }
}
