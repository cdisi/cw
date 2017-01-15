package com.zk.cw.os;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import com.zk.cw.cihaz.CihazView;

public class OSChangeListener implements  ItemListener {
    
	private CihazView fView;
	public OSChangeListener(CihazView aView){
		fView=aView;
	}
	@Override
    public void itemStateChanged(ItemEvent event) {
       if (event.getStateChange() == ItemEvent.SELECTED) {
           OS os = (OS) event.getItem();
          fView.refreshOSSurumComboField(os);
       }
    }
       
}
