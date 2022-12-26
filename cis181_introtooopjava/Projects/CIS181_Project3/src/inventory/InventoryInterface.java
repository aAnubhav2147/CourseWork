package inventory;

import sortedList.SortedList;

/**
 * @author Haiping Xu
 */
public interface InventoryInterface {

    ///////////////////////
    // StockItem Management
    ///////////////////////

    public void listInventory();
    // List the inventory in alphabetical order by title

    public StockItem findStockItem(String title);
    // Find the inventory item associated with a title

    public void displayStockItemInfo(StockItem item);
    // Display the inventory information for a specified stock item

    public void replaceStockItem(String title, StockItem newStockItem);
    // Replace the inventory item associated with a title

    public void insertStockItem(StockItem item);
    // Insert a new stock item into the inventory list

    public void deleteStockItem(StockItem item);
    // Delete a stock item from the inventory list

    //////////////////////////
    // Waiting List Management
    //////////////////////////

    public void addToWaitingList(String title, Person person);
    // Add a new person to the end of the waiting list (when the video that
    // the person wants is sold out)

    public Person deleteFromWaitingList(String title);
    // Delete the person at the beginning of the waiting list for a
    // specified title (when a video is delivered to the person)

    public void displayWaitingList(String title);
    // Display the names on a waiting list for a specified title

    //////////////
    // File Access
    //////////////

    public void saveInventory();
    // Save the current inventory and associated waiting lists when
    // porgram execution terminates

    public SortedList restoreInventory();
    // Restore the current inventory and associated waiting lists when
    // program execution begins again

}
