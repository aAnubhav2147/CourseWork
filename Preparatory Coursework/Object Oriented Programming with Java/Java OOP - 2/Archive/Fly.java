public class Fly {

//Initiate Variables
private double mass;
private double speed;

//static variables
public static final double DEFAULT_MASS = 5;
public static final double DEFAULT_SPEED = 10;
public static final double DEAD_MASS = 0;
	
//Constructors
public Fly(double tempmass){
    this(tempmass,DEFAULT_SPEED);
}

public Fly(){
	this(DEFAULT_MASS,DEFAULT_SPEED);
}

public Fly(double mass,double speed){
	this.mass = mass;
	this.speed = speed;
}

//Methods
public double getMass(){
	return mass;
}

public double getSpeed(){
	return speed;
}

public void setMass(double mass){
	this.mass = mass;
}

public void setSpeed(double speed){
	this.speed = speed;
}

public boolean isDead(){
	if(mass == DEAD_MASS){
		return true;
	} else {
		return false;
	}
}

public void grow(int amount){
	mass = (int)mass + amount;
	if (mass < 20){
		do{
			speed = speed++;
		} while (mass <= 20);

	} else if (mass >= 20){
		do{
			speed = speed - 0.5;
		} while (mass++ >= 20);
	}

}

public String toString(){
	String str_speed = String.format("%.2f",speed);
	String str_mass = String.format("%.2f",mass);
	if (mass == DEAD_MASS){
		return "I'm dead, but I used to be a fly with a speed of " + str_speed + ".";
	}
	else{
		return "I'm a speedy fly with  " + str_speed + "speed and " + str_mass + " mass."; 
	}
} 

}    