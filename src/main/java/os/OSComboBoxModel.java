package os;

import javax.swing.DefaultComboBoxModel;

public class OSComboBoxModel extends DefaultComboBoxModel<OS> {
	private static final long serialVersionUID = 1L;

	public OSComboBoxModel() {
        super();
    }
 
    @Override
    public OS getSelectedItem() {
    	OS selectedOS = (OS) super.getSelectedItem();
        return selectedOS;
    }
}