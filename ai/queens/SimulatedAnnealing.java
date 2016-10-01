/* Michael Neas
 * CSE 4705, Spring 2016
 * Homework 1
 */
package queens;

/*
 * Similar to previous classes except simulated annealing using probability
 * to help guide its way to the top
 * 1. Generate a random initial solution.
 * 2. Generate a “neighbor” solution based on current node just change it slightly, 
 * so we don’t do huge jumps along the search space, we achieve this by taking a 
 * random queen and moving it a single step in a random direction.
 * 3. Accept the neighbor if simulated annealing says so.
 */
public class SimulatedAnnealing {
	private Node initialState;
	private Queen[] queenStatus;

	/*
	 * Constructor same as both previous constructors
	 */
	public SimulatedAnnealing(Queen[] startSim) {
		initialState = new Node();
		queenStatus = new Queen[8];
		
		for(int i=0; i<8; i++){
			queenStatus[i] = new Queen(startSim[i].getX(), startSim[i].getY());
		}
		initialState.setProperties(queenStatus);
		initialState.heuristicFunction();
	}
	
	/*
	 * Setters and getters
	 */
	public Node jumpAround() {
		Node workingNode = initialState;
		double temperature = 4;
		double variance = .1;
		double probability;
		int deltaE = 0;
		while(workingNode.getHeuristic()!=0 && temperature > 0){
			probability = variance*(deltaE/temperature);
		}
		return null;
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
