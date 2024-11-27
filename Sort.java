import java.util.Comparator;

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
	 * As configured, uses IUDoubleLinkedList class. 
	 * 
	 * @return a new list that implements the IndexedUnsortedList interface
	 */
	private static <T> IndexedUnsortedList<T> newList() 
	{
		return new IUDoubleLinkedList<T>();
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
		// split list into right and left halves
		if(list.size() > 1) {
			int mid = list.size() / 2;
			IUDoubleLinkedList<T> leftList = new IUDoubleLinkedList<T>();
			IUDoubleLinkedList<T> rightList = new IUDoubleLinkedList<T>();

			// add first half to leftList
			for(int i = 0; i < mid; i++) {
				leftList.addToRear(list.removeFirst());
			}
			mergesort(leftList);

			// add second half to rightList
			while(!list.isEmpty()) {
				rightList.addToRear(list.removeFirst());
			}
			mergesort(rightList);

			//System.out.println("Left list is: " + leftList.toString());
			//System.out.println("Right list is: " + rightList.toString());
			//System.out.println("List is: " + list.toString());

			// sort two lists back into main list
			while(!leftList.isEmpty() && !rightList.isEmpty()) {
				if(rightList.get(0).compareTo(leftList.get(0)) < 0) {
					list.addToRear(rightList.removeFirst());
				} else if (rightList.get(0).compareTo(leftList.get(0)) > 0) {
					list.addToRear(leftList.removeFirst());
				} else {
					list.addToRear(rightList.removeFirst());
				}
			}

			while(!rightList.isEmpty()) {
				list.addToRear(rightList.removeFirst());
			}

			while(!leftList.isEmpty()) {
				list.addToRear(leftList.removeFirst());
			}

			//System.out.println("List after merge is: " + list.toString());
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
		if(list.size() > 1) {
			int mid = list.size() / 2;
			IUDoubleLinkedList<T> leftList = new IUDoubleLinkedList<T>();
			IUDoubleLinkedList<T> rightList = new IUDoubleLinkedList<T>();

			// add first half to leftList
			for (int i = 0; i < mid; i++) {
				leftList.addToRear(list.removeFirst());
			}
			mergesort(leftList, c);

			// add second half to rightList
			while (!list.isEmpty()) {
				rightList.addToRear(list.removeFirst());
			}
			mergesort(rightList, c);

			// sort two lists back into main list
			while(!leftList.isEmpty() && !rightList.isEmpty()) {
				if(c.compare(rightList.get(0), leftList.get(0)) < 0) {
					list.addToRear(rightList.removeFirst());
				} else if (c.compare(rightList.get(0), leftList.get(0)) > 0) {
					list.addToRear(leftList.removeFirst());
				} else {
					list.addToRear(rightList.removeFirst());
				}
			}

			while(!rightList.isEmpty()) {
				list.addToRear(rightList.removeFirst());
			}

			while(!leftList.isEmpty()) {
				list.addToRear(leftList.removeFirst());
			}
		} 
	}
}
