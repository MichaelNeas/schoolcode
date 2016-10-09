/*Maze
 *Michael Neas
 */

import java.util.ArrayList;

public class Vertex {
	private Cell _cellObj; //initialize a cell inside of the vertex
	private int _index; //each vertex will have an index to reference
	private ArrayList<Edge> _incidentEdges = new ArrayList<Edge>(); //list of all incident edges a given vertex has
	private boolean _deservesAsterisk = false;

	public Vertex(int index, int xCoordinate, int yCoordinate) {//vertex object constructor
		_cellObj = new Cell(xCoordinate, yCoordinate);
		_index = index;
	}

	public void addEdge(Edge e) {//add an edge to the incidentedge list
		if(!(_incidentEdges.contains(e))) //if its not already in the list
			_incidentEdges.add(e); //add it
	}

	public void removeEdge(Edge e) {//to remove an edge from list
		if(_incidentEdges.contains(e)) //check if its even in there
		{
			int i = _incidentEdges.indexOf(e); //set temp equal to the index so you can remove it
			_incidentEdges.remove(i);
		}
	}

	public ArrayList<Edge> getAllEdges() {//return the list of edge weights
		return _incidentEdges;
	}

	public int getIndividualEdgeWeight(int i) {//get a specific weight of an edge object inside incident lists
		return _incidentEdges.get(i).getWeight();
	}

	public void printVertex(){
		_cellObj.printLocation(); //required for end vertices to know locations
	}

	//misc getters and setters if needed for graph manipulation
	public Cell getCellObj(){
		return _cellObj;
	}

	public int getIndex(){
		return _index;
	}

	public void setIndex(int index){
		_index = index;
	}

	public Cell setCellObj(int xCoordinate, int yCoordinate){
		Cell tempCell = _cellObj;
		_cellObj = new Cell(xCoordinate, yCoordinate);
		return tempCell;
	}

	public void removeIncidentEdges() {
		_incidentEdges.removeAll(_incidentEdges);
	}

	public boolean getAsterisk(){
		return _deservesAsterisk;
	}

	public void setAsteriskTrue(){
		_deservesAsterisk = true;
	}
}
