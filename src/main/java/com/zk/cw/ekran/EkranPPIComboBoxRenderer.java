package com.zk.cw.ekran;

import java.awt.Component;

import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

public class EkranPPIComboBoxRenderer extends BasicComboBoxRenderer {
	private static final long serialVersionUID = 1L;

	public Component getListCellRendererComponent(
        JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
        {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
     
            if (value instanceof EkranPPI)
            {
            	EkranPPI ekranPPI = (EkranPPI)value;
                setText( ekranPPI.getPpi());
            }     
            return this;
    }
}
