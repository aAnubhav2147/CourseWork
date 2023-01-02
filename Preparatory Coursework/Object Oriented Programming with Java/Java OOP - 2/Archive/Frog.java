public class Frog{

	//Initiate Variables
	private double tongueSpeed;
	private int age;
	//private double ageInYears = (double) age/12;
	private boolean isFroglet;
	private String name;
	public String species = "Rare Pepe";

	public static final int DEFAULT_AGE = 5;
	public static final double DEFAULT_TONGUESPEED = 5.0;
	
    //Constructors

    public Frog(String name){
    	this(DEFAULT_AGE,DEFAULT_TONGUESPEED,name);
    }
    public Frog(double ageInYears, double tongueSpeed,String name){
    	this.tongueSpeed = tongueSpeed;
    	this.age = (int)(ageInYears * 12);
    	this.name = name;
    }
    public Frog(int age, double tongueSpeed,String name){
    	this.tongueSpeed = tongueSpeed;
    	this.age = age;
    	this.name = name;
    }

    //Getters and Setters

    public String getName(){
    	return name;
    }

    public void setName(){
    	this.name = name;
    }

    public int getAge(){
    	return age;
    }

    public void setAge(int age){
    	this.age = age;
    }

    public double getTongueSpeed(){
    	return tongueSpeed;
    }

    public void setTongueSpeed(double tongueSpeed){
    	this.tongueSpeed = tongueSpeed;
    }

    public boolean isFroglet(){
    	return isFroglet;
    }

    public void setIsFroglet(boolean isFroglet){
    	this.isFroglet = isFroglet;
    }

    public String getSpecies(){
    	return species;
    }

    public void setSpecies(String species){
    	this.species = species;
    }

    //Methods

    //Grow

    public void grow(int addage){
    	if(age<12){
    		int temp_age = age;
    		for(int i = 0; i<addage; i++){
    			if(temp_age < 12){
    				tongueSpeed++;
    			}
    			temp_age++;
    		}
    	}

    	//adding age

        age = age + addage;

        if(age>7){
    	    isFroglet = false;
    }

    for(int i = 30; i<=30; i++){
    	tongueSpeed--;
    }

    }

    public void grow(){
    	if(age>12){
    		tongueSpeed++;
    	}

    	age = age + 1;
    	for(int i = 30; i<=30; i++){
    		tongueSpeed--;
    	}
    }

    public boolean eat(Fly fly){
    	if(!fly.isDead()){
    		if(tongueSpeed>fly.getSpeed()){
    			if((fly.getMass() - age) >= 0.5){
    				grow();
    			}
    			fly.setMass(0);
    			return true;
    		}
    		else{
    			fly.grow(1);
    			return false;
    		}
    	}
    	return false;
    }

    public String toString(){
    	String tongue = String.format("%.2f", tongueSpeed);
    	if(isFroglet == true){
    		return "My name is " +name+ " and I'm a rare froglet. I'm " +age+ 
    		" months old and my tongue has a speed of " +tongue;
    	}
    	return "My name is " +name+ " and I'm a rare frog. I'm " +age+ " months old and my tongue has a speed of " +tongue;
    }
}