package com.zk.cw.ekran;

import javax.swing.DefaultComboBoxModel;

public class CokluDokunmatikComboBoxModel extends DefaultComboBoxModel<CokluDokunmatik> {
	
		private static final long serialVersionUID = 1L;

		public CokluDokunmatikComboBoxModel() {
	        super();
	    }
	 
	    @Override
	    public CokluDokunmatik getSelectedItem() {
	    	CokluDokunmatik selectedCokluDokunmatik = (CokluDokunmatik) super.getSelectedItem();
	        return selectedCokluDokunmatik;
	    }    
}
