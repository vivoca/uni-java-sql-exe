package com.uni.miskolc.egyudv;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

public class RaceList extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private RaceTM raceTM;

	/**
	 * Create the dialog.
	 */
	public RaceList(JFrame f, RaceTM rtm) {
		super(f, "Race result", true);
		raceTM = rtm;
		
		setBounds(100, 100, 450, 300);
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
		scrollPane.setBounds(10, 11, 414, 205);
		getContentPane().add(scrollPane);
		
		table = new JTable(raceTM);
		scrollPane.setViewportView(table);
		
		TableColumn tc = null;
		for (int i = 0; i < 6; i++) {
			tc = table.getColumnModel().getColumn(i);
			if (i == 0 || i == 1) tc.setPreferredWidth(30);
			else if (i == 4 || i == 2) tc.setPreferredWidth(150);
			else if (i == 6) tc.setPreferredWidth(60);
			else tc.setPreferredWidth(100);
		}
		
		table.setAutoCreateRowSorter(true);
		TableRowSorter<RaceTM> trs = (TableRowSorter<RaceTM>) table.getRowSorter();
		trs.setSortable(0, false);

	}
}
