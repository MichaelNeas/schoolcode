/*Puzzle Solve 
 *CSE2100 Project 3, Fall 2014
 *Michael Neas
 *10/09/2014
 *Instructor: Chun-Hsi (Vincent) Huang
 */

public class SinglyLinkedList<E>  
{
	private ListNode<E> _head = null; //no assigned head as of now
	private ListNode<E> _tail = null; //initialize but no assignment
	private int _size = 0;
	public SinglyLinkedList(){} // default constructor

	public int size() //gives size
	{
		return _size;
	}

	public boolean isEmpty() 
	{
		return _size == 0;
	}

	public E first()  
	{
		if(isEmpty()) 
			return null;
		return _head.getInfo();
	}

	public E last() 
	{
		if(isEmpty())
			return null;
		else
			return _tail.getInfo();
	}

	public void addFirst(E e) 
	{
		_head = new ListNode<>(e, _head);
		if(_size == 0)
			_tail = _head;
		_size++;
	}
	
	public void addLast(E e)
	{
		ListNode<E> newest = new ListNode<>(e, null);
		if(isEmpty())
			_head = newest;
		else
			_tail.setNext(newest);
		_tail = newest;
		_size++;
	}

	public E removeFirst() 
	{
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
