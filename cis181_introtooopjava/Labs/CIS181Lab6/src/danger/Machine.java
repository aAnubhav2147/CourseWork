package danger;

public class Machine {
	  String name;
	    Goods g;

	    public boolean isDanger(String name) {
	        String score[] = {"bomb","poison","knife","gun","machete"}; //array of unsafe materials
	        boolean flag =false;
	        for(int i=0;i<score.length;i++) {
	            if(name.equals(score[i])) {
	            flag = true;
	            break;
	            }
	        }
	        return flag;
	         
	    }

	    void checkBag(Goods g) throws DangerException{
	        this.g=g;
	        name=g.getName();
	        // add code here starting
	        if (isDanger(name)) {
				DangerException danger = new DangerException(name); //initiate a new DangerException class to throw the error/exception
				throw danger;
				
			} else {
				System.out.println("Passed!");
			}
	        /*
	        if (g.isDanger()) {
				DangerException danger = new DangerException(name);
				throw danger;
			} else {
				System.out.println("Passed!");
			}  */	           
		// add code here ending	    
}
}
