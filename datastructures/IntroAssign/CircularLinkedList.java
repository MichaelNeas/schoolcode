public class CircularLinkedList<E> {
	private Node<E> head;
	public int length;
	public CircularLinkedList() {
        head = new Node<E>(null, head);
        length = 0;
	}

	public void add(E item) {
		Node<E> tmp = head;
		Node<E> node = new Node<E>(item, head);
		if (isEmpty()){
			head.setNext(node);
		}else {
			while (head != tmp.getNext()){
				tmp = tmp.getNext();
			}
			tmp.setNext(node);
		}
		length++;
	}

	public E firstData(){
		return head.next.data;
	}

	public void removeFirst(){
		Node<E> tmp = head;
		if (length < 1) {
			System.out.println("Error: no item to delete");
		}else if (1 == length){
			head.setNext(head);
			length--;
		}else {
			head.setNext(tmp.getNext().getNext());
			length--;
		}
	}

	public boolean isEmpty(){
		return length==0;
	}
}