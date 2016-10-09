/*PQScheduler
 *Michael Neas
 */

import java.util.Comparator;

public class DefaultComparator<E> implements Comparator<E>{
	@SuppressWarnings("unchecked")
	public int compare(E a, E b) throws ClassCastException {//comparator for heap hierarchy
		return ((Comparable<E>) a).compareTo(b);
	}
}
