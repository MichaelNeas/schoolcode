/*Equation Solver
 *CSE2100 Project 4, Fall 2014
 *Michael Neas
 *10/26/2014
 *Instructor: Chun-Hsi (Vincent) Huang
 */

import java.util.Comparator;

public class MergeSort<E> //generic merge sort for a doubly linked list
{
	public static <E> void mergeSort(DoublyLinkedList<E> listToSort, Comparator<E> comp)
	{
		int n = listToSort.size();
		if(n < 2) return; //splits up values into individual linked lists
		DoublyLinkedList<E> leftSide = new DoublyLinkedList<>();
		DoublyLinkedList<E> rightSide = new DoublyLinkedList<>();

		while(leftSide.size() < n/2) 
			leftSide.addLast(listToSort.removeFirst());
		while(!listToSort.isEmpty())
			rightSide.addLast(listToSort.removeFirst()); //splitting process to remove from bigger list into half sized lists
		mergeSort(leftSide, comp); // call mergeSort recursively until no pairs
		mergeSort(rightSide, comp);
		merge(leftSide, rightSide, listToSort, comp); //once all lists have been split put them together WRT the comparator
	}

	public static <E> void merge(DoublyLinkedList<E> leftInput, DoublyLinkedList<E> rightInput, DoublyLinkedList<E> sortedList, Comparator<E> comp)
	{	
		while(!leftInput.isEmpty() && !rightInput.isEmpty()) //merge together the lists until one is empty
		{
			if(comp.compare(leftInput.first(), rightInput.first()) < 0) //comparator function to control which gets inserted
				sortedList.addLast(leftInput.removeFirst()); //small gets added last 
			else
				sortedList.addLast(rightInput.removeFirst());
		}
		while(!leftInput.isEmpty()) //fills the remaining list with left overs 
			sortedList.addLast(leftInput.removeFirst());
		while(!rightInput.isEmpty())
			sortedList.addLast(rightInput.removeFirst());
	}
}
