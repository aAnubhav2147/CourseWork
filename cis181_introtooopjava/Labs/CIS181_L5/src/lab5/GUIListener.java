package lab5;

import java.awt.event.*;
import javax.swing.*;

public class GUIListener extends WindowAdapter implements ActionListener {
    protected JTextField inputField;
    protected Process process;

    // Postcondition: This GUI listener has been initialized.
    public GUIListener(){}

    // Postcondition: This GUI listener has been initialized according to
    //                the values of tf and p.
    public GUIListener(JTextField tf, Process p) {   // constructor
        inputField = tf;
        process = p;
    }

    // Postcondition: The window has closed.
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    // Postcondition: The input has been processed.
    public void actionPerformed(ActionEvent e) {
        inputField.setText("");
        inputField.setEnabled(false);
        process.processInput(e.getActionCommand());
        inputField.setEnabled(true);
    }
  
}

