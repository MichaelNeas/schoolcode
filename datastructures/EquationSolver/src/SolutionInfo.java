/*Equation Solver
 *Michael Neas
 */

import java.util.Comparator;

public class SolutionInfo implements Comparator<SolutionInfo>{//object for node nothing can be static
	private long _ad;//storing all values in the object
	private long _be;
	private long _cf;
	private long _sol;

	public SolutionInfo(long AD, long BE, long CF, long solution){//simple constructor assignment
		_ad = AD;
		_be = BE;
		_cf = CF;
		_sol = solution;
		//		printData();
	}

	public void printData(){//printing for the final output
		System.out.print(_ad+ " " + _be + " " + _cf + " ");
	}

	public long get_ad() {//getters and setters for all values in the object
		return _ad;
	}

	public void set_ad(long ad) {//you don't really need setters here
		this._ad = ad;
	}

	public long get_be(){
		return _be;
	}

	public void set_be(long _be) {
		this._be = _be;
	}

	public long get_cf() {
		return _cf;
	}
	}

	public void set_cf(long _cf) {
		this._cf = _cf;
	}

	public long get_sol() {
		return _sol;
	}

	public void set_sol(long _sol) {
		this._sol = _sol;
	}

	@Override
	public int compare(SolutionInfo o1, SolutionInfo o2) {//implement compare for the mergesort
		if(o1.get_sol() < o2.get_sol()) //if the solution is less than the other solution return -1
			return -1;
		else if (o1.get_sol() == o2.get_sol())  //if they're the same give a 0
			return 0;
		else //else give back a 1, this all allows for sorting procedure to prioritize
			return 1;
	}
}
