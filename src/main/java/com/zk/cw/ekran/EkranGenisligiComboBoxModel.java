package com.zk.cw.ekran;

import javax.swing.DefaultComboBoxModel;


public class EkranGenisligiComboBoxModel extends DefaultComboBoxModel<EkranGenisligi> {
		private static final long serialVersionUID = 1L;

		public EkranGenisligiComboBoxModel() {
	        super();
	    }
	 
	    @Override
	    public EkranGenisligi getSelectedItem() {
	    	EkranGenisligi selectedEkranGenisligi = (EkranGenisligi) super.getSelectedItem();
	        return selectedEkranGenisligi;
	        
	    }
}
