package com.zk.cw.ekran;

import javax.swing.DefaultComboBoxModel;

public class EkranPPIComboBoxModel extends DefaultComboBoxModel<EkranPPI> {
	private static final long serialVersionUID = 1L;

	public EkranPPIComboBoxModel() {
        super();
    }
 
    @Override
    public EkranPPI getSelectedItem() {
    	EkranPPI selectedEkranPPI = (EkranPPI) super.getSelectedItem();
        return selectedEkranPPI;
        
    }
}
