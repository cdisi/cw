package com.zk.cw.ekran;

import javax.swing.DefaultComboBoxModel;

public class EkranCozunurlukComboBoxModel extends DefaultComboBoxModel<EkranCozunurluk> {
	private static final long serialVersionUID = 1L;

	public EkranCozunurlukComboBoxModel() {
        super();
    }
 
    @Override
    public EkranCozunurluk getSelectedItem() {
    	EkranCozunurluk selectedEkranCozunurluk = (EkranCozunurluk) super.getSelectedItem();
        return selectedEkranCozunurluk;
        
    }
}
