/*Stock Queue Implementation 
 *CSE2100 Project 1, Fall 2014
 *Michael Neas
 *9/14/2014
 *Instructor: Chun-Hsi (Vincent) Huang
 */

public interface AQueue<E>  {//class is not neccessarily needed for this program but
	//allows for multiple queues to be made in bigger programs
	int size();
	boolean isEmpty();
	void enqueue(E e);
	E first();
	E dequeue();
}
