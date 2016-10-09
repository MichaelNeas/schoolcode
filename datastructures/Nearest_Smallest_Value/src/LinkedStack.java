/*Stack Implementation 
 *Michael Neas
 */

public class LinkedStack<E> implements AStack<E> {
	private SinglyLinkedList<E> nsvList = new SinglyLinkedList<>();//the stack is a new linked list
	public LinkedStack(){} //constructor

	public int size() {//how big stack is
		return nsvList.size();
	}

	public boolean isEmpty() {//check method
		return nsvList.isEmpty();
	}

	public void push(E value) {//add a new top
		nsvList.addFirst(value);
	}

	public E top() {//check top
		return nsvList.first();
	}

	public E pop()  {//get rid of top
		return nsvList.removeFirst();
	}
}
