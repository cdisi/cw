package com.zk.cw.yonga_seti;

import java.awt.Component;

import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

public class YongaSetiComboBoxRenderer extends BasicComboBoxRenderer {
	private static final long serialVersionUID = 1L;

	public Component getListCellRendererComponent(
			
        JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
        {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
     
            if (value instanceof YongaSeti)
            {
            	YongaSeti yongaSeti = (YongaSeti)value;
                setText( yongaSeti.getAd());
            }     
            return this;
    }
}
