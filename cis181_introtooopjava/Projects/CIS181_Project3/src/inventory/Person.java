package inventory;

import java.io.Serializable;

/**
 * @author Haiping Xu
 */
public class Person implements Comparable, Serializable {
    private String lastName;
    private String firstName;

    // constructor
    public Person(String first, String last) {
        lastName = last;
        firstName = first;
    }

    public String toString() {
        return firstName + " " + lastName;
    }

    // define how Person names are compared
    public int compareTo(Object rhs){
        String lhsStr = lastName + " " + firstName;
        lhsStr = lhsStr.toUpperCase();
        String rhsStr = ((Person)rhs).lastName + " " + ((Person)rhs).firstName;
        rhsStr = rhsStr.toUpperCase();
        return lhsStr.compareTo(rhsStr);
    }
}
