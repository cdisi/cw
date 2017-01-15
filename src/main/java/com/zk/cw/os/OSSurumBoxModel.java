package com.zk.cw.os;

import javax.swing.DefaultComboBoxModel;

public class OSSurumBoxModel extends DefaultComboBoxModel<OSSurum> {
	private static final long serialVersionUID = 1L;

	public OSSurumBoxModel() {
        super();
    }
 
    @Override
    public OSSurum getSelectedItem() {
    	OSSurum selectedOSSurum = (OSSurum) super.getSelectedItem();
        return selectedOSSurum;
    }
}