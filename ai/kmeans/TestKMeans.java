/*
 * Michael Neas
 * KMeans Implementation
 * Assignment 3
 * April 3, 2016
 */

public class TestKMeans {

	private Kmeans kMeansArrangement = null;
	/*
	 * KMeans test suite for seeing various relationships
	 */
	public TestKMeans(Kmeans original) {
		this.kMeansArrangement = original;
		testAssignments();
		testCentroids();
	}

	/*
	 * Test that the centroids are updating
	 */
	private void testCentroids() {
		for(DataObject vals : kMeansArrangement.getCentroids()){
			System.out.println("Centroid Points " + vals.getX() + " : " + vals.getY() + " = " + vals.getId());
		}

	}

	/*
	 * test that the points are being assigned correctly
	 */
	private void testAssignments() {
		for(DataObject vals : kMeansArrangement.getPoints()){
			System.out.println("Point " + vals.getX() + " : " + vals.getY() + " Belongs to Kluster " + vals.getId());
		}
	}

	public void close() {
		System.out.println("=)");		
	}

}
