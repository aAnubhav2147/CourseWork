import java.util.Arrays;

public class RedAstronaut extends Player implements Impostor {
    private String skill;

    private static String defaultSkill = "experienced";
    private static int defaultSusLevel = 15;

    public RedAstronaut(String name, int susLevel, String skill) {
        super(name, susLevel);
        this.skill = skill;
    }

    public RedAstronaut(String name) {
        this(name, defaultSusLevel, defaultSkill);
    }

    public void emergencyMeeting() {
        if (!this.isFrozen()) {
            Player[] players = getPlayers();
            Arrays.sort(players);
            if (players.length >= 2) {
                for (int i = 1; i < players.length; i++) {
                    for (int j = 2; j < players.length; j++) {
                        if (!(players[players.length - i].isFrozen()) && !(players[players.length - j].isFrozen())
                                && players[players.length - i] != this) {
                            if (players[players.length-i].getSusLevel() == players[players.length-j].getSusLevel()) {
                                return;
                            }
                            else {
                            players[players.length - i].setFrozen(true);
                            gameOver();
                            return;
                            } 
                        }
                    }
                }
            }
        }
    }
    public String getSkill() {
        return this.skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    @Override
    public void freeze(Player p) {
        if (!(isFrozen() || p.isFrozen())) {
            if (!(p instanceof Impostor)) {
                if (this.getSusLevel() < p.getSusLevel()) {
                    p.setFrozen(true);
                    gameOver();
                } else {
                    this.setSusLevel(this.getSusLevel() * 2);
                }

            }
        }
    }

    @Override
    public void sabotage(Player p) {
        if (!(isFrozen() || p.isFrozen())) {
            if (this.getSusLevel() < 20) {
                p.setSusLevel(p.getSusLevel() + (p.getSusLevel() / 2));
            } else {
                p.setSusLevel(p.getSusLevel() + (p.getSusLevel() / 4));
            }
        }
    }

    public boolean equals(Object o) {
        if (o instanceof RedAstronaut) {
            RedAstronaut player = (RedAstronaut) o;
            return this.getName().equals(player.getName())
                    && this.isFrozen() == player.isFrozen()
                    && this.getSusLevel() == player.getSusLevel()
                    && this.getSkill().equals(player.getSkill());
        }
        return false;
    }

    public String toString() {
        String frozenString = isFrozen() ? "frozen" : "not frozen";
        if (this.getSusLevel() < 15) {
            return "My name is " + this.getName() + " , and I have a suslevel of " + this.getSusLevel() +
                    ". I am currently " + frozenString + " ." + " I am an " + this.skill + " player!";
        } else {
            String frozenCapString = isFrozen() ? "FROZEN" : "NOT FROZEN";
            String skillCapString
                    = (this.skill == "inexperienced") ? "INEXPERIENCED"
                    : (this.skill == "experienced") ? "EXPERIENCED"
                    : "EXPERT";

            return "MY NAME IS " + this.getName().toUpperCase() + " , AND I HAVE A SUSLEVEL OF "
                    + this.getSusLevel() + ". I AM CURRENTLY " + frozenCapString + " ." + " I AN AN " + skillCapString + " PLAYER!";
        }
    }
}