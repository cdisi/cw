package com.zk.cw.cihaz;

import java.sql.SQLException;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.zk.cw.cihaz_tur.CihazTur;
import com.zk.cw.cihaz_tur.CihazTurDAO;
import com.zk.cw.uretici.Uretici;
import com.zk.cw.uretici.UreticiDAO;


public class CihazTableModel extends AbstractTableModel {
	
	private CihazDAO DAO;
	private List<Cihaz> cihazlar;
	private UreticiDAO ureticiDAO = new UreticiDAO();
	private Uretici uretici;
	private CihazTurDAO cihazTurDAO = new CihazTurDAO();
	private CihazTur cihazTur;
	public CihazTableModel(){
		DAO = new CihazDAO();
		cihazlar = DAO.list();
	}
	
	public void refreshView() {
		try {
			DAO.all();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cihazlar = DAO.list();
		fireTableDataChanged();
	}	
	
	public Cihaz getCihaz(int aRow){
		return cihazlar.get(aRow);
	}	
	/** Return the number of columns in the table. */
	@Override public int getColumnCount() {
	    return 7;
	}
	  
	/** Return the number of rows in the table. */
	@Override public int getRowCount() {
	    return cihazlar.size();
	}
	  
	  /** Return the <tt>Object</tt> in a specific table cell. */
	@Override public Object getValueAt(int aRow, int aCol) {
	    Object result = null;
	    Cihaz cihaz = cihazlar.get(aRow);

	    if(aCol == 0) {
	      result = cihaz.getId();
	    }
	    else if(aCol == 1) {
	    	result = cihaz.getAd();

	    }
	    else if(aCol == 2) {
	    	try {
				uretici = ureticiDAO.findById(cihaz.getUreticiId());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	      result = uretici.adAl();
	    }
	    else if(aCol == 3) {
		  try {
			cihazTur = cihazTurDAO.findById(cihaz.getTuru());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	      result = cihazTur.getAd();
	    }
	    else if(aCol == 4) {
		      result = cihaz.getDuyurulma();
		}	    
	    else if(aCol == 5) {
		      result = cihaz.getAnasayfa();
		}	    
	    else if(aCol == 6) {
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
	      result = "Cihaz Adı";
	    }
	    else if( aIdx == 2) {
	      result = "Üretici Adı";
	    }
	    else if( aIdx == 3) {
		      result =  "Türü";
		    }
	    else if( aIdx == 4) {
		      result =  "Duyurulma";
		    }
	    else if( aIdx == 5) {
		      result =  "Anasayfa";
		    }
	    else if( aIdx == 6) {
		      result =  "Aktif";
		    }
	    return result;
	}		

}
