package com.zk.cw.cihaz_tur;

import javax.swing.DefaultComboBoxModel;

public class CihazTurCombBoxModel extends DefaultComboBoxModel<CihazTur> {

	private static final long serialVersionUID = 1L;

	public CihazTurCombBoxModel() {
        super();
    }
 
    @Override
    public CihazTur getSelectedItem() {
    	CihazTur selectedCihazTur = (CihazTur) super.getSelectedItem();
        return selectedCihazTur;
        
    }
}
