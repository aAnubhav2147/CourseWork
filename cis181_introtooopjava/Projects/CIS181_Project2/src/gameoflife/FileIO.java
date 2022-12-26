/**
 * CIS 181 Boolean 2D Array IO
 *
 * An IO class for reading and writing text file
 * representations of two-dimensional boolean arrays.
 *
 **/
package gameoflife;
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;

public class FileIO {
    protected int maxRowsRead = 0, maxColsRead = 0;
    protected boolean echoIO = false;
    protected JFileChooser chooser; // Remembers previous directory
    protected SimpleFileFilter lifeFilter;

    FileIO(String fileExtension, String fileDescription) {
        chooser = new JFileChooser();
        lifeFilter = new SimpleFileFilter(fileExtension, fileDescription);
    }

    public File getFile() {
        File file = null;
        int returnVal;

        chooser.setFileFilter(lifeFilter);
        returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = chooser.getSelectedFile();
            System.out.println("You chose to open the file: "+ file.getName()+".");
        } else
            System.out.println("No file selected.");

        return file;
    }

    public File putFile() {
        File file = null; int returnVal;
        chooser.setFileFilter(lifeFilter);
        returnVal = chooser.showSaveDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = chooser.getSelectedFile();
            System.out.println("You chose to save the file: "+ file.getName()+".");
        } else
            System.out.println("Save was not approved.");

        return file;
    }

    public int getMaxRowsRead() {
        return maxRowsRead;
    }

    public int getMaxColsRead() {
        return maxColsRead;
    }

    public boolean read(boolean bray[][]) {
        try {
            File file = getFile();
            if (file == null)
                return false;
            else {
                int inInt;
                char inChar; String inStr;
                int row = 0, col = 0;

                FileInputStream inStream = new FileInputStream(file);
                InputStreamReader reader = new InputStreamReader(inStream);
                inInt = reader.read();
                while ((inInt != -1)&&(row < bray.length)) {
                    inChar = (char)inInt;
                    switch (inChar) {
                    case '-':
                        if (col < bray[row].length){
                            bray[row][col] = false;
                            col++;
                        }
                        break;
                    case '*':
                        if (col < bray[row].length){
                            bray[row][col] = true;
                            col++;
                        }
                        break;
                    case '\n':
                        if (maxColsRead < col)
                            maxColsRead = col;
                        col = 0;
                        row++;
                        break;
                    default: ;
                    }
                    if (echoIO)
                        System.out.print(inChar);
                    inInt = reader.read();
                } // while
                maxRowsRead = row;
                if (echoIO)
                    System.out.println("");
                reader.close();
                return true;
            } // else
        } catch (IOException e){
            System.out.println("Error in reading.");
            return false;
        }
    }

    public void write(boolean bray[][]) {
        try {
            File file = putFile();
            if (file == null)
                System.out.print("File null");
            else {
                FileOutputStream outStream = new FileOutputStream(file);
                OutputStreamWriter writer = new OutputStreamWriter(outStream);
                final int DASH = (int)'-';
                final int ASTERISK = (int)'*';
                final int NEW_LINE = (int)'\n';

                for (int row = 0; row < bray.length; row++){
                     for (int col = 0; col < bray[row].length; col++){
                          if (bray[row][col]) {
                              writer.write(ASTERISK);
                              if (echoIO)
                                     System.out.print('*');
                          } else {
                              writer.write(DASH);
                              if (echoIO)
                                    System.out.print('-');
                          }
                     }
                     writer.write(NEW_LINE);
                     if (echoIO) System.out.println();
                 }
                 writer.flush();
                 writer.close();
             }
         } catch (IOException e) {
             System.out.println("Error in writing.");
         }
    }

    private class SimpleFileFilter extends FileFilter {

        protected String extensionStr="";
        protected String fileDescriptionStr = "";

        public SimpleFileFilter(String extensionStr, String fileDescriptionStr) {
            this.extensionStr = extensionStr;
            this.fileDescriptionStr = fileDescriptionStr;
        }

        public String getExtension(File f) {
            String ext = null;
            String s = f.getName();
            int i = s.lastIndexOf('.');
            if (i > 0 &&  i < s.length() - 1)
                ext = s.substring(i+1).toLowerCase();
            return ext;
        }

        public boolean accept(File f) {
            if (f.isDirectory())
                return true;
            else {
              String extension = getExtension(f);
              if (extension != null)
                  return (extension.equals(extensionStr));
            }
            return false;
        }

        public String getDescription() {
            return fileDescriptionStr;
        }
    }
}
