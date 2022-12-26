package list;

public class ListException extends RuntimeException {

    public ListException(String s) {
        System.out.println("ListException: " + s);
    }
}
