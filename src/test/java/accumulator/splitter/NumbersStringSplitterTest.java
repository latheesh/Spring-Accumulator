package accumulator.splitter;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

public class NumbersStringSplitterTest {

    private NumbersStringSplitter numbersStringSplitter;

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Before
    public void init() {
        numbersStringSplitter = new NumbersStringSplitter();
    }

    @Test
    public void splitNumberString() {
        String numberStr = "1,2,3";
        List<Integer> numbers = numbersStringSplitter.splitNumberString(numberStr);
        assertThat(numbers, hasSize(3));
        assertThat(numbers, contains(1, 2, 3));
    }

    @Test
    public void numbersStringSplitter2() {
        String numberStr = "1\n2,3";
        List<Integer> numers = numbersStringSplitter.splitNumberString(numberStr);
        assertThat(numers, hasSize(3));
        assertThat(numers, contains(1, 2, 3));
    }

    @Test
    public void numbersStringSplitter3() {
        String numberStr = "1\n1002,3";
        List<Integer> numers = numbersStringSplitter.splitNumberString(numberStr);
        assertThat(numers, hasSize(2));
        assertThat(numers, contains(1, 3));
    }

    @Test
    public void numbersStringSplitter4() {
        String numberStr = "//***\n1***2***3";
        List<Integer> numers = numbersStringSplitter.splitNumberString(numberStr);
        assertThat(numers, hasSize(3));
        assertThat(numers, contains(1, 2, 3));
    }

    @Test
    public void numbersStringSplitter5() {
        String numberStr = "//*|1*2%3";
        List<Integer> numers = numbersStringSplitter.splitNumberString(numberStr);
        assertThat(numers, hasSize(3));
        assertThat(numers, contains(1, 2, 3));
    }

    @Test
    public void negativeNumberStringSplitter() {
        String numberStr = "//*|-1*-2%3";
        expected.expect(RuntimeException.class);
        expected.expectMessage("negative numbers not allowed :-1,-2");
        numbersStringSplitter.splitNumberString(numberStr);
    }
}