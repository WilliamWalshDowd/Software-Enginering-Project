import java.util.Scanner;

public class Calculator 
{
	// Set scanner to a universal variable so it can be used by all functions.
	private static Scanner input;
	/**
	 * Main.
	 */
	public static void main(String[] args) 
	{
		// Have dummy character array here so it enters the while loop.
		String equation = "";
		
		// As long as the user's input is not 'exit', then loop indefinitely.
		while (equation != null)
		{
			equation = askInput();
			
			//Call function here to resolve equation.
			System.out.println("Answer: " + Interpriter.calculate(equation));
		}
		
		// Closes scanner input.
		input.close();
	}
	
	/**
	 * 
	 * @return character array to be used to resolve calculations.
	 */
	public static String askInput()
	{
		boolean exit = false;
		String userInput = null;
		
		while(!exit)
		{
			// Creates scanner for user input.
			input = new Scanner(System.in);
			System.out.println("Please enter an equation for the calculator. You may use addition(+), subtraction(-), multiplication(*) and Exponentials(^) or type 'exit' to leave.");
			if (input.hasNextLine())
			{
				String answer = input.nextLine();
				if (answer.equalsIgnoreCase("exit"))
				{
					exit = true;
					System.out.println("You have exited the program");
				}
				else
				{
					exit = true;
					userInput = answer;
				}
			}
		}
		
		return userInput;
	}

}
