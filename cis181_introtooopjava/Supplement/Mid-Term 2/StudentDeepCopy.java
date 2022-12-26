package copy;

public class StudentDeepCopy implements Cloneable { 
	 
	   // reference objects
	   private Subject subj; 
	   private String name; 
	 
	   public StudentDeepCopy(String s, String sub) { 
	      name = s; 
	      subj = new Subject(sub); 
	   } 
	 
	   public Subject getSubj() { 
	      return subj; 
	   } 
	 
	   public String getName() { 
	      return name; 
	   } 
	 
	   public void setName(String s) { 
	      name = s; 
	   } 
	 
	   // override clone method
	   public Object clone() { 
		   // deep copy
		   // create a new object, two object are independent
		   StudentDeepCopy s = new StudentDeepCopy(name, subj.getName()); 
		   return s; 
	   } 
	}