package com.zk.cw.sim;

import javax.swing.DefaultComboBoxModel;

public class SimSayisiComboBoxModel extends DefaultComboBoxModel<SimSayisi>{
	private static final long serialVersionUID = 1L;

	public SimSayisiComboBoxModel() {
        super();
    }
 
    @Override
    public SimSayisi getSelectedItem() {
    	SimSayisi selectedSimSayisi = (SimSayisi) super.getSelectedItem();
        return selectedSimSayisi;
    }    
}
