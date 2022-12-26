package cis452project;

import java.util.*;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class GUI {
	static Scanner sc = new Scanner(System.in);
	static DataTransactions dt = new DataTransactions();
		
public static void main(String[] args){
	
		//Driver method to query/interact with the autosDB
	
	    /**
	     * Initialize a JFrame class to create the GUI
	     * The GUI can only be closed by explicitly closing the GUI window
	     */
		JFrame frame = new JFrame("GUI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(500,500,500,500);
		
		//Add panels to ensure that the GUI has enough space to 
		//accommodate the interaction elements
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		//JPanel panel5 = new JPanel();
		//JPanel panel6 = new JPanel();
		
		/*
		 * Upon clicking, the button invokes the insertAccidents method in 
		 * DataTransformations class
		 */
		JButton accident = new JButton("Add Accidents");
		accident.addActionListener(new ActionListener() {			
			private Connection conn;
			public void actionPerformed(ActionEvent e) {
				try {conn = dt.connect();
					System.out.println("Please enter an Accident ID: ");
					int aid = sc.nextInt();
					System.out.println("Please enter the Accident Date: ");
					String accident_date = sc.next();
					System.out.println("Please enter the City: ");
					String city = sc.next();
					System.out.println("Please enter the State: ");
					String state =sc.next();
				    dt.insertAccidents(aid,accident_date,city,state);
				    
				} catch (Exception e1) {
					e1.printStackTrace();
				}finally {
					try {
						conn.close();
					}catch(SQLException e2) {
						e2.printStackTrace();
					}
					
				}
			}});
		
		/*
		 * Upon clicking, the button invokes the insertInvolvements method in 
		 * DataTransformations class
		 */
		JButton involvements = new JButton("Add Involvements");
		involvements.addActionListener(new ActionListener() {			
			private Connection conn;
			public void actionPerformed(ActionEvent e) {
				try {conn = dt.connect();
					System.out.println("Please enter an Accident ID: ");
					int aid = sc.nextInt();
					System.out.println("Please enter a VIN: \n");
					String vin = sc.next();
					System.out.println("Please enter the calculated damages: ");
					float damages = sc.nextFloat();
					System.out.println("Please enter the Driver SSN: ");
					String driver_ssn =sc.next();
				    dt.insertInvolvements(aid,vin,damages,driver_ssn);
				    
				} catch (Exception e1) {
					e1.printStackTrace();
				}finally {
					try {
						conn.close();
					}catch(SQLException e2) {
						e2.printStackTrace();
					}
					
				}
			}});
		
		/*
		 * Upon clicking, the button invokes the query method in 
		 * DataTransformations class
		 */
		JButton query = new JButton("Query");
		query.addActionListener(new ActionListener(){
			private Connection conn;
			public void actionPerformed(ActionEvent e) {
				try {
					conn = dt.connect();
					System.out.println("Please enter an Accident ID: ");
					int aid = sc.nextInt();
					System.out.println("Fetching details. Please wait...\n");
					dt.query(aid);					
				}catch (Exception e1) {
					e1.printStackTrace();
				}finally {
					try {
						conn.close();
					}catch(SQLException e2) {
						e2.printStackTrace();
					}
			}
		}});
		
		/*
		 * Upon clicking, the button invokes any of the four search method in 
		 * DataTransformations class basis the user choice
		 */
		JButton search = new JButton("Search");
		search.addActionListener(new ActionListener(){
			private Connection conn;
			public void actionPerformed(ActionEvent e) {
				try {
					conn = dt.connect();
					System.out.println("Select Search Criteria: ");
					System.out.println("A. Search Basis Date Range");
					System.out.println("B. Search Basis Average Damages");
					System.out.println("C. Search Basis Total Damages");
					System.out.println("D. Custom Search");
					System.out.println("E. Exit");
					
					Scanner criteria = new Scanner(System.in);
					System.out.println("Enter Search Criteria: ");
					String c = criteria.next();
					
					switch(c = c.toLowerCase()) {
					case "a":
						System.out.println("Enter min date: ");
						String minDate = criteria.next();
						System.out.println("Enter max date: ");
						String maxDate = criteria.next();
						System.out.println("Fetching details. Please wait...\n");
						dt.searchByDate(minDate,maxDate);
						break;
					
					case "b":
						System.out.println("Enter min average damage: ");
						float minDamage = criteria.nextFloat();
						System.out.println("Enter max average damage: ");
						float maxDamage = criteria.nextFloat();
						System.out.println("Fetching details. Please wait...\n");
						dt.searchByAvgDamages(minDamage,maxDamage);
						break;
						
					case "c":
						System.out.println("Enter min damage: ");
						float minTotalDamage = criteria.nextFloat();
						System.out.println("Enter max damage: ");
						float maxTotalDamage = criteria.nextFloat();
						System.out.println("Fetching details. Please wait...\n");
						dt.searchByTotalDamages(minTotalDamage,maxTotalDamage);
						break;
						
					case "d":
						System.out.println("Enter min date: ");
						String minDate1 = criteria.next();
						System.out.println("Enter max date: ");
						String maxDate1 = criteria.next();
						System.out.println("Enter min average damage: ");
						float minDamage1 = criteria.nextFloat();
						System.out.println("Enter max average damage: ");
						float maxDamage1 = criteria.nextFloat();
						System.out.println("Enter min damage: ");
						float minTotalDamage1 = criteria.nextFloat();
						System.out.println("Enter max damage: ");
						float maxTotalDamage1 = criteria.nextFloat();
						System.out.println("Fetching details. Please wait...\n");
						dt.customSearch(minDate1,maxDate1,minDamage1, maxDamage1,
								         minTotalDamage1,
								         maxTotalDamage1);
						break;
						
					case "e":
						System.out.println("Terminating program...");
						System.exit(1);
						break;
						
					default:
						System.out.println("Oops!!! Wrong Choice...");
						System.exit(1);
						criteria.close();
						break;
					}					
				}catch (Exception e1) {
					e1.printStackTrace();
				}finally {
					try {
						conn.close();
					}catch(SQLException e2) {
						e2.printStackTrace();
					}
			}
		}});
		
		/* The Delete and Backup options were only for testing purposes */
		
		/*
		 * Upon clicking, the button invokes the delete method in 
		 * DataTransformations class
		 */
		/*
		JButton delete = new JButton("Delete");
		delete.addActionListener(new ActionListener(){
			private Connection conn;
			public void actionPerformed(ActionEvent e) {
				try {
					conn = dt.connect();
					System.out.println("Please enter an Accident ID: ");
					int aid = sc.nextInt();
					dt.delete(aid);					
				}catch (Exception e1) {
					e1.printStackTrace();
				}finally {
					try {
						conn.close();
					}catch(SQLException e2) {
						e2.printStackTrace();
					}
			}
		}});
		*/
		
		/*
		 * Upon clicking, the button invokes the createBackup method in 
		 * the TakeBackup class
		 */
		/*
		JButton backup = new JButton("Backup");
		backup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TakeBackup.createBackup();
			//System.out.println("Backup created!");
			}});
			*/
				
		panel1.add(accident);
		panel2.add(involvements);
		panel3.add(query);
		panel4.add(search);
		
		/*Uncomment the below two lines if you activate the delete and
		 * backup buttons
		 */
		//panel5.add(delete);
		//panel6.add(backup);
		
		Container container = frame.getContentPane();
		container.setLayout(new GridLayout(5,5));
		container.add(panel1);
		container.add(panel2);
		container.add(panel3);
		container.add(panel4);
		
		/*Uncomment the below two lines if you activate the delete and
		 * backup buttons
		 */
		//container.add(panel5);
		//container.add(panel6);
		
		/**
		 * Make the GUI visible.
		 * Putting this before the button initializations will
		 * lead to the generation of a blank GUI
		 * 
		 * @param: a boolean argument to make the GUI visible 
		 * 
		 */
		frame.setVisible(true);  
}

/////////////////////// IGNORE ///////////////////////////////////////

/*********************  TRIALS & ERRORS *************************/

            //GUI g = new GUI();
            //frame.setSize(500,500);
	        //frame.getContentPane().add(accident);
			//frame.getContentPane().add(involvements);
			//frame.getContentPane().add(query);
			//frame.getContentPane().add(search);
	        //frame.pack();

/*
public Connection connect() {		
	conn = dt.connect();
	return conn;
}
*/

/* Bunch of unsuccessful attempts to add background to the
 * GUI dialog box
 */

//Image img = Toolkit.getDefaultToolkit().getImage("GUI_bkg.jpg");
//
//public GUI() throws IOException {
//      this.setContentPane(new JPanel() {
//         @Override
//         public void paintComponent(Graphics g) {
//            super.paintComponent(g);
//            g.drawImage(img, 0, 0, null);
//         }
//      });
//      pack();
//      setVisible(true);
//   }

//container.add(background);
//JLabel background=new JLabel(new ImageIcon("GUI_bkg.jpg"));
////JFrame.add(background);
//background.setLayout(new FlowLayout());

//JLabel lbl = new JLabel();
//final ImageIcon icon = new ImageIcon("GUI_bkg.jpg");
//lbl.setIcon(icon);
////Image img = icon.getImage();

//JLabel lbl = new JLabel();
//final ImageIcon icon = new ImageIcon("GUI_bkg.jpg");
//lbl.setIcon(icon);

//frame.getContentPane().add(new JPanelWithBackground(""));

//		ImageIcon background=new ImageIcon("GUI_bkg.jpg");
//	    Image img=background.getImage();
//	    Image temp=img.getScaledInstance(500,600,Image.SCALE_SMOOTH);
//	    background=new ImageIcon(temp);
//	    JLabel back=new JLabel(background);
//	    //back.setLayout(null);
//	    back.setBounds(0,0,500,600);

/****************************************************************/

}
