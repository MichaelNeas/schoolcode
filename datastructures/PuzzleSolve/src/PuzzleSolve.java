/*Puzzle Solve 
 *Michael Neas
 */

import java.util.Scanner;

public class PuzzleSolve {
	private static Scanner kbd = new Scanner(System.in);
	private static String _puzzleString; //stores the entire input string
	private static String _alphabet = "";//initialize _alphabet

	public static void main(String[] args) {
		puzzleSolve();//call puzzleSolve()
	}

	public static void puzzleSolve(){
		System.out.println("Enter a combination less than 10 variables: ");
		String currentPuzzleString = kbd.nextLine();
		getUniques(currentPuzzleString); //calls method below
		SinglyLinkedList<Integer> assignmentList = new SinglyLinkedList<Integer>(); //create empty SLL
		SinglyLinkedList<Integer> unusedNumbers = new SinglyLinkedList<Integer>(); //create SLL 0->9 for use later
		for(int j = 0; j < 10; j++){
			unusedNumbers.addLast(j);
		}
		System.out.println(_alphabet + "       |      ---Equations---"); //returns the string w/o permutations/spaces/+/=
		recursiveSolve(_alphabet.length(), unusedNumbers, assignmentList); //call to recursive step
	}

	public static void getUniques(String puzzleString) //input handling{
		setPuzzleString(puzzleString); //set the puzzle string
		for(int i = 0; i < puzzleString.length(); i++){
			String holdValue = Character.toString(puzzleString.charAt(i));//pull character from string and store it
			if(!holdValue.equals("="))
				if(!holdValue.equals("+"))
					if(!holdValue.equals(" ")){//bad programming but || was messing up for some reason
						if(_alphabet.length() != 10 && !_alphabet.contains(holdValue)) {//limits max and checks for repeats
							_alphabet = _alphabet.concat(holdValue); //builds the string
						}else if(_alphabet.length() >= 10){
							System.out.println("Too many input letters, puzzle is unsolvable");
							System.exit(0); //quits program when inputs are too large
						}
					}
		}
		//System.out.println(_alphabet) -- test case;
	}


	public static void evaluate(SinglyLinkedList<Integer> assignList) {//checks the assignmentList if it works!
		String tempPuzzleString = _puzzleString;
		String tempAlphabet = _alphabet;
		tempPuzzleString = tempPuzzleString.replace(" ", ""); //temporary values to allow manipulation without destroying orig

		for(int n = 0; n < assignList.size(); n++){	
			String tempValue = assignList.first().toString(); //get the related numbers
			String stringLetter = Character.toString(tempAlphabet.charAt(n)); //get the letters

			tempPuzzleString = tempPuzzleString.replaceAll(stringLetter, tempValue); //replacing all the instances of letters with numbers
			//System.out.println(tempPuzzleString); -- test
			assignList.addLast(assignList.removeFirst()); //add back to the list for good form
		}

		String[]valueOfParts = tempPuzzleString.split("[+=]");
		int[] convertToInt = new int[valueOfParts.length];
		//System.out.println(Arrays.toString(valueOfParts)); -- will print out all possibilities
		for(int k = 0; k < valueOfParts.length; k++){
			convertToInt[k] = Integer.parseInt(valueOfParts[k]); //stores the string variables into array of ints
		}
		
		int leftSide = 0; //BONUS -- to allow for the left side to have multiple plus's
		for(int q = 0; q < convertToInt.length-1; q++) {//up to length - 1
			leftSide = leftSide + convertToInt[q]; //adds up the left side
		}

		if(leftSide == convertToInt[convertToInt.length-1]) {//simple comparison
			for(int p = 0; p < assignList.size(); p++){
				System.out.print(assignList.first());
				assignList.addLast(assignList.removeFirst()); //this is for the print of values as required by format
			}
			System.out.println("    " + "   |      Solved by: " + tempPuzzleString); //will do a tab effect
			//System.out.println("This is a correct input solution: " + Arrays.toString(valueOfParts));
		}
	}

	public static void recursiveSolve(int d, SinglyLinkedList<Integer> uNumbers, SinglyLinkedList<Integer> assignList){
		if(d == 0) //base case
			evaluate(assignList); //evaluate when base case is hit(-crucial-)
		else{
			for(int l = 0; l < uNumbers.size(); l++){	
				assignList.addFirst(uNumbers.removeFirst()); //takes the number from the unassigned and assigns them
				recursiveSolve(d - 1, uNumbers, assignList); //recursive step
				uNumbers.addLast(assignList.removeFirst()); //adds the numbers back for all possibilities
			}
		}
	}

	public static String getPuzzleString() {//getter
		return _puzzleString;
	}

	public static void setPuzzleString(String puzzleString)  {//setter
		PuzzleSolve._puzzleString = puzzleString;
	}
}
