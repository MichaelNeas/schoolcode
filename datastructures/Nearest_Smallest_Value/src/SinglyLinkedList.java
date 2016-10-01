/*Stack Implementation 
 *CSE2100 Project 2, Fall 2014
 *Michael Neas
 *9/21/2014
 *Instructor: Chun-Hsi (Vincent) Huang
 */

public class SinglyLinkedList<E>  {
	private ListNode<E> _head = null; //no assigned head as of now
	private ListNode<E> _tail = null; //initialize but no assignment
	private int _size = 0;
	public SinglyLinkedList(){} // default constructor

	public int size() {//gives size
		return _size;
	}

	public boolean isEmpty() {//checks if empty
		return _size == 0;
	}

	public E first() {//top of the stack
		if(isEmpty()) 
			return null;
		return _head.getInfo();
	}

	public E last() {//dont need this method but i have so there isnt a yellow error
		if(isEmpty())
			return null;
		else
			return _tail.getInfo();
	}

	public void addFirst(E e) {//used for push method, add to the top of stack
		_head = new ListNode<>(e, _head);
		if(_size == 0)
			_tail = _head;
		_size++;
	}

	public E removeFirst() {//pop method with reassignment
		if(isEmpty())
			return null;
		E desired = _head.getInfo();
		_head = _head.getNext();
		_size--;
		if(_size == 0)
			_tail = null;
		return desired;
	}
}
