/*PQScheduler
 *Michael Neas
 */

public interface Entry<K,V> {
	K getKey(); //each entry to the heap has a key and a value
	V getValue();
}
