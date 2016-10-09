/*Stock Queue Implementation 
 *Michael Neas
 */

public class StockLinkedQueue<E> implements AQueue<E>  {//use the circular linked list to implement a queue structure
	private StockCircularlyLinkedList<E> circList = new StockCircularlyLinkedList<>(); 
	public StockLinkedQueue(){}

	public int size() {//gives size of queue
		return circList.size();
	}

	public boolean isEmpty() {//checks empty
		return circList.isEmpty();
	}

	public E first() {//gives first element
		return circList.first();
	}

	public void enqueue(E e) {//adds to the end of queue
		circList.addLast(e);
	}

	public E dequeue() {//gets rid of front
		return circList.removeFirst();	
	}
}
