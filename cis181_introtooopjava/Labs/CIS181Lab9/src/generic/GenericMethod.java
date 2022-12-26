package generic;

public class GenericMethod {
	//define a generic method
    public <M> void method(M m){
        System.out.println(m);
    }

    //define a static generic method
    public static <S> void method2(S s){
        System.out.println(s);
    }
}
