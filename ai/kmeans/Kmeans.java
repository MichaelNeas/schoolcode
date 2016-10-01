/*
 * Michael Neas
 * KMeans Implementation
 * Assignment 3
 * April 3, 2016
 */

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import org.jfree.ui.RefineryUtilities;

/*
 * KMeans algorithm: 
 *    1. Calculate Euclidean distances and assign cluster IDs to dataset
 *    2. Calculate new coordinates for centroids by taking means
 *    3. Update the history
 *    4. Rinse and repeat
 */
public class Kmeans {
	private ArrayList<DataObject> points = new ArrayList<DataObject>();
	private ArrayList<DataObject> centroids = new ArrayList<DataObject>();
	private ArrayList<DataObject> centroidHistory = new ArrayList<DataObject>();
	private int rangeMin = -2;
	private int rangeMax = 2;
	private int kValue = 0;
	Random r = new Random();

	public Kmeans(DataReader myData, int initCentroids) {
		this.kValue = initCentroids;
		//add the data to the points arraylist
		for(int i = 0; i<myData.getxInput().size(); i++){
			points.add(new DataObject(myData.getxInput().get(i), myData.getyInput().get(i), 0));
		}
		//generate random initial centroids as instructed by the kmeans algorithm
		for(int i = 0; i<initCentroids; i++){
			double randomValueX = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
			double randomValueY = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
			centroids.add(new DataObject(randomValueX, randomValueY, i));
		}

		//Clone each object to give different object ids
		for(DataObject randTroids : centroids){
			centroidHistory.add(randTroids.clone());
		}
	}

	/*
	 * assignAreas will assign the klusters based on the euclidean distance between the points and centroids
	 * Each point gets compared to each centroid and the winner determines that points new kluster ID
	 */
	private void assignAreas() {
		double winningDistance = Double.MAX_VALUE;
		int winningCentroidID = -1; //initially set to -1
		for(DataObject pobjs : points){
			for(DataObject cobjs: centroids){
				if(calcEuclideanDistance(pobjs, cobjs) < winningDistance){
					winningDistance = calcEuclideanDistance(pobjs, cobjs);
					winningCentroidID = cobjs.getId();
				}
			}
			pobjs.setId(winningCentroidID);
			winningCentroidID = -1; //reset the id for the next point
			winningDistance = 5; //reset the winning distance to 0 for new point
		}
	}

	//Take a point and centroid and calculate the euclidean distance
	private double calcEuclideanDistance(DataObject pobjs, DataObject cobjs) {
		return calcEuclideanDistance(pobjs.getX(), pobjs.getY(), cobjs.getX(), cobjs.getY());
	}

	//Overloaded helper function to formally calculate euclidean distance
	private double calcEuclideanDistance(double x1, double y1, double x2, double y2){
		return Math.sqrt((Math.pow((x2 - x1),2.0)) + (Math.pow((y2 - y1), 2.0)));
	}

	/*
	 * Calculate the new centroid coordinates based on the points within the clusters
	 * Iterate through all the possible k clusters in order to assign the mean
	 */
	private void newCentroidCoords(){
		double xSum = 0.0;
		double ySum = 0.0;
		double count = 0.0;
		for(int i = 0; i<kValue; i++){

			for(DataObject pobjs: points){
				if(pobjs.getId() == i){
					xSum += pobjs.getX();
					ySum += pobjs.getY();
					count++;
				}
			}
			//System.out.println(xSum + " / " + count );
			if(xSum == 0.0){
				//this random point isn't close to any specific data point.
				double randomValueX = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
				double randomValueY = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
				centroids.get(i).setX(randomValueX);
				centroids.get(i).setY(randomValueY);
			}else{
				//System.out.println(centroids.get(i).getX() + " -> " + xSum/count );
				centroids.get(i).setX(xSum/count);
				centroids.get(i).setY(ySum/count);
				xSum = 0.0;
				ySum = 0.0;
				count = 0.0;
			}
		}
	}

	/*
	 * Solving the problem until the mean doesnt move
	 */
	private void solve(){
		assignAreas();
		newCentroidCoords();

		//while loop to go until convergence
		while(changeable()){
			setNewHistory(); //continually update this history arraylist
			assignAreas(); // calculate the new clusters
			newCentroidCoords(); //make new coordinates
			//			Debugging..
			//			for(int i = 0; i < centroids.size(); i++){
			//				System.out.println(centroidHistory.get(i) + " === " + centroids.get(i));
			//			}
			//			System.out.println("------looping-------");
		}
		//		System.out.println("broken out");
	}

	/*
	 * Setting new history will set the centroids to the history arraylist before their coordinates are updated
	 */
	private void setNewHistory() {
		for(int i = 0; i<centroidHistory.size(); i++){
			centroidHistory.get(i).setX(centroids.get(i).getX());
			centroidHistory.get(i).setY(centroids.get(i).getY());
		}
	}


	/*
	 * This controls the loop to say when to continue through or not based on whether the new means is different from
	 * the old means arraylist
	 */
	private boolean changeable() {
		for(int i = 0; i<centroids.size(); i++){
			if(centroidHistory.get(i).getX() != centroids.get(i).getX() || 
					centroidHistory.get(i).getY() != centroids.get(i).getY()){
				//System.out.println("centroid: " + i + " || " + centroidHistory.get(i) + " != " + centroids.get(i));
				return true;
			}
		}
		return false;
	}

	/*
	 * Main takes in the text file and splits the input by the data reader class and creates an object for kmeans
	 * Kmeans does the alorithm
	 * Scatterplot will graph the initial and final results of the centroids and data points
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException { 
		InputStream is = Scatterplot.class.getResourceAsStream("HW3_Data.txt");
		Scanner scannedText = new Scanner(is).useDelimiter("\\A");
		DataReader myData = new DataReader(scannedText);
		//		Uncomment this for assignment requirements		
		//		int j = 2;
		//		for(int i=1; i<16; i++){
		//			if(i == 5 || i == 10){
		//				j++;
		//			}
		Kmeans original = new Kmeans(myData, 2);
		Scatterplot initPlot = new Scatterplot("KMeans Setup Plot", original); 
		initPlot.pack(); 
		RefineryUtilities.centerFrameOnScreen(initPlot); 
		initPlot.setVisible(true); 
		original.solve();

		//TestKMeans testSuite = new TestKMeans(original);
		//testSuite.close();

		Scatterplot secondPlot = new Scatterplot("KMeans Final Plot", original);
		secondPlot.pack(); 
		RefineryUtilities.centerFrameOnScreen(secondPlot); 
		secondPlot.setVisible(true); 	
		//		}


	} 


	//Getters and setters
	public ArrayList<DataObject> getPoints() {
		return points;
	}

	public void setPoints(ArrayList<DataObject> points) {
		this.points = points;
	}

	public ArrayList<DataObject> getCentroids() {
		return centroids;
	}

	public void setCentroids(ArrayList<DataObject> centroids) {
		this.centroids = centroids;
	}


}
