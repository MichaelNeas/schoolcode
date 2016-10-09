/*Stock Queue Implementation 
 *Michael Neas
 */

public interface AQueue<E>  {//class is not neccessarily needed for this program but
	//allows for multiple queues to be made in bigger programs
	int size();
	boolean isEmpty();
	void enqueue(E e);
	E first();
	E dequeue();
}
