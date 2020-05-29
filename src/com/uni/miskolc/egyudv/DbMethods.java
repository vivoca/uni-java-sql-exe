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
            showMsg("Error during db driver registration" + ex.getMessage(), 0);
        }
    }

    public void showMsg(String msg, int tipus) {
        JOptionPane.showMessageDialog(null, msg, "Message Dialog", tipus);
    }

    public void connect() {
        try {
            String url = "jdbc:sqlite:persondb";
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
    
    public PersonTM readData() {
    	Object persontmn[] = {"C", "ID", "Name", "Birth date", "City", "Height", "Weight"};
		PersonTM personTM = new PersonTM(persontmn, 0);
    	String name;
    	String birthDate;
    	String city;
    	int id;
    	int height;
    	int weight;
    	String sqlp = "SELECT id, name, birth_date, city, height, weight FROM person ";
    	try {
    		statement = conn.createStatement();
    		resultSet = statement.executeQuery(sqlp);
    		while(resultSet.next()) {
    			id = resultSet.getInt("id");
    			name = resultSet.getString("name");
    			birthDate = resultSet.getString("birth_date");
    			city = resultSet.getString("city");
    			height = resultSet.getInt("height");
    			weight = resultSet.getInt("weight");
    			personTM.addRow(new Object[] {false, id, name, birthDate, city, height, weight});
    		}
    		resultSet.close();
    	} catch (SQLException ex) {
    		showMsg(ex.getMessage(), 0);
    	}
    	return personTM;
    }
    
    public boolean checkIdExists(String id) {
    	
    	String sqlp = "SELECT id FROM person WHERE id = " + id;
    	try {
    		statement = conn.createStatement();
    		resultSet = statement.executeQuery(sqlp);

			if (!resultSet.next()) {
				//ResultSet is empty, ID not exists
				resultSet.close();
				return false;
			} else {
				resultSet.close();
				return true;
			}
    		
    	} catch (SQLException ex) {
    		showMsg(ex.getMessage(), 0);
    	}
    	return false;
    }
    
    
    public double getAvgWeightByCity(String city) {
    	String sqlp = "SELECT avg(weight) as avgWeight FROM person WHERE upper(city) = upper('" + city + "')";
    	try {
    		statement = conn.createStatement();
    		resultSet = statement.executeQuery(sqlp);
    		return resultSet.getDouble("avgWeight");
    		
    	} catch (SQLException ex) {
    		showMsg(ex.getMessage(), 0);
    	}
    	return 0;
    }
    
    
    public void insertData(String id, String name, String birthDate, String city, String height, String weight) {
    	
    	String sqlp = "INSERT INTO person values(" + id + ", '" + name + "', '" + birthDate + "', '" + city + "', " + height + ", " + weight + ")";
    	
    	try {
    		statement = conn.createStatement();
    		statement.execute(sqlp);
    		showMsg("Insert OK", 1);
    	} catch ( Exception ex) {
    		showMsg("JDBC insert: " + ex.getMessage(), 0);
    	}
    }
    
    
    
    
    
    
    
    
}