import java.util.Comparator;
import java.util.LinkedList;

/**
 * Class for sorting lists that implement the IndexedUnsortedList interface,
 * using ordering defined by class of objects in list or a Comparator.
 * As written uses Mergesort algorithm.
 *
 * @author CS221, mzabriskie
 */
public class Sort
{	
	/**
	 * Returns a new list that implements the IndexedUnsortedList interface. 
	 * As configured, uses WrappedDLL. Must be changed if using 
	 * your own IUDoubleLinkedList class. 
	 * 
	 * @return a new list that implements the IndexedUnsortedList interface
	 */
	private static <T> IndexedUnsortedList<T> newList() 
	{
		return new WrappedDLL<T>(); //TODO: replace with your IUDoubleLinkedList for extra-credit
	}
	
	/**
	 * Sorts a list that implements the IndexedUnsortedList interface 
	 * using compareTo() method defined by class of objects in list.
	 * DO NOT MODIFY THIS METHOD
	 * 
	 * @param <T>
	 *            The class of elements in the list, must extend Comparable
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 * @see IndexedUnsortedList 
	 */
	public static <T extends Comparable<T>> void sort(IndexedUnsortedList<T> list) 
	{
		mergesort(list);
	}

	/**
	 * Sorts a list that implements the IndexedUnsortedList interface 
	 * using given Comparator.
	 * DO NOT MODIFY THIS METHOD
	 * 
	 * @param <T>
	 *            The class of elements in the list
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 * @param c
	 *            The Comparator used
	 * @see IndexedUnsortedList 
	 */
	public static <T> void sort(IndexedUnsortedList <T> list, Comparator<T> c) 
	{
		mergesort(list, c);
	}
	
	/**
	 * Mergesort algorithm to sort objects in a list 
	 * that implements the IndexedUnsortedList interface, 
	 * using compareTo() method defined by class of objects in list.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The class of elements in the list, must extend Comparable
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 */
	private static <T extends Comparable<T>> void mergesort(IndexedUnsortedList<T> list)
	{
		// TODO: Implement recursive mergesort algorithm 
		// split list into right and left halves
		if(list.size() > 1) {
			int mid = list.size() / 2;
			WrappedDLL<T> leftList = new WrappedDLL<T>();
			WrappedDLL<T> rightList = new WrappedDLL<T>();

			// add first half to leftList
			for(int i = 0; i < mid; i++) {
				leftList.add(i, list.get(i));
				list.remove(i);
			}
			mergesort(leftList);

			// add second half to rightList
			for(int i = mid; i < list.size(); i++) {
				rightList.add(i, list.get(i));
				list.remove(i);
			}
			mergesort(rightList);

			// sort two lists back into main list
			while(!leftList.isEmpty() && !rightList.isEmpty()) {
				if(rightList.get(0).compareTo(leftList.get(0)) < 0) {
					list.addToRear(rightList.get(0));
					rightList.remove(0);
				} else if (rightList.get(0).compareTo(leftList.get(0)) > 0) {
					list.addToRear(leftList.get(0));
					leftList.remove(0);
				} else {
					list.addToRear(rightList.get(0));
					rightList.remove(0);
				}
			}

			if(!rightList.isEmpty()) {
				list.addToRear(rightList.get(0));
				rightList.remove(0);
			}
		} 
	}
		
	/**
	 * Mergesort algorithm to sort objects in a list 
	 * that implements the IndexedUnsortedList interface,
	 * using the given Comparator.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The class of elements in the list
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 * @param c
	 *            The Comparator used
	 */
	private static <T> void mergesort(IndexedUnsortedList<T> list, Comparator<T> c)
	{
		// TODO: Implement recursive mergesort algorithm using Comparator
		if(list.size() > 1) {
			int mid = list.size() / 2;
			WrappedDLL<T> leftList = new WrappedDLL<T>();
			WrappedDLL<T> rightList = new WrappedDLL<T>();

			// add first half to leftList
			for(int i = 0; i < mid; i++) {
				leftList.add(i, list.get(i));
				list.remove(i);
			}
			mergesort(leftList, c);

			// add second half to rightList
			for(int i = mid; i < list.size(); i++) {
				rightList.add(i, list.get(i));
				list.remove(i);
			}
			mergesort(rightList, c);

			// sort two lists back into main list
			while(!leftList.isEmpty() && !rightList.isEmpty()) {
				if(compare(rightList.get(0), leftList.get(0)) < 0) {
					list.addToRear(rightList.get(0));
					rightList.remove(0);
				} else if (compare(rightList.get(0), leftList.get(0)) > 0) {
					list.addToRear(leftList.get(0));
					leftList.remove(0);
				} else {
					list.addToRear(rightList.get(0));
					rightList.remove(0);
				}
			}

			if(!rightList.isEmpty()) {
				list.addToRear(rightList.get(0));
				rightList.remove(0);
			}
		} 
	}
	}
	
}
