package accumulator;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class StringAccumulatorTest {

    private StringAccumulator stringAccumulator;

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Before
    public void init() {
        stringAccumulator = new StringAccumulator();
    }

    @Test
    public void add() {
        String numberStr = "1,2,3";
        int sum = stringAccumulator.add(numberStr);
        assertThat(sum, is(6));
    }

    @Test
    public void add2() {
        String numberStr = "1,2,3";
        int sum = stringAccumulator.add(numberStr);
        assertThat(sum, is(6));
    }

    @Test
    public void add3() {
        String numberStr = "1\n2,3";
        int sum = stringAccumulator.add(numberStr);
        assertThat(sum, is(6));
    }

    @Test
    public void addNumbersWithGreaterThanAllowedLimit() {
        String numberStr = "1\n1002,3";
        int sum = stringAccumulator.add(numberStr);
        assertThat(sum, is(4));
    }

    @Test
    public void addNumbersWithGreaterThanAllowedLimit2() {
        String numberStr = "1\n1002,1000,1005,10010,100000,3";
        int sum = stringAccumulator.add(numberStr);
        assertThat(sum, is(1004));
    }

    @Test
    public void add5() {
        String numberStr = "//***\n1***2***3";
        int sum = stringAccumulator.add(numberStr);
        assertThat(sum, is(6));
    }

    @Test
    public void add6() {
        String numberStr = "//*|1*2%3";
        int sum = stringAccumulator.add(numberStr);
        assertThat(sum, is(6));
    }

    @Test
    public void addNumbersWithNegative() {
        String numberStr = "//*|-1*3";
        expected.expect(RuntimeException.class);
        expected.expectMessage("negative numbers not allowed :-1");
        stringAccumulator.add(numberStr);
    }

    @Test
    public void addNumbersWithNegative2() {
        String numberStr = "//*|-1-2-4*5";
        expected.expect(RuntimeException.class);
        expected.expectMessage("negative numbers not allowed :-1,-2,-4");
        stringAccumulator.add(numberStr);
    }

    @Test
    public void addNumbersWithMultipleDelimeters() {
        String numberStr = "//***\n1;;;****2||||||||********3";
        int sum = stringAccumulator.add(numberStr);
        assertThat(sum, is(6));
    }

    @Test
    public void addManyNumbers() {
        String numberStr = "//***\n1;;;****2||||||||********3*****4\n\n\n\n\n5%%%%%%%%****************6$$$$$$$$$$$$$$$$$*****************7****10*******15@@@@@@@@@25";
        int sum = stringAccumulator.add(numberStr);
        assertThat(sum, is(78));
    }
}