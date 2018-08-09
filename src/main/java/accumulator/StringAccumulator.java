package accumulator;

import accumulator.splitter.NumbersStringSplitter;

import java.util.List;

public class StringAccumulator {
    private NumbersStringSplitter numbersStringSplitter;

    public StringAccumulator() {
        numbersStringSplitter = new NumbersStringSplitter();
    }

    public int add(String numbers) {
        List<Integer> numbersList = numbersStringSplitter.splitNumberString(numbers);
        return numbersList.stream().reduce(0, Integer::sum);
    }

    public static void main(String[] args) {
        String numbers = "//;\n1;2";
        StringAccumulator stringAccumulator = new StringAccumulator();
        int sum = stringAccumulator.add(numbers);
        System.out.println("sum = " + sum);
    }
}
