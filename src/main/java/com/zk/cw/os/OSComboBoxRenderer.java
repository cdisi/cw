package com.zk.cw.os;

import java.awt.Component;

import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

public class OSComboBoxRenderer extends BasicComboBoxRenderer {
	private static final long serialVersionUID = 1L;

	public Component getListCellRendererComponent(
			
        JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
        {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
     
            if (value instanceof OS)
            {
            	OS os = (OS)value;
                setText( os.getAd());
            }     
            return this;
    }
}