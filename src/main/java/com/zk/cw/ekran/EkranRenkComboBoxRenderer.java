package com.zk.cw.ekran;

import java.awt.Component;

import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

public class EkranRenkComboBoxRenderer extends BasicComboBoxRenderer  {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Component getListCellRendererComponent(
        JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
        {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
     
            if (value instanceof EkranRenk)
            {
            	EkranRenk ekranRenk = (EkranRenk)value;
                setText( ekranRenk.getAd());
            }     
            return this;
    }
}
