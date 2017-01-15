package com.zk.cw.harici_hafiza;

import java.awt.Component;

import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

public class HariciHafizaBuyuklukComboBoxRenderer extends BasicComboBoxRenderer {
	private static final long serialVersionUID = 1L;

	public Component getListCellRendererComponent(
			
        JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
        {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
     
            if (value instanceof HariciHafizaBuyukluk)
            {
            	HariciHafizaBuyukluk hariciHafizaBuyukluk = (HariciHafizaBuyukluk)value;
                setText( hariciHafizaBuyukluk.getBuyukluk());
            }     
            return this;
    }
}
