package com.zk.cw.batarya;

import javax.swing.DefaultComboBoxModel;

public class BataryaDegisirCombBoxModel extends DefaultComboBoxModel<BataryaDegisir> {
	private static final long serialVersionUID = 1L;

	public BataryaDegisirCombBoxModel() {
        super();
    }
 
    @Override
    public BataryaDegisir getSelectedItem() {
    	BataryaDegisir selectedBataryaDegisir = (BataryaDegisir) super.getSelectedItem();
        return selectedBataryaDegisir;
    }
}
