/* Michael Neas
 * CSE 4705, Spring 2016
 * Homework 1
 */
package queens;

import java.util.ArrayList;
import java.util.Random;

/*
 * Hill climbing by descent because we want to get to the heuristic of least possible 
 * queen takability.
 */
public class HillClimbing {
	private Node initialState; 
	private Queen[] queenStatus;

	/*
	 * Constructor required for random hillclimbing 
	 */
	public HillClimbing(){
		initialState = new Node(); 
		queenStatus = new Queen[8]; 
		randomRestart();
	}
	
	
	/*
	 * Constructor to initialize the hill climbing, I create a new node
	 * where I fill the queenStatus array with new queen instances to be able 
	 * to calculate the heuristic
	 */
	public HillClimbing(Queen[] startClimb){
		initialState = new Node();
		queenStatus = new Queen[8];
		for(int i=0; i<8; i++){
			queenStatus[i] = new Queen(startClimb[i].getX(), startClimb[i].getY());
		}
		//take that new node and fill it with the properties of all the other queens on board
		initialState.setProperties(queenStatus);
		//then compute the heuristic for it.
		initialState.heuristicFunction();
	}
	
	/*
	 * Starting with the first state loop through all the neighbors 
	 * Make comparisons and evaluate successors.
	 */
	public Node goClimbing(){
		Node workingNode = initialState;
		
		while(true){
			ArrayList<Node> successors = workingNode.checkSurroundings(workingNode);
			
			Node nextNode = null;
			
			for(int i=0; i<successors.size(); i++){
				if(successors.get(i).climbingComparison(workingNode) < 0){
					nextNode = successors.get(i);
				}
			}
			
			if(nextNode==null)
				return workingNode;
			
			workingNode = nextNode;
		}
	}
	
	/*
	 * Random number generator for queen columns
	 */
	public void randomRestart(){
		Random rng = new Random();
		for(int i=0; i<8; i++){
			queenStatus[i] = new Queen(rng.nextInt(8), i);
		}
		initialState.makeStateFromRandomGen(queenStatus);
		initialState.heuristicFunction();
	}
	
	/*
	 * Getters and setters
	 */
	public Node initialState(){
		return initialState;
	}

	public Node getInitialState() {
		return initialState;
	}


	public void setInitialState(Node initialState) {
		this.initialState = initialState;
	}


	public Queen[] getQueenStatus() {
		return queenStatus;
	}


	public void setQueenStatus(Queen[] queenStatus) {
		this.queenStatus = queenStatus;
	}
}
