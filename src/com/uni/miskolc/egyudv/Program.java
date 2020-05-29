package com.uni.miskolc.egyudv;

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
	private PersonTM personTM;

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
				personTM = dbMethods.readData();
				dbMethods.disconnect();
				PersonList personList = new PersonList(Program.this, personTM);
				personList.setVisible(true);

			}
		});
		btnLista.setBounds(10, 11, 110, 23);
		contentPane.add(btnLista);
		
		JButton btnCreateData = new JButton("Create");
		btnCreateData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewPerson newPerson = new NewPerson();
				newPerson.setVisible(true);
			}
		});
		btnCreateData.setBounds(10, 45, 110, 23);
		contentPane.add(btnCreateData);
		
		JButton btnAvgWeight = new JButton("Avg Weight");
		btnAvgWeight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AvgWeight avgWeight = new AvgWeight();
				avgWeight.setVisible(true);
			}
		});
		btnAvgWeight.setBounds(10, 79, 110, 23);
		contentPane.add(btnAvgWeight);
		
		Object persontmn[] = {"C", "ID", "Name", "Birth date", "City", "Height", "Weight"};
		personTM = new PersonTM(persontmn, 0);
	}
}
