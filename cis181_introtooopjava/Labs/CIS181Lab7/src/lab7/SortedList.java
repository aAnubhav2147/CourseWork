package lab7;

public class SortedList implements SortedListInterface {
    private static final int MAX_LIST = 50;
    private Object items[];  // an array of list items
    private int numItems;    // number of items in list

    // default constructor
    public SortedList() {
         items = new Object[MAX_LIST];
         numItems = 0;
    }

    public boolean isEmpty() {
        return numItems == 0;
    }

    public int size() {
        return numItems;
    }

    public Object get(int index) throws ListException {

        if (index >= 1 && index <= size()) {
            return items[index-1];
        } else  {  // index out of range
            throw new ListException("get (index out of range): " + index);
        }
    }

    public void removeAll() {
        items = new Object[MAX_LIST];
        numItems = 0;
    }

    // new operations: sortedAdd
    @SuppressWarnings("rawtypes")
    public void sortedAdd(Comparable newItem) throws ListException {
        int index = 1;

        if (size() == MAX_LIST)
            throw new ListException("add (array is full)");

        // ToDo
        if (numItems == 0) {
            items[0] = newItem;
            numItems++; // adds items to an empty list
            return;
        }

        Object[] tempList = new Object[MAX_LIST];
        for (int i = 0; i < numItems; i++) { 
        	// for loop adds items 
            if (newItem.compareTo(items[i]) < 0) { 
            	// add new item
                tempList[i] = newItem;
                numItems++;
                for (int j = i + 1; j < numItems; j++) {  
                	// item is added in sorted order
                    tempList[j] = items[i];
                    i++;
                }
                items = tempList;
                return;
            } else { 

                tempList[i] = items[i];
            }
        }
        tempList[numItems]=newItem; //
        numItems++;
        items=tempList; //put the sorted elements in a new list
        return; 
        
        //Attempt 1
        /*
        for (int i = 0; i < numItems; i++) { 
        	// for loop adds items 
            if (newItem.compareTo(items[i]) < 0) { 
            	// add new item
                tempList[i] = newItem;
                numItems++;
                for (int j = i + 1; j < numItems; j++) {  
                	// item is added in sorted order
                    tempList[j] = items[i];
                    j++;
                }
                items = tempList;
                return; */
        
        //Attempt 2
        /*
        for (int i = 0; i < numItems; i++) { 
        	// for loop adds items 
            if (newItem.compareTo(items[i]) < 0) { 
            	// add new item
                tempList[i] = newItem;
                numItems++;
                for (int j = i; j < items.length; j++) {  
                	// item is added in sorted order
                    tempList[j] = items[i];
                    j++;
                }
                items = tempList;
                return; */       

    }

    // new operation: sortedRemove
    @SuppressWarnings("rawtypes")
    public void sortedRemove(Comparable anItem) throws ListException {
        //int index = 1;
    	int index = 7;

        // ToDo -> remove items from sorted list
        Object[] tempArr = new Object[MAX_LIST];
        for (int i = 0; i < numItems; i++) {
            if (anItem.compareTo(items[i])== 0) { //use the comparable interface
            	
                for (int j = i ; j < numItems; j++) { // for loop removes items
                    i++;
                    tempArr[j] = items[i];
                    //throw new ListException("sortedRemove (item not in the list): " + anItem);
                }
                tempArr[numItems - 1]=null; // declares new list null
                numItems--; // removes items
                items = tempArr;
                //tempArr = items;
                 
                //System.out.println("sortedRemove (item not in the list): ");
                
                throw new ListException("sortedRemove (item not in the list): " + anItem);
                //return;
            } else {
                tempArr[i] = items[i];
                //System.out.println("sortedRemove (item not in the list): ");
            }
            //System.out.println("sortedRemove (item not in the list): ");
        }
        
        //Attempt 1
        /*
        Object[] tempList = new Object[MAX_LIST];
        for (int i = 0; i < numItems; i++) {
            if (anItem.compareTo(items[i])== 0) {//use the comparable interface
                for (int j = 0 ; j < numItems; j++) { 
                    j++;
                    newList[j] = items[i];
                }
                newList[numItems-1]=0; 
                numItems--; // removes items 
                items = newList;
                return;
            } else {
                newList[i] = items[i];
            }
        } */

    }

    // new operation: locateIndex
    public int locateIndex(Comparable anItem) {
        //int index = 1;
    	int index = 7;

        // ToDo -> return index position in a sorted list if the element exists in a sorted list
        for (int i = 0; i < numItems; i++) {
            if (anItem.compareTo(items[i]) == 0) {
                return i+1;
            }
        }
        
        //Attempt 1
        /*
        for (int i = 0; i < anItem.count; i++) {
            if (anItem.compareTo(items[i]) == 0) {
                return i+1;
            }
        } */

        return index;
    }
}