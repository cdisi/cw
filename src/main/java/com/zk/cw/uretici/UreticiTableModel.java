package com.zk.cw.uretici;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import com.zk.cw.util.Util;
import javax.swing.table.AbstractTableModel;

public final class UreticiTableModel extends AbstractTableModel {
  // PRIVATE //
  private UreticiDAO fDAO;
  private List<Uretici> fUretici;
  private int fNumClicks = 0;  
	
  /** Constructor. */
  public UreticiTableModel(){
    fDAO = new UreticiDAO();
    fUretici = fDAO.list();
  }

  public void refreshView() {
	fUretici = fDAO.list();
    fireTableDataChanged();
  }
  
  
  /** Return the selected {@link Uretici}. */
  public Uretici getUretici(int aRow){
    return fUretici.get(aRow);
  }
  
  
  /** Return the number of columns in the table. */
  @Override public int getColumnCount() {
    return 4;
  }
  
  /** Return the number of rows in the table. */
  @Override public int getRowCount() {
    return fUretici.size();
  }
  
  /** Return the <tt>Object</tt> in a specific table cell. */
  @Override public Object getValueAt(int aRow, int aCol) {
    Object result = null;
    Uretici uretici = fUretici.get(aRow);
    if(aCol == 0) {
      result = uretici.idAl();
    }
    else if(aCol == 1) {
      result = uretici.adAl();
    }
    else if(aCol == 2) {
      result = uretici.logoUrlAl();
    }
    else if(aCol == 3) {
      result = uretici.aktifAl();
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
      result = "Ad";
    }
    else if( aIdx == 2) {
      result = "logo";
    }
    else if( aIdx == 3) {
      result =  "Aktif";
    }
    return result;
  }
  

}
