package list;
import java.io.Serializable;

public class Node implements Serializable {

    private Object item;
    private Node next;

    public Node(Object newItem) {
        item = newItem;
        next = null;
    }

    public Node(Object newItem, Node nextNode) {
        item = newItem;
        next = nextNode;
    }

    public void setItem(Object newItem) {
        item = newItem;
    }

    public Object getItem() {
        return item;
    }

    public void setNext(Node nextNode) {
        next = nextNode;
    }

    public Node getNext() {
        return next;
    }
}
