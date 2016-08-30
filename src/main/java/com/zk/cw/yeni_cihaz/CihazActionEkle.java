package com.zk.cw.yeni_cihaz;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JTable;


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
	  }
	  
	  @Override public void actionPerformed(ActionEvent aActionEvent) {
		    int row = this.table.getSelectedRow();
		    System.out.println(row);
		    YeniCihaz selectedCihaz = yeniCihazTableModel.getCihaz(row);
		    CihazView view = new CihazView(mainFrame,selectedCihaz);
	  }	  
}
