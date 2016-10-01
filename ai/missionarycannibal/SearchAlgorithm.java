/* Michael Neas
 * CSE 4705, Spring 2016
 * Homework 1
 */
package missionarycannibal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
/*
 * Appropriate Search algorithm for the homework solution
 */
public class SearchAlgorithm implements Queue<Node>{
	private ArrayList<Node> workingTree;
	Node goalNode = null;
	
	public SearchAlgorithm(ArrayList<Node> treeData) {
		setWorkingTree(treeData);
		saveMissionaryBFS();
		for(Node allnodes : workingTree){
			System.out.println(allnodes.getState().toString() + " " + allnodes.getDepth());
		}
		
	}

	/*
	 * This BFS iterates through all the valid nodes found in the action tree
	 * with a queue structure
	 */
	public void saveMissionaryBFS(){
		Queue<Node> nodeQueue = new LinkedList<Node>();
		nodeQueue.add(workingTree.get(0));
		//System.out.println(workingTree.get(0).getState().toString());
		workingTree.get(0).setVisited(true);
		searchLoop:
		while(!nodeQueue.isEmpty()){
			Node node = (Node)nodeQueue.remove();
			Node child = null;
			int childIteration = 0;
			while((child=getUnvisitedChildren(node, childIteration)) != null){
				child.setVisited(true);
				//System.out.println(child.getState().toString());
				if(foundPath(child)){
					goalNode = child;
					break searchLoop;
				}
				nodeQueue.add(child);
				childIteration++;
			}
		}
	}
	
	/*
	 * foundPath tells me when I have reached the goal, because this is bfs the
	 * solution is optimal because it stops at the first and closest solution
	 */
	
	private boolean foundPath(Node aChildNode) {
		if(aChildNode.getState().getCannibals() == 3 && aChildNode.getState().getMissionaries() == 3 && aChildNode.getState().getBoatStatus() == 0)
			return true;
		else
			return false;
	}

	/*
	 * Null pointer try catch statement to get the children arrayList for queue
	 */
	
	private Node getUnvisitedChildren(Node node, int index) {
		try{
		Node childNode = node.getChildren().get(index);
		return childNode;
		}catch(Exception e){
			return null;
		}
	}

	/*
	 * Store the path from the goal node to the origin in a stack
	 * This way I accomplish printing the trace the way I want to
	 * Also practice with iterator 
	 */
	public void print() {
		Stack<Node> printStack = new Stack<Node>();
		//System.out.println("Best Path to solution");
		while(goalNode.getParent() != null){
			printStack.push(goalNode);
			goalNode = goalNode.getParent();
		}
		printStack.push(workingTree.get(0));
		
		Iterator<Node> iterator = printStack.iterator();
		while(iterator.hasNext()){
			System.out.println(printStack.peek().getDepth() + ". " + printStack.peek().getState().toString() + "--------" +printStack.peek().getActionTaken());
			printStack.pop();
		}
		
	}
	
	public ArrayList<Node> getWorkingTree() {
		return workingTree;
	}

	public void setWorkingTree(ArrayList<Node> treeData) {
		this.workingTree = treeData;
	}

	//Didn't really use any of these methods from implementing the queue
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<Node> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends Node> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean add(Node e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean offer(Node e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Node remove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node poll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node element() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node peek() {
		// TODO Auto-generated method stub
		return null;
	}
}
