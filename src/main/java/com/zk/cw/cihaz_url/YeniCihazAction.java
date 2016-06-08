package com.zk.cw.cihaz_url;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;

public class YeniCihazAction extends AbstractAction  {
	
	private JFrame fFrame;
	
	public YeniCihazAction(JFrame aFrame){
	    super("Yeni Cihazlar", null );
	    putValue(SHORT_DESCRIPTION, "Yeni Cihazlar..."); 
	    putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_A) );
	    fFrame = aFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
