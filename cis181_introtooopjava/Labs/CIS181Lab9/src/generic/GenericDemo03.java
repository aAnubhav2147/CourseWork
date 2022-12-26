package generic;

public class GenericDemo03 {

	public static void main(String[] args) {
        //implementation1
        GenericInterfaceImplementation1 genericInterfaceImpl1 = new GenericInterfaceImplementation1();
        // coding starting here
        //genericInterfaceImpl1.method("receive String as a parameter");
        genericInterfaceImpl1.method("can only transmit string type here");
	// coding ending here


        //implementation2
        GenericInterfaceImplementation2<String> stringGenericInterfaceImpl2 = new GenericInterfaceImplementation2<>();
        // coding starting here
        //stringGenericInterfaceImpl2.method("transmit string" + "\n" + "transmit integer type");
        stringGenericInterfaceImpl2.method("transmit string");
        //stringGenericInterfaceImpl2.method("transmit integer type");
	// coding ending here

        GenericInterfaceImplementation2<Integer> integerGenericInterfaceImpl2 = new GenericInterfaceImplementation2<>();
        // coding starting here
        //stringGenericInterfaceImpl2.method("transmit integer type");
        System.out.println("transmit integer type");
        integerGenericInterfaceImpl2.method(111);        
	// coding ending here;
    }

}
