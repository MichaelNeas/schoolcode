/*Maze
 *Michael Neas
 */

public class Cell {//basic object of each vertex in the graph
	private int _xCoordinate; //locations to give distinctions and allow drawing
	private int _yCoordinate;
	
	public Cell(int xLoc, int yLoc) {//a cell is the object in a vertex, it has an x and y
		_xCoordinate = xLoc;
		_yCoordinate = yLoc;
	}

	public int get_yCoordinate() {
		return _yCoordinate;
	}

	public int get_xCoordinate(){
		return _xCoordinate;
	}

	public void printLocation() {
		System.out.print(_xCoordinate + "," + _yCoordinate);	
	}
}
