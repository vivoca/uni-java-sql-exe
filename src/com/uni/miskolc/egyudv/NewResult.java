package com.uni.miskolc.egyudv;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewResult extends JDialog {
	private JTextField id;
	private JTextField location;
	private JTextField driverName;
	private JTextField raceDate;
	private JTextField point;
	private DbMethods dbMethods = new DbMethods();

	/**
	 * Create the dialog.
	 */
	public NewResult() {
		setBounds(100, 100, 215, 242);
		getContentPane().setLayout(null);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(10, 11, 75, 14);
		getContentPane().add(lblId);
		
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setBounds(10, 41, 75, 14);
		getContentPane().add(lblLocation);
		
		JLabel lblRaceDate = new JLabel("Race date");
		lblRaceDate.setBounds(10, 71, 75, 14);
		getContentPane().add(lblRaceDate);
		
		JLabel lblDriverName = new JLabel("Driver name");
		lblDriverName.setBounds(10, 101, 75, 14);
		getContentPane().add(lblDriverName);
		
		JLabel lblPoint = new JLabel("Point");
		lblPoint.setBounds(10, 131, 75, 14);
		getContentPane().add(lblPoint);
		
		id = new JTextField();
		id.setBounds(95, 11, 86, 20);
		getContentPane().add(id);
		id.setColumns(10);
		
		location = new JTextField();
		location.setBounds(95, 38, 86, 20);
		getContentPane().add(location);
		location.setColumns(10);
		
		raceDate = new JTextField();
		raceDate.setBounds(95, 68, 86, 20);
		getContentPane().add(raceDate);
		raceDate.setColumns(10);
		
		driverName = new JTextField();
		driverName.setBounds(95, 98, 86, 20);
		getContentPane().add(driverName);
		driverName.setColumns(10);
		
		point = new JTextField();
		point.setBounds(95, 128, 86, 20);
		getContentPane().add(point);
		point.setColumns(10);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dbMethods.connect();
				dbMethods.insertData(returnTextField(id), returnTextField(location), returnTextField(raceDate), returnTextField(driverName), returnTextField(point));
				dbMethods.disconnect();
				dispose();
			}
		});
		btnCreate.setBounds(53, 169, 89, 23);
		getContentPane().add(btnCreate);

	}
	
	public String returnTextField(JTextField jtf) {
		return jtf.getText();
	}
}
