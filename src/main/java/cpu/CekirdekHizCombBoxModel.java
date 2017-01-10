package cpu;

import javax.swing.DefaultComboBoxModel;

public class CekirdekHizCombBoxModel extends DefaultComboBoxModel<CekirdekHiz> {
	private static final long serialVersionUID = 1L;

	public CekirdekHizCombBoxModel() {
        super();
    }
 
    @Override
    public CekirdekHiz getSelectedItem() {
    	CekirdekHiz selectedCekirdekHiz = (CekirdekHiz) super.getSelectedItem();
        return selectedCekirdekHiz;
    }
}