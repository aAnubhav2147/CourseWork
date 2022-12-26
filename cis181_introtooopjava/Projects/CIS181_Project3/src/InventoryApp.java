
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import inventory.*;

public class InventoryApp {
    private Inventory inventory = new Inventory();
    //private StockItem stock = new StockItem();
    private String nextLine;
    private BufferedReader stdin = new BufferedReader(
                           new InputStreamReader(System.in));

    // Read user input
    private void readInput(String prompt) {
        System.out.print(prompt);
        try {
            nextLine = stdin.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Display the inventory information for a specified title.
    // Precondtions: None.
    // Postcondtion: If the title is not currently in the inventory, print error
    //               message; otherwise, print the inventory information for the
    //               specified title.
    //
    private void displayVideoInfo(String title){
        StockItem stockItem = inventory.findStockItem(title);
        if (stockItem != null)
            inventory.displayStockItemInfo(stockItem);
        else
            System.out.println("Stock item not found! (title: " + title + ")");
    }

    // Add a new title to the inventory.
    // Precondtions: None.
    // Postcondtion: If the title is not currently in the inventory, add it
    //               into the inventory, and set up the initial want value
    //               for that title.
    //
    private void addNewTitle(String title){
        StockItem stockItem = inventory.findStockItem(title);

        if (stockItem != null) {
            System.out.println("-- Title already exists! Use M <title> " +
                               "to modify want value.");
            return;
        } else stockItem = new StockItem(title);

        readInput("Input the initial want value for stock item \"" + title + "\": ");
        int w;

        try {
            w = Integer.parseInt(nextLine);
        } catch (Exception e) {
            System.out.println("** Invalid input: the initial want " +
                    "value is set to zero!");
            w = 0;
        }
        stockItem.setWant(w);
        inventory.insertStockItem(stockItem);
        System.out.println("-- A new title (" + title +
             ") with the initial want value of " + w + " is added!");
    }

    // Modify the want value for a specified title.
    // Preconditions: None.
    // Postcondition: Prompt the user to input the want value for the specified
    //                title. If the input is valid, the want value for the title
    //                is modified. Otherwise, the want value is set to zero.
    //
    private void modifyWantValue(final String title) {

        // ==> 1. Add your code here!
        //System.out.println("-- To be implemented!"); // comment this statement!
    	final StockItem stockItem = this.inventory.findStockItem(title);
        if (stockItem != null) {
            this.readInput("Input the want value for stock item \"" + title + "\": ");
            int w;
            try {
                w = Integer.parseInt(this.nextLine);
                System.out.println();
            }
            catch (Exception e) {
                System.out.println("** Invalid input: the want value is set to zero!");
                w = 0;
            }
            final int originalWant = stockItem.getWant();
            stockItem.setWant(w);
            System.out.println("-- The want value for stock item \"" + title + "\" is changed from " + originalWant + " to " + w + ".");
        }
        else {
            System.out.println("Stock item not found! (title: " + title + ")\n");
        }

    }

    // Print out the purchase order for additional videos based on a comparison
    // of the have and want values in the inventory.
    // Preconditions: None.
    // Postconditoon: For each title in the inventory list, if the want value is
    //                bigger than the have value, the have vaule is brought up to
    //                the want value, and the purchase order for that title is
    //                printed out.
    //
    private void purchaseOrder() {

        // ==> 2. Add your code here!
        //System.out.println("-- To be implemented!"); // comment this statement!
    	int count = 0;
        System.out.println("\nPurchase Order");
        System.out.println("==============");
        for (int j = 1; j <= this.inventory.size(); j++) {
            final StockItem stockItem = (StockItem)this.inventory.get(j);
            final String title = stockItem.getTitle();
            final int originalHave = stockItem.getHave();
            final int order = stockItem.getWant() - originalHave;
            if (order > 0) {
                count++;
                System.out.println("[" + count + "] " + order + " videos for stock item \"" + title + "\" has been ordered.");
                stockItem.setHave(originalHave + order);
            }
        }
        if (count == 0) {
            System.out.println("None.");
        }

    }

    // Print out the return order for unwanted videos based on a comparison
    // of the have and want values in the inventory.
    // Preconditions: None.
    // Postconditoon: For each title, if the have value is bigger than the want
    //                value, the have vaule is reduced to the want value, and
    //                the return order for that title is printed out.
    //
    private void returnOrder() {

        // ==> 3. Add your code here!
        //System.out.println("-- To be implemented!"); // comment this statement!
    	int count = 0;
        System.out.println("\nReturn Order");
        System.out.println("============");
        for (int i= 1; i <= this.inventory.size(); i++) {
            final StockItem stockItem = (StockItem)this.inventory.get(i);
            final String title = stockItem.getTitle();
            final int originalWant = stockItem.getWant();
            final int returnValue = stockItem.getHave() - originalWant;
            if (returnValue > 0) {
                count++;
                System.out.println("[" + count + "] " + returnValue + " videos for stock item \"" + title + "\" has been returned.");
                stockItem.setHave(originalWant);
            }
        }
        if (count == 0) {
            System.out.println("None.");
        }

    }

    // Sell a video for the specified title. If the title is sold out, put
    // a name on the waiting list for the title.
    // Preconditions: None.
    // Postcondition: The have value for the specified title is decreased
    //                by 1 if the have value is bigger than zero. If the title
    //                is sold out, put a name on the wait list for the title.
    //
    private void sellVideo(String title) {

        // ==> 4. Add your code here!
        //System.out.println("-- To be implemented!"); // comment this statement!
    	int h = 0;
        final StockItem stockItem = this.inventory.findStockItem(title);
        if (stockItem != null) {
            h = stockItem.getHave();
            if (h > 0) {
                stockItem.setHave(h - 1);
                System.out.println("-- A video of title \"" + title + "\" is sold.");
            }
            else {
                this.readInput("The title is sold out! Put a name on the waiting list for stock item \"" + title + "\"\nInput first name: ");
                final String firstName = this.nextLine;
                this.readInput("Input last name: ");
                final String lastName = this.nextLine;
                final Person aPerson = new Person(firstName, lastName);
                this.inventory.addToWaitingList(title, aPerson);
                System.out.println("-- \"" + firstName + " " + lastName + "\" has been put on the waiting list for stock item \"" + title + "\".");
            }
        }
        else {
            System.out.println("Stock item not found! (title: " + title + ")");
        }

    }

    // Deliver videos to people on the waiting list for a specified title
    // Preconditions: None.
    // Postcondition: If the have value for the specified title is bigger than
    //                zero, deliver videos to the people on the waiting list
    //                for that title. If there are not enough videos currently
    //                in stock, those people in the front of the waiting
    //                list will receive videos.
    //
    private void deliverVideo(String title) {

        // ==> 5. Add your code here!
        //System.out.println("-- To be implemented!"); // comment this statement!
    	final StockItem stockItem = this.inventory.findStockItem(title);
        if (stockItem != null) {
            int h = stockItem.getHave();
            final WaitingList waitingList = stockItem.getWaitingList();
            if (waitingList.size() == 0) {
                System.out.println("-- The waiting list for this title is empty!");
            }
            else if (h == 0) {
                System.out.println("-- No video for this title is currently in stock!\n   Please order first!");
            }
            while (h > 0) {
                if (waitingList.size() <= 0) {
                    break;
                }
                final Person aPerson = this.inventory.deleteFromWaitingList(title);
                System.out.println("-- The video \"" + title + "\" is delivered " + "to \"" + aPerson.toString() + "\"");
                h--;
                stockItem.setHave(h);
            }
        }
        else {
            System.out.println("Stock item not found! (title: " + title + ")");
        }

    }

    // Display the help menu for user interface
    //
    private void helpMenu() {
        System.out.println();
        System.out.println("                             =========");
        System.out.println("                             Help Menu");
        System.out.println("                             =========");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("H         (Help)      Help menu");
        System.out.println("I <title> (inquire)   Display the inventory info");
        System.out.println("L         (list)      List the entire inventory");
        System.out.println("A <title> (add)       Add a new title to the inventory");
        System.out.println("M <title> (modify)    Modify the want value for a specific title");
        System.out.println("D <title> (delivery)  Deliver videos to people on the waiting list");
        System.out.println("O         (order)     Display the purchase order");
        System.out.println("R         (return)    Display the return order");
        System.out.println("S <title> (sell)      Sell a specified title");
        System.out.println("X <title> (delete)    Delete the stock from the inventory");
        System.out.println("Q         (quit)      Save the inventory info and terminate execution");
        System.out.println("---------------------------------------------------------------------");

    }

    // process user input and print out the results
    public void processInput() {
        Inventory inv = null;
        StockItem stockItem = new StockItem();
        String command, title =  null;

        System.out.println("Restoring inventory from file \"inventory.dat\" ... ");
        if ((inv = (Inventory) inventory.restoreInventory()) != null)
            inventory = inv;
        else {
            System.out.println("Restore inventory error!");
        }

        this.helpMenu(); // display help menu

        while(true) {
            readInput("\nInput the command (enter \"Q\" or \"q\" to quit): ");

            StringTokenizer input = new StringTokenizer(nextLine);
            try {
                command = input.nextToken();
                if (command.charAt(0) == 'I' || command.charAt(0) == 'i' ||
                    command.charAt(0) == 'A' || command.charAt(0) == 'a' ||
                    command.charAt(0) == 'M' || command.charAt(0) == 'm' ||
                    command.charAt(0) == 'D' || command.charAt(0) == 'd' ||
                    command.charAt(0) == 'S' || command.charAt(0) == 's')
                    //command.charAt(0) == 'X' || command.charAt(0) == 'x')
                	{
                    title = nextLine.substring(2);
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Type 'H' for help.");
                continue;
            }

            switch (command.charAt(0)) {
               case 'H': case 'h': helpMenu(); break;
               case 'L': case 'l': inventory.listInventory(); break;
               case 'I': case 'i': displayVideoInfo(title); break;
               case 'A': case 'a': addNewTitle(title); break;
               case 'M': case 'm': modifyWantValue(title); break;
               case 'D': case 'd': deliverVideo(title); break;
               case 'O': case 'o': purchaseOrder(); break;
               case 'R': case 'r': returnOrder(); break;
               case 'S': case 's': sellVideo(title); break;
               //case 'C': case 'c' : person.displaysWaitingList(); break;
               //case 'X': case 'x' : inventory.deleteStockItem(stockItem); break; 
               case 'Q': case 'q':
                  System.out.print("\nSaving inventory to file \"inventory.dat\" ... ");
                  inventory.saveInventory();
                  System.out.println("Done!");
                  System.exit(0);
               default: System.out.println("Invalid input! Type 'H' for help.");
            }
        }
    }

    public static void main(String[] args) {
        InventoryApp inventoryApp = new InventoryApp();
        inventoryApp.processInput();
    }
}
