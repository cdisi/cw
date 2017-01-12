package com.zk.cw.hafiza;

import javax.swing.DefaultComboBoxModel;

public class DahiliHafizaCombBoxModel extends DefaultComboBoxModel<DahiliHafiza> {
	private static final long serialVersionUID = 1L;

	public DahiliHafizaCombBoxModel() {
        super();
    }
 
    @Override
    public DahiliHafiza getSelectedItem() {
    	DahiliHafiza selectedDahiliHafiza = (DahiliHafiza) super.getSelectedItem();
        return selectedDahiliHafiza;
    }
}