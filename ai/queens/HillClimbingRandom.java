/* Michael Neas
 * CSE 4705, Spring 2016
 * Homework 1
 */
package queens;

/*
 * This class is very similar to HillClimbing but just with the ability to restart the problem.
 */
public class HillClimbingRandom {
	private HillClimbing hillClimber;
	private Node initialState;
	
	/*
	 * Constructor which accepts the game board and does a hill climber on it initially
	 */
	public HillClimbingRandom(Queen[] gameBoard) {
		hillClimber = new HillClimbing(gameBoard);
	}

	/*
	 * Get the initial state from the hillclimber above, set it to the random start state
	 * Then the same loop is performed as hill climber but with the restart if things dont work 
	 * out the way I need for a correct solution.
	 */
	public Node goClimbingRandomly() {
		Node workingNode = hillClimber.getInitialState();
		this.initialState = workingNode;
		int heuristic = workingNode.getHeuristic();
				
		while(heuristic!=0){
			Node baseClimber = hillClimber.goClimbing();
			heuristic = baseClimber.getHeuristic();
			if(heuristic!=0){ 
				hillClimber = new HillClimbing();
			}else{
				workingNode = baseClimber;
			}
		}
		return workingNode;
	}
	
	public void setInitialState(Node n){
		initialState = n;
	}
	
	public Node getInitialState(){
		return initialState;
	}
}
