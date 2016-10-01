/*
 * Michael Neas AI HW2 Part2
 */

/*
 * Output pair is the class that makes an object of classifier outputs, their boolean labels, and their type
 */
public class OutputPair {
	private double classifierOutput;
	private double trueLabel;
	private String rocType;

	/*
	 * The constructor takes the label and classifier output and will set the types accordingly.
	 */
	public OutputPair(double trueLabel, double classifierOutput){
		this.setTrueLabel(trueLabel);
		this.setClassifierOutput(classifierOutput);
		if(trueLabel > 0 && classifierOutput > 0){
			this.setRocType("TP");
		}else if(trueLabel > 0 && classifierOutput < 0){
			this.setRocType("FP");
		}else if(trueLabel < 0 && classifierOutput < 0){
			this.setRocType("TN");
		}else{
			this.setRocType("FN");
		}
	}

	/*
	 * getters and setters for private variable access
	 */
	public String getRocType() {
		return rocType;
	}

	public void setRocType(String rocType) {
		this.rocType = rocType;
	}

	public double getTrueLabel() {
		return trueLabel;
	}

	public void setTrueLabel(double trueLabel) {
		this.trueLabel = trueLabel;
	}

	public double getClassifierOutput() {
		return classifierOutput;
	}

	public void setClassifierOutput(double classifierOutput) {
		this.classifierOutput = classifierOutput;
	}

}
