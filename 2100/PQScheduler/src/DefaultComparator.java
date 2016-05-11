/*PQScheduler
 *CSE2100 Project 5, Fall 2014
 *Michael Neas
 *11/12/2014
 *Instructor: Chun-Hsi (Vincent) Huang
 */

import java.util.Comparator;

public class DefaultComparator<E> implements Comparator<E>{
	@SuppressWarnings("unchecked")
	public int compare(E a, E b) throws ClassCastException {//comparator for heap hierarchy
		return ((Comparable<E>) a).compareTo(b);
	}
}
