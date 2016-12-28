package com.zk.cw.ekran;
import javax.swing.DefaultComboBoxModel;

public class EkranRenkComboBoxModel extends DefaultComboBoxModel<EkranRenk> {
	private static final long serialVersionUID = 1L;

	public EkranRenkComboBoxModel() {
        super();
    }
 
    @Override
    public EkranRenk getSelectedItem() {
    	EkranRenk selectedEkranRenk = (EkranRenk) super.getSelectedItem();
        return selectedEkranRenk;
        
    }
}
