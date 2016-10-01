/* Michael Neas
 * CSE 4705, Spring 2016
 * Homework 1
 */

package missionarycannibal;
/* 
 * State holds the current state the two sides of the river are in
 */
public class State {
	private int missionaries;
	private int cannibals;
	private int boatStatus;
	
	public State(int missionaries, int cannibals, int boatStatus){
		setMissionaries(missionaries);
		setCannibals(cannibals);
		setBoatStatus(boatStatus);		
	}
	
	/* 
	 *toString is a big driver in all debugging and console outputs
	 */
	@Override
	public String toString(){
		String whereBoat;
		if(this.boatStatus == 0){
			whereBoat = "GoalSide";
		}else{
			whereBoat = "OriginalSide";
		}
		return "Missionaries=" + this.missionaries + " : Cannibals=" + this.cannibals + " : Boat on " + whereBoat;
	}

	public int getMissionaries() {
		return missionaries;
	}

	public void setMissionaries(int missionaries) {
		this.missionaries = missionaries;
	}

	public int getCannibals() {
		return cannibals;
	}

	public void setCannibals(int cannibals) {
		this.cannibals = cannibals;
	}

	public int getBoatStatus() {
		return boatStatus;
	}

	public void setBoatStatus(int boatStatus) {
		this.boatStatus = boatStatus;
	}

}
