package com.zk.cw.yeni_cihaz;

import java.util.List;

import javax.swing.table.AbstractTableModel;


public class YeniCihazTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private YeniCihazDAO DAO;
	private List<YeniCihaz> yeniCihazlar;
	  
	public YeniCihazTableModel(){
		DAO = new YeniCihazDAO();
		yeniCihazlar = DAO.list();
	}
	
	public YeniCihaz getCihaz(int aRow){
		return yeniCihazlar.get(aRow);
	}
	  
	  /** Return the number of columns in the table. */
	  @Override public int getColumnCount() {
	    return 4;
	  }
	  
	  /** Return the number of rows in the table. */
	  @Override public int getRowCount() {
	    return yeniCihazlar.size();
	  }
	  
	  /** Return the <tt>Object</tt> in a specific table cell. */
	  @Override public Object getValueAt(int aRow, int aCol) {
	    Object result = null;
	    YeniCihaz cihaz = yeniCihazlar.get(aRow);
	    if(aCol == 0) {
	      result = cihaz.getId();
	    }
	    else if(aCol == 1) {
	      result = cihaz.getUreticiId();
	    }
	    else if(aCol == 2) {
	      result = cihaz.getUrl();
	    }
	    else if(aCol == 3) {
	      result = cihaz.getAktif();
	    }
	    return result;
	  }
	  
	  /** Return the name of a specific column. */
	  @Override public String getColumnName(int aIdx){
	    String result = "";
	    if( aIdx == 0) {
	      result = "ID";
	    }
	    else if( aIdx == 1) {
	      result = "Üretici";
	    }
	    else if( aIdx == 2) {
	      result = "URL";
	    }
	    else if( aIdx == 3) {
	      result =  "Aktif";
	    }
	    return result;
	  }	
}
