package com.zk.cw.sensor;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class SensorListener implements ItemListener {
	@Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getSource() instanceof SensorJCheckBox){
        	SensorJCheckBox checkBoxWithID = (SensorJCheckBox) e.getSource();
            if(checkBoxWithID.isSelected()){
                System.out.println(checkBoxWithID.getText());
            } else {
                
            }
        }
    }
}
