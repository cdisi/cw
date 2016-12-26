package com.zk.cw.ekran;

import javax.swing.DefaultComboBoxModel;

public class EkranTipComboBoxModel extends DefaultComboBoxModel<EkranTip> {

	private static final long serialVersionUID = 1L;

	public EkranTipComboBoxModel() {
        super();
    }
 
    @Override
    public EkranTip getSelectedItem() {
    	EkranTip selectedEkranTip = (EkranTip) super.getSelectedItem();
        return selectedEkranTip;
        
    }
}