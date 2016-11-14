package com.zk.cw.cihaz_resim_galeri;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JTable;

import com.zk.cw.cihaz.Cihaz;
import com.zk.cw.cihaz.CihazTableModel;

public class ResimAction extends AbstractAction {
	  private JFrame mainFrame;	
	  private JTable table;
	  private CihazTableModel cihazTableModel;
	  /** Constructor. */
	  public ResimAction(JFrame aFrame, JTable table, CihazTableModel cihazTableModel){
	    super("Resim Galerisi...", null );
	    putValue(SHORT_DESCRIPTION, "Resim Galerisi"); 
	    putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_A) );
	    mainFrame = aFrame;
	    this.table=table;
	    this.cihazTableModel = cihazTableModel;
	  }
	  
	  @Override public void actionPerformed(ActionEvent aActionEvent) {
	    int row = this.table.getSelectedRow();
	    Cihaz selectedCihaz = cihazTableModel.getCihaz(row);
	    ResimGaleriView view = new ResimGaleriView(mainFrame,selectedCihaz);
	  }
}
