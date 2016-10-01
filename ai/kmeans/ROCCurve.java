/*
 * Michael Neas, AI HW2 part 2
 */

import java.util.Arrays;
import java.util.Comparator;

/*
 * ROCCurve takes 2 arrays of data, where one is the true checks and the other being the probabalistic output given by some function
 */
public class ROCCurve {
	static double[] output = {0.3105, -0.2495, 0.5037, -0.8927, 1.9085, 0.1222, 1.0470,-0.2269,-0.1625, 0.6901,0.5558,-1.1203,-1.5327,-1.0979, -1.4158, 0.0596, -0.4113, -0.3680, -1.3610, 0.7796};
	static double[] trueValues = {1.0000, 1.0000,1.0000, 1.0000, 1.0000, 1.0000, 1.0000, 1.0000,1.0000,1.0000,-1.0000,-1.0000,-1.0000,-1.0000,-1.0000,-1.0000,-1.0000,-1.0000,-1.0000,-1.0000};
	static OutputPair[] pairArray = new OutputPair[20];
	
	/*
	 * make pair combines both arrays into one array of pairs with out and true values
	 * It uses the built in sort function to sort the outputs in descending order
	 * where i overwrote the compare method to compare two objects based on their output values
	 */
	public OutputPair[] makeOpair(){
		for(int i = 0; i<output.length; i++){
			pairArray[i] = new OutputPair(trueValues[i],output[i]);
		}
		Arrays.sort(pairArray, new Comparator<OutputPair>(){

			@Override
			public int compare(OutputPair o1, OutputPair o2) {
				return -Double.compare(o1.getClassifierOutput(), o2.getClassifierOutput());
			}
		});
		for(OutputPair obs : pairArray){
			System.out.println(obs.getClassifierOutput());
		}
		return pairArray;
	}
	
	/*
	 * Main will create a new sorted data set
	 * Then it will iterate through the data set and essentially make a table of graphing points based on the varying threshold
	 * I print a to string method to show the changing tpr and fpr as the threshold moves down.
	 */
	public static void main(String[] args) {
		ROCCurve doit = new ROCCurve();
		doit.makeOpair();
		for(int i=0; i<20; i++){
			ROCPoints graphPoints = new ROCPoints(pairArray, pairArray[i].getClassifierOutput());
			graphPoints.thresholdCorrection();
			System.out.println(graphPoints.toString());
		}
		System.out.println("Done!");
	}

}
