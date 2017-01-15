package com.zk.cw.harici_hafiza;

import javax.swing.DefaultComboBoxModel;

public class HariciHafizaBuyuklukCombBoxModel extends DefaultComboBoxModel<HariciHafizaBuyukluk> {
	private static final long serialVersionUID = 1L;

	public HariciHafizaBuyuklukCombBoxModel() {
        super();
    }
 
    @Override
    public HariciHafizaBuyukluk getSelectedItem() {
    	HariciHafizaBuyukluk selectedHariciHafizaBuyukluk = (HariciHafizaBuyukluk) super.getSelectedItem();
        return selectedHariciHafizaBuyukluk;
    }
}
