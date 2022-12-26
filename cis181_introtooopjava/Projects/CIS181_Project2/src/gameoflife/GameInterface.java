package gameoflife;
//import java.awt.Point;

public interface GameInterface {
    public final boolean DEAD = false;        // State of a dead cell
    public final boolean ALIVE = true;        // State of a live cell
    public final int BIRTH_NBR_COUNTS = 3;    // The neighbor counts that allow a dead
                                              // cell to be vivified in the next generation
    public final int SURV_NBR_COUNTS_2 = 2;   // The neighbor counts that allow a live
    public final int SURV_NBR_COUNTS_3 = 3;   // cell to survive to the next generation
    public final int MAX_ROWS = 50;           // The maxium number of grid rows
    public final int MAX_COLS = 50;           // The maxium number of grid columns

    public  void nextGenerationForFlatGrid(); // Run game of life for one step
    public  void nextGenerationForTorusGrid();// Run game of life for one step

    public int getGeneration();               // Get current generation number
    public void reset();                      // Reset the map to the original map
    public void readExample(int n);           // Read sample examples
    public void readInMap();                  // Read in map from file
    public void writeMap();                   // Write map to file
    public void updateMap(int row, int col);  // Change the state of a cell

}
