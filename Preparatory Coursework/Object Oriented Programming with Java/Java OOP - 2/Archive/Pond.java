public class Pond{
	
	public static void main(String[] args){

	    //Frog Objects
	    Frog peepo = new Frog("Peepo");
	    Frog pepe = new Frog(15,10,"Pepe");	    
	    Frog peepaw = new Frog(5,4.6,"Peepaw");
	    Frog froggo = new Frog(10,10,"Froggo");

	    //Fly Objects
	    Fly f1 = new Fly(3,1);
	    Fly f2 = new Fly(6);
	    Fly f3 = new Fly(4,5);

	    //Operations

	    //Set species

	    froggo.setSpecies("1331 Frogs");

	    //Print out Peepo's info

	    System.out.println(peepo.toString());

	    //Peepo's eating attempt

	    peepo.eat(f2);

	    //Info for Fly f2

	    System.out.println(f2.toString());

	    //Make peepo grow by 8 months

	    peepo.grow(8);

	    //Peepo's second eating attempt

	    peepo.eat(f2);

	    //Print out the required lines

	    System.out.println(f2.toString());
	    System.out.println(peepo.toString());
	    System.out.println(froggo.toString());

	    //Grow Peepaw

	    peepaw.grow(4);
	    System.out.println(peepaw.toString());
	    System.out.println(pepe.toString());

	}
}
