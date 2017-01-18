package com.zk.cw.kamera;

import javax.swing.DefaultComboBoxModel;


public class KameraCozunurlukCombBoxModel extends DefaultComboBoxModel<KameraCozunurluk> {
	private static final long serialVersionUID = 1L;

	public KameraCozunurlukCombBoxModel() {
        super();
    }
 
    @Override
    public KameraCozunurluk getSelectedItem() {
    	KameraCozunurluk selectedArkaKameraCozunurluk = (KameraCozunurluk) super.getSelectedItem();
        return selectedArkaKameraCozunurluk;
    }
}
