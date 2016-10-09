import java.util.Scanner;


public class Main {

	static String getText(){   // gets input text from user and returns it to be stored in a variable.
		@SuppressWarnings("resource")
		Scanner scnr = new Scanner(System.in);  // opens a scanner
		String line;   // creates the line string variable
		String string= "";  // places the empty string in the string variable so that things can be added to it
		System.out.println("Enter text to store in a file, when finished leave the\nline empty and hit enter. ");  // prompts the user for input
		while(true){  // while true is true (forever)
			line = scnr.nextLine();  // get user input and store it in the line variable

			if(line.isEmpty()){  // check if the line variable is empty
				break;	 // if it is then 
			}
			string += line + "\n";  // append the line variable to the string variable and create a new line
		}
		return string;	// returns completed string
	}

	public static void main(String[] args) {
		System.out.print("Please enter a file name: ");  // asks the user to name the file
		Scanner kbd = new Scanner(System.in);  // opens the scanner
		String name = kbd.next();   // stores the given name in the name variable
		String stringToWrite=getText();  // calls getText() and stores the return value in the stringToWrite variable
		WriteFile.go(name, stringToWrite);  // calls the go method from the class WriteFile on the name and stringToWrite variables 
		String readString =ReadFile.go(name);  // calls the go method from the class ReadFile on the name  variable and returns it to the readString variable
		System.out.println(readString);  // displays the readString variable
		kbd.close();  //closes the kbd scanner 
		System.out.println("Don't fret, the program is finished");  // reassuring message to the user
	}

}
