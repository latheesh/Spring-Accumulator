package accumulator.util;

import java.util.ArrayList;
import java.util.List;

public class NegativeNumberThreadLocal {
    private static final ThreadLocal<List<String>> negativeNumbers = ThreadLocal.withInitial(ArrayList::new);

    public static List<String> getNegativeNumberThreadLocal() {
        return negativeNumbers.get();
    }

    public static void clearThreadLocal() {
        negativeNumbers.get().clear();
    }
}
