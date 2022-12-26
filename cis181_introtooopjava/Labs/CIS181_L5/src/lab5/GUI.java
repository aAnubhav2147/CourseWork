package lab5;

import javax.swing.*;

public class GUI extends JFrame {
    protected JTextArea outputArea;
    protected JTextField inputField;

    // constructor
    public GUI(Process process) {

        setSize(600, 400);
        JPanel panel = new JPanel();
        getContentPane().add(panel);

        JLabel inputLabel = new JLabel("Input:    ");
        panel.add(inputLabel);

        inputField = new JTextField(47);
        panel.add(inputField);
        inputField.setEditable(true);

        JLabel outputLabel = new JLabel("Output: ");
        panel.add(outputLabel);

        outputArea = new JTextArea("", 18, 47);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        panel.add(scrollPane);

        setVisible(true);

        addWindowListener(new GUIListener());
        inputField.addActionListener(new GUIListener (inputField, process));
    }

    // Postcondition: the output area is now blank.
    public void clear() {
        outputArea.setText("");
    }

    // Postcondition: s has been converted to a string and output.
    public void print(Object s) {
        outputArea.append(s.toString());
        inputField.requestFocus();
    }

    // Postcondition: s has been converted to a string and output, and
    //                the next line has been advanced to.
    public void println(Object s) {
        print(s + "\n");
    }

    public void print(long i) {
        print(new Long (i));
    }

    public void println(long i) {
        print(i + "\n");
    }

    public void print(double x) {
        print(new Double (x));
    }

    public void println(double x) {
        print(x + "\n");
    }

    public void print(char c) {
        print(new Character (c));
    }

    public void println(char c) {
        print(c + "\n");
    }

    public void print(boolean b) {
        print(new Boolean(b));
    }

    public void println(boolean b) {
        print(b + "\n");
    }

    // Postcondition: the Input Line in this GUI window can no
    //                longer be written to.
    public void freeze() {
        inputField.setEditable(false);
    }
}

