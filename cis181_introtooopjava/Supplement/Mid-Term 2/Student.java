package copy;

//An example of shallow copy
public class Student implements Cloneable { 
	 
	   // reference objects
	   private Subject subj; 
	   private String name; 
	 
	   // constructor
	   public Student(String s, String sub) { 
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
	      // shallow copy
	      try { 
	         // directly call clone method from super class
	         return super.clone(); 
	      } catch (CloneNotSupportedException e) { 
	         return null; // garbage collection for null
	      } 
	   } 
	}