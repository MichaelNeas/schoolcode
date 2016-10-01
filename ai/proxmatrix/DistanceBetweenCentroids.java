import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class DistanceBetweenCentroids {
	private ArrayList<DataObject> points = new ArrayList<DataObject>();
	private ArrayList<DataObject> centroids = new ArrayList<DataObject>();
	private ArrayList<DataObject> tempStorage = new ArrayList<DataObject>();
	private ProximityMatrix pMatrix;
	private int totalPoints = 0;
	
	/*
	 * Constructor is an initial setup.  
	 * It works by creating a copy of the data objects into centroids and points
	 * points gets passed to proximitymatrix first to get the initial foundation laid
	 * Reason being at the begining each point is its own cluster
	 */
	public DistanceBetweenCentroids(DataReader myData, int totalPoints) {
		this.totalPoints = totalPoints;
		for(int i = 0; i<totalPoints; i++){
			points.add(new DataObject(myData.getxInput().get(i), myData.getyInput().get(i), i));
			centroids.add(new DataObject(myData.getxInput().get(i), myData.getyInput().get(i), i));
			}
		pMatrix = new ProximityMatrix(points);
	}
	
	/*
	 * Merge drives the majority of the algorithm
	 * this loop will print the matrix, and calls the proximity matrix functions to update as the clusters grow
	 */
	private void merge() {
		//pMatrix.toString();
		//System.out.println(pMatrix.findClosest());
		tempStorage = new ArrayList<DataObject>(pMatrix.findClosest());
		totalPoints--;
		centroids.clear();
		for(DataObject dObjs : tempStorage){
			centroids.add(dObjs);
		}
		pMatrix = new ProximityMatrix(centroids);
	}
	
	/*
	 * Main takes in the text file and splits the input by the data reader class and creates an object for Distance Between Centroids
	 * DistanceBetweenCentroids does the algorithm
	 * ProximityMatrix calculates euclidean distances until there is only 1 cluster left.
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		InputStream is = Scatterplot.class.getResourceAsStream("HW3_Data.txt");
		Scanner scannedText = new Scanner(is).useDelimiter("\\A");
		DataReader myData = new DataReader(scannedText);
		DistanceBetweenCentroids original = new DistanceBetweenCentroids(myData, 6);
		while(original.getTotalPoints() > 0){
			original.merge();
		}
	}

	/*
	 * Getters and setters
	 */
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

	public int getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(int totalPoints) {
		this.totalPoints = totalPoints;
	}
}
