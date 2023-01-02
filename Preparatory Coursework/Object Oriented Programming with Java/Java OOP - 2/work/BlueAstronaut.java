import java.util.Arrays;

public class BlueAstronaut extends Player implements Crewmate {
    private int numTasks;
    private int taskSpeed;
    private static int defaultSusLevel = 15;
    private static int defaultNumTasks = 6;
    private static int defaultTaskSpeed = 10;

    public BlueAstronaut(String name, int susLevel, int numTasks, int taskSpeed) {
        super(name, susLevel);
        this.numTasks = numTasks;
        this.taskSpeed = taskSpeed;
    }

    public BlueAstronaut(String name) {
        this(name, defaultSusLevel, defaultNumTasks, defaultTaskSpeed);
    }

    public int getNumTasks(){
        return this.numTasks;
    }
    public void setNumTasks(int numTasks) {
        this.numTasks = numTasks;
    }

    public int getTaskSpeed(){
        return this.taskSpeed;
    }
    public void setTaskSpeed(){
        this.taskSpeed = taskSpeed;
    }

    public void emergencyMeeting() {
        if (!this.isFrozen()) {
            Player[] players = getPlayers();
            Arrays.sort(players);
            if (players.length >= 2) {
                for (int i = 1; i < players.length; i++) {
                    for (int j = 2; j < players.length; j++) {
                        if (!(players[players.length - i].isFrozen()) && !(players[players.length - j].isFrozen())) {
                            if (players[players.length - i].getSusLevel() != players[players.length - j].getSusLevel()) {
                                players[players.length - i].setFrozen(true);
                                gameOver();
                            }
                            return;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void completeTask() {
        if (!(isFrozen())) {
            if (this.taskSpeed >= 20) {
                setNumTasks(getNumTasks() - 2);
            }
            else {
                setNumTasks(getNumTasks() - 1);
            }
            if (this.numTasks < 0) {
                setNumTasks(0);
            }
            if (this.numTasks == 0) {
                System.out.println("I have completed all my tasks");
                this.setSusLevel(getSusLevel() / 2);
            }

        }

    }
    public boolean equals(Object o) {
        if (o instanceof BlueAstronaut) {
            BlueAstronaut player = (BlueAstronaut) o;
            return this.getName().equals(player.getName())
                    && this.isFrozen() == player.isFrozen()
                    && this.getSusLevel() == player.getSusLevel()
                    && this.getNumTasks() == player.getNumTasks()
                    && this.getTaskSpeed() == player.getTaskSpeed();

        }
        return false;
    }
    
    public String toString() {
        String frozenString = isFrozen() ? "frozen" : "not frozen";
        if (getSusLevel() < 15) {
            return "My name is " + getName() + " , and I have a suslevel of " + getSusLevel() + ". I am currently "
                     + frozenString + " . I have " + getNumTasks() + " left over.";
        } else {
            String frozenCapString = isFrozen() ? "FROZEN" : "NOT FROZEN";

            return "MY NAME IS " + getName().toUpperCase() + " , AND I HAVE A SUSLEVEL OF "
                    + getSusLevel() + ". I AM CURRENTLY " + frozenCapString + " . I HAVE " + getNumTasks() + " LEFT OVER.";
        }
    }


}


