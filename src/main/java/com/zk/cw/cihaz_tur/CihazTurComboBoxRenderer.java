package com.zk.cw.cihaz_tur;

import java.awt.Component;

import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

public class CihazTurComboBoxRenderer extends BasicComboBoxRenderer {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Component getListCellRendererComponent(
            JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
        {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
     
            if (value instanceof CihazTur)
            {
            	CihazTur cihazTur = (CihazTur)value;
                setText( cihazTur.getAd());
            }
     
            return this;
        }
}
