package counter;

public class Counter {
	public static void main(String[] args) {
		
	}
	int count = 0;
	
	public int increment() {
		count++;
		return count;
	}
	
	public int decrement() {
		count--;
		return count;
	}
	
	public int getCount() {
		return count;
	}

}
