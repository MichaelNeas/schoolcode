/*Maze
 *CSE2100 Project 6, Fall 2014
 *Michael Neas
 *12/07/2014
 *Instructor: Chun-Hsi (Vincent) Huang
 */

public interface Entry<K,V> {
	K getKey(); //each entry to the heap has a key and a value
	V getValue();
}