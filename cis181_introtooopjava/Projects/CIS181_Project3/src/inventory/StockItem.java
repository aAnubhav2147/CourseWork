package inventory;
import java.io.Serializable;

public class StockItem implements java.lang.Comparable, Serializable {
    private String title;
    private int have, want;
    private WaitingList waitingList;

    public StockItem() {
        title = "No title";
        have = 0;
        want = 0;
        waitingList = new WaitingList(title);
    }

    public StockItem(String title) {
        this.title = title;
        have = 0;
        want = 0;
        waitingList = new WaitingList(title);
    }

    // for display StockItem instance
    public String toString() {
        return title + ": have = " + have + " want = " + want;
    }

    // define how StockItems are compared
    public int compareTo(Object rhs){
        String lhsStr = title.toUpperCase();
        String rhsStr = ((StockItem)rhs).title.toUpperCase();
        return lhsStr.compareTo(rhsStr);
    }

    // get the have value
    public int getHave() {
        return have;
    }

    // set the have value
    public void setHave(int have) {
        this.have = have;
    }

    // get the title
    public String getTitle() {
        return title;
    }

    // set the title
    public void setTitle(String title) {
        this.title = title;
    }

    // get the want value
    public int getWant() {
        return want;
    }

    // set the want value
    public void setWant(int want) {
        this.want = want;
    }

    // get the head reference to the waiting list
    public WaitingList getWaitingList() {
        return waitingList;
    }
}
