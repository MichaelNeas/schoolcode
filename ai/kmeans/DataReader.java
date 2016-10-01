/*
 * Michael Neas
 * KMeans Implementation
 * Assignment 3
 * April 3, 2016
 */
import java.util.ArrayList;
import java.util.Scanner;

public class DataReader {
	private ArrayList<Double> xInput = new ArrayList<Double>();
	private ArrayList<Double> yInput = new ArrayList<Double>();
	//String[] stringArray;
	String myString = null;

	/*
	 * DataReader takes scanned input and will add to arraylists based off whitespace
	 */
	public DataReader(Scanner s) {
		myString = s.hasNext() ? s.next() : "";
		//System.out.println(myString);
		String[] stringArray = myString.split("\\s+");
		//System.out.println(stringArray[stringArray.length-1]);
		//for(String bleh : stringArray){
		//System.out.println(bleh);
		//}
		for(int i = 3; i<stringArray.length; i++){
			if(i%2 != 0){
				xInput.add(Double.parseDouble(stringArray[i]));
			}else{
				yInput.add(Double.parseDouble(stringArray[i]));
			}
		}
		//System.out.println(yInput.get(0));
		//DataObject dataObj = new DataObject(xInput, yInput);

	}

	/*
	 * Getters and setters
	 */
	public ArrayList<Double> getxInput() {
		return xInput;
	}

	public void setxInput(ArrayList<Double> xInput) {
		this.xInput = xInput;
	}

	public ArrayList<Double> getyInput() {
		return yInput;
	}

	public void setyInput(ArrayList<Double> yInput) {
		this.yInput = yInput;
	}


}
