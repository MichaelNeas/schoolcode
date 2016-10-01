/* Michael Neas
 * CSE 4705, Spring 2016
 * Homework 1
 */
package queens;

import java.util.Random;

/*
 * The board of queens, will be randomly generated at first
 */
public class Board {	
	private Queen[] gameBoard;
	
	/*
	 * Most basic constructor ever.
	 */
	public Board(){
		makeBoard();
	}
	
	/*
	 * Mainly used for debugging
	 */
	public Board(Board finalBoard){
		finalBoard.printBoard();
	}
	
	/*
	 * Visual representation of the queen pieces locations within the board
	 */
	
	public void makeBoard(){
		Queen[] queenArray = new Queen[8];
		Random randomYPlacement = new Random();
		for(int i=0; i<8; i++){
			queenArray[i] = new Queen(i,randomYPlacement.nextInt(8));
		}
		this.gameBoard = queenArray;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		for(Queen queenLocations : gameBoard){
			System.out.println("X: " + queenLocations.getX() + " Y: " + queenLocations.getY());
		}
		return null;
	}
	
	/*
	 * ASCii representation of board to show at the end
	 */
	public void printBoard(){
		for(int i=0; i<8;i++){
			for(int j=0; j<8; j++){
				for(Queen queenLocations:gameBoard){
					if(j==queenLocations.getX() && i==queenLocations.getY()){
						System.out.print(":Q:");
						j++;
					}
				}
				if(j!=8)
					System.out.print("[ ]");
			}
		System.out.println();
		}
	}
	
	public Queen[] getGameBoard() {
		return gameBoard;
	}


	public void setGameBoard(Queen[] gameBoard) {
		this.gameBoard = gameBoard;
	}

}
