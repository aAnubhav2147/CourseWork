package list;
import java.io.Serializable;

/////////////////////////////////////////////
// Reference-based implementation of ADT list
/////////////////////////////////////////////

public class ListReferenceBased implements ListInterface, Serializable {

    // reference to linked list of items
    private Node head;
    private int numItems; // number of items in list

    public ListReferenceBased() {
        numItems = 0;
        head = null;
    }

    public boolean isEmpty() {
        return numItems == 0;
    }

    public int size() {
        return numItems;
    }

    // --------------------------------------------------
    // Locates a specified node in a linked list.
    // Precondition: index is the number of the desired
    // node. Assumes that 1 <= index <= numItems+1
    // Postcondition: Returns a reference to the desired
    // node.
    // --------------------------------------------------
    private Node find(int index) {
        Node curr = head;

        for (int skip = 1; skip < index; skip++) {
            curr = curr.getNext();
        }
        return curr;
    }

    public Object get(int index) throws ListException {

        if (index >= 1 && index <= numItems) {
            // get reference to node, then data in node
            Node curr = find(index);
            Object dataItem = curr.getItem();
            return dataItem;
        } else {
            throw new ListException("List index out of bounds exception " +
                    "on get: " + index);
        }
    }

    public void add(int index, Object item) throws ListException {
        if (index >= 1 && index <= numItems+1) {
            if (index == 1) {
                // insert the new node containing item at
                // beginning of list
                Node newNode = new Node(item, head);
                head = newNode;
            } else {
                Node prev = find(index-1);
                // insert the new node containing item after
                // the node that prev references
                Node newNode = new Node(item, prev.getNext());
                prev.setNext(newNode);
            }
            numItems++;
        } else {
            throw new ListException("List index out of bounds exception " +
                    "on add: " + index);
        }
    }

    public void remove(int index) throws ListException {
        if (index >= 1 && index <= numItems) {
            if (index == 1) {
                // delete the first node from the list
                head = head.getNext();
            } else {
                Node prev = find(index-1);
                // delete the node after the node that prev
                // references, save reference to node
                Node curr = prev.getNext();
                prev.setNext(curr.getNext());
            }
            numItems--;
        } else {
            throw new ListException("List index out of bounds exception " +
                    "on remove: " + index);
        }
    }

    public void removeAll() {
        // setting head to null causes list to be
        // unreachable and thus marked for garbage
        // collection
        head = null;
        numItems = 0;
    }
}