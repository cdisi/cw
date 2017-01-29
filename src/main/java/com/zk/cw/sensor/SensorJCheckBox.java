package com.zk.cw.sensor;

import javax.swing.JCheckBox;

public class SensorJCheckBox extends JCheckBox {
    private Integer _id;

    public SensorJCheckBox(String text, Integer id) {
        super(text);
        _id = id;
    }

    public void setId(Integer id){
        _id = id;
    }

    public Integer getId(){
        return _id;
    }
}
