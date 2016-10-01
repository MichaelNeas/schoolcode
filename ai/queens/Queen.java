/* Michael Neas
 * CSE 4705, Spring 2016
 * Homework 1
 */
package queens;

/*
 * What a queen can do and their individual locations
 * My queens are frozen in their respective x columns
 */
public class Queen {
	private int xLocation;
	private int yLocation;
	
	//constructor for queens
	public Queen(int x, int y){
		this.xLocation = x;
		this.yLocation = y;
	}
	
	/*
	 * The backbone of the entire hill climbing algorithm
	 * This detects the possible movements up and down the queen can go
	 */
	public void skiddlyDoo(int places) {
		yLocation = yLocation + places;
		
		//check the boundaries of the board
		if(yLocation>7 && yLocation%7!=0){
			//if goes out of bounds we pull it into the spot of remainder-1
			yLocation=(yLocation%7)-1;
		}
		//otherwise check if it is divisible by 7 the way out there
		else if(yLocation>7 && yLocation%7==0){
			yLocation=7;
		}

	}
	
	/*
	 * What other queens this queen can take
	 */
	public boolean canTakeQueen(Queen queen){
		//initially not takable until proven otherwise
		boolean takable = false;
		//same row or column 
		if(xLocation == queen.getX() || yLocation == queen.getY()){
			takable = true;
		}
		//diagonals
		if(Math.abs(xLocation-queen.getX()) == Math.abs(yLocation-queen.getY())){
			takable = true;
		}
		return takable;
	}
	
	/*
	 * Getters and setters
	 */
	public int getX() {
		return xLocation;
	}
	public void setX(int x) {
		this.xLocation = x;
	}
	
	public int getY() {
		return yLocation;
	}
	public void setY(int y) {
		this.yLocation = y;
	}
}
