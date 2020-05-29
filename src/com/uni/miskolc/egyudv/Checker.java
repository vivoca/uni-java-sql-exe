package com.uni.miskolc.egyudv;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

public class Checker {

    private DbMethods dbMethods = new DbMethods();

    public boolean filled(JTextField textField, String fieldLabel) {
        String fieldValue = getFieldValue(textField);
        if (fieldValue.length() > 0) {
            return true;
        } else {
            showMsgDialog("Error: " + fieldLabel + " is empty!", 0);
            return false;
        }
    }

    public boolean goodInt(JTextField textField, String fieldLabel) {
        String fieldValue = getFieldValue(textField);
        if (filled(textField, fieldLabel)) {
            try {
                Integer.parseInt(fieldValue);
                if (fieldLabel.equals("ID")) {
                    dbMethods.connect();
                    if(dbMethods.checkIdExists(fieldValue)) {
                        dbMethods.disconnect();
                        showMsgDialog("ID already exists!", 0);
                        return false;
                    }
                }
                return true;
            } catch (NumberFormatException ex) {
                showMsgDialog("Wrong number input in " + fieldLabel, 0);
            }
        }
        return false;
    }

    public boolean dateFormatChecker(String stringDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        try {
            Date date = sdf.parse(stringDate);
            if (!sdf.format(date).equals(stringDate)) {
                return false;
            } else {
                return true;
            }
        } catch (ParseException ex) {
            return false;
        }
    }
    
    public boolean goodDate(JTextField textField, String fieldLabel) {
    	String fieldValue = getFieldValue(textField);
    	boolean isFilled = filled(textField, fieldLabel);
    	if (isFilled && dateFormatChecker(fieldValue)) {
    		return true;
    	} else {
    		showMsgDialog("Wrong date format in " + fieldLabel, 0);
    		return false;
    	}
    }

    public String getFieldValue(JTextField jtf) {
        return jtf.getText();
    }

    public void showMsgDialog(String msg, int type) {
        JOptionPane.showMessageDialog(null, msg, "Message Dialog", type);
    }
}
