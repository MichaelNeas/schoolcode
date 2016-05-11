/*PQScheduler
 *CSE2100 Project 5, Fall 2014
 *Michael Neas
 *11/12/2014
 *Instructor: Chun-Hsi (Vincent) Huang
 */

import java.util.Scanner;

public class PQScheduler 
{
	public static Scanner kbd = new Scanner(System.in);
	public static HeapPQ<Integer,String> heap = new HeapPQ<Integer, String>(); //creates the pq heap

	public static void main(String[] args)
	{
		System.out.println("Please input the correct format to simulate CPU job handling, type 'Exit' to terminate.");
		while(!kbd.hasNext("exit")) //keep it in the loop
		{
			String _initialInput = kbd.nextLine(); //gets input from user (thanks for no I/O)
			if(_initialInput.contains("no new job"))
			{
				decrement(); //no given input so just decrement
			}
			else if(_initialInput.contains("add job")) //add job call
			{
				heapOp(_initialInput); //split the input then decrement
				decrement();
			}
			else if(_initialInput.equalsIgnoreCase("jobs left?")) //check how many jobs are left
			{
				System.out.println("There are " + heap.size() + " jobs left to process.");
			}
			else
				System.out.println("Please format as expected, or type 'exit' to terminate."); //help statement
		}
		System.out.println("Have a good day!");
	}
	private static void decrement() //'process' one length unit
	{
		if(!heap.isEmpty()) //if the heaps not empty
		{
			String tempDecVar[] = heap.min().getValue().split(","); //create a string array from min element
			int holdKey = heap.min().getKey(); //temporary key for reference later
			int newLength = Integer.parseInt(tempDecVar[1]) - 1; //decrementing step
			if(newLength == 0) //check for length
			{
				heap.removeMin(); //remove the min from the heap since the length says its done
				System.out.println(tempDecVar[0] + " " + ":: Processing has been completed.");
			}
			else
			{
				heap.removeMin(); //or else remove the min then add the decremented 
				heap.insert(holdKey, tempDecVar[0] + "," + newLength);
				System.out.println(tempDecVar[0] + " " + ":: being processed ");
			}
		}
		else
			System.out.println("There's nothing in the priority queue to be processed.");
	}

	public static void heapOp(String input)
	{
		String tempStringArray[] = input.split(" "); //create array of strings from input
		//System.out.println(tempStringArray[2] + "," + tempStringArray[5]);
		//insert in to heap with the format {int key, string value and length}
		heap.insert(Integer.parseInt(tempStringArray[8]), tempStringArray[2] + "," + tempStringArray[5]); 
		//System.out.println(heap.min().getValue());
	}
}
