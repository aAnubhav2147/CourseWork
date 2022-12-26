package generic;

public class GenericDemo02 {

	public static void main(String[] args) {
        //create an instance of the class including generic method
        GenericMethod genericMethod = new GenericMethod();
        //call generic method
        //transmit any type of arguments into method
        genericMethod.method(11);
        // coding starting here
        genericMethod.method("string type");
        genericMethod.method(true);
        genericMethod.method(33.3333);
        System.out.println("-------------------------------");
	// coding ending here


        //static method cannot be called by an object
        //static method is called by class
        GenericMethod.method2("can be any data type");
        // coding starting here
        GenericMethod.method2(333);
        GenericMethod.method2(false);
        System.out.println("-------------------------------");
	// coding ending here

    }

}
