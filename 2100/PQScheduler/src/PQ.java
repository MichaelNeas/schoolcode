/*PQScheduler
 *CSE2100 Project 5, Fall 2014
 *Michael Neas
 *11/12/2014
 *Instructor: Chun-Hsi (Vincent) Huang
 */

public interface PQ<K,V> {//pq interface for the size, whether its empty, insert and mins
	int size(); //O(1) for size empty and min
	boolean isEmpty();
	Entry<K,V> insert(K key, V value) throws IllegalArgumentException; //O(logn) and same for remove min
	Entry<K,V> min();
	Entry<K,V> removeMin();
}
