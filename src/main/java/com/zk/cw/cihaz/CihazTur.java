package com.zk.cw.cihaz;


public class CihazTur {
	
    private Integer id;
    private String ad;

    public CihazTur(Integer id, String ad) {
        this.id = id;
        this.ad = ad;
    }
    public CihazTur() {
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
        return this.id;
    }
	public String getAd() {
        return this.ad;
    }	
    
    @Override
    public String toString()
    {
        return ad;
    }    
}
