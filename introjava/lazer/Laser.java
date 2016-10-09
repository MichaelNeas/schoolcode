/*Mirrors&Grids
 */

import java.util.Scanner;


public class Laser 
{
	static int _rows;
	static int _columns;
	static int north = 0; //package private
	static int south = 1;
	static int east = 2;
	static int west = 3;
	static int fire = -1;
	static char[][] _squares; //2-d array 
	static Scanner kbd = new Scanner(System.in);
	static String[] initStrings =
		{
		 "...../...\\",
		 "..\\.......",
		 "......./..",
		 "..........",
		 "........\\.",
		 "..........",
		 "..........",
		 ".....\\../.",
		 "..\\....../",
		 ".........."
		};
	public static void initialize()
	{
		_rows = initStrings.length; //same length of the array
		_columns = initStrings[0].length(); //same length of any column of the array
		_squares = new char[_rows][_columns]; //creates squares the m rows and n columns if considering mxn matrix
		
		for(int i=0; i<_columns; i++)
		{
			for (int j=0; j<_rows; j++) //inner for loop to pass the rows
			{
				_squares[i][j] = (initStrings[i].charAt(j)); //character at this location
			}
		}
	}
	public static void show()
	{
		for(int i=0; i<_columns; i++) //for loop to traverse through the columns
		{
			System.out.print(i); //number of columns to the left
			if(fire == i) //if fire is in the specific row then > sign to show where it came from
			{
				System.out.print(">");
			}
			else
				System.out.print(" ");
			for (int j=0; j<_rows; j++) //inside each of the columns go through each row individually
			{
				System.out.print(_squares[i][j]); //prints symbols at locations
			}
			System.out.println("");//new line
		}
	}
	
	public static void fire()
	{
		   int currentRow = fire; //start where fired
		   int currentColumn = 0; //starting in 0 columns                                       
		   int moving = east; //always going to go east after shot
		   
		 
		while(currentColumn >= 0 && currentRow >= 0 && currentColumn <= 9 && currentRow <= 9) //while loop to check the laser is still in bounds
		   {
		      
		     char currentChar = _squares [currentRow] [currentColumn]; //current location of the laser beam
		       
		     if(currentChar == '.' && moving == east)
		     {
		    	 _squares [currentRow] [currentColumn] = '-'; //if on . space and moving east then replace with - and move 1 column to the right
		    	 currentColumn += 1;
		     }
		     else if(currentChar == '.' && moving == west) 
		     {
		    	 _squares [currentRow] [currentColumn] = '-';
		    	 currentColumn -= 1;
		     } 
		     else if(currentChar == '.' && moving == north)
		     {
		    	 _squares [currentRow] [currentColumn] = '|';
		    	 currentRow -= 1;
		     }
		     else if(currentChar == '.' && moving == south)
		     {
		    	 _squares [currentRow] [currentColumn] = '|'; //build categorizes south as positive move down a row and replace . with |
		    	 currentRow += 1;
		     }
		     else if(currentChar == '/' && moving == east) //hits a mirror moving east then start going north
		     {
		    	 currentRow -= 1;
		    	 moving = north;
		     }
		     else if(currentChar == '/' && moving == west)
		     {
		    	 currentRow += 1;
		    	 moving = south;
		     }
		     else if(currentChar == '/' && moving == north)
		     {
		    	 currentColumn += 1;
		    	 moving = east;
		     }
		     else if(currentChar == '/' && moving == south) 
		     {
		    	 currentColumn -=1;
		    	 moving = west;
		     }
		     else if(currentChar == '\\' && moving == east) // '\\' is \ so its a mirror at 45degrees and direction should change accordingly
		     {
		    	 currentRow += 1;
		    	 moving = south;
		     }
		     else if(currentChar == '\\' && moving == west)
		     {
		    	 currentRow -= 1;
		    	 moving = north;
		     }
		     else if(currentChar == '\\' && moving == north)
		     {
		    	 currentColumn -= 1;
		    	 moving = west;
		     }
		     else if(currentChar == '\\' && moving == south)
		     {
		    	 currentColumn += 1;
		    	 moving = east;
		     }
		     else if(currentChar == '-' && moving == north) //replacement of cross paths!
		     {
		    	 _squares [currentRow] [currentColumn] = '+';
		    	 currentRow -= 1;
		     }
		     else if(currentChar == '-' && moving == south)
		     {
		    	 _squares [currentRow] [currentColumn] = '+';
		    	 currentRow += 1;
		     }
		     else if(currentChar == '|' && moving == east)
		     {
		    	 _squares [currentRow] [currentColumn] = '+';
		    	 currentColumn += 1;
		     }
		     else if(currentChar == '|' && moving == west)
		     {
		    	 _squares [currentRow] [currentColumn] = '+';
		    	 currentColumn -= 1;
		     }
		   }
		 }
		
	
	public static void main(String[] args)
	{
		initialize(); //1-d Array of strings into 2-d array of characters
		show(); //prints the map on screen to show where you can fire
		System.out.print("So you would like to fire the laser!? From which location?: ");
		fire = kbd.nextInt(); //snags user input
		fire(); //allows for the laser to run through the path
		show(); //prints the new path after laser was fired
	}
}
