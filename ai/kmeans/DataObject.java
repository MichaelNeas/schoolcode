/*
 * Michael Neas
 * KMeans Implementation
 * Assignment 3
 * April 3, 2016
 */

public class DataObject implements Cloneable {
	private double X;
	private double Y;
	private int id = 0;

	/*
	 * Data object constructor has the datas coordinates and the cluster the data belongs to
	 * Every point in the graph is a dataobject
	 */
	public DataObject(double xInput, double yInput, int identity) {
		this.setX(xInput);
		this.setY(yInput);
		this.setId(identity);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#clone()
	 * New clone method to create new dataobject with different id's but the same primatives
	 */
	@Override public DataObject clone() {
		try {
			final DataObject result = (DataObject) super.clone();
			// copy fields that need to be copied here!
			return result;
		} catch (final CloneNotSupportedException ex) {
			throw new AssertionError();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * for simpler debugging
	 */
	@Override
	public String toString(){
		return X + "," + Y ;

	}

	/*
	 * Getters and setters
	 */
	public double getX() {
		return X;
	}

	public void setX(double x) {
		X = x;
	}

	public double getY() {
		return Y;
	}

	public void setY(double y) {
		Y = y;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


}
