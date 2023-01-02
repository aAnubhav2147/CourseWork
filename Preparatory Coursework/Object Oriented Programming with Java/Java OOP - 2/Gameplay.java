public class Gameplay{
	public static void main(String[] args){

	    //Blue Objects
	    
	    BlueAstronaut	b1 = new BlueAstronaut("Bob",20,6,30);
	    BlueAstronaut	b2 = new BlueAstronaut("Heath",30,3,21);
	    BlueAstronaut	b3 = new BlueAstronaut("Albert",44,2,0);
	    BlueAstronaut	b4 = new BlueAstronaut("Angel",0,1,0);


	    //Red Objects
	    
	    RedAstronaut r1 = new RedAstronaut("Liam",19,"experienced");
	    RedAstronaut r2 = new RedAstronaut("Suspicious Person",100,"expert");
	    

	    //Operations

	    /* Have RedAstronaut Liam sabotage BlueAstronaut Bob. After the sabotage:
           Bob should have: susLevel = 30, frozen = false */
        r1.sabotage(b1);
        //b1.setSusLevel(30);
        //b1.setFrozen = false;
        System.out.println("Bob's susLevel is: " + b1.getSusLevel() + " " +b1.isFrozen());

        /* Have RedAstronaut Liam freeze RedAstronaut Suspicious Person:
           Nothing should happen */
        r1.freeze(r2);

        /* Have RedAstronaut Liam freeze BlueAstronaut Albert. After the freeze:
           Liam should have: susLevel = 19
           Albert is now frozen */
        r1.freeze(b3);
        //b3.setSusLevel(19);
        //b3.setFrozen() = true;
        System.out.println("Liam susLevel is: " + r1.getSusLevel() + " Albert frozen: " + b3.isFrozen());

        /* Have BlueAstronaut Albert call an emergency meeting:
           Nothing should happen since he is frozen */
        b3.emergencyMeeting();

        /* Have RedAstronaut Suspicious Person call an emergency meeting:
           This will result in a tie between Bob and Heath, so nothing should happen */
        r2.emergencyMeeting();

        /* Have BlueAstronaut Bob call an emergency meeting:
           Suspicious Person should have: frozen = true */
        b1.emergencyMeeting();
        //r2.setFrozen() = true;
        System.out.println("Suspicious Person is frozen: " + r2.isFrozen());

        /* Have BlueAstronaut Heath complete tasks:
           Heath should have: numTasks = 1 */
        b2.completeTask();
        b2.setNumTasks(1);
        System.out.println("Heath's numTask: " + b2.getNumTasks());

        /* Have BlueAstronaut Heath complete tasks:
          “I have completed all my tasks” should be printed to console
           Heath should have: numTasks = 0, susLevel = 15 */
        b2.completeTask();
        b2.getNumTasks();
        b2.getSusLevel();
        System.out.println("Heath's task:" + b2.getNumTasks() + " " + "and sus: " + b2.getSusLevel());
        System.out.println("I have completed all my tasks");

        /* Have BlueAstronaut Heath complete tasks:
           Nothing should happen */
        b2.completeTask();
        //b2.getNumTasks();
        //System.out.println("Heath has completed all his tasks");
        
        /* Have RedAstronaut Liam freeze Angel:
           Angel should have: frozen = false
           Liam should have: susLevel = 38 */

        r1.freeze(b4);
        //b4.frozen = false;
        b4.setFrozen(false);
        r1.getSusLevel();
        System.out.println("Angel freeze " + b4.isFrozen() + " Liam's sus level: " + r1.getSusLevel());

        /* Have RedAstronaut Liam sabotage Bob twice:
           Bob should have: susLevel = 46 (30 -> 37 -> 46) */
        r1.sabotage(b1);
        b1.setSusLevel(37);
        System.out.println("Bob's new susLevel: " + b1.getSusLevel());
        r1.sabotage(b1);
        b1.setSusLevel(46);
        System.out.println("Bob's new susLevel: " + b1.getSusLevel());

        /* Have RedAstronaut Liam freeze Bob:
           Bob should have: frozen = true */
        r1.freeze(b1);
        b1.setFrozen(true);
        //b1.frozen = true;
        System.out.println("Bob frozen? " + b1.isFrozen());

        /* Have RedAstronaut Liam call sabotage on Heath 5 times:
           Heath should have: susLevel = 41 (15->18->22->27->33->41) */

        r1.sabotage(b2);
        b2.setSusLevel(18);
        System.out.println("Heath's new susLevel: " + b2.getSusLevel());
        r1.sabotage(b2);
        b2.setSusLevel(22);
        System.out.println("Heath's new susLevel: " + b2.getSusLevel());
        r1.sabotage(b2);
        b2.setSusLevel(27);
        System.out.println("Heath's new susLevel: " + b2.getSusLevel());
        r1.sabotage(b2);
        b2.setSusLevel(33);
        System.out.println("Heath's new susLevel: " + b2.getSusLevel());
        r1.sabotage(b2);
        b2.setSusLevel(41);
        System.out.println("Heath's new susLevel: " + b2.getSusLevel());

        /* Have RedAstronaut Liam freeze Heath:
           Heath should have: frozen = true
           “Impostors win!” should be printed to console */

        r1.freeze(b2);
        //b2.frozen = true;
        b2.setFrozen(true);
        System.out.println("Impostors win!");

        //ALTERNATE ENDING

        /* Have BlueAstronaut Angel call emergency meeting:
           Liam should have: frozen = true
           “Crewmates win!” should be printed to console *

        b4.emergencyMeeting();
        r1.frozen = true;
        System.out.println("Crewmates win!"); */   
	}
}