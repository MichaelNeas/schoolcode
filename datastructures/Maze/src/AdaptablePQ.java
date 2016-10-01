/*Maze
 *CSE2100 Project 6, Fall 2014
 *Michael Neas
 *12/07/2014
 *Instructor: Chun-Hsi (Vincent) Huang
 */

public interface AdaptablePQ<K, V> {
	void remove(Entry<K, V> entry);
	void replaceKey(Entry<K, V> entry,K key);
	void replaceValue(Entry<K, V> entry, V value);
}
