package com.uni.miskolc.egyudv;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Program extends JFrame {

	private JPanel contentPane;
	private DbMethods dbMethods = new DbMethods();
	private RaceTM raceTM;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Program frame = new Program();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Program() {
		dbMethods.reg();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 276, 224);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLista = new JButton("List");
		btnLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dbMethods.connect();
				raceTM = dbMethods.readData();
				dbMethods.disconnect();
				RaceList raceList = new RaceList(Program.this, raceTM);
				raceList.setVisible(true);

			}
		});
		btnLista.setBounds(10, 11, 89, 23);
		contentPane.add(btnLista);
		
		JButton btnCreateData = new JButton("Create");
		btnCreateData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewResult newResult = new NewResult();
				newResult.setVisible(true);
			}
		});
		btnCreateData.setBounds(10, 45, 89, 23);
		contentPane.add(btnCreateData);
		
		Object racetmn[] = {"C", "ID", "Location", "Race date", "Driver name", "Point"};
		raceTM = new RaceTM(racetmn, 0);
	}
}
