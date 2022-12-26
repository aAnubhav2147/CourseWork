package lab7;

public class SortedListTester {

    // displays the items on the list aList
    public static void displayList(SortedListInterface aList){
        Object dataItem;
        try {
            for (int index = 1; index < aList.size(); index++){
                dataItem = aList.get(index);
                System.out.print(dataItem + ", ");
            }
            if (aList.size() > 0) {
                dataItem = aList.get(aList.size());
                System.out.println(dataItem + ".");
            }
        } catch(ListException e) {}
    }

    public static void main(String[] args) {
        SortedList aList = new SortedList();
        String dataItem;
        int index;

        try {
            aList.sortedAdd("bb");
            aList.sortedAdd("cc");
            aList.sortedAdd("aa");
            aList.sortedAdd("ee");
            aList.sortedAdd("dd");
        } catch(ListException e) {}

        displayList(aList);

        try{
            aList.sortedRemove("cc");
            aList.sortedAdd("ff");
        } catch(ListException e) {}

        displayList(aList);

        try {
            aList.sortedRemove("cc");
        } catch(ListException e) {}

        try {
            aList.sortedAdd("cat");
        } catch(ListException e) {}

        displayList(aList);

        try {
            dataItem = (String)aList.get(99);
            System.out.println("dataItem = " + dataItem);
        } catch(ListException e) {}

        displayList(aList);

        index = aList.locateIndex("dd");
        System.out.println("The index of \"dd\" in the list is: " + index);
        index = aList.locateIndex("kk");
        System.out.println("The index of \"kk\" in the list is: " + index);

    }
}
