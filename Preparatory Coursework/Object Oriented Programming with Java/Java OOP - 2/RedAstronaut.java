import java.util.Arrays;

public class RedAstronaut extends Player implements Impostor{

	/* NOTE: RED Astronauts are the impostors. Hence, the parameters in sabotage() and freeze() reference the Blue's */ 

	//Required variables
	private String skill;
	//private int susLevelRed;
	//private boolean impostor;

    //Static variables
	private static String SKILL_LV1 = "inexperienced";
	private static String SKILL_LV2 = "expert";
	private static String DEFAULT_SKILL_LV = "experienced";
	private static int susLevelRed = 15;

    //Constructors
	public RedAstronaut(String name){
		this(name, susLevelRed, DEFAULT_SKILL_LV);

		//this.susLevelRed = 15;
		//this.skill = DEFAULT_SKILL_LV;
	}

	public RedAstronaut(String name,int susLevel,String skill){
		super(name,susLevel);
		//this.susLevelRed = super(susLevel);
		//this.skill = skill.toLowerCase();
		this.skill = skill.toLowerCase();
	}

	//Getters and Setters

    /*
	public int getSusLevelRed(){
		this.susLevelRed = susLevelRed;
	}

	public void setSusLevelRed(int susLevelRed) {
        if (susLevelRed >= 0) {
            this.susLevelRed = susLevelRed;
        } else {
            super(susLevel);
        }
    } */

	public String getSkill(){
		return this.skill;
	}

	public void setSkill(String skill){
		this.skill = skill;
	}

	//Methods
    
    //@Override
	public void emergencyMeeting(){
		if(!this.isFrozen()){
			Player[] players = getPlayers();
			Arrays.sort(players);
			if(players.length >= 2){
				for(int x = 1; x < players.length; x++){
					for(int y = 2; y < players.length; y++){
						if(!(players[players.length - x].isFrozen()) && !(players[players.length - y].isFrozen())
							&& players[players.length - x] != this){
							if(players[players.length - x].getSusLevel() == players[players.length - y].getSusLevel()){
								return;
							}
							else{
								players[players.length - x].setFrozen(true);
								gameOver();
								return;
							}
						}
					}
				}
			}
		}
	}
    
    @Override
	/*
	public void freeze(Player p){
		if(impostor = false){
			if(susLevelRed < p.susLevel){
			p.super(frozen) = true;
		}
		else{
			susLevelRed = susLevelRed * 2;
		}

		}

		super(gameOver());
	} */

	public void freeze(Player p){
		if(!(isFrozen()) || p.isFrozen()){
			if(!(p instanceof Impostor)){
				if(this.getSusLevel() < p.getSusLevel()){
					p.setFrozen(true);
					gameOver();
				}
				else{
					this.setSusLevel(this.getSusLevel() * 2);
				}
			}
		}
	}
    
    @Override
	public void sabotage(Player p){
		if(!(isFrozen()) || p.isFrozen()){
			if(this.getSusLevel() < 20){
				p.setSusLevel(p.getSusLevel() + (p.getSusLevel()/2));
			} else {
				p.setSusLevel(p.getSusLevel() + (p.getSusLevel()/4));
			}
		} 
	}

	public boolean equals(Object o){
        if (o instanceof RedAstronaut) {
            RedAstronaut player = (RedAstronaut) o;
            return this.getName().equals(player.getName()) && this.isFrozen() == player.isFrozen()
                    && this.getSusLevel() == player.getSusLevel() && this.getSkill().equals(player.getSkill());
        }
        return false;
	}

	public String toString(){
        String frozenString = isFrozen() ? "frozen" : "not frozen";
        String outRed = "My name is " + this.getName() + " , and I have a suslevel of " + this.getSusLevel() +
                    ". I am currently " + frozenString + " ." + " I am an " + this.skill + " player!";
        if(susLevelRed < 15){
        	return outRed;
        }
        else{
        	return outRed.toUpperCase();
        }   
    }

    /*
    public static void main(String[] args){
    	RedAstronaut[] players;
    }
    */
}