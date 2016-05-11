/*Maze
 *CSE2100 Project 6, Fall 2014
 *Michael Neas
 *12/07/2014
 *Instructor: Chun-Hsi (Vincent) Huang
 */

import java.util.ArrayList;

public class AdjacencyListGraph implements Graph {
	private ArrayList<Edge> _setOfEdges = new ArrayList<Edge>();
	private ArrayList<Vertex> _setOfVertices = new ArrayList<Vertex>(); //a graph is comprised of a set of vertices and edges

	public AdjacencyListGraph() {//constructor to initiate the lists
		_setOfEdges = new ArrayList<Edge>();
		_setOfVertices = new ArrayList<Vertex>();
	}

	public void insertVertex(Vertex O) {//adds vertex to the adjacency list based on the index keys
		_setOfVertices.add(O.getIndex(),O);
	}

	public void insertEdge(Vertex u, Vertex v, int x, int count) {//adds edge to graph with respected two vertices
		Edge newEdgeToBe = new Edge(x, u, v);
		newEdgeToBe.setIndex(count);//set the index
		_setOfEdges.add(newEdgeToBe.getIndex(), newEdgeToBe);//adds to the list
		u.addEdge(newEdgeToBe);//updates the edges of the vertices
		v.addEdge(newEdgeToBe);
	}

	public ArrayList<Vertex> endVertices(Edge e) {//an array of two endverticies of and edge
		return e.getVertices();
	}

	public Vertex opposite(Vertex v, Edge e) {// the vertex opposite of v on e
		ArrayList<Vertex> comparableEdge = e.getVertices();
		if(comparableEdge.get(0).equals(v))
			return comparableEdge.get(1);
		else
			return comparableEdge.get(0);
	}

	public boolean areAdjacent(Vertex v, Vertex w) {//true if v and w are adjacent aka there's an edge between them
		boolean isAdjacent = false; //begin with notion of no connection
		for(int a = 0; a < v.getAllEdges().size(); a++) //test all edges to see if two vertices are connected
			for(int b = 0; b < w.getAllEdges().size(); b++) {
				if(v.getIndividualEdgeWeight(a) == w.getIndividualEdgeWeight(b)){
					isAdjacent = true; //when found quit out of the loop
					break;
				}
			}
		return isAdjacent;
	}

	public ArrayList<Edge> incidentEdges(Vertex v){
		return v.getAllEdges();
	}

	public int numVertices(){
		return _setOfVertices.size();
	}

	public int numEdges(){
		return _setOfEdges.size();
	}

	public ArrayList<Vertex> setOfVertices(){
		return _setOfVertices;
	}

	public ArrayList<Edge> setOfEdges() {
		return _setOfEdges;
	}

	public void printVList() {//print statements for debugging
		for(int p=0; p<_setOfVertices.size() ; p++){
			_setOfVertices.get(p).printVertex();
			System.out.println();
		}
	}

	public void printEList(){
		for(int p=0; p<_setOfEdges.size(); p++) {
			_setOfEdges.get(p).printEdgeConnections();
			System.out.println();
		}
	}

	public int oppositeIndex(Vertex vertex1, int edgeAt) {//used when accessing the indexes for comparison in asterisks
		Vertex tempV = opposite(vertex1,vertex1.getAllEdges().get(edgeAt)); 
		return tempV.getIndex();
	}
}
