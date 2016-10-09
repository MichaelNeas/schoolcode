/*PQScheduler
 *Michael Neas
 */

public interface PQ<K,V> {//pq interface for the size, whether its empty, insert and mins
	int size(); //O(1) for size empty and min
	boolean isEmpty();
	Entry<K,V> insert(K key, V value) throws IllegalArgumentException; //O(logn) and same for remove min
	Entry<K,V> min();
	Entry<K,V> removeMin();
}
