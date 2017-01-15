package com.zk.cw.harici_hafiza;

import javax.swing.DefaultComboBoxModel;

public class HariciHafizaTipiCombBoxModel extends DefaultComboBoxModel<HariciHafizaTipi> {
	private static final long serialVersionUID = 1L;

	public HariciHafizaTipiCombBoxModel() {
        super();
    }
 
    @Override
    public HariciHafizaTipi getSelectedItem() {
    	HariciHafizaTipi selectedHariciHafizaTipi = (HariciHafizaTipi) super.getSelectedItem();
        return selectedHariciHafizaTipi;
    }
}
