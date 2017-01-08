package com.zk.cw.yonga_seti;

import javax.swing.DefaultComboBoxModel;

import com.zk.cw.sim.Sim;

public class YongaSetiBoxModel  extends DefaultComboBoxModel<YongaSeti> {
	private static final long serialVersionUID = 1L;

	public YongaSetiBoxModel() {
        super();
    }
 
    @Override
    public YongaSeti getSelectedItem() {
    	YongaSeti selectedYongaSeti = (YongaSeti) super.getSelectedItem();
        return selectedYongaSeti;
    }
}