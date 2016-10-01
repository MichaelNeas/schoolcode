public class Node<E> {
	public Node<E> next;
	public E data;
	

	public Node(E data) {
		this.data = data;
		next = null;
	}

	public Node(E data, Node< E> nextNode) {
		this.data = data;
		next = nextNode;
	}

	public void setNext(Node< E> next) {
		this.next = next;
	}

	public Node< E> getNext() {
		return next;
	}
}
