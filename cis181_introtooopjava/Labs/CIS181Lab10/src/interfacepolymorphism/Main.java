package interfacepolymorphism;

public class Main {
    public static void main(String[] args) {
        //create an object of laptop mac
        LapTop mac = new LapTop("Mac", "white", 14999);

        //start to run laptop
        mac.start();
        System.out.println("-------------------------------");

        //using keyboard of laptop
        // coding starts here
        System.out.println("Use USB connected devices");
        Keyboard k = new Keyboard();
        k.open();
        System.out.println("-------------------------------");
        //coding ends here

        //use mouth of laptop
        // coding starts here
        System.out.println("Use USB connected devices");
        Mouse m = new Mouse();
        m.open();
        System.out.println("-------------------------------");
        //coding ends here

        //close mouse, keyboard, and shutdown laptop mac
        Mouse mouse = new Mouse();
        mouse.close();
        Keyboard keyboard = new Keyboard();
        keyboard.close();
        mac.powerOff();
    }
}
