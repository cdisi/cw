package com.zk.cw.batarya;

import javax.swing.DefaultComboBoxModel;

public class BataryaTeknolojiCombBoxModel extends DefaultComboBoxModel<BataryaTeknoloji> {
	private static final long serialVersionUID = 1L;

	public BataryaTeknolojiCombBoxModel() {
        super();
    }
 
    @Override
    public BataryaTeknoloji getSelectedItem() {
    	BataryaTeknoloji selectedBataryaTeknoloji = (BataryaTeknoloji) super.getSelectedItem();
        return selectedBataryaTeknoloji;
    }
}

