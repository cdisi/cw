package com.zk.cw.yeni_cihaz;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JTable;

import com.zk.cw.uretici.Uretici;
import com.zk.cw.uretici.UreticiDAO;


public class CihazActionEkle extends AbstractAction {
	  // PRIVATE
	  private JFrame mainFrame;	
	  private JTable table;
	  private YeniCihazTableModel yeniCihazTableModel;
	  /** Constructor. */
	  public CihazActionEkle(JFrame aFrame, JTable table, YeniCihazTableModel yeniCihazTableModel){
	    super("Ekle...", null );
	    putValue(SHORT_DESCRIPTION, "Yeni Cihaz Ekle"); 
	    putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_A) );
	    mainFrame = aFrame;
	    this.table=table;
	    this.yeniCihazTableModel = yeniCihazTableModel;
	  }
	  
	  @Override public void actionPerformed(ActionEvent aActionEvent) {
	    int row = this.table.getSelectedRow();
	    YeniCihaz selectedCihaz = yeniCihazTableModel.getCihaz(row);
	    UreticiDAO ureticiDAO  = new UreticiDAO();
	    Uretici uretici = null;
	    try {
			uretici  = ureticiDAO.findById(selectedCihaz.getUreticiId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    CihazView view = new CihazView(mainFrame,selectedCihaz,uretici);
	  }
	  
}
