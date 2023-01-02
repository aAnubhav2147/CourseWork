public class SubZero extends SuperHero {
    public SubZero() {
        System.out.println("sub zero");
    }
    public SubZero(int i) {
        this();
        System.out.println("sub integer");
    }
    public static void main(String[] args) {
        SuperHero sd = new SuperHero();
        System.out.println("111");
        SubZero one = new SubZero(000);
        System.out.println("222");
        SubZero two = new SubZero();
        System.out.println("333");
        SuperHero sd2 = new SubZero(3);
    }
}