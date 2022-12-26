package list;

import java.util.ArrayList;
import java.util.List;

public class List01 {
    public static void main(String[] args) {
        //Create List
        List<String> strings = new ArrayList<>();
        strings.add("James");
        strings.add("Monica");
        strings.add("Leslie");

        //get
        System.out.println(strings.get(1));
        
        //remove
        strings.remove(1);
        
        
        //set
        strings.set(1, "Jessica");
        System.out.println(strings);
        System.out.println("-------------------------------");

        //traverse
        for (String string : strings) {
            System.out.println(string);
        }

    }
}
