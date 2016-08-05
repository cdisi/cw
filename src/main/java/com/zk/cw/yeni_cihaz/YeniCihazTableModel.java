package com.zk.cw.yeni_cihaz;

import java.util.List;

import javax.swing.table.AbstractTableModel;


public class YeniCihazTableModel extends AbstractTableModel {
	private YeniCihazDAO DAO;
	
	private List<YeniCihaz> yeniCihaz;
	  
	public YeniCihazTableModel(){
		    fDAO = new MovieDAO();
		    fMovies = fDAO.list();
	}
}
