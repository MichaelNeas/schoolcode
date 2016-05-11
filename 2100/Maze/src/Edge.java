/*Maze
 *CSE2100 Project 6, Fall 2014
 *Michael Neas
 *12/07/2014
 *Instructor: Chun-Hsi (Vincent) Huang
 */

import java.util.ArrayList;

public class Edge //edge connects two vertices
{
	private int _index; //index is required to go through the list of edges for comparison
	private int _weight; //weight of the edge
	private Vertex _vertex1;//vertices being connected
	private Vertex _vertex2;

	public Edge(int weight, Vertex v1, Vertex v2) //constructor for an edge where index is internally defined
	{
		_weight = weight; 
		_vertex1 = v1;
		_vertex2 = v2;
	}
	public void printEdgeConnections()
	{
		System.out.print("The vertex at location ");
		_vertex1.printVertex();
		System.out.print(" is connected with weight of " + _weight + " and an index of " + getIndex() +" to the vertex at location ");
		_vertex2.printVertex();
	}
	public void printVerticesCoordinates() //get coordinates printed
	{
		_vertex1.printVertex();
		_vertex2.printVertex();
	}

	public ArrayList<Vertex> getVertices()  //used in endVertices method
	{
		ArrayList<Vertex> endVerts = new ArrayList<Vertex>();
		endVerts.add(0, _vertex1);
		endVerts.add(1, _vertex2);
		return endVerts;
	}

	public Vertex getVertex1()
	{
		return _vertex1;
	}
	public Vertex getVertex2()
	{
		return _vertex2;
	}
	public int getWeight()
	{
		return _weight;
	}
	public void setWeight(int weight)
	{
		this._weight = weight;
	}
	public int getIndex()
	{
		return _index;
	}
	public void setIndex(int index)
	{
		this._index = index;
	}
}
