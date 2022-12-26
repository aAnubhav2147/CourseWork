package lab7;

public interface SortedListInterface {

     public boolean isEmpty();
     // Determines whether a sorted list is empty.
     // Precondition: None.
     // Postcondition: Returns true if the sorted list is empty, otherwise returns false.

     public int size();
     // Determines the length of a sorted list.
     // Precondition: None.
     // Postcondition: Returns the number of items that are currently in the sorted list.

     public void sortedAdd(Comparable newItem) throws ListException;
     // Adds an item to the sorted list.
     // Precondition: None.
     // Postcondition: The item is added to the sorted list in sorted order.

     public void sortedRemove(Comparable anItem) throws ListException;
     // Removes an item from the sorted list.
     // Precondition: None.
     // Postcondition: The item is removed from the sorted list and the sorted order maintained.
     // If the item is not in the list, a ListException is thrown.

     public Object get(int index) throws ListException;
     // Retrieves a list item by position.
     // Precondition: index is the position of the item to be retrieved.
     // Postcondition: If 1 <= index <= size(), the item at position index in the sorted
     // list is returned; otherwise, a ListException is thrown.

     public void removeAll();
     // Deletes all the items from the sorted list.
     // Precondition: None.
     // Postcondition: The sorted list is empty.

     public int locateIndex(Comparable anItem);
     // Finds an item in the sorted list.
     // Precondition: None.
     // Postcondition: If the item is in the sorted list, its index position in the sorted
     // list is returned. If the item is not in the sorted list, the index of where it belongs
     // in the sorted list is returned.

}
