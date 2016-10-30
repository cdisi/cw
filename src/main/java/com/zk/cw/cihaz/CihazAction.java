package com.zk.cw.cihaz;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

public class CihazAction extends AbstractAction {
	private JFrame mainFrame;	
	private JMenuBar menuBar;
	
	public CihazAction(JFrame aFrame, JMenuBar menuBar){
	    super("Tüm Cihazlar", null );
	    putValue(SHORT_DESCRIPTION, "Tüm Cihazlar"); 
	    putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_A) );
	    mainFrame = aFrame;
	    this.menuBar = menuBar;
	}
	@Override 
	public void actionPerformed(ActionEvent aActionEvent) {
		CihazMainWindow tumCihazlarMainWindow = CihazMainWindow.getInstance();
		tumCihazlarMainWindow.buildGui(mainFrame,this.menuBar);
	}	
}
