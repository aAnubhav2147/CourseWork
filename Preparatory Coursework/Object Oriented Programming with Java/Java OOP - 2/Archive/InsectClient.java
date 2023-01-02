public class InsectClient{
	public static void main(String args[]){
	 System.out.println(Insect.produceRandomFacts());
	 Insect bug1 = new Insect(13,31,0);
	 System.out.println(bug1.getWeight());
	 System.out.println(bug1.getX());
	 bug1.setX(314);
	 System.out.println(bug1.getY());
	 bug1.setY(133);
	 System.out.println(Insect.getPopulation());
	 System.out.println(bug1.getX());
	 System.out.println(bug1.getY());

	 Insect bug2 = new Insect(31);
	 System.out.println(bug2);
	 System.out.println(Insect.getPopulation());
	}
}