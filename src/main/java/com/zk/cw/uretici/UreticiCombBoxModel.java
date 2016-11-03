package com.zk.cw.uretici;

import javax.swing.DefaultComboBoxModel;

public class UreticiCombBoxModel extends DefaultComboBoxModel<Uretici> {

	private static final long serialVersionUID = 1L;

	public UreticiCombBoxModel() {
        super();
    }
 
    @Override
    public Uretici getSelectedItem() {
    	Uretici selectedUretici = (Uretici) super.getSelectedItem();
        return selectedUretici;
    }
}
