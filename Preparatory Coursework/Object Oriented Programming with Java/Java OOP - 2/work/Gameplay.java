public class Gameplay {

    public static void main(String[] args) {
        BlueAstronaut blueAstronautBob = new BlueAstronaut("Bob", 20, 6, 30);
        BlueAstronaut blueAstronautHealth = new BlueAstronaut("Health", 30, 3, 21);
        BlueAstronaut blueAstronautAlbert = new BlueAstronaut("Albert", 44, 2, 0);
        BlueAstronaut blueAstronautAngel = new BlueAstronaut("Angel", 0, 1, 0);

        RedAstronaut redAstronautLiam = new RedAstronaut("Liam", 19, "experienced");
        RedAstronaut redAstronautSus = new RedAstronaut("Suspicious Person", 100, "expert");

        //TODO: 1
        redAstronautLiam.sabotage(blueAstronautBob);
        // Bob should have: susLevel = 30, frozen = false
        System.out.println("Bob's susLevel: " + blueAstronautBob.getSusLevel() + " " +blueAstronautBob.isFrozen());

        //TODO: 2
        redAstronautLiam.sabotage(redAstronautSus);
        //nothing should happen

        //TODO: 3
        redAstronautLiam.freeze(blueAstronautAlbert);
        // Liam sus level should be 19
        //Albert should be frozen
        System.out.println("Liam susLevel: " + redAstronautLiam.getSusLevel() + " Albert frozen: " + blueAstronautAlbert.isFrozen());

        //TODO: 4
        blueAstronautAlbert.emergencyMeeting();
        //nothing should happen

        //TODO: 5


        redAstronautSus.emergencyMeeting();
        System.out.println("Angle frozen: " + blueAstronautAngel.isFrozen());

        //nothing should happen


        //TODO 6:
        blueAstronautBob.emergencyMeeting();
        //suspicious person should be frozen
        System.out.println("Sus is frozen: " + redAstronautSus.isFrozen());

        //TODO 7:
        blueAstronautHealth.completeTask();
        //num = 1
        System.out.println("Health's numTask " + blueAstronautHealth.getNumTasks());

        //TODO 8:
        blueAstronautHealth.completeTask();
        //numtask = 0, susLevel = 15
        System.out.println("Health task and sus: " + blueAstronautHealth.getNumTasks() + " " + blueAstronautHealth.getSusLevel());

        //TODO 9:

        blueAstronautHealth.completeTask();
        //nothing should happen

        //TODO: 10:
        System.out.println(blueAstronautAngel.isFrozen());
        redAstronautLiam.freeze(blueAstronautAngel);
        //Angel frozen = false
        //Liam susLevel = 38
        System.out.println("Angel frozen? " + blueAstronautAngel.isFrozen() + " Liam sus level: " + redAstronautLiam.getSusLevel());

        //TODO: 11:
        System.out.println("Bob susLevel: " + blueAstronautBob.getSusLevel());
        redAstronautLiam.sabotage(blueAstronautBob);
        System.out.println("Bob susLevel: " + blueAstronautBob.getSusLevel());
        redAstronautLiam.sabotage(blueAstronautBob);
        System.out.println("Bob susLevel: " + blueAstronautBob.getSusLevel());

        //TODO: 12
        redAstronautLiam.freeze(blueAstronautBob);
        System.out.println("Bob frozen? " + blueAstronautBob.isFrozen());
        //Liam = frozen, "Crew mates win!"



    }
}
