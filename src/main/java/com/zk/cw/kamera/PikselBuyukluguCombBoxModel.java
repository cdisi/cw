package com.zk.cw.kamera;

import javax.swing.DefaultComboBoxModel;

public class PikselBuyukluguCombBoxModel extends DefaultComboBoxModel<PikselBuyuklugu> {
	private static final long serialVersionUID = 1L;

	public PikselBuyukluguCombBoxModel() {
        super();
    }
 
    @Override
    public PikselBuyuklugu getSelectedItem() {
    	PikselBuyuklugu selectedArkaKameraPikselBuyuklugu = (PikselBuyuklugu) super.getSelectedItem();
        return selectedArkaKameraPikselBuyuklugu;
    }
}

