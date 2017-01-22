package com.zk.cw.batarya;

import javax.swing.DefaultComboBoxModel;


public class BataryaKapasiteCombBoxModel extends DefaultComboBoxModel<BataryaKapasite> {
	private static final long serialVersionUID = 1L;

	public BataryaKapasiteCombBoxModel() {
        super();
    }
 
    @Override
    public BataryaKapasite getSelectedItem() {
    	BataryaKapasite selectedBataryaKapasite = (BataryaKapasite) super.getSelectedItem();
        return selectedBataryaKapasite;
    }
}
