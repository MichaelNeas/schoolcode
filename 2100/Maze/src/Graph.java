/*Maze
 *CSE2100 Project 6, Fall 2014
 *Michael Neas
 *12/07/2014
 *Instructor: Chun-Hsi (Vincent) Huang
 */

import java.util.ArrayList;

public interface Graph {//graph interface to know what i'm going to need in the graph
	void insertVertex(Vertex O); //insert a vertex object
	void insertEdge(Vertex u, Vertex v, int x, int count); //insert edge
	ArrayList<Vertex> endVertices(Edge e);//get the end vertices
	Vertex opposite(Vertex v, Edge e); //see what is on the other end of an edge
	boolean areAdjacent(Vertex v, Vertex w); //are they adjacent?
	ArrayList<Edge> incidentEdges(Vertex v); //get the edges that are a part of vertices
	int numVertices(); //how many vertices
	int numEdges(); //how many edges
	ArrayList<Vertex> setOfVertices(); //the accessor of stored vertices
	ArrayList<Edge> setOfEdges(); //accessor of stored edges
}
