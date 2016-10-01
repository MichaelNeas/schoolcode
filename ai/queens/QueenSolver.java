/* Michael Neas
 * CSE 4705, Spring 2016
 * Homework 1
 */
package queens;

public class QueenSolver {
	/*
	 * Heuristic h=number of queens attacking each other directly or indirectly
	 * Directly = no blockage, indirectly = blockage
	 * Successors are all possible states moving one queen in the same column
	 * 8*7 = 56 other possible states.
	 * I compute the heuristic after each move across the board
	 * I look at the best successors to consume
	 */
	
	public static void main(String[] args){
		double count = 0;
		double randoCount = 0;
		double simAnnealingCount = 0;
		double iterations = 100;
		long totalHillClimbingTime = 0;
		long totalHillClimbingRandomTime = 0;
		long totalSimTime = 0;
		
		for(int i=0; i<iterations; i++){
			Board gameBoard = new Board();
			//gameBoard.toString();
			//gameBoard.printBoard();
			HillClimbing hillClimbingAlgorithm = new HillClimbing(gameBoard.getGameBoard());
			long startTime = System.nanoTime();
			Node finalPeak = hillClimbingAlgorithm.goClimbing();
			long endTime = System.nanoTime();
			//System.out.println(hillSolved.getHeuristic());
			HillClimbingRandom randomHCAlgorithm = new HillClimbingRandom(gameBoard.getGameBoard());
			long startRTime = System.nanoTime();
			Node randomPeaks = randomHCAlgorithm.goClimbingRandomly();
			long endRTime = System.nanoTime();
// 			SimulatedAnnealing simulatedAnnealingSolution = new SimulatedAnnealing(gameBoard.getGameBoard());
//			long startSimTime = System.nanoTime();
//			Node simedPeaks = simulatedAnnealingSolution.jumpAround();
//			long endSimTime = System.nanoTime();
			//GeneticAlgorithm genes = new GeneticAlgorithm(8, gameBoard.getGameBoard(), .5);
			//genes.go();
				if(finalPeak.getHeuristic() == 0){
					totalHillClimbingTime += endTime -startTime;
					count++;
				}
				if(randomPeaks.getHeuristic() == 0){
					totalHillClimbingRandomTime += endRTime -startRTime;
					randoCount++;
				}
//				if(simedPeaks.getHeuristic() == 0){
//					totalSimTime += endSimTime - startSimTime;
//					simAnnealingCount++;
//				}
				
		}
		double hillclimbOut = count/iterations * 100;
		double randomHCAOut = randoCount/iterations * 100;
		double simAnnealOut = simAnnealingCount/iterations * 100;
		
		long hillClimbAverage = (long) (totalHillClimbingTime / count);
		long hillClimbRandomAverage = (long) (totalHillClimbingRandomTime / randoCount);
		long simTimeAverage = (long) (totalSimTime/simAnnealingCount);
		
		System.out.println(hillclimbOut + "% problems solved by Hill Climbing");
		System.out.println("Took an average of "+ hillClimbAverage + " ns for a correct solution, otherwise known as : " + hillClimbAverage/1000000000.0 + " seconds per correct solution \n"); 
		System.out.println(randomHCAOut + "% problems solved by Random Hill Climbing");
		System.out.println("Took an average of "+ hillClimbRandomAverage + " ns for a correct solution, otherwise known as : " + hillClimbRandomAverage/1000000000.0 + " seconds per correct solution \n"); 
		System.out.println(simAnnealOut + "% problems solved by Simulated Annealing");
		System.out.println("Took an average of "+ simTimeAverage + " ns for a correct solution, otherwise known as : " + simTimeAverage/1000000000.0 + " seconds per correct solution \n");
		System.out.println("This is where my genetic algorithm would print, if I could make it work =(");
		
	}

}
