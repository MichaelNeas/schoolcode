/*Stack Implementation 
 *CSE2100 Project 2, Fall 2014
 *Michael Neas
 *9/21/2014
 *Instructor: Chun-Hsi (Vincent) Huang
 */

import java.util.Scanner;

public class StackANSVP
{
	private static LinkedStack<Integer> S = new LinkedStack<Integer>();
	private static int x[];
	private static Scanner kbd = new Scanner(System.in);
	private static String _singularString;
	private static String _stringArray[] = new String[50];


	public static void main(String[] args) 
	{
		System.out.println("Enter the sequence: ");		
		_singularString = kbd.nextLine();
		_stringArray = _singularString.split("\\s+");
		x = new int[_stringArray.length];
		for(int i = 0; i < _stringArray.length; i++) //b/c its not nested it is N time (only does it once)
		{
			x[i] = Integer.parseInt(_stringArray[i]);
		}
		nearestSmallestValue();
	}


	public static void nearestSmallestValue()
	{
		for(int i = 0; i < x.length; i++) //N time based exactly on input size
		{
			while(!S.isEmpty() && S.top() >= x[i]) //constant time operations, doing many things but the exact same thing every time
			{
				S.pop(); //always constant
			}

			if(S.isEmpty())
				System.out.print("- ");
			else
				System.out.print(S.top() + " ");

			S.push(x[i]);		
		}
	}

}
