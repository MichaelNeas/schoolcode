/*
 * Michael Neas
 */
import java.util.Scanner;

public class Calculator {
	
	public static void main(String[] args)
	{
		Scanner kbd = new Scanner(System.in);
		boolean contin = true;
		double accumulator = 0.0;
		double input = 0.0;
		while(contin)
		{
			System.out.println("Accumulator = " + accumulator);
			System.out.println("Please choose one of the following options:");
			System.out.println("0) Exit");
			System.out.println("1) Addition");
			System.out.println("2) Subtraction");
			System.out.println("3) Multiplication");
			System.out.println("4) Division");
			System.out.println("5) Square Root");
			System.out.println("6) Clear");
			System.out.print("What is your choice? ");
			int choice = kbd.nextInt();
			if (choice == 0)
			{
				contin = false;
			}
			else if (choice == 1)
			{
				System.out.print("Enter a number: ");
				input = kbd.nextDouble();
				accumulator = accumulator + input;
			}
			else if (choice == 2)
			{
				System.out.print("Enter a number: ");
				input = kbd.nextDouble();
				accumulator = accumulator - input;
			}
			else if (choice == 3)
			{
				System.out.print("Enter a number: ");
				input = kbd.nextDouble();
				accumulator = accumulator * input;
			}
			else if (choice == 4)
			{
				System.out.print("Enter a number: ");
				input = kbd.nextDouble();
				accumulator = accumulator / input;
			}
			else if (choice == 5)
			{
				accumulator = Math.sqrt(accumulator);
			}
			else if (choice == 6)
			{
				accumulator = 0.0;
			}
			else
			{
				System.out.println("Illegal operation: " + choice);
			}
		}
	}

}