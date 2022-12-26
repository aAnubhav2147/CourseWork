package sortedList;
import list.*;

public interface SortedListInterface extends ListInterface {

    public void sortedAdd(Comparable newItem) throws ListException;
    // Adds an item to the list.
    // Precondition: None.
    // Postcondition: The item is added to the list in sorted order.

    public void sortedRemove(Comparable anItem);
    // Removes an item from the list.
    // Precondition: None.
    // Postcondition: The item is removed from the list
    // and the sorted order maintained.

    public int locateIndex(Comparable anItem);
    // Finds an item in the list.
    // Precondition: None.
    // Postcondition: If the item is in the list, its
    // index position in the list is returned.  If the
    // item is not in the list, the index of where it
    // belongs in the list is returned.
}
