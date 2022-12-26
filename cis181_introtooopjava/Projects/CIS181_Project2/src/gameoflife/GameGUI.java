package gameoflife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Runnable;
import java.lang.Thread;

public class GameGUI extends JFrame implements Runnable, ActionListener {
    public final int FLAT_GRID = 1;           // One of two possible grid modes
    public final int TORUS_GRID = 2;          // The second of two possible grid modes

    protected GameInterface game;
    protected GameBoard board;
    protected JButton goButton, stepButton,resetButton,
                      readInMapButton, saveButton;
    protected JComboBox gridTypeComboBox, speedComboBox, exampleComboBox;
    protected JLabel generationLabel, rowColLabel;

    protected int gridMode = FLAT_GRID;
    protected Thread playGameThread = null;
    protected boolean gameStopped = true;
    protected int gameSpeed = 500;

    public GameGUI(GameInterface game, boolean[][] map) {
        this.game = game;
        board = new GameBoard(map);
        setContentPane(makeContentPane());
        pack();
        setVisible(true);
    }

    public Container makeContentPane() {
        Color birthColor = new Color(160, 160, 222);
        Color survivalColor = new Color(192, 192, 255);
        Color lightBlue = new Color(200, 200, 240);
        Color gray12 = new Color(222,222,222);
        setSize(550, 600);

        //IO buttons
        readInMapButton = new JButton("Open Cell Configuration...");
        readInMapButton.setActionCommand("Open");
        readInMapButton.addActionListener(this);
        readInMapButton.setBackground(lightBlue);

        saveButton = new JButton("Save Cell Configuration...");
        saveButton.setActionCommand("Save");
        saveButton.addActionListener(this);
        saveButton.setBackground(lightBlue);

        String[] exampleStrs = {"Fish", "Plus", "Glider", "FlyingMachine", "ClearSpace"};
        exampleComboBox = new JComboBox(exampleStrs);
        exampleComboBox.setSelectedIndex(1);
        exampleComboBox.addActionListener(this);
        exampleComboBox.setBackground(lightBlue);

        JPanel IOPane = new JPanel();
        IOPane.setBackground(lightBlue);

        JPanel topPane = new JPanel();
        topPane.setLayout(new BorderLayout());
        topPane.add(IOPane,BorderLayout.CENTER);
        IOPane.add(readInMapButton);
        IOPane.add(saveButton);
        IOPane.add(exampleComboBox);

        JPanel tempPane = new JPanel();

        //Game generation control buttons

        String[] gridStrs = {"Flat Grid", "Torus Grid"};
        gridTypeComboBox = new JComboBox(gridStrs);
        gridTypeComboBox.setSelectedIndex(0);
        gridTypeComboBox.addActionListener(this);
        gridTypeComboBox.setBackground(gray12);

        String[] speedStrs = {"Slow", "Medium", "Fast", "Light"};
        speedComboBox = new JComboBox(speedStrs);
        speedComboBox.setSelectedIndex(1);
        speedComboBox.addActionListener(this);
        speedComboBox.setBackground(gray12);

        stepButton = new JButton("Step");
        stepButton.setActionCommand("Step");
        stepButton.addActionListener(this);
        stepButton.setBackground(gray12);

        goButton = new JButton("Go");
        goButton.setActionCommand("Go");
        goButton.addActionListener(this);
        goButton.setBackground(gray12);

        resetButton = new JButton("Reset");
        resetButton.setActionCommand("Reset");
        resetButton.addActionListener(this);
        resetButton.setBackground(gray12);

        JPanel buttonPane = new JPanel();
        buttonPane.setBackground(gray12);

        tempPane = new JPanel();
        tempPane.setLayout(new BorderLayout());
        tempPane.setBackground(gray12);
        tempPane.add(gridTypeComboBox, BorderLayout.NORTH);
        buttonPane.add(tempPane);

        tempPane = new JPanel();
        tempPane.setLayout(new BorderLayout());
        tempPane.setBackground(gray12);
        tempPane.add(speedComboBox, BorderLayout.NORTH);
        buttonPane.add(tempPane);

        tempPane = new JPanel();
        tempPane.add(goButton);
        tempPane.setBackground(gray12);
        buttonPane.add(tempPane);

        tempPane = new JPanel();
        tempPane.add(stepButton);
        tempPane.setBackground(gray12);
        buttonPane.add(tempPane);

        tempPane = new JPanel();
        tempPane.add(resetButton);
        tempPane.setBackground(gray12);
        buttonPane.add(tempPane);

        generationLabel = new JLabel("Generation: 0");
        rowColLabel = new JLabel("[0,0]",JLabel.CENTER);
        rowColLabel.setForeground(Color.blue);
        tempPane = new JPanel();
        tempPane.setLayout(new BorderLayout());
        tempPane.setBackground(gray12);
        tempPane.add(generationLabel, BorderLayout.NORTH);
        tempPane.add(rowColLabel, BorderLayout.CENTER);
        buttonPane.add(tempPane);

        JPanel bottomPane = new JPanel();
        bottomPane.setLayout(new BorderLayout());
        bottomPane.add(buttonPane, BorderLayout.CENTER);
        bottomPane.setBackground(gray12);

        JPanel controlsPane = new JPanel();
        controlsPane.setLayout(new BorderLayout());
        controlsPane.add(topPane,  BorderLayout.NORTH);
        controlsPane.add(bottomPane, BorderLayout.SOUTH);

        JPanel mainPane = new JPanel();
        mainPane.setLayout(new BorderLayout());
        mainPane.add(controlsPane, BorderLayout.NORTH);

       // Component board = game.getBoard();
        board.addMouseListener(new gameMouseAdapter());
        board.addMouseMotionListener(new gameMouseMotionAdapter());
        tempPane = new JPanel();
        tempPane.add(board);
        tempPane.setBackground(gray12);
        mainPane.add(tempPane, BorderLayout.SOUTH);

        mainPane.setBorder(BorderFactory.createMatteBorder(1,1,2,2,Color.black));

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        return mainPane;
    }

    private void resetGoButton(boolean resetGenerationLabel) {
        goButton.setText("Go");
        if (resetGenerationLabel)
            generationLabel.setText("Generation: 0");
        gameStopped = true;
        playGameThread = null;
    }

    private void step() {
        if (gridMode == FLAT_GRID)
            game.nextGenerationForFlatGrid();
        else
            game.nextGenerationForTorusGrid();
        this.repaint();
        generationLabel.setText("Generation: " + game.getGeneration());
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("comboBoxChanged")){
            if (e.getSource() == gridTypeComboBox){
                resetGoButton(true);
                switch (gridTypeComboBox.getSelectedIndex()){
                   case 0: gridMode = FLAT_GRID; break;
                   case 1: gridMode = TORUS_GRID; break;
                }
            } else if (e.getSource() == speedComboBox){
                switch (speedComboBox.getSelectedIndex()){
                   case 0:  gameSpeed = 1000; break;
                   case 1:  gameSpeed =  500; break;
                   case 2:  gameSpeed =  100; break;
                   case 3:  gameSpeed =   10; break;
                }
            } else if (e.getSource() == exampleComboBox){
                switch (exampleComboBox.getSelectedIndex()){
                   case 0:  game.readExample(1); break;
                   case 1:  game.readExample(2); break;
                   case 2:  game.readExample(3); break;
                   case 3:  game.readExample(4); break;
                   case 4:  game.readExample(0); break;
                }
             }
        } else if(command.equals("Step")) {
            resetGoButton(false);
            step();
        } else if(command.equals("Go")){
            if (goButton.getText().equals("Go")) {
                goButton.setText("Stop");
                playGameThread = new Thread(this);
                gameStopped  = false;
                playGameThread.start();
            } else {
                resetGoButton(false);
                this.repaint();
            }
        }else if(command.equals("Reset")){
            resetGoButton(true);
            game.reset();
        } else if(command.equals("Open")){
            resetGoButton(true);
            game.readInMap();
        } else if(command.equals("Save")){
            if(!gameStopped)
               resetGoButton(true);
            game.writeMap();
        }
    }

    public class gameMouseAdapter extends MouseAdapter {
        public void mousePressed(MouseEvent e){
            if (!gameStopped)
                resetGoButton(true);
            toggleCell(e.getPoint());
        }
    }

    // change the state of a cell
    public void toggleCell(Point clickedPt) {
        try{
            Point cell = board.getCell(clickedPt);
            game.updateMap(cell.y, cell.x);
            board.drawCell(cell);
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("ERROR in ToggleCell.\n" + e);
        }
    }

    public class gameMouseMotionAdapter extends MouseMotionAdapter {
        public void mouseMoved(MouseEvent e){
            GameBoard board = (GameBoard)e.getComponent();
            Point cell = board.getCell(e.getPoint());
            rowColLabel.setText("[" + cell.y + ", " + cell.x + "]");
        }
    }

    public void run() {
        while(!gameStopped){
            step();
            try{
                Thread.sleep(gameSpeed);
            } catch(InterruptedException e){}
        }
    }
}