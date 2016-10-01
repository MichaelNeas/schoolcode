/* Michael Neas
 * CSE 4705, Spring 2016
 * Homework 1
 */
package queens;

import java.util.ArrayList;

public class Node {
	private int heuristic;
	private Queen[] properties;
	private ArrayList<Node> listOfNeighbors;
	
	/*
	 * Initializing an empty node with no neighbors
	 */
	public Node(){
		this.properties = new Queen[8]; 
		this.listOfNeighbors = new ArrayList<Node>(); 	} 

	/*
	 * Initializing a node where there are empty neighbors but full queens, not evaluated yet
	 */
	public Node(Node node){
		this.properties = new Queen[8];
		this.listOfNeighbors = new ArrayList<Node>();
		for(int i=0; i<8; i++)
			this.properties[i] = new Queen(node.properties[i].getX(), node.properties[i].getY());
		this.heuristic=0;
	}
	
	/*
	 * check surrounding nodes, taking the start node as the base
	 */
	public ArrayList<Node> checkSurroundings(Node start){
		int neighborLocation=0;
		
		for(int i=0; i<8; i++){
			for(int j=1; j<8; j++){
				Node currentNode = new Node(start);
				currentNode.properties[i].skiddlyDoo(j);
				currentNode.heuristicFunction();
				listOfNeighbors.add(neighborLocation, currentNode);
				neighborLocation++;
			}
		}
		return listOfNeighbors;
	}
	
	/*
	 * For the heuristic we have to go through all the queens and see if it is possible
	 * to take each other, continually summing when this is possible, if this is not 
	 * possible then ignore
	 */
	public int heuristicFunction() {
		for(int i=0; i<7; i++){
			for(int j=i+1; j<8; j++){
				if(properties[i].canTakeQueen(properties[j])){
						heuristic++;
				}
			}
		}
		return heuristic;		
	}
	
	/*
	 * Climbing is based on the lower heuristic, the more desirable
	 * and if the nodes are equal then we don't care.
	 */
	public int climbingComparison(Node comparisonNode){
		if(this.heuristic > comparisonNode.getHeuristic())
			return 1;
		else if(this.heuristic < comparisonNode.getHeuristic())
			return -1;
		else 
			return 0;
	}
	
	public void makeStateFromRandomGen(Queen[] s){
		for(int i=0; i<8; i++){
			properties[i]= new Queen(s[i].getX(), s[i].getY());
		}
	}

	/*
	 * Getters and setters for private vars
	 */
	public int getHeuristic() {
		return heuristic;
	}

	public void setHeuristic(int heuristic) {
		this.heuristic = heuristic;
	}

	public Queen[] getProperties() {
		return properties;
	}

	public void setProperties(Queen[] properties) {
		this.properties = properties;
	}

	public ArrayList<Node> getNeighbors() {
		return listOfNeighbors;
	}

	public void setNeighbors(ArrayList<Node> neighbors) {
		this.listOfNeighbors = neighbors;
	}
	
}
