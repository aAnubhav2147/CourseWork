public class ParkingGarage {

	// instance variables
	private Car[] cars;
	private int spots;

	public ParkingGarage(int spots) {
		this.spots = spots;
		cars = new Car[spots]; // array of length = spots
	}

	/**
	 * try and park the car. return false if lot is full
	 * 
	 * @param carToBeParked
	 * @return
	 */
	public boolean park(Car carToBeParked) {
		// loop through array
		// first empty spot
		int i = 0;	
		while ((cars[i] != null) && (i < cars.length)) {
			i++;
		}
		if (i == cars.length) return false;
		// i will now be the first empty spot
		cars[i] = carToBeParked;
		// car's parking spot is being set
		carToBeParked.setParkingLoc(i);
		return true;
	}
}