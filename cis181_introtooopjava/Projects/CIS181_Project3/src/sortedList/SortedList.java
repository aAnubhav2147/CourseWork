package sortedList;
import java.io.Serializable;
import list.*;

public class SortedList extends ListReferenceBased
                        implements SortedListInterface, Serializable {

    // constructor: invokes default constructor of superclass
    public SortedList() {}

    // new operations:
    public void sortedAdd(Comparable newItem) {
        int newPosition = locateIndex(newItem);

        super.add(newPosition, newItem);
    }

    public void sortedRemove(Comparable anItem) {
        int position = locateIndex(anItem);

        if ((anItem.compareTo(get(position))==0)) {
            super.remove(position);
        } else {
            throw new ListException("Sorted remove failed");
        }
    }

    public int locateIndex(Comparable anItem) {
        int len = size();
        int index = 1;

        // loop invariant: anItem belongs after all the elements up to
        // the element referenced by index
        while ((index <= len) &&
               (anItem.compareTo(get(index)) > 0)) {
            ++index;
        }
        return index;
    }

    // restrictive inheritance
    public void add(int index, Object item) {
        throw new ListException("Unsupported operation add on a sorted list");
    }

    // restrictive inheritance
    public void remove(int index) {
        throw new ListException("Unsupported operation remove on a sorted list");
    }
}