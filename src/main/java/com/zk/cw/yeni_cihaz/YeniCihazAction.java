
package com.zk.cw.yeni_cihaz;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class YeniCihazAction extends AbstractAction {
	
	private static final long serialVersionUID = 1L;
	private JFrame mainFrame;	
    private YeniCihazTableModel yeniCihazTableModel;
	private JTable yeniCihazTable;
	private JMenuBar menuBar;
	
	public YeniCihazAction(JFrame aFrame, JMenuBar menuBar){
	    super("Yeni Cihazlar", null );
	    putValue(SHORT_DESCRIPTION, "Yeni Cihazlar"); 
	    putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_A) );
	    mainFrame = aFrame;
	    this.menuBar = menuBar;
	}
	
	@Override 
	public void actionPerformed(ActionEvent aActionEvent) {
		YeniCihazMainWindow yeniCihazMainWindow = YeniCihazMainWindow.getInstance();
		yeniCihazMainWindow.buildGui(mainFrame,this.menuBar);
	}	
	
}
