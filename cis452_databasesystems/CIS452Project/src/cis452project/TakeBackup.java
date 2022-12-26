package cis452project;

import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.sqlite.SQLiteDataSource;

/**
*
* @author Anubhav Shankar
*/

public class TakeBackup {
	/**
     * Create a new table in the test database
     *
     */
    public static void createBackup() {
        // SQLite connection string
        String url = "jdbc:sqlite:C:/Users/anubh/Desktop/Course Requisites/Fall 2021/CIS 452/autosDB.sqlite";
    	SQLiteDataSource ds = null;
    	try {
			ds = new SQLiteDataSource();
			ds.setUrl(url);
		} catch ( Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		System.out.println( "Opened database successfully" );
        
        // SQL statement for creating a new table/taking a backup
        /*String sql = "CREATE TABLE accidents_bkp AS ("
        		+ "SELECT * FROM accidents"
                + ");"; */
		//String stmnt = "CREATE TABLE accidents_bkp as SELECT * FROM accidents;";
		String stmnt = "CREATE TABLE involvements_bkp as SELECT * FROM involvements;";
		String sql = stmnt;
        
        try (//Connection conn = DriverManager.getConnection(url);
        		Connection conn = ds.getConnection();
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
            System.out.println("Table created Successfully!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

	public static void main(String[] args) {
		// Driver method to execute the above method
		createBackup();

	}

}
