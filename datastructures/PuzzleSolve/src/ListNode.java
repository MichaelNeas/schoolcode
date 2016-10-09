/*Puzzle Solve 
 *Michael Neas
 */

public class ListNode<E>  {//node class, for singular linked list
	private E _info;
	private ListNode<E> _next;

	public ListNode(E info, ListNode<E> next){
		this._info = info;
		this._next = next;
	}

	public E getInfo(){
		return _info;
	}

	public ListNode<E> getNext(){
		return _next;
	}

	public void setNext(ListNode<E> next){
		this._next = next;
	}

	public void setInfo(E info){
		this._info = info;
	}
}
