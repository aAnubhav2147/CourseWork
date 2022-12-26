package danger;

public class DangerException extends Exception{
    String information;
    
    public DangerException(String information){
        this.information=information;
    }
    
    /*
    public DangerException(Goods g){
    	Goods.information= g;
    	} */
    
    void toShow(){
            System.out.println(information);
   }
}