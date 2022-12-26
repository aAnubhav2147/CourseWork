package generic;

// Another implementation of generic interface

public class GenericInterfaceImplementation2<E> implements GenericInterface<E>  {
	@Override
    public void method(E i) {
        System.out.println("Generic class, accepting any type of data");
        System.out.println(i);
        System.out.println("-------------------------------");
    }
}
