
package com.zk.cw.yeni_cihaz;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class YeniCihazAction extends AbstractAction {
	
	private static final long serialVersionUID = 1L;
	private JFrame mFrame;	
	private JScrollPane mainPanel;
	
	public YeniCihazAction(JFrame aFrame, JScrollPane mainPanel){
	    super("Yeni Cihazlar", null );
	    putValue(SHORT_DESCRIPTION, "Yeni Cihazlar"); 
	    putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_A) );
	    mFrame = aFrame;
	    this.mainPanel=mainPanel;
	}
	
	@Override 
	public void actionPerformed(ActionEvent aActionEvent) {
	    this.mainPanel.removeAll();
	    this.mainPanel.repaint();
		//MovieView view = new MovieView(fFrame);
	}	
	
}
