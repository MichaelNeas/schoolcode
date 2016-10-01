/* Michael Neas
 * CSE 4705, Spring 2016
 * Homework 1
 */

package missionarycannibal;

import java.util.ArrayList;

/* 
 * Node class holds all the information required for the tree data structure
 */
public class Node {
	private Node parent;
	private ArrayList<Node> children = new ArrayList<Node>();
	private State state;
	private int depth;
	private boolean visited = false;
	private String actionTaken;
	
	public Node(State startState){
		setParent(null);
		setState(startState);
		setChildren(null);
		setDepth(0);
	}
	
	public Node(Node parentNode, ArrayList<Node> possibleChildren, State nodeState, int depthLocation){
		setParent(parentNode);
		setChildren(possibleChildren);
		setDepth(depthLocation);
		setState(nodeState);
	}
	
	/* 
	 * equals, this equals is critical to finding and getting rid of nonValid paths
	 */
		@Override
		public boolean equals(Object other) {
		    if (!(other instanceof Node)) {
		        return false;
		    }
		    Node that = (Node) other;
		    
		    return this.getState().getMissionaries() == that.getState().getMissionaries()
		        && this.getState().getCannibals() == that.getState().getCannibals()
		        && this.getState().getBoatStatus() == that.getState().getBoatStatus();
		}

	/* 
	 * I don't use this toString much besides debugging
	 */
	@Override
	public String toString(){
		return "Current State {" + this.state.toString() + "} ~~ Parent=" + this.parent + " ~~ Depth=" + this.depth + " ~~ Children=" + this.children;
	}
	
	
	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public ArrayList<Node> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<Node> children) {
		this.children = children;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public String getActionTaken() {
		return actionTaken;
	}

	public void setActionTaken(String actionTaken) {
		this.actionTaken = actionTaken;
	}

}
