/*PQScheduler
 *CSE2100 Project 5, Fall 2014
 *Michael Neas
 *11/12/2014
 *Instructor: Chun-Hsi (Vincent) Huang
 */

import java.util.Comparator;

public abstract class AbstractPQ<K,V> implements PQ<K,V>
{
	protected static class PQEntry<K,V> implements Entry<K,V> //entry for the priority queue
	{
		private K _key; //priority queue element key and value
		private V _value; 
		public PQEntry(K key, V value) //entry constructor
		{
			_key = key;
			_value = value;
		}
		public K getKey() //get the key
		{
			return _key;
		}
		public V getValue()
		{
			return _value;
		}
		protected void setKey(K key) //set the key or value
		{
			_key = key;
		}
		protected void setValue(V value)
		{
			_value = value;
		}
	}
	private Comparator<K> _comp; //new comparator initialized
	protected AbstractPQ(Comparator<K> c) //now there is a comparator inserted to organize the tree
	{
		_comp = c;
	}
	protected AbstractPQ() //constructor with comparator call
	{
		this(new DefaultComparator<K>());
	}
	protected int compare(Entry<K,V> a, Entry<K,V> b) //compares the value to give hierarchy
	{
		return _comp.compare(a.getKey(), b.getKey());
	}
	protected boolean checkKey(K key) throws IllegalArgumentException //check the key for the heap
	{
		try
		{
			return (_comp.compare(key, key) == 0);
		}
		catch(ClassCastException e)
		{
			throw new IllegalArgumentException("Incompatible key"); //hopefully it doesn't reach this
		}
	}
	public boolean isEmpty() //is it empty?
	{
		return size() == 0;
	}
}
