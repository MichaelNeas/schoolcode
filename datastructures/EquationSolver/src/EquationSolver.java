/*Equation Solver
 *Michael Neas
 */

import java.util.Scanner;

public class EquationSolver {
	public static Scanner kbd = new Scanner(System.in);
	public static DoublyLinkedList<SolutionInfo> abcSide = new DoublyLinkedList<SolutionInfo>(); //hold left values
	public static DoublyLinkedList<SolutionInfo> defSide = new DoublyLinkedList<SolutionInfo>(); //hold right values

	public static void main(String[] args){
		System.out.print("Welcome to EquationSolver, enter an integer max element size greater than 74: ");
		int N = kbd.nextInt();
		if(N < 75){//instructions say that N can be as small as 75 so I made this
			System.out.println("Input is too small, try again bigger than 74!");
			System.exit(1);
		}
		allSolvableEquations(N); // creates a list for all possible equations right or not
		//		System.out.println(abcSide.toString()); ---- will show all objects in DLL
		//while(abcSide.isEmpty() == false) test ----- to show that there is an unordered DLL
		//	System.out.println(abcSide.removeFirst().get_sol());

		MergeSort.mergeSort(abcSide, new SolutionComparator()); //sorts for comparison
		MergeSort.mergeSort(defSide, new SolutionComparator());

		//		while(abcSide.isEmpty() == false) ---- test to get sorted list
		//			System.out.println(abcSide.removeFirst().get_sol());

		System.out.println(" A  B  C  D  E  F");
		findSolutions(abcSide, defSide); //takes the two lists and compares them
		System.out.println("Have a good day.");
	}

	private static void findSolutions(DoublyLinkedList<SolutionInfo> leftSide, DoublyLinkedList<SolutionInfo> rightSide){
		while(rightSide.first().get_sol() <= 0) //removes negatives
			rightSide.removeFirst();
		//System.out.println(rightSide.first().get_sol()); ---- test to show first is positive now

		while(!leftSide.isEmpty() && !rightSide.isEmpty()){
			if(leftSide.first().get_sol() == rightSide.first().get_sol()) //if the first two are the same there could be a solution
				if(leftSide.first().get_cf() <= rightSide.first().get_ad()){//conditional check where c <= d
					leftSide.first().printData();
					rightSide.first().printData();
					System.out.println();
					leftSide.removeFirst();
					rightSide.removeFirst(); //if it checks out it'll return solutions and remove them to check for more
				} else{
					leftSide.removeFirst(); //or else it just removes them and checks next
					rightSide.removeFirst();
				} else if(leftSide.first().get_sol() < rightSide.first().get_sol())
					leftSide.removeFirst();
				else
					rightSide.removeFirst();
		}
		//System.out.println("No Solution"); --- it wont reach this because there will always be a solution if input >=75
	}

	public static void allSolvableEquations(int n){
		long ad,be,cf, aNoRoot, bNoRoot, cNoRoot, sumLeft, sumRight; //store variables respectively
		for(int i=1; i<=n; i++)  
			for(int j=i; j<=n; j++)
				for(int k=j; k<=n; k++){//triple for-loop to assign all possible values up to n following conditions
					aNoRoot=i;
					bNoRoot=j;
					cNoRoot=k;//no roots for easier printing
					ad = (long)Math.pow(i, 5); 
					be = (long)Math.pow(j, 5);
					cf = (long)Math.pow(k, 5);//powers of all to get the sums
					sumLeft = ad + be + cf;
					sumRight = cf - (ad + be);//sums with the equations as required
					abcSide.addLast(new SolutionInfo(aNoRoot, bNoRoot, cNoRoot, sumLeft)); 	
					defSide.addLast(new SolutionInfo(aNoRoot, bNoRoot, cNoRoot, sumRight)); //ads them to the DLL
				}
	}
}
