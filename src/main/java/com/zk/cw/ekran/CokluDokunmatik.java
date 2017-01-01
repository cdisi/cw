package com.zk.cw.ekran;

public class CokluDokunmatik {
    private String deger;
    
    public CokluDokunmatik() {
    }
    public CokluDokunmatik(String deger) {
        this.deger = deger;
    }

	public void settDeger(String deger) {
        this.deger=deger;
    }	
	public String getDeger() {
        return this.deger;
    }	
    
    @Override
    public String toString()
    {
        return deger;
    }
}
