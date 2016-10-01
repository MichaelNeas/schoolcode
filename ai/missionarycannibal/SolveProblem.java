/* Michael Neas
 * CSE 4705, Spring 2016
 * Homework 1
 */
package missionarycannibal;

import java.util.ArrayList;

public class SolveProblem {
	/* 1. Create the starting state
	 * 2. Make it the source node
	 * 3. Build a tree from the source node
	 * 4. Search through the tree so the missionaries don't get eaten!
	 */
	static ArrayList<Node> treeData = new ArrayList<Node>();
	
	public static void main(String[] args) {
		State meState = new State(3, 3, 1);
		//System.out.println(meState.toString());
		Node startingNode = new Node(meState);
		//System.out.println(startingNode.toString());
		ActionTree createdTree = new ActionTree(startingNode, treeData);
		System.out.println("~~~~~~~~~~~~~~~Total findings~~~~~~~~~~~~~~");
		createdTree.buildTree();
		System.out.println("~~~~~~~~~~~Best Path to Soltion~~~~~~~~~~~~~~");
		SearchAlgorithm findSolution = new SearchAlgorithm(treeData);
		findSolution.print();
	}
}
