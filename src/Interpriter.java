import java.util.ArrayList;

public class Interpriter {   

    public Interpriter() {
    }

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

    //used for converting the input equation into its values and operators in seperate parts for calculation
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

    private static boolean isOperator(String x) {
        if (x.equals("+") || x.equals("-") || x.equals("*") || x.equals("/") || x.equals("^")) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isOperator(char x) {
        if (x == '+' || x == '-' || x == '*' || x == '/' || x == '^') {
            return true;
        } else {
            return false;
        }
    }

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
