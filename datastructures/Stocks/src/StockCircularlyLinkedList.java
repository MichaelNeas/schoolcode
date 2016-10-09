/*Stock Queue Implementation 
 *Michael Neas
 */

public class StockCircularlyLinkedList<E>  {//circular list
	private ListNode<E> _tail = null;  //tail of the node
	private int _size = 0;
	public StockCircularlyLinkedList() {} //makes the empty list

	public int size(){
		return _size;
	}

	public boolean isEmpty(){
		return _size == 0;  //if the size is 0 isEmpty() returns true
	}

	public E first(){
		if(isEmpty())
			return null;
		else
			return _tail.getNext().getInfo(); //returns the info in the first node of the list
	}

	public E last(){
		if(isEmpty())
			return null;
		else
			return _tail.getInfo();  //returns the info in the tail of the node
	}

	public void rotate()  {//moves the first element to the end of the list
		if(_tail != null)
			_tail = _tail.getNext();
	}

	public void addFront(E e){
		if(_size == 0){
			_tail = new ListNode<>(e, null);
			_tail.setNext(_tail);               //circular link implementation when there's nothing in list
		}else{
			ListNode<E> newNode = new ListNode<>(e, _tail.getNext()); //puts the node in order
			_tail.setNext(newNode);
		}
		_size++;
	}

	public void addLast(E e){
		addFront(e);
		_tail = _tail.getNext(); //will add to the back of the list by calling previous method
	}

	public E removeFirst(){
		if(isEmpty())
			return null; //nothing to remove case
		else{
			ListNode<E> head = _tail.getNext(); 
			if (head == _tail)  //if the node is a both the head and tail of list 
				_tail = null;   //then set the tail of that node to null
			else{
				_tail.setNext(head.getNext()); //if not then set the pointer of the node to this new head
			}
			_size--; //decrease size
			return head.getInfo(); //give back the element
		}
	}
}
