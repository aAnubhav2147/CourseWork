package interfacepolymorphism;

//Keyboard class implements USB interface
public class Keyboard implements USB{
	// overriding methods open() and close() and output messages:
	// "Keyboard USB connected successfully"
	// "Keyboard USB disconnected"
	
	// coding starts here
	@Override
	public void open() {
		System.out.println("Keyboard USB connected successfully");
	}
	
	@Override
	public void close() {
		System.out.println("Keyboard USB disconnected");
	}

  	// coding ends here
  
  void click(){
      System.out.println("Keyboard is used with inputs");
  }
}
