/*
 * Michael Neas
 * Artificial Intelligence Homework 2
 */

package ridgeregression;

import Jama.Matrix;

public class RidgeRegression {
	
	private Matrix returnedMatrix;
	private double[] xVals;
	private double[] yVals;
	private int deg;
	
	/*
	 * Ridge regression takes the xvalues, yvalues of data, the polynomial order desired, with a lambda value
	 * There are print methods in the bottom of the method to allow for you to see the polynomial created from the regression
	 * This uses the JAMA framework provided as a jar in the zip file
	 */

	
	public RidgeRegression(double[] xValues, double[] yValues, int order, double lambdaValue) {
		this.setxVals(xValues);
		this.setyVals(yValues);
		this.setDeg(order);
		//make the goal vector
		Matrix yMatrix = new Matrix(yValues, yValues.length);
		
		//initialize X 
		double[][] X = new double[xValues.length][order+1];
		
		//fill X
		for(int i = 0; i < xValues.length; i++){
			for(int j = 0; j <= order; j++){
				X[i][j] = Math.pow(xValues[i], j);
			}
		}
		
		
		//create new matrix from jama
		Matrix xMatrix = new Matrix(X);
		//xMatrix.print(1, 4);
		
		// w = (X^T * X + \I)* X^T * y
		//transposeX
		Matrix xTranspose = xMatrix.transpose();
		//xTranspose.print(1, 4);
		
		//multiply the transpose by the original
		Matrix xProd = xTranspose.times(xMatrix);
		//xProd.print(1,4);
		
		//create identity matrix
		Matrix identityMatrix = Matrix.identity(xProd.getRowDimension(), xProd.getColumnDimension());
		//identityMatrix.print(1,4);
		
		//create lambda multiplication
		identityMatrix.timesEquals(lambdaValue);
		//identityMatrix.print(1, 4);
		
		//add together 
		Matrix addLambdentities = identityMatrix.plus(xProd);
		//addLambdentities.print(1, 4);
		
		//take the inverse 
		Matrix xInverse = addLambdentities.inverse();
		//xInverse.print(1, 4);
		
		//multiply by the transpose again
		Matrix xMultiplyByTranspose = xInverse.times(xTranspose);
		//xMultiplyByTranspose.print(3, 4);
		
		Matrix w = xMultiplyByTranspose.times(yMatrix);
		
		//System.out.println(w.getColumnDimension());
		//System.out.println(w.getRowDimension());
		//w.print(0,4);
		
		/* This method prints the equation from linear regression
		for(int val = w.getRowDimension(); val>0; val--){
			if(val != 1){
				System.out.print((double)Math.round(w.get(val-1, 0) * 10000d) / 10000d + "x^"+ (val-1) + " + ");
			}else{
				System.out.print((double)Math.round(w.get(val-1, 0) * 10000d) / 10000d);
			}
		}
		System.out.println();
		*/
		setReturnedMatrix(w);
	}
	
	/*
	 * Plug an x value in and get a y out from the ridge regression 
	 */
	public double estimate(double x){
		double total = 0;
		//returnedMatrix.print(1, 4);
		for(int i = 0; i < returnedMatrix.getRowDimension(); i++){
			total += (Math.pow(x,i))*returnedMatrix.get(i, 0);
			//System.out.println(i);
		}
		return total;
	}
	
	/*
	 * This finds the Root Mean Square error
	 */
	public double ermsVsLambda(){
		double e;
		double r;
		double tempTotal = 0;
		for(int x = 0; x < xVals.length; x++){
			e = estimate(xVals[x]) - yVals[x];
			r = Math.pow(e, 2);
			tempTotal += r;
		}
		double dividesqrtN = Math.sqrt(tempTotal / xVals.length);
		return dividesqrtN;
	}


	public static void main(String[] args) {
		//training data
		double[] xValues = {0,.1,.2,.3,.4,.5,.6,.7,.8,.9, 1};
		double[] yValues = {.084,.499,.9611,.8966,.6181,-.06,-.5388,-.8771,-.7799,-.6072,-.2138};
		
		//test data
		double[] xTestValues = new double[101];
		xTestValues[0] = 0;
		for(int i = 1; i<101; i++){
			xTestValues[i] = xTestValues[i-1] + 1 ; 
			//System.out.println(xTestValues[i]);
		}
		for(double i: xTestValues){
			xTestValues[(int)i] = xTestValues[(int)i]/100;
			//System.out.println(xTestValues[(int)i]);
		}
		
		double[] yTestValues = {.0601,.1060,.2088,.3850,.3846,.1664,.1094,.5260,.2778,.5432,.5917,.8849,.6769,.6726,.7967,.8363,.8521,.8087,.7690,.9649,.8018,.8539,1.1302,.9456,.9824,1.1,.9647,1.1065,.9439,1.0811,1.0210,.9061,.8086,.7604,.8143,.7603,.7251,.8383,.6515,.7645,.5287,.6439,.4237,.4454,.4760,.2630,.2,.41,.231,.0148,.0721,-.1028,-.0469,-.0301,-.4270,-.1947,-.2061,-.4205,-.2877,-.5186,-.7252,-.8811,-.7216,-.6497,-.7352,-.7631,-.9084,-.8603,-1.0869,-1.0142,-1.0420,-.9108,-.9839,-1.1205,-.9991,-1.0766,-1.0721,-.8961,-.9697,-.9243,-.8528,-.9097,-.8436,-.8004,-.7143,-.7561,-.6136,-.7265,-.6899,-.4484,-.6444,-.5361,-.3795,-.4091,-.2120,-.1941,-.2163,-.2738,-.0624,-.2164,.0272};
		//System.out.println(yTestValues.length);
		
		//Testing out with various lambda values 
		RidgeRegression polynomialFit = new RidgeRegression(xValues, yValues, 9, -18);
		//RidgeRegression polynomialFitTestData = new RidgeRegression(xTestValues, yTestValues, 9, -18);
		//RidgeRegression polynomialLamb6 = new RidgeRegression(xValues, yValues, 9, -6);
		//RidgeRegression polynomialLamb6Test = new RidgeRegression(xTestValues, yTestValues, 9, -6);
		RidgeRegression polynomialLamb0 = new RidgeRegression(xValues, yValues, 9, 0);
		//RidgeRegression polynomialLamb0Test = new RidgeRegression(xTestValues, yTestValues, 9, 0);
		RidgeRegression polynomialLamb10 = new RidgeRegression(xValues, yValues, 9, 10);
		//RidgeRegression polynomialLamb10Test = new RidgeRegression(xTestValues, yTestValues, 9, 10);
		System.out.println(polynomialLamb0.estimate(.5));
		System.out.println(polynomialFit.estimate(.5));
		System.out.println(polynomialLamb10.estimate(.5));
		
		double[] trainingData = new double[50];
		double[] testData = new double[50];
		int index = 0;
		for(int i = -25; i < 25; i++){
			RidgeRegression polynomialTrainingIteration = new RidgeRegression(xValues, yValues, 9, i);
			trainingData[index] = polynomialTrainingIteration.ermsVsLambda();
			RidgeRegression polynomialTestIteration = new RidgeRegression(xTestValues, yTestValues, 9, i);
			testData[index] = polynomialTestIteration.ermsVsLambda();
			index++;
		}
		
		//Data for graphing!
		double loglambda = -25;
		for(int i = 0; i<trainingData.length; i++){
			System.out.print(loglambda + " : ");
			System.out.println(trainingData[i]);
			loglambda++;
		}
		
		System.out.println("Test Data");
		loglambda = -25;
		
		
		for(int i = 0; i<testData.length; i++){
			System.out.print(loglambda + " : ");
			System.out.println(testData[i]);
			loglambda++;
		}
		
		
		
	}

	/*
	 * Getters and setters!
	 */
	public Matrix getReturnedMatrix() {
		return returnedMatrix;
	}

	public void setReturnedMatrix(Matrix w) {
		this.returnedMatrix = w;
	}

	public double[] getxVals() {
		return xVals;
	}

	public void setxVals(double[] xVals) {
		this.xVals = xVals;
	}

	public double[] getyVals() {
		return yVals;
	}

	public void setyVals(double[] yVals) {
		this.yVals = yVals;
	}

	public int getDeg() {
		return deg;
	}

	public void setDeg(int deg) {
		this.deg = deg;
	}
}
