/**
 *  CIS 181 A boolean 2D array-based game board class
 *
 **/
package gameoflife;
import javax.swing.*;
import java.awt.*;

public class GameBoard extends JComponent {
    public static final int BOARD_WIDTH = 500;
    public static final int BOARD_HEIGHT = 500;
    public static final int BOARD_MARGIN = 5;
    public static final int GAME_BOARD_BOTTOM = BOARD_HEIGHT + 2*BOARD_MARGIN;
    public static final int GAME_BOARD_RIGHT = BOARD_WIDTH + 2*BOARD_MARGIN;
    public static final int CELL_HEIGHT = 10;
    public static final int CELL_WIDTH = 10;

    Dimension preferredSize;
    boolean boardArray[][];
    Image gridImage511_By_511;

    public GameBoard(boolean boardArray[][]){
        preferredSize = new Dimension(GAME_BOARD_RIGHT + 1, GAME_BOARD_BOTTOM + 1);
        this.setSize(preferredSize.width, preferredSize.height);
        this.boardArray= boardArray;
        setBorder(BorderFactory.createMatteBorder(BOARD_MARGIN, BOARD_MARGIN,
                                                  BOARD_MARGIN, BOARD_MARGIN,
                                                  Color.BLUE));
        setOpaque(true);
        gridImage511_By_511 =
            Toolkit.getDefaultToolkit().getImage(getClass().getResource("gameGrid.gif"));
        this.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
    }

    public Dimension getPreferredSize() {
        return preferredSize;
    }

    protected void paintComponent(Graphics g) {
        if (isOpaque()) {
            g.setColor(getBackground());
            g.fillRect(0, 0, getWidth(), getHeight());
        }
        g.drawImage(gridImage511_By_511,0,0, this);
        g.setColor(Color.BLUE);
         for (int row = 1; row <= boardArray.length; row++)
            for (int col = 1; col <= boardArray[0].length; col++)
               if (boardArray[row-1][col-1]) {
                   g.fillRect(col*CELL_WIDTH-2, row*CELL_HEIGHT-2,
                               CELL_WIDTH-BOARD_MARGIN, CELL_HEIGHT-BOARD_MARGIN);
               }
    }

    public Point getCell(Point pt){
        if (pt.x < BOARD_MARGIN)
            pt.x = BOARD_MARGIN;
        else if (pt.x >=  GAME_BOARD_RIGHT-BOARD_MARGIN)
            pt.x =  GAME_BOARD_RIGHT-BOARD_MARGIN-1;

        if (pt.y < BOARD_MARGIN)
            pt.y = BOARD_MARGIN;
        else if (pt.y >= GAME_BOARD_BOTTOM-BOARD_MARGIN)
            pt.y = GAME_BOARD_BOTTOM-BOARD_MARGIN-1;
        return new Point((pt.x-BOARD_MARGIN)/CELL_WIDTH, (pt.y-BOARD_MARGIN)/CELL_HEIGHT);
    }

    public void drawCell(Point cell){
        int x = (cell.x+1)*CELL_WIDTH-BOARD_MARGIN;
        int y = (cell.y+1)*CELL_HEIGHT-BOARD_MARGIN;
        repaint(x,y,CELL_WIDTH,CELL_HEIGHT);
    }
}