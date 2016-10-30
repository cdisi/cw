package com.zk.cw.cihaz;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JTable;

public class CihazActionAdd extends AbstractAction {
	  private JFrame mainFrame;	
	  private JTable table;
	  private CihazTableModel cihazTableModel;
	  /** Constructor. */
	  public CihazActionAdd(JFrame aFrame, JTable table, CihazTableModel cihazTableModel){
	    super("Ekle...", null );
	    putValue(SHORT_DESCRIPTION, "Cihaz Ekle"); 
	    putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_A) );
	    mainFrame = aFrame;
	    this.table=table;
	    this.cihazTableModel = cihazTableModel;
	  }
	  
	  @Override public void actionPerformed(ActionEvent aActionEvent) {
	    CihazView view = new CihazView(mainFrame);
	  }
}
