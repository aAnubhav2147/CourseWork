package interfacepolymorphism;

public class Demo01 implements IP01 {

	@Override
    public void methodAbs() {
        System.out.println("method 1");
    }

    @Override
    public void methodAbs2() {
        System.out.println("method 2");
    }

    @Override
    public void methodAbs3() {
        System.out.println("method 3");
    }

    @Override
    public void methodAbs4() {
        System.out.println("method 4");
    }
    
  //test methods
    public static void main(String[] args) {
        Demo01 Demo01 = new Demo01();

        // using Demo01 to call those methods defined in ther interface IP01
        Demo01.methodAbs();
        Demo01.methodAbs2();
        Demo01.methodAbs3();
        Demo01.methodAbs4();
    }

}
