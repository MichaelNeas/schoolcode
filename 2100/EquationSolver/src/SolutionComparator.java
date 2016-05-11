/*Equation Solver
 *CSE2100 Project 4, Fall 2014
 *Michael Neas
 *10/26/2014
 *Instructor: Chun-Hsi (Vincent) Huang
 */

import java.util.Comparator;

public class SolutionComparator implements Comparator<SolutionInfo> { //comparator class allows for easy comparing of values in mergeSort

	@Override
	public int compare(SolutionInfo o1, SolutionInfo o2) { //Spaghetti code from SolutionInfo.java
		if(o1.get_sol() < o2.get_sol())//if the solution is less than the other solution return -1
			return -1;
		else if (o1.get_sol() == o2.get_sol()) //if they're the same give a 0
			return 0;
		else //else give back a 1, this all allows for sorting procedure to prioritize
			return 1;
	}

}
