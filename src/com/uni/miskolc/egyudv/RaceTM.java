package com.uni.miskolc.egyudv;

import javax.swing.table.DefaultTableModel;

public class RaceTM extends DefaultTableModel {
	
	public RaceTM (Object fieldNames[], int rows) {
		super(fieldNames, rows);
	}
	
	public boolean isCellEditable(int row, int col) {
		if (col == 0) {
			return true;
		}
		return false;
	}
	
	public Class<?> getColumnClass(int index) {
		if (index == 0) {
			return (Boolean.class);
		} else if ( index == 1 || index == 5) {
			return (Integer.class);
		} else {
			return (String.class);
		}
	}

}
