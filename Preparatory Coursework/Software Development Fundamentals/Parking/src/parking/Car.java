package parking;

public class Car {
	//instance variables
	private String ownerName;
	private int ownerLicence;
	private int registrationNum;
	private int parkingLoc;
	
	//Constructor
	public Car(String name, int licence, int registration) {
		this.ownerName = name;
		this.ownerLicence = licence;
		this.registrationNum = registration;
	}
	
	//Getters and Setters
	public String getOwnerName() {
		return ownerName;
	}
	
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	
	public int getOwnerLicence() {
		return ownerLicence;
	}
	
	public int getRegistrationNum() {
		return registrationNum;
	}
	
	public int getParkingLoc() {
		return parkingLoc;
	}

	public void setParkingLoc(int parkingLoc) {
		this.parkingLoc = parkingLoc;
	}
	

}
