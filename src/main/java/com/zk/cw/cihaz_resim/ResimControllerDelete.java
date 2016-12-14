package com.zk.cw.cihaz_resim;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JPanel;

public class ResimControllerDelete extends AbstractAction {
	ResimView fView;
	JPanel fMainPanel;
	Resim resimGaleri = new Resim();
	ResimDAO resimDAO;
	
	public ResimControllerDelete(ResimView aView, JPanel aMainPanel){
		fView = aView;
		fMainPanel = aMainPanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
