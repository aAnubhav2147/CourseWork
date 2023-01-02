import java.util.Arrays;

public class BlueAstronaut extends Player implements Crewmate{

	/* NOTE: BLUE Astronauts are the crewmates */

	//Required variables
	private int numTasks;
	private int taskSpeed;
	//private int susLevelBlue;
	//private boolean crewmate;

    //Static variables
	private static int DEFAULT_NUMTASKS = 6;
	private static int DEFAULT_TASKSPEED = 10;
	private static int DEFAULT_SUSLEVELBLUE = 15;

    //Constructors
	public BlueAstronaut(String name){
		this(name,DEFAULT_SUSLEVELBLUE,DEFAULT_NUMTASKS,DEFAULT_TASKSPEED);

		//this.susLevelBlue = DEFAULT_SUSLEVELBLUE;
		//this.taskSpeed = DEFAULT_TASKSPEED;
		//this.numTasks = DEFAULT_NUMTASKS;
	}

	public BlueAstronaut(String name,int susLevel,int numTasks, int taskSpeed){
		super(name,susLevel);
		//super(susLevel);
		this.numTasks = numTasks;
		this.taskSpeed = taskSpeed;
	}

	//Getters and Setters

	/* public int getSusLevelBlue(){
		this.susLevelBlue = susLevelBlue;
	}

	public void setSusLevelBlue(int susLevelBlue){
        if (susLevelBlue >= 0) {
            this.susLevelBlue = susLevelBlue;
        } else {
            super(susLevel);
        }
    } */

	public int getNumTasks(){
		return this.numTasks;
	}

	public void setNumTasks(int numTasks){
		this.numTasks = numTasks;
	}

	public int getTaskSpeed(){
		return this.taskSpeed;
	}

	public void setTaskSpeed(int taskSpeed){
		this.taskSpeed = taskSpeed;
	}

	//Methods
    
    //@Override
	public void emergencyMeeting(){
		//Frozen player can't call an emergency meeting
		if(!this.isFrozen()){
			Player[] players = getPlayers();
			Arrays.sort(players);
			//For this game to function optimally we need at-least two players
			if(players.length >= 2){
				//To iterate through the arrays efficiently without having to check each element individually
				for(int x = 1; x < players.length; x++){
					for(int y = 2; y < players.length; y++){
						//An already frozen player cannot freeze another player
						if(!(players[players.length - x].isFrozen()) && !(players[players.length - y].isFrozen())){
							//Players with equal susLevel cannot be voted off (frozen)
							if((players[players.length - x].getSusLevel()) != (players[players.length - y].getSusLevel())){
								players[players.length - x].setFrozen(true);
								gameOver();
							}
							return;
						}
					}
				}
			}
		}
	}

	public void completeTask(){
		if(!isFrozen()){
			if(this.taskSpeed > 20){
				setNumTasks(getNumTasks() - 2);
			}
			else{
				setNumTasks(getNumTasks() - 1);
			}

			if(this.numTasks < 0){
				setNumTasks(0);
			}

			if(this.numTasks == 0){
				System.out.println("I have completed all my tasks");
				this.setSusLevel(getSusLevel()/2);
			}			
		}			
	}

    //This method ensures that all the player entries and attributes are inherited from the Player(supercalss) to BlueAstronaut(Subclass)
	public boolean equals(Object o){
        if (o instanceof BlueAstronaut) {
            BlueAstronaut player = (BlueAstronaut) o;
            return this.getName().equals(player.getName()) && this.isFrozen() == player.isFrozen()
                && this.getSusLevel() == player.getSusLevel() && this.getNumTasks() == player.getNumTasks() 
                && this.getTaskSpeed() == player.getTaskSpeed();
        }
        return false;
	}

	public String toString(){
        String frozenString = isFrozen() ? "frozen" : "not frozen";
        String outBlue = "My name is " + getName() + " , and I have a suslevel of " + getSusLevel() + ". I am currently "
                     + frozenString + " . I have " + getNumTasks() + " left over." ;

        if(getSusLevel() < 15){
        	return outBlue;
        }
        else{
        	return outBlue.toUpperCase();
        }   
    }

    /*
    public static void main(String[] args){
    	BlueAstronaut[] players;
    } 
    */
}