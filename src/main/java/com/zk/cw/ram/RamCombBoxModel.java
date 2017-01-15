package com.zk.cw.ram;

import javax.swing.DefaultComboBoxModel;

public class RamCombBoxModel extends DefaultComboBoxModel<Ram> {
	private static final long serialVersionUID = 1L;

	public RamCombBoxModel() {
        super();
    }
 
    @Override
    public Ram getSelectedItem() {
    	Ram selectedRam = (Ram) super.getSelectedItem();
        return selectedRam;
    }
}
