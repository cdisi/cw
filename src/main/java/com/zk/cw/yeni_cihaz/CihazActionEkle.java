package com.zk.cw.yeni_cihaz;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;

public class CihazActionEkle extends AbstractAction {
	  // PRIVATE
	  private JFrame mainFrame;	
	  /** Constructor. */
	  public CihazActionEkle(JFrame aFrame){
	    super("Ekle...", null );
	    putValue(SHORT_DESCRIPTION, "Yeni Cihaz Ekle"); 
	    putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_A) );
	    mainFrame = aFrame;
	  }
	  
	  @Override public void actionPerformed(ActionEvent aActionEvent) {
		    CihazView view = new CihazView(mainFrame);
	  }	  
}
