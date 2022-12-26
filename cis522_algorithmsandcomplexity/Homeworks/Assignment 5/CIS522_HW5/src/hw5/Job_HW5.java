package hw5;

class Job //Class to create new Job objects
{
    int week; //initialize the week numbers
    String stress; //for defining the stress categories
    int value; // revenue associated with each job

    Job() //Constructor to set the defaults 
    {
        this.value = 0;
        this.stress = "no jobs";
    }

    Job(int week, String stress, int values) //Constructor to assign user defined values
    {
        this.week = week;
        this.value = values;
        this.stress = stress;
    }
}

//Driver function
public class Job_HW5
{
    public static void main(String []args)
    {
        int weeks = 5;

        Job l1 = new Job(1, "Low Stress", 4);
        Job l2 = new Job(2, "Low Stress", 1);
        Job l3 = new Job(3, "Low Stress", 4);
        Job l4 = new Job(4, "Low Stress", 7);
        Job l5 = new Job(5, "Low Stress", 5);
        
        Job lowStress[] = {l1, l2, l3, l4,l5};

        Job h1 = new Job(1, "High Stress", 5);
        Job h2 = new Job(2, "High Stress", 50);
        Job h3 = new Job(3, "High Stress", 20);
        Job h4 = new Job(4, "High Stress", 30);
        Job h5 = new Job(5, "High Stress", 40);
        
        Job highStress[] = {h1, h2, h3, h4,h5};

        optmaxrevenue(highStress,lowStress,weeks);
    }

    private static void optmaxrevenue(Job[] highStress, Job[] lowStress, int weeks) {

        Job low, high;
        Job jobs[] = new Job[weeks]; //number of jobs scheduled

        for(int i=0; i<weeks-1; i++) //weeks-1 to prevent overflowing array bounds
        {
            low = (lowStress[i].value > highStress[i].value)?(lowStress[i]):highStress[i];
            high = (lowStress[i+1].value > highStress[i+1].value)?(lowStress[i+1]):highStress[i+1];

            if(low.value > high.value)
            {
                jobs[i] = low;
            }
            else
            {
                if(high.stress.equalsIgnoreCase("low stress"))
                {
                    jobs[i] = low;
                    jobs[i+1] = high;
                }
                else if(high.stress.equalsIgnoreCase("high stress"))
                {
                    jobs[i] = new Job();
                    jobs[i+1] = high;
                }

            }
        }

        int maxrevenue = 0;
        for(int i=0; i<jobs.length; i++)
        {
            System.out.println("Week " + (i+1) + " = "+jobs[i].value);
            maxrevenue += jobs[i].value;
        }
        System.out.println("Maximum achievable revenue: " + maxrevenue);
    }
}

