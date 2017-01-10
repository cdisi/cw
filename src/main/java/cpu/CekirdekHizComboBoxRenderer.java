package cpu;

import java.awt.Component;

import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

public class CekirdekHizComboBoxRenderer extends BasicComboBoxRenderer {
	private static final long serialVersionUID = 1L;

	public Component getListCellRendererComponent(
			
        JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
        {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
     
            if (value instanceof CekirdekHiz)
            {
            	CekirdekHiz cekirdekHiz = (CekirdekHiz)value;
                setText( cekirdekHiz.getHiz());
            }     
            return this;
    }
}
