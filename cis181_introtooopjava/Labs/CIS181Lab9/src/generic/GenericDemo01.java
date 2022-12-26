package generic;

public class GenericDemo01 {

	public static void main(String[] args) {
		//Do not specify any type, the default type is Object
        GenericClass genericClass = new GenericClass();
        genericClass.setName("Can be any type");
        System.out.println(genericClass.getName());
        genericClass.setName(111);
        System.out.println(genericClass.getName());
        System.out.println("-------------------------------");

        //Specify Integer class,only be used as int type
        GenericClass<Integer> integerGenericClass = new GenericClass<Integer>();
        // coding starting here
        integerGenericClass.setName(11111);
        System.out.println(integerGenericClass.getName());
        System.out.println("-------------------------------");
	// coding ending here


        //Specify String class,only be used as String type
        GenericClass<String> stringGenericClass = new GenericClass<String>();
        // coding starting here
        stringGenericClass.setName("make sure it is String Type");
        System.out.println(stringGenericClass.getName());
        System.out.println("-------------------------------");

	// coding ending here

	}

}
