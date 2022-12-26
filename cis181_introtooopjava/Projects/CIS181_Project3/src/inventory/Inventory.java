package inventory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import sortedList.*;
import list.*;

public class Inventory extends SortedList
                       implements InventoryInterface, Serializable {

    // constructor
    public Inventory() {}

    ///////////////////////
    // StockItem Management
    ///////////////////////

    // list the inventory in alphabetical order by title
    public void listInventory() {
        StockItem item;
        System.out.println("\nList the inventory in alphabetical order by title");
        System.out.println("=================================================");

        if (size() == 0) System.out.println("list is empty");
        for (int i = 1; i <= size(); i++) {
            item = (StockItem) get(i);
            displayStockItemInfo(item);
        }
    }

    // find the inventory item associated with a title
    public StockItem findStockItem(String title) {
        StockItem stockItemTmp = new StockItem(title);
        int position = locateIndex(stockItemTmp);

        if (position > 0 && position < size()+1) {
            StockItem stockItem = (StockItem)get(position);
            if ((stockItemTmp.compareTo(stockItem) == 0))
                return stockItem;
        }
        return null;
    }

    // display the inventory information for a specified stock item
    public void displayStockItemInfo(StockItem item) {
        System.out.println("Video Title: " + item.getTitle());
        System.out.println("[1] " + item.getHave() + " videos currently in stock");
        System.out.println("[2] " + item.getWant() + " videos should be in stock");
        System.out.print("[3] ");
        displayWaitingList(item);
        System.out.println("-------------------------------------------------");
    }

    // replace the inventory item associated with a title
    public void replaceStockItem(String title, StockItem newStockItem) {
        sortedRemove(new StockItem(title));
        sortedAdd(newStockItem);
    }

    // insert a new stock item into the inventory list
    public void insertStockItem(StockItem item) {
        if (item != null)
            sortedAdd(item);
        else throw new ListException("insertStockItem (item == null)");
    }

    // delete a stock item from the inventory list
    public void deleteStockItem(StockItem item) {
        if (item != null)
            sortedRemove(item);
        else throw new ListException("deleteStockItem (item == null)");
    }

    //////////////////////////
    // Waiting List Management
    //////////////////////////

    // add a new person to the end of the waiting list (when the video that
    // the person wants is sold out)
    public void addToWaitingList(String title, Person person) {
        StockItem item = findStockItem(title);
        WaitingList aList = item.getWaitingList();
        aList.addLast(person); // add a person at the end of the list
    }

    // delete the person at the beginning of the waiting list for a
    // specified title (when a video is delivered to the person)
    public Person deleteFromWaitingList(String title) {
        StockItem item = findStockItem(title);
        WaitingList aList = item.getWaitingList();
        Person aPerson = null;

        if (aList.size() > 0) {
            aPerson = (Person) aList.get(1);
            aList.removeFirst(); // remove the first person on the waiting list
        }
        return aPerson;
    }

    // display the names on a waiting list for a specified title
    public void displayWaitingList(String title) {
        StockItem item = findStockItem(title);
        displayWaitingList(item);
    }
    
    /*
    public void displaysWaitingList(String person) {
        Person p = null;
        displayWaitingList(person);
    } */


    // display the names on a waiting list for a stock item
    private void displayWaitingList(StockItem item) {
        WaitingList aList = item.getWaitingList();
        Object dataItem;

        System.out.print(aList.size() + " person(s) on waiting list: ");
        try {
            for (int index = 1; index < aList.size(); index++){
                dataItem = aList.get(index);
                System.out.print(dataItem + ", ");
            }
            if (aList.size() > 0) {
                dataItem = aList.get(aList.size());
                System.out.println(dataItem + ".");
            } else System.out.println("list is empty");
        } catch(ListException e) {}
    }

    //////////////
    // File Access
    //////////////

    // save the current inventory and associated waiting lists when
    // porgram execution terminates
    public void saveInventory() {
        try {
            FileOutputStream fos = new FileOutputStream("inventory.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            fos.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // restore the current inventory and associated waiting lists when
    // program execution begins again
    public SortedList restoreInventory() {
        SortedList restoredInventory = null;
        try {
            FileInputStream fis = new FileInputStream("inventory.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object o = ois.readObject();
            restoredInventory = (SortedList) o;
        } catch (Exception e) {
            System.out.println(e);
        }
        return restoredInventory;
    }
}
