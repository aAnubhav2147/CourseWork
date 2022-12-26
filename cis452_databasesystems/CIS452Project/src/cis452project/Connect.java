package cis452project;

import java.sql.*;

/**
*
* @author Anubhav Shankar
* 
*/

public class Connect{
	/**
     * Connect to the database
     */
public static Connection connect() {
        Connection conn = null;
        try {
            // Establish a relative path to the DB
            String url = "jdbc:sqlite:autosDB.sqlite";
            
            /**
             * Create a connection to the database
             */
            conn = DriverManager.getConnection(url);
            
            System.out.println("Connection to SQLite has been established.");
            
        } catch (SQLException e){
            System.out.println(e.getMessage());
        } 
        return conn;
    }
    
    
public static void main(String[] args) {
    	// Driver method to execute the above method
    	connect();
 }

}
