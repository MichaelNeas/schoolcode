import java.util.Scanner;
/*this is how you type a block comment
*more
*more more
*/
//single line comment, start classes with a capital letter, and use only letters and digits
public class Chapter02 
{
	public static void main(String[] args)
	{					//start with lower case then all words after are caps, meaningful
		int firstVariable = 0; //integers (whole #) which is between -2bill/+2b
		double secondVariable = 2.9428372; //double for specific numbers 16 decimal places
		firstVariable = 1; //how you reassign the variable after its been initialized
		int adding = firstVariable + 6;
		double multiplying = secondVariable * 3.535;
		double total = 0.0;
		total = total + multiplying + adding;
		System.out.println(total);
		total = total + 1;
		System.out.println(adding + "penis\n" + multiplying);
		System.out.println(multiplying); //there's + - / * go figure..
		System.out.println();
		System.out.println(total);
		String firstString = "Hai!";
		String secondString = " The total for all the numbers is ";
		System.out.println(firstString + secondString + total);
		String escapeSequence = "Code: JSPS\nPrice: $49.50\rCool\ttabbing \"quotes\"\nAnd backslash\\";
		System.out.println(escapeSequence);
		//Scanner API
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter three integer values: ");
		int i1 = sc.nextInt();
		int i2 = sc.nextInt();
		int i3 = sc.nextInt();
		int total2 = i1 + i2 + i3;
		int avg = total2 / 3;
		System.out.println("Average: " + avg);
		System.out.println();
		if (avg <= 20)
		{
			System.out.println("Get some");
		}
		else if (avg == 9)
		{
			System.out.println("Lucky");
		}
		else 
		{
			System.out.println("Yeuh");
		}
	
	}
}
