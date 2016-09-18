package com.zk.cw.yeni_cihaz;


public class CihazTur {
	
    private Integer value;
    private String text;

    public CihazTur(Integer value, String text) {
        this.value = value;
        this.text = text;
    }
    public Integer getValue() {
        return this.value;
    }
    
    @Override
    public String toString()
    {
        return text;
    }    
}
