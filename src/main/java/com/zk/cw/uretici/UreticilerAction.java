package com.zk.cw.uretici;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class UreticilerAction extends AbstractAction  {
	// PRIVATE
	private JFrame fFrame;	
	private JScrollPane mainPanel;
	
	public UreticilerAction(JFrame aFrame){
		    super("Üreticiler", null );
		    putValue(SHORT_DESCRIPTION, "Üreticiler"); 
		    putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_A) );
		    fFrame = aFrame;
		    this.mainPanel=mainPanel;
	}
	
	@Override 
	public void actionPerformed(ActionEvent aActionEvent) {
	    this.mainPanel.removeAll();
	    this.mainPanel.repaint();
		//MovieView view = new MovieView(fFrame);
	}
		  

}
