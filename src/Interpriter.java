import java.util.ArrayList;

public class Interpriter {   

    public Interpriter() {
    }

    /**
     * Calculates a given equation.
     * 
     * @param equation String representing an equation.
     * @return result calculated from the equation.
     */
    public static Double calculate(String equation) {
    	
        double answer = 0;
        String[] splitEquation = splitEquation(equation);
        Stack<Double> nums = new Stack<Double>();
        Stack<String> ops = new Stack<String>(); 
        
        // main calculation loop
        for (int i = 0; i < splitEquation.length; i++) {
            String currentString = splitEquation[i];
            if (isOperator(currentString)) { // is an operator so add to stack or do a calculation if possible
                if (ops.isEmpty() || getOperatorPowerLevel(currentString) > getOperatorPowerLevel(ops.safePop())) { // adds current operator to stack if its Precedence is higher
                    ops.push(currentString);
                } else {
                    while (!ops.isEmpty() && getOperatorPowerLevel(currentString) <= getOperatorPowerLevel(ops.safePop())) {
                        String calculate = ops.pop();
                        doACalculation(calculate, ops, nums);
                    }
                    ops.push(currentString);
                }
            } else if (currentString != "(" || currentString != ")") { // is a value so add to stack 
                double value = Double.parseDouble(currentString);
                nums.push(value);
            } else if (currentString == "(") { // if its a bracket then add to stack so it can be calculate below
                ops.push(currentString);
            } else if (currentString == ")") { // performs a calculation on all parts of the values in brackets
                while (!ops.isEmpty() && isOperator(ops.safePop())) {
                    String calculate = ops.pop();
                    doACalculation(calculate, ops, nums);
                }
                if (!ops.isEmpty() && ops.safePop() == "(") {
                    ops.pop();
                } else {
                    System.out.println("Error: missing bracket in equation");
                }
            }
        }

        // clean Stacks before getting answer
        while (!ops.isEmpty() && isOperator(ops.safePop())) {
            String calculate = ops.pop();
            doACalculation(calculate, ops, nums);
            System.out.println(calculate);
        }

        // returning result left in value stack
        answer = nums.pop();

        // check for Errors in stacks
        if (!ops.isEmpty() || !nums.isEmpty()) {
            System.out.println("Error: invalid input format (values where left in stacks with no use)");
        }

        return answer;
    }

    /**
     * used for converting the input equation into its values and operators in separate parts for calculation
     * 
     * @param eq String representing an equation.
     * @return equation split into separate parts represented as an array.
     */
    private static String[] splitEquation(String eq) {
        ArrayList<String> splitEq = new ArrayList<String>();

        //remove white spaces from equation
        eq = eq.replaceAll("\\s+","");

        System.out.println("interpreted as:" + eq);

        //finds where chars in the string change from operators to values and adds them to spliEq once they are found
        int trackerPoint = 0;
        for (int i = 0; i < eq.length(); i++) {
            if (isOperator(eq.charAt(i)) || !isOperator(eq.charAt(i)) && isOperator(eq.charAt(trackerPoint))) {
                String val = eq.substring(trackerPoint, i);
                trackerPoint = i;
                splitEq.add(val);
            }
        }
        if (isOperator(splitEq.get(splitEq.size() - 1))) {
            String val = eq.substring(trackerPoint, eq.length());
            splitEq.add(val);
        }

        //convert from arraylist to string array
        String[] splitString = new String[splitEq.size()];
        splitEq.toArray(splitString);

        //System.out.println(splitString[0] + " " + splitString[1] + " " + splitString[2]);

        return splitString;

    }

    /**
     * Checks if the given string is an operator.
     * 
     * @param x String to be checked if an operator.
     * @return True if x is an operator. False, otherwise.
     */
    private static boolean isOperator(String x) {
        if (x.equals("+") || x.equals("-") || x.equals("*") || x.equals("/") || x.equals("^")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the given character is an operator.
     * 
     * @param x character to be checked if an operator.
     * @return True if x is an operator. False, otherwise.
     */
    private static boolean isOperator(char x) {
        if (x == '+' || x == '-' || x == '*' || x == '/' || x == '^') {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Takes an equation and determines if it's a valid equation.
     * 
     * @param equation String representing the equation.
     * @return True if the equation is valid. False, otherwise.
     */
    public static boolean isValidEquation(String equation)
    {
    	int numberOfOpenBrackets = 0;
    	int numberOfCloseBrackets = 0;
    	
    	// Accounts for equations such as 
    	//	*1+2+3
    	//	1+2+3*
    	//	)1+2+3
    	//	1+2+3(
    	if (isOperator(equation.charAt(0)) || isOperator(equation.charAt(equation.length() - 1))
    			|| equation.charAt(0) == ')' || equation.charAt(equation.length() - 1) == '(')
		{
			return false;
		}
    	
    	for (int currentChar = 0; currentChar < equation.length(); currentChar++)
    	{
    		if (equation.charAt(currentChar) == '(')
    		{
    			// Accounts for equations where an operator is after an open bracket such as:
    			//	1+(*2+3)
    			if (isOperator(equation.charAt(currentChar + 1)))
    			{
    				return false;
    			}
    			numberOfOpenBrackets++;
    		}
    		else if (equation.charAt(currentChar) == ')')
    		{   
    			// Accounts for equations where a close brackets is after an operator such as:
    			//	1+(2+3*)
    			if (isOperator(equation.charAt(currentChar - 1)))
    			{
    				return false;
    			}
    			numberOfCloseBrackets++;
    		}
    		// Accounts for equations that contain non-operator and non-digit characters.
    		else if (!isOperator(equation.charAt(currentChar)) && !Character.isDigit(equation.charAt(currentChar)))
    		{
    			return false;
    		}
    	}
    	
    	// Accounts for unpaired brackets such as:
    	//	1+(2+3
    	//	1+2+3)
    	if (numberOfOpenBrackets != numberOfCloseBrackets)
		{
			return false;
		}
    	 
    	return true;
    }

    /**
     * Resolves precedence levels for operators, 
     * i.e. Resolution of operators are as follows: 
     * 	exponent, multiplication or division, then addition or subtraction.
     * 
     * @param x String representing an operator.
     * @return the precedence level of the given operator.
     */
    private static int getOperatorPowerLevel(String x) {
        if (x.equals("+") || x.equals("-")) {
            return 1;
        } else if (x.equals("*") || x.equals("/")) {
            return 2;
        } else if (x.equals("^")) {
            return 3;
        }
        return 0;
    }

    /**
     * Resolves a calculator given an operator and the numbers involved.
     * 
     * @param operator
     * @param ops
     * @param nums
     */
    private static void doACalculation(String operator, Stack<String> ops, Stack<Double> nums) {
        double val1, val2;

        if (nums.isEmpty()) {
            System.out.println("Error in input");
            return;
        } else {
            val2 = nums.pop();
        }

        if (nums.isEmpty()) {
            System.out.println("Error in input");
            return;
        } else {
            val1 = nums.pop();
        }

        double output = 0;

        if (operator.equals("+")) {
            output = val1 + val2;
        } else if (operator.equals("-")) {
            output = val1 - val2;
        } else if (operator.equals("*")) {  
            output = val1 * val2;
        } else if (operator.equals("/")) {
            output = val1 / val2;
        } else if (operator.equals("^")) {
            output = Math.pow(val1, val2);
        }

        //System.out.println("Val1: " + val1 + ", Val2: " + val2 + ", Output: " + output);

        nums.push(output);
    }
}