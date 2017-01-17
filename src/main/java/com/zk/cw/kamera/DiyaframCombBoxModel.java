package com.zk.cw.kamera;

import javax.swing.DefaultComboBoxModel;

public class DiyaframCombBoxModel extends DefaultComboBoxModel<Diyafram> {
	private static final long serialVersionUID = 1L;

	public DiyaframCombBoxModel() {
        super();
    }
 
    @Override
    public Diyafram getSelectedItem() {
    	Diyafram selectedArkaKameraDiyafram = (Diyafram) super.getSelectedItem();
        return selectedArkaKameraDiyafram;
    }
}

