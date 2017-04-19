package eu.thephisics101.parser;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

public class MathParserTest {
    @Test
    public void testMathParser() throws Exception {
        MathParser parser = new MathParser();

        double result = parser.parser("1+1");

        Assert.assertThat(result, CoreMatchers.is(2d));
    }
}
