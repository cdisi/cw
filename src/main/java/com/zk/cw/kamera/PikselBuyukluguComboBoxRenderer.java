package com.zk.cw.kamera;

import java.awt.Component;

import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

public class PikselBuyukluguComboBoxRenderer extends BasicComboBoxRenderer {
	private static final long serialVersionUID = 1L;

	public Component getListCellRendererComponent(
			
        JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
        {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
     
            if (value instanceof PikselBuyuklugu)
            {
            	PikselBuyuklugu pikselBuyuklugu= (PikselBuyuklugu)value;
                setText( pikselBuyuklugu.getBuyukluk());
            }     
            return this;
    }
}
