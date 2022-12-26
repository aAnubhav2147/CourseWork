package generic;

public class GenericInterfaceImplementation1 implements GenericInterface<String> {
	@Override
    public void method(String i) {
        System.out.println("receive string as a parameter");
        System.out.println(i);
        System.out.println("-------------------------------");
    }
}