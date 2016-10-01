/*
 * Michael Neas, AI HW 2 part 2
 */

/*
 * ROCPoints does all the calculations while the threshold moves to fill the table
 */
public class ROCPoints {
	private double truePositive = 0;
	private double falsePositive = 0;
	private double trueNegative = 0;
	private double falseNegative = 0;
	private OutputPair[] theData;
	private double threshold;
	private double tpr;
	private double fpr;
	
	/*
	 * The constructor takes the pair array and a threshold and calls threshold correction to get a new sum of variables
	 */
	public ROCPoints(OutputPair[] anArray, double threshold){
		this.theData = anArray;
		this.threshold = threshold;
		thresholdCorrection();
	}
	
	/*
	 * Threshold Correction is where the new types are set according to what the threshold is limiting
	 * where everything above the threshold should be assumed positive and everything below should be assumed negative
	 */
	public void thresholdCorrection(){
		for(OutputPair ob : theData){
			if(ob.getClassifierOutput() >= threshold && ob.getTrueLabel() == 1){
				ob.setRocType("TP");
				truePositive++;
			}else if(ob.getClassifierOutput() >= threshold && ob.getTrueLabel() == -1){
				ob.setRocType("FP");
				falsePositive++;
			}else if(ob.getClassifierOutput() < threshold && ob.getTrueLabel() == 1){
				ob.setRocType("FN");
				falseNegative++;
			}else{
				ob.setRocType("TN");
				trueNegative++;
			}
		}
		setTpr(truePositive/(truePositive + falseNegative));
		setFpr(falsePositive/(falsePositive + trueNegative));
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * To string to print out the values per new threshold
	 */
	public String toString(){
		return "TPR: " + tpr + " FPR: " + fpr;
	}
	
	/*
	 * getters and setters
	 */

	public double getTpr() {
		return tpr;
	}

	public void setTpr(double tpr) {
		this.tpr = tpr;
	}

	public double getFpr() {
		return fpr;
	}

	public void setFpr(double fpr) {
		this.fpr = fpr;
	}
}
