/* Michael Neas
 * CSE 4705, Spring 2016
 * Homework 1
 */

package missionarycannibal;

import java.util.ArrayList;

/* 
 * Build a tree of nodes with possible states
 * A boat can either take, 1 missionary, 1 cannibal, 2 missionaries, 2 cannibals, or one of each
 */
public class ActionTree {
	private ArrayList<Node> childNodes;
	private ArrayList<Node> tree;
	private Node workingNode;
	private State workingState;
	private int missionaryCount;
	private int cannibalCount;
	private int boatLocation;
	
	//build a tree from the starting node this handles  the initial node as well
	public ActionTree(Node startNode, ArrayList<Node> previousTree){
		setWorkingNode(startNode);	
		setWorkingState(startNode.getState());
		missionaryCount = startNode.getState().getMissionaries();
		cannibalCount = startNode.getState().getCannibals();
		boatLocation = startNode.getState().getBoatStatus();
		childNodes = new ArrayList<Node>();
		this.tree = previousTree;
		if(previousTree.isEmpty()){
			startNode.setActionTaken("We Begin");
			previousTree.add(startNode);
		}
	}
	
	/* 
	 * Add Node is responsible for creating a new state given by the conditional below
	 * Then creates a new node from that state and checks if that node has already been found
	 * from there it is added to the tree and given as a child node to the parent.
	 */
	public void addNode(int missionaries, int cannibals, int status, String action){
		State newState = new State(missionaries, cannibals, status);
		Node newNode = new Node(workingNode, null, newState, workingNode.getDepth()+1);
		if(checkDuplicates(newNode)){
			//System.out.println("State already seen"); //don't do anything
		}else{
			System.out.println(newNode.getDepth() + ") Now there is: " + newState.toString() + " on the tree" + ", because " + action + "----- Parent: " + newNode.getParent().getState().toString());
			newNode.setActionTaken(action);
			tree.add(newNode);
			childNodes.add(newNode);
		}
	}
	
	/* 
	 * Calls the equals method found in Node class on all the nodes in the tree.
	 */
	private boolean checkDuplicates(Node nodeToCheck) {
		//System.out.println("Size of tree"+tree.size()); count size increase of tree
		for(Node allNodes : tree){
			if(nodeToCheck.equals(allNodes)){
				return true;
			}
		}
		return false;
	}
	
	/* 
	 * Logic behind finding valid node states where missionaries wouldn't get eaten
	 * I took into account the fact that the question asked for 3 missionaries and 3 cannibals
	 */
	public void buildTree(){
		//addNode(0, 2, 1^1); // check that the equality checking occurs
		if(cannibalCount == 0 && missionaryCount == 0 && boatLocation == 0){
			//System.out.println("We Made It");
		}else if(boatLocation == 1 || boatLocation == 0){
			if(missionaryCount > 0 && (3-(missionaryCount-1)>=(3-cannibalCount) && (missionaryCount-1>=cannibalCount) || (missionaryCount-1 == 0))){
				//System.out.println("1 missionary left from-- " + workingState.toString());
				addNode(3-(missionaryCount-1), 3-cannibalCount, boatLocation^1, "1 missionary takes the boat");
			}
			if(missionaryCount > 1 && (3-(missionaryCount-2)>=(3-cannibalCount) && (missionaryCount-2>=cannibalCount) || (missionaryCount-2 == 0))){
				//System.out.println("2 missionaries left from-- " + workingState.toString());
				addNode(3-(missionaryCount-2), 3-cannibalCount, boatLocation^1, "2 missionaries take the boat");
			}
			if(cannibalCount > 0 && ((3-missionaryCount)>=((3-cannibalCount)+1) || (3-missionaryCount == 0))){
				//System.out.println("1 cannibal left from-- " + workingState.toString());
				addNode(3-missionaryCount, 3-(cannibalCount-1), boatLocation^1, "1 cannibal takes the boat");
			}
			if(cannibalCount > 1 && ((3-missionaryCount)>=((3-cannibalCount)+2) || (3-missionaryCount == 0))){
				//System.out.println("2 cannibals left from-- " + workingState.toString());
				addNode(3-missionaryCount, 3-(cannibalCount-2), boatLocation^1, "2 cannibals take the boat");
			}
			if(cannibalCount > 0 && missionaryCount > 0 && 3-(missionaryCount-1)>=3-(cannibalCount-1)){
				//System.out.println("A cannibal and missionary left from-- " + workingState.toString());
				addNode(3-(missionaryCount-1), 3-(cannibalCount-1), boatLocation^1, "A cannibal and missionary take the boat");
			}
			workingNode.setChildren(childNodes);
		}else{
			System.out.println("An error occured adding nodes to the action tree");
		}
		
		//recursive call for the tree to be fully expanded upon
		for(Node newNode : childNodes){
			ActionTree nextLevel = new ActionTree(newNode, tree);
			nextLevel.buildTree();
		}
	}
	
	@Override
	public String toString(){
		return "I'm a tree";
	}
	
	public Node getWorkingNode() {
		return workingNode;
	}
	public void setWorkingNode(Node workingNode) {
		this.workingNode = workingNode;
	}

	public State getWorkingState() {
		return workingState;
	}

	public void setWorkingState(State workingState) {
		this.workingState = workingState;
	}

}
