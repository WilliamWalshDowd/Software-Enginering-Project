import java.text.DecimalFormat;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


@RunWith(JUnit4.class)
public class CalculatorTests {

    @Test
    public void calculatorTests() {

        // basic addition test
        String test2Input = "5+5";
        String test2Output = "10.00";
        assertEquals("Test with basic addition '5+5' ", test2Output, Interpriter.calculate(test2Input));

        // complex addition test
        String test3Input = "5+5 +    2.5556 +   7+ .1101 +  44444444.4";
        String test3Output = "44444464.07";
        assertEquals("Test with complex additon '5+5 +    2.5556 +   7+ .1101 +  44444444.4' ", test3Output, Interpriter.calculate(test3Input));


        // basic subtraction test
        String test4Input = "5-5";
        String test4Output = "0.00";
        assertEquals("Test with basic subtraction '5-5' ", test4Output, Interpriter.calculate(test4Input));

        // complex subtraction test
        String test5Input = "5-5 -    2.5556 -   7- .1101 -  4444.4";
        String test5Output = "-4454.07";
        assertEquals("Test with complex subtraction '5-5 -    2.5556 -   7- .1101 -  44444444.4' ", test5Output, Interpriter.calculate(test5Input));
        
        // basic multiplication test
        String test6Input = "5*5";
        String test6Output = "25.00";
        assertEquals("Test with basic multiplication '5*5' ", test6Output, Interpriter.calculate(test6Input));

        // complex multiplication test
        String test7Input = "5*5 *    2.5556 *   0.7* .1101 *  44444444.4";
        String test7Output = "218844546.45";
        assertEquals("Test with complex multiplication '5*5 *    2.5556 *   0.7* .1101 *  44444444.4' ", test7Output, Interpriter.calculate(test7Input));


        // basic division test
        String test8Input = "5/5";
        String test8Output = "1.00";
        assertEquals("Test with basic division '5/5' ", test8Output, Interpriter.calculate(test8Input));

        // complex division test
        String test9Input = "44444444.4 /    2.5556 /   0.7/ .1101 /  5";
        String test9Output = "45130405.81";
        assertEquals("Test with complex division '44444444.4 /    2.5556 /   0.7/ .1101 /  5' ",test9Output, Interpriter.calculate(test9Input));


        // basic exponent test
        String test10Input = "5^5";
        String test10Output = "3125.00";
        assertEquals("Test with basic exponent '5^5' ", test10Output, Interpriter.calculate(test10Input));

        // complex exponent test
        String test11Input = "44 ^    2.5556 ^ 0.232";
        String test11Output = "9.43";
        assertEquals("Test with complex exponent '44 ^    2.5556 ^ 0.232' ", test11Output, Interpriter.calculate(test11Input));


        // basic bracket test
        String test12Input = "(5+5) + (4+4)";
        String test12Output = "18.00";
        assertEquals("Test with basic bracket '(5+5) + (4+4)' ", test12Output, Interpriter.calculate(test12Input));

        // complex bracket test
        String test13Input = "(44 ^    2.5556) ^ 0.232";
        String test13Output = "9.43";
        assertEquals("Test with complex bracket '(44 ^    2.5556) ^ 0.232' ", test13Output, Interpriter.calculate(test13Input));
    }
}

