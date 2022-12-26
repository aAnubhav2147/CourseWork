package cis452project;

import java.sql.*;
import java.util.*;

public class DataTransactions implements Operations {
		Connection conn = null;
	    PreparedStatement pstmnt = null;
/**
 * Implement a connect method to establish a connection
 * to the DB by activating the Connect class and override
 * the Connect method in the Operations interface
 * 
 * @return an output of type Connection, variable: conn 
 * 	
 */
@Override
public Connection connect() {
		//Connect ct = new Connect();
		conn = Connect.connect();
		return conn;
}

/**
 * Implement a method to insert a new record into
 * the accidents table in the autosDB. This is done by overriding
 * the similar method in the Operations interface
 * 
 * @param int aid i.e. the Accident ID
 * @param String accident_date: enter a date as a String class
 * @param String city: enter the city of the accident
 * @param String state: enter the state of the accident
 *  	
 */
@Override
public void insertAccidents(int aid,String accident_date, 
		                    String city, String state){
	
	//accident_date = new java.sql.Date(latestDate.getTime());
	
	String sql = "INSERT INTO accidents(aid,accident_date,city,state) "
			+ "VALUES(?,?,?,?)";
	
	try{conn = this.connect();
	    pstmnt = conn.prepareStatement(sql);
	    pstmnt.setInt(1, aid);
		pstmnt.setString(2, accident_date);
		pstmnt.setString(3, city);
		pstmnt.setString(4, state);
		pstmnt.executeUpdate();
		System.out.println("New record added to accidents table!");
	}catch (SQLException e) {
        System.out.println(e.getMessage());
    }
}

/**
 * Implement a method to insert a new record into
 * the involvements table in the autosDB. This is done by overriding
 * the similar method in the Operations interface
 * 
 * @param int aid i.e. the Accident ID
 * @param float damages: enter the calculated damages
 * @param String driver_ssn: enter the SSN of the driver involved in the accident
 *  	
 */
@Override
public void insertInvolvements(int aid, String vin,float damages,
		                       String driver_ssn){
	String sql = "INSERT INTO involvements(aid,vin,damages,driver_ssn)"
			+ "VALUES(?,?,?,?)";
	
	try{conn = this.connect();
		pstmnt = conn.prepareStatement(sql);
		pstmnt.setInt(1, aid);
		pstmnt.setString(2, vin);
		pstmnt.setFloat(3, damages);
		pstmnt.setString(4, driver_ssn);
		pstmnt.executeUpdate();
		System.out.println("New record added to involvements table!");
		//close();
	}catch (SQLException e) {
        System.out.println(e.getMessage());
    }		
}

/**
 * Implement a method to delete a record from
 * either the involvements or the accidents table in the autosDB. 
 * This is done by overriding the similar method in the Operations interface
 * This was just put in for connection/transaction testing purposes.
 *
 * @param int aid i.e. the Accident ID
 *  	
 */
@Override
public void delete(int aid){
	String sql1 = "DELETE FROM involvements where aid = ?";
	String sql2 = "DELETE FROM accidents where aid = ?";
	System.out.println("Select a table: ");
	System.out.println("1. Involvements");
	System.out.println("2. Accidents");
	System.out.println("3. Exit");
	
	Scanner s = new Scanner(System.in);
	System.out.println("Enter your choice: ");
	int c = s.nextInt();
	
	switch(c) {
	case 1:
		try{conn = this.connect();
	    pstmnt = conn.prepareStatement(sql1);
	    pstmnt.setInt(1, aid);
	    pstmnt.executeUpdate();
		System.out.println("Record Deleted from Involvements!");
	}catch (SQLException e) {
        System.out.println(e.getMessage());
    }
		break;
		
	case 2:
		try{conn = this.connect();
	    pstmnt = conn.prepareStatement(sql2);
	    pstmnt.setInt(1, aid);
	    pstmnt.executeUpdate();
		System.out.println("Record Deleted from Accidents!");
	}catch (SQLException e) {
        System.out.println(e.getMessage());
    }
		break;
		
	case 3:
		System.out.println("Terminating program...");
		System.exit(1);
		break;
		
	default:
		System.out.println("Oops!!! Wrong Choice...");
		System.exit(1);
		s.close();
		break;
	}	
}

/**
 * The following methods are used to search for specific
 * instances by providing detailed bounds for the search.
 * All are implemented by overriding similar methods in 
 * the Operations interface
 *  
 */
@Override
/**
 * @param minDate: Provide the starting date for a search
 * @param maxDate: Provide the ending date for a search
 * 
 * @return aid, accident_date, city,and state
 * 
 */
public void searchByDate(String minDate,String maxDate) {
	String sql = "select a.aid,\r\n"
			+ "   a.accident_date,\r\n"
			+ "   a.city,\r\n"
			+ "   a.state\r\n"
			+ "from accidents as a\r\n"
			+ "inner join involvements as i\r\n"
			+ "on a.aid = i.aid\r\n"
			+ "where accident_date between ? and ?"
			+ "group by a.aid";
	
	try {
		conn = this.connect();
		PreparedStatement pstmnt = conn.prepareStatement(sql);
		
		pstmnt.setString(1, minDate);
		pstmnt.setString(2, maxDate);
		
		//Initialize a result set class to extract the values
		ResultSet rs = pstmnt.executeQuery();
		
		//Format output
        System.out.println("aid\t   Date\t   City\t   State ");
      
        //Loop through the result set to get the desired output
        while(rs.next()){
        	int aid =  rs.getInt("aid");
        	String accident_date = rs.getString("accident_date");
     	    String city = rs.getString("city");
     	    String state = rs.getString("state");
     	   System.out.println(aid+"\t "+accident_date+"\t "+city+"\t "+state);	
        }
		rs.close();
		
	}catch(SQLException e) {
		System.out.println(e.getMessage());
		System.exit(0);
	}
}

@Override
/**
 * @param minAvgDamage:Provide the low end of an Average damage to conduct the search
 * @param maxAvgDamage:Provide the high end of an Average damage to conduct the search
 * 
 * @return aid, accident_date, city,and state
 * 
 */
public void searchByAvgDamages(float minAvgDamage,float maxAvgDamage) {
	String sql = "select a.aid,\r\n"
			+ "   a.accident_date,\r\n"
			+ "   a.city,\r\n"
			+ "   a.state\r\n"
			+ "from accidents as a\r\n"
			+ "inner join involvements as i\r\n"
			+ "on a.aid = i.aid\r\n"
			+ "group by a.aid\r\n"
			+ "having avg(DISTINCT(i.damages)) between ? and ?";
	
	try {
		conn = this.connect();
		PreparedStatement pstmnt = conn.prepareStatement(sql);
		
		pstmnt.setFloat(1, minAvgDamage);
		pstmnt.setFloat(2, maxAvgDamage);
		
		//Initialize a result set class to extract the values
		ResultSet rs = pstmnt.executeQuery();
		
		//Format output
        System.out.println("aid\t   Date\t   City\t   State ");
      
        //Loop through the result set to get the desired output
        while(rs.next()){
        	int aid =  rs.getInt("aid");
        	String accident_date = rs.getString("accident_date");
     	    String city = rs.getString("city");
     	    String state = rs.getString("state");
     	   System.out.println(aid+"\t "+accident_date+"\t "+city+"\t "+state);	
        }
		rs.close();	
	}catch(SQLException e) {
		System.out.println(e.getMessage());
		System.exit(0);
	}
}

@Override
/**
 * @param minDamage:Provide the low end of total damages to conduct the search
 * @param maxDamage:Provide the high end of total damages to conduct the search
 * 
 * @return aid, accident_date, city,and state
 * 
 */
public void searchByTotalDamages(float minDamage,float maxDamage) {
	String sql = "select a.aid,\r\n"
			+ "   a.accident_date,\r\n"
			+ "   a.city,\r\n"
			+ "   a.state\r\n"
			+ "from accidents as a\r\n"
			+ "inner join involvements as i\r\n"
			+ "on a.aid = i.aid\r\n"
			+ "group by a.aid\r\n"
			+ "having SUM((i.damages)) between ? and ?";
	
	try {
		conn = this.connect();
		PreparedStatement pstmnt = conn.prepareStatement(sql);
		
		pstmnt.setFloat(1, minDamage);
		pstmnt.setFloat(2, maxDamage);
		
		//Initialize a result set class to extract the values
		ResultSet rs = pstmnt.executeQuery();
		
		//Format output
        System.out.println("aid\t   Date\t   City\t   State ");
        
        //Loop through the result set to get the desired output
        while(rs.next()){
        	int aid =  rs.getInt("aid");
        	String accident_date = rs.getString("accident_date");
     	    String city = rs.getString("city");
     	    String state = rs.getString("state");
     	   System.out.println(aid+"\t "+accident_date+"\t "+city+"\t "+state);	
        }
		rs.close();	
	}catch(SQLException e) {
		System.out.println(e.getMessage());
		System.exit(0);
	}
}

@Override
/**
 * A combination of all the parameters mentioned in the above three
 * searches.
 * 
 * @param minDate
 * @param maxDate
 * @param minAvgDamage
 * @param maxAvgDamage
 * @param minDamage
 * @param maxDamage
 * 
 * @return aid, accident_date, city,and state
 * 
 */
public void customSearch(String minDate, String maxDate,
        float minAvgDamage,float maxAvgDamage,
        float minDamage,float maxDamage) {
	String sql = "select a.aid,\r\n"
			+ "   a.accident_date,\r\n"
			+ "   a.city,\r\n"
			+ "   a.state\r\n"
			+ "from accidents as a\r\n"
			+ "inner join involvements as i\r\n"
			+ "on a.aid = i.aid\r\n"
			+ "where accident_date between ? and ?"
			+ "group by a.aid\r\n"
			+ "having (avg(DISTINCT(i.damages)) between ? and ?)"
			+ "and\r\n"
			+ "(SUM((i.damages)) between ? and ?)";
	try {
		conn = this.connect();
		PreparedStatement pstmnt = conn.prepareStatement(sql);
		
		pstmnt.setString(1, minDate);
		pstmnt.setString(2, maxDate);
		pstmnt.setFloat(3, minAvgDamage);
		pstmnt.setFloat(4, maxAvgDamage);
		pstmnt.setFloat(5, minDamage);
		pstmnt.setFloat(6, maxDamage);
		
		//Initialize a result set class to extract the values
		ResultSet rs = pstmnt.executeQuery();
		
		//Format output
        System.out.println("aid\t   Date\t   City\t   State ");
        
       //Loop through the result set to get the desired output
        while(rs.next()){
        	int aid =  rs.getInt("aid");
        	String accident_date = rs.getString("accident_date");
     	    String city = rs.getString("city");
     	    String state = rs.getString("state");
     	   System.out.println(aid+"\t "+accident_date+"\t "+city+"\t "+state);	
        }
		rs.close();	
	}catch(SQLException e) {
		System.out.println(e.getMessage());
		System.exit(0);
	}
}

@Override
/**
 * Implement a method to conduct a generic search for a specific accident. 
 * This is done by overriding the similar method in the Operations interface
 * 
 * @param int aid i.e. the Accident ID
 * @return aid, accident_date, city,state, vin, damages, and driver_ssn
 *  	
 */
public void query(int aid){
	String sql = "select a.aid,\r\n"
			+ "       a.accident_date,\r\n"
			+ "       a.city,\r\n"
			+ "       a.state, \r\n"
			+ "       i.vin,\r\n"
			+ "       i.damages,\r\n"
			+ "       i.driver_ssn\r\n"
			+ "from accidents as a\r\n"
			+ "inner join involvements as i\r\n"
			+ "on a.aid = i.aid\r\n"
			+ "where a.aid = ?";
	
			try {conn = this.connect();
		         PreparedStatement pstmnt = conn.prepareStatement(sql);
		         
		         pstmnt.setInt(1, aid);
		         
		         //Initialize a result set class to extract the values
		         ResultSet rs = pstmnt.executeQuery();
		         
		        //Format output
		         System.out.println("aid\t  Date\t        City\t "
		         		+ "     State\t    VIN\t      Damages\t    SSN");
		         
		         //Loop through the result set to get the desired output
		         while(rs.next()) {
		        	 aid = rs.getInt("aid");
		        	 String accident_date = rs.getString("accident_date");
		        	 String city = rs.getString("city");
		        	 String state = rs.getString("state");
		        	 String vin = rs.getString("vin");
		        	 float damages = rs.getFloat("damages");
		        	 String driver_ssn = rs.getString("driver_ssn");
		        	 System.out.println(aid+"\t "+accident_date+"\t "+city+"\t "+state
		        					 +"\t "+vin+"\t "+damages+"\t "+driver_ssn);
		         }
		         rs.close();					
			}catch(SQLException e) {
				System.out.println(e.getMessage());
				System.exit(0);
			}
}

////////////////////// ATTEMPTS & ////////////////////////////
///////////////////// EXPERIMENTS /////////////////////////////

    //static java.util.Date latestDate = new java.util.Date();
    //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    //accident_date = java.sql.Date(java.util.Date);
	/*
	 try {
		accident_date = (Date) sdf.parse(latestDate);
	} catch (ParseException e1) {
		e1.printStackTrace();
	}
	*/
	//accident_date = sdf;
	
	//Convert java.util.Date to java.sql.Date
     //dt1.connect();
	//Scanner s = new Scanner(System.in);
	
	//Date date = new Date(2011,11,22);
	//dt.insertAccidents(date,"CORPUS CHRISTI","TX");

//As this class was being instantiated in the GUI class separately
//a driver main method here was simply redundant

/*
public static void main(String args[]) {
	DataTransactions dt1 = new DataTransactions();
	Scanner sc = new Scanner(System.in);
	
	int aid = sc.nextInt();
	String vin = sc.next();
	float damages = sc.nextFloat();
	String driver_ssn =sc.next();
	dt1.insertInvolvements(aid,vin,damages,driver_ssn);
	sc.close();
	
} */

}
