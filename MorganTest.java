import java.util.Comparator;

public class MorganTest {
    
    public static void main(String[] args) {
        IUDoubleLinkedList<Integer> myList = new IUDoubleLinkedList<Integer>();
        myList.addToFront(3);
        myList.addToFront(1);
        myList.addToFront(2);
        myList.addToFront(5);
        myList.addToFront(4);

        System.out.println("Before running myList is: " + myList.toString());

        Sort.sort(myList);

        System.out.println("After running myList is: " + myList.toString());
    }

}


