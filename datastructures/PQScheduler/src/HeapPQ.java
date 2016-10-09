/*PQScheduler
 *Michael Neas
 */

import java.util.ArrayList;
import java.util.Comparator;

public class HeapPQ<K,V> extends AbstractPQ<K,V> {
	protected ArrayList<Entry<K,V>> heap = new ArrayList<>(); //implementing a heap through an arraylist
	public HeapPQ() {//constructor to the abstract pq
		super();
	}

	public HeapPQ(Comparator<K> comp) {//another constructor to abstractpq
		super(comp);
	}

	protected int parent(int j) {//parent node
		return (j-1)/2;
	}

	protected int left(int j){
		return 2*j + 1;
	}

	protected int right(int j){
		return 2*j + 2;
	}

	protected boolean hasLeft(int j) {//checks if there is a left or right child
		return left(j) < heap.size();
	}

	protected boolean hasRight(int j){
		return right(j) < heap.size();
	}

	protected void swap(int i, int j) {//exchange values when ordering
		Entry<K,V> temp = heap.get(i); //takes temporary value and switches
		heap.set(i, heap.get(j));
		heap.set(j, temp);
	}

	protected void upheap(int j) {//moves entry higher
		while(j > 0) {//while j is larger than 0 check to see if the keys are 
			int p = parent(j);
			if (compare(heap.get(j), heap.get(p)) >= 0) //keys of arraylist comparison
				break;
			swap(j, p); //swap if smaller
			j = p; //continue from parents location
		}
	}

	protected void downheap(int j){//moves entry down
		while(hasLeft(j)) {//keep going till leaf
			int leftIndex = left(j); //keep track of the left inserts
			int smallChildIndex = leftIndex;
			if(hasRight(j)) {//if there is a right node
				int rightIndex = right(j); //index that right node
				if(compare(heap.get(leftIndex), heap.get(rightIndex)) > 0) //compare the two
					smallChildIndex = rightIndex; //set the left index to be the right
			}
			if (compare(heap.get(smallChildIndex), heap.get(j)) >= 0) //compare to see if heap is even
				break;
			swap(j, smallChildIndex); //then just swap moving entry with smaller child
			j = smallChildIndex; //and make j the new child
		}
	}

	public int size(){
		return heap.size(); //size of arraylist
	}

	public Entry<K,V> min() {//check the min of the heap
		if(heap.isEmpty())
			return null;
		return heap.get(0); //get the first value in the arraylist
	}

	public Entry<K,V> insert(K key, V value) throws IllegalArgumentException {//insert an entry to the heap
		checkKey(key); //see if key is equal to 0
		Entry<K,V> newest = new PQEntry<>(key, value); //make a new node for the pq
		heap.add(newest); //add the newly created node
		upheap(heap.size() - 1); //upheap the heap to make it organized
		return newest;
	}

	public Entry<K,V> removeMin(){//get rid of the top entry
		if(heap.isEmpty())
			return null;
		Entry<K,V> answer = heap.get(0); //give answer before you remove it
		swap(0, heap.size()-1); //arrange the heap to have a smaller size
		heap.remove(heap.size()-1); //remove the min
		downheap(0); //organize
		return answer;
	}
}
