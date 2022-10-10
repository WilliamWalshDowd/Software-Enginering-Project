import static org.junit.Assert.assertEquals;

import java.beans.Transient;
import java.io.IOException;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


@RunWith(JUnit4.class)
public class CalculatorTests {


    @Test
    public void calculatorTests() {

        // basic addition test
        String test2Input = "5+5";
        Double test2Output = 10.0;
        assertEquals("Test with basic addition '5+5' ", test2Output, Interpriter.calculate(test2Input));

        // complex addition test
        String test3Input = "5+5 +    2.5556 +   7+ .1101 +  44444444.4";
        Double test3Output = 44444464.0657;
        assertEquals("Test with complex additon '5+5 +    2.5556 +   7+ .1101 +  44444444.4' ", test3Output, Interpriter.calculate(test3Input));


        // basic subtraction test
        String test4Input = "5-5";
        Double test4Output = 0.0;
        assertEquals("Test with basic subtraction '5-5' ", test4Output, Interpriter.calculate(test4Input));

        // complex subtraction test
        String test5Input = "5-5 -    2.5556 -   7- .1101 -  44444444.4";
        Double test5Output = -44444454.0657;
        assertEquals("Test with complex subtraction '5-5 -    2.5556 -   7- .1101 -  44444444.4' ", test5Output, Interpriter.calculate(test5Input));


        // basic multiplication test
        String test6Input = "5*5";
        Double test6Output = 25.0;
        assertEquals("Test with basic multiplication '5*5' ", test4Output, Interpriter.calculate(test4Input));

        // complex multiplication test
        String test7Input = "5*5 *    2.5556 *   0.7* .1101 *  44444444.4";
        Double test7Output = 218844546.448;
        assertEquals("Test with complex multiplication '5*5 *    2.5556 *   0.7* .1101 *  44444444.4' ", test5Output, Interpriter.calculate(test5Input));


        // basic division test
        String test8Input = "5/5";
        Double test8Output = 1.0;
        assertEquals("Test with basic division '5/5' ", test4Output, Interpriter.calculate(test4Input));

        // complex division test
        String test9Input = "44444444.4 /    2.5556 /   0.7/ .1101 /  5";
        Double test9Output = 45130405.8083;
        assertEquals("Test with complex division '44444444.4 /    2.5556 /   0.7/ .1101 /  5' ", test5Output, Interpriter.calculate(test5Input));


        // basic division test
        String test8Input = "5/5";
        Double test8Output = 1.0;
        assertEquals("Test with basic division '5/5' ", test4Output, Interpriter.calculate(test4Input));

        // complex division test
        String test9Input = "44444444.4 /    2.5556 /   0.7/ .1101 /  5";
        Double test9Output = 45130405.8083;
        assertEquals("Test with complex division '44444444.4 /    2.5556 /   0.7/ .1101 /  5' ", test5Output, Interpriter.calculate(test5Input));
    }
}
