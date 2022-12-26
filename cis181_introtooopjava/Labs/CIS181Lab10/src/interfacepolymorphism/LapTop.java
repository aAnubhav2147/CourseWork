package interfacepolymorphism;

//define Laptop class which has functions of starting, shutting down, and using USB
public class LapTop {
  String brand;//laptop brand
  String color;//laptop color
  double price;//laptop price

  //start laptop
  void start(){
      System.out.println("start laptop");
  }
  //shut down laptop
  void powerOff(){
      System.out.println("shut down laptop");
  }
  //use USB functionality
  USB useUsb(USB usb){
      System.out.println("Use USB connected devices ");
      return usb;
  }

  public LapTop() {
  }

  public LapTop(String brand, String color, double price) {
      this.brand = brand;
      this.color = color;
      this.price = price;
  }

  public String getBrand() {
      return brand;
  }

  public void setBrand(String brand) {
      this.brand = brand;
  }

  public String getColor() {
      return color;
  }

  public void setColor(String color) {
      this.color = color;
  }

  public double getPrice() {
      return price;
  }

  public void setPrice(double price) {
      this.price = price;
  }

  @Override
  public String toString() {
      return "LapTop{" +
              "brand='" + brand + '\'' +
              ", color='" + color + '\'' +
              ", price=" + price +
              '}';
  }
}