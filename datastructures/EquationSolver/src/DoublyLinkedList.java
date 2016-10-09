/*Equation Solver
 *Michael Neas
 */

public class DoublyLinkedList<E> { //generic DLL class

	private static class Node<E> { //nested node class
	
		private E _element;
		private Node<E> _prev; 
		private Node<E> _next; //pointers to other nodes or null
		public Node(E e, Node<E> p, Node<E> n){
			_element = e;
			_prev = p;
			_next = n; //assigning the pointers
		}

		public E getElement(){
			return _element; //returns the node
		}

		public Node<E> getPrev(){ //gives node before current
			return _prev;
		}

		public Node<E> getNext(){ //looks at the next node
			return _next;
		}

		public void setPrev(Node<E> p){
			_prev = p;
		}

		public void setNext(Node<E> n){ //add on nodes to be able to add to DLL
			_next = n;
		}
	}

	private Node<E> _header;
	private Node<E> _trailer;
	private int _size = 0; //initially 0 sized DLL with pointers of a head and tail

	public DoublyLinkedList(){
		_header = new Node<>(null, null, null);
		_trailer = new Node<>(null, _header, null); //creates the first node
		_header.setNext(_trailer);
	}

	public int size(){ //returns size
		return _size;
	}

	public boolean isEmpty(){ //returns if the list is empty or not
		return _size == 0;
	}

	public E first(){ //gives first element of the list
		if(isEmpty())
			return null;
		return _header.getNext().getElement();
	}

	public E last(){ //looks at the last element of the list
		if(isEmpty())
			return null;
		return _trailer.getPrev().getElement();
	}

	private void addBetween(E e, Node<E> predecessor, Node<E> successor){ //puts a new node in the list wrt head and tail pointers
		Node<E> newest = new Node<>(e, predecessor, successor); //creates a new  node
		predecessor.setNext(newest); //setting the pointers to new node
		successor.setPrev(newest);
		_size++;
	}

	public void addFirst(E e){ //add to the front of the list
		addBetween(e, _header, _header.getNext());
	}

	public void addLast(E e){ //add to the back of the list
		addBetween(e, _trailer.getPrev(), _trailer);
	}

	private E remove(Node<E> node){ //remove a node from the DLL
		Node<E> predecessor = node.getPrev();
		Node<E> successor = node.getNext();
		predecessor.setNext(successor);
		successor.setPrev(predecessor);
		_size--;
		return node.getElement();
	}

	public E removeFirst(){//removes the first node from the DLL
		if(isEmpty())
			return null;
		return remove(_header.getNext());
	}

	public E removeLast(){//removes the last node from the DLL
		if(isEmpty())
			return null;
		return remove(_trailer.getPrev());
	}

	public String toString() { //made a toString to return all elements of the list
		String result = "";
		Node<E> current = _header;
		while(current.getNext() != null){
			current = current.getNext();
			result += current.getElement() + ", ";
		}
		return "List: " + (result);
	}
}






