package inventory;

import java.io.Serializable;
import list.ListReferenceBased;

public class WaitingList extends ListReferenceBased implements Serializable {

    private String title;

    public WaitingList(String title) {
        this.title = title;
    }

    // get the title associted the waiting list
    public String getTitle() {
        return title;
    }

    // set the title associated with the waiting list
    public void setTitle(String title) {
        this.title = title;
    }

    // add an item at the end of the list
    public void addLast(Object item) {
        add(size()+1, item);
    }

    // remove the first item at the beginning of the list
    public void removeFirst() {
        remove(1);
    }
}
