package com.uni.miskolc.egyudv;

import javax.swing.*;
import java.sql.*;

public class DbMethods {

    private Statement statement = null;
    private Connection conn = null;
    private ResultSet resultSet = null;

    public void reg() {
        try {
            Class.forName("org.sqlite.JDBC");
            //showMsg("Sikeres driver regisztracio!", 1);
        } catch (ClassNotFoundException ex) {
            showMsg("Hibas driver regisztracio!" + ex.getMessage(), 0);
        }
    }

    public void showMsg(String msg, int tipus) {
        JOptionPane.showMessageDialog(null, msg, "Program uzenet", tipus);
    }

    public void connect() {
        try {
            String url = "jdbc:sqlite:C:/Saj�t/University/adatbazis2/sqlite/racedb";
            conn = DriverManager.getConnection(url);
            //showMsg("Connection OK!", 1);
        } catch (SQLException ex) {
            showMsg("JDBC connect: " + ex.getMessage(), 0);
        }
    }

    public void disconnect() {
        try {
            conn.close();
            //showMsg("Disconnection OK", 1);
        } catch (SQLException ex) {
            showMsg(ex.getMessage(), 0);
        }
    }
    
    public RaceTM readData() {
    	Object racetmn[] = {"C", "ID", "Location", "Race date", "Driver name", "Point"};
		RaceTM raceTM = new RaceTM(racetmn, 0);
    	String location = "";
    	String raceDate = "";
    	String driverName = "";
    	int id = 0;
    	int point = 0;
    	String x = "\t";
    	String sqlp = "SELECT id, location, race_date, driver_name, point FROM race_result";
    	try {
    		statement = conn.createStatement();
    		resultSet = statement.executeQuery(sqlp);
    		while(resultSet.next()) {
    			id = resultSet.getInt("id");
    			location = resultSet.getString("location");
    			raceDate = resultSet.getString("race_date");
    			driverName = resultSet.getString("driver_name");
    			point = resultSet.getInt("point");
    			raceTM.addRow(new Object[] {false, id, location, raceDate, driverName, point});
    		}
    		resultSet.close();
    	} catch (SQLException ex) {
    		showMsg(ex.getMessage(), 0);
    	}
    	return raceTM;
    }
    
    
    public void insertData(String id, String location, String raceDate, String driverName, String point) {
    	
    	String sqlp = "INSERT INTO race_result values(" + id + ", '" + location + "', '" + raceDate + "', '" + driverName + "', " + point + ")";
    	
    	try {
    		statement = conn.createStatement();
    		statement.execute(sqlp);
    		showMsg("Insert OK", 1);
    	} catch ( Exception ex) {
    		showMsg("JDBC insert: " + ex.getMessage(), 0);
    	}
    }
    
    
    
    
    
    
    
    
}