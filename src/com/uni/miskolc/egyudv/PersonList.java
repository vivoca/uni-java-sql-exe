package com.uni.miskolc.egyudv;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

public class PersonList extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private PersonTM personTM;

	/**
	 * Create the dialog.
	 */
	public PersonList(JFrame f, PersonTM rtm) {
		super(f, "Person", true);
		personTM = rtm;
		
		setBounds(100, 100, 550, 300);
		getContentPane().setLayout(null);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnClose.setBounds(174, 227, 89, 23);
		getContentPane().add(btnClose);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 514, 205);
		getContentPane().add(scrollPane);
		
		table = new JTable(personTM);
		scrollPane.setViewportView(table);
		
		TableColumn tc = null;
		for (int i = 0; i < 7; i++) {
			tc = table.getColumnModel().getColumn(i);
			switch (i) {
				case 0:
				case 1:
					tc.setPreferredWidth(30);
					break;
				case 2:
				case 3:
				case 4:
					tc.setPreferredWidth(250);
					break;
				default:
					tc.setPreferredWidth(100);
					break;
			}
		}
		
		table.setAutoCreateRowSorter(true);
		TableRowSorter<PersonTM> trs = (TableRowSorter<PersonTM>) table.getRowSorter();
		trs.setSortable(0, false);

	}
}
