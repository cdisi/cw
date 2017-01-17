package com.zk.cw.kamera;

import javax.swing.DefaultComboBoxModel;


public class ArkaKameraCozunurlukCombBoxModel extends DefaultComboBoxModel<ArkaKameraCozunurluk> {
	private static final long serialVersionUID = 1L;

	public ArkaKameraCozunurlukCombBoxModel() {
        super();
    }
 
    @Override
    public ArkaKameraCozunurluk getSelectedItem() {
    	ArkaKameraCozunurluk selectedArkaKameraCozunurluk = (ArkaKameraCozunurluk) super.getSelectedItem();
        return selectedArkaKameraCozunurluk;
    }
}
