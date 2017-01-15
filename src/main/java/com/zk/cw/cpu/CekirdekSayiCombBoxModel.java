package com.zk.cw.cpu;

import javax.swing.DefaultComboBoxModel;

public class CekirdekSayiCombBoxModel extends DefaultComboBoxModel<CekirdekSayi> {
	private static final long serialVersionUID = 1L;

	public CekirdekSayiCombBoxModel() {
        super();
    }
 
    @Override
    public CekirdekSayi getSelectedItem() {
    	CekirdekSayi selectedCekirdekSayi = (CekirdekSayi) super.getSelectedItem();
        return selectedCekirdekSayi;
    }
}