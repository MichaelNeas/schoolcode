/*Stack Implementation 
 *CSE2100 Project 2, Fall 2014
 *Michael Neas
 *9/21/2014
 *Instructor: Chun-Hsi (Vincent) Huang
 */

import java.util.Scanner;

public class BigOhComparison {
	private static SinglyLinkedList<Integer> nS = new SinglyLinkedList<Integer>();
	private static int x[];
	private static Scanner kbd = new Scanner(System.in);
	private static String _singularString;
	private static String _stringArray[] = new String[50];

	public static void main(String[] args) {
		System.out.println("Enter the sequence: ");		
		_singularString = kbd.nextLine();
		_stringArray = _singularString.split("\\s+");
		x = new int[_stringArray.length];
		for(int i = 0; i < _stringArray.length; i++) {//b/c its not nested it is N time (only does it once)
			x[i] = Integer.parseInt(_stringArray[i]);
		}
		nearestSmallestValueNested4Loop();
	}

	public static void nearestSmallestValueNested4Loop(){
		for(int i = 0; i < x.length; i++){
			for(int j = i; j >= 0; j--){
				if(nS.isEmpty()){
					System.out.print("- ");
					nS.addFirst(x[i]);
					break;
				} else if(x[i] > nS.first()) {
					System.out.print(nS.first() + " ");
					nS.addFirst(x[i]);
					break;
				}else
					nS.removeFirst();
			}
		}
	}
}
