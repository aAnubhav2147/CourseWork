package cis452project;

import java.sql.*;

public interface Operations {
    Connection connect();
	void insertAccidents(int aid,String accident_date, String city, String state);
	void insertInvolvements(int aid, String vin,float damages,String driver_ssn);
	void delete(int aid);
	void query(int aid);
	void searchByDate(String minDate,String maxDate);
	void searchByAvgDamages(float minAvgDamage,float maxAvgDamage);
	void searchByTotalDamages(float minDamage,float maxDamage);
	void customSearch(String minDate, String maxDate,
            float minAvgDamage,float maxAvgDamage,
            float minDamage,float maxDamage);
}
