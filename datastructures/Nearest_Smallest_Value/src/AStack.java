/*Stack Implementation 
 *Michael Neas
 */

public interface AStack<E>  {//interface which is technically not needed but is good practice
	int size();//gives number of elements in the stack
	boolean isEmpty();//tells if the stack is empty or not
	void push(E e); //will push an element e on to the top of the stack
	E top(); //checks the top of the stack
	E pop(); //pops off the top element of the stack
}
