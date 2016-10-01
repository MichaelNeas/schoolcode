import java.util.ArrayList;

public class ProximityMatrix {
	private double[][] proximityMatrix;
	private ArrayList<DataObject> points;

	/*
	 * Everything is done within the proximity matrix.
	 */
	public ProximityMatrix(ArrayList<DataObject> points) {
		this.points = points;
		proximityMatrix = new double[points.size()][points.size()];
		for(int i=0; i<points.size();i++){
			for(int j=0; j<points.size();j++){
				proximityMatrix[i][j] = calcEuclideanDistance(points.get(i), points.get(j));
			}
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
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * In order to print out the matrix
	 */
	@Override
	public String toString(){
		for(int i=0; i<proximityMatrix.length;i++){
			for(int j=0; j<proximityMatrix.length;j++){
				System.out.print("|" + proximityMatrix[i][j] + "|");
			}
			System.out.println();
		}
		return null;
	}

	/*
	 * Find the closest clusters to make the next expansion between
	 */
	public ArrayList<DataObject> findClosest(){
		double leader = 999999999;
		int point1 = 0;
		int point2 = 0;
		for(int i=0; i<proximityMatrix.length;i++){
			for(int j=0; j<proximityMatrix.length;j++){
				if(proximityMatrix[i][j]<leader && proximityMatrix[i][j] != 0){
					leader = proximityMatrix[i][j];
					point1 = i;
					point2 = j;
				}
			}
		}
		System.out.print(points.get(point1).getId() + " & " + points.get(point2).getId() + " located at ");
		System.out.println(points.get(point1) + " & " + points.get(point2) + " combined!");
		midPointFormula(point1, point2);
		return points;
	}

	/*
	 * The midpoint formula computes the new location of the cluster.
	 * The new cluster takes the first points old position
	 * Then it removes the second point from the matrix
	 */
	public DataObject midPointFormula(int pointa, int pointb){
		DataObject clusterHome;
		clusterHome = new DataObject(((points.get(pointa).getX() + points.get(pointb).getX()) / 2.0),((points.get(pointa).getY() + points.get(pointb).getY()) / 2.0),points.get(pointa).getId());
		System.out.print(calcEuclideanDistance(clusterHome, points.get(pointa)));
		System.out.print("::");
		System.out.print(calcEuclideanDistance(clusterHome, points.get(pointb)));
		System.out.println(" dendrogram distance");
		System.out.println("New cluster home is: " + clusterHome.toString());
		points.set(pointa, clusterHome);
		points.remove(pointb);
		return clusterHome;
	}
}
