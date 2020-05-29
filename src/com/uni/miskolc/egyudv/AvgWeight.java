package com.uni.miskolc.egyudv;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AvgWeight extends JDialog {

	private JTextField city;
    private DbMethods dbMethods = new DbMethods();
    private Checker checker = new Checker();

    /**
     * Create the dialog.
     */
    public AvgWeight() {
        setBounds(100, 100, 215, 215);
        getContentPane().setLayout(null);

        JLabel lblCity = new JLabel("City");
        lblCity.setBounds(10, 40, 75, 14);
        getContentPane().add(lblCity);

        city = new JTextField();
        city.setBounds(103, 37, 86, 20);
        getContentPane().add(city);
        city.setColumns(10);
        
        JButton btnRunQuery = new JButton("Run query");
        btnRunQuery.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(checker.filled(city, "City")) {
            		dbMethods.connect();
            		double avgWeight = dbMethods.getAvgWeightByCity(getFieldValue(city));
            		showMsgDialog("The avarage weight in " + getFieldValue(city) + ": " + avgWeight, 1);
            		dbMethods.disconnect();
            	}
            	dispose();
        	}
        });
        btnRunQuery.setBounds(52, 142, 106, 23);
        getContentPane().add(btnRunQuery);


    }

    public String getFieldValue(JTextField jtf) {
        return jtf.getText();
    }
    
    public void showMsgDialog(String msg, int type) {
        JOptionPane.showMessageDialog(null, msg, "Message Dialog", type);
    }

}


