package com.zk.cw.ekran;

public class EkranTip {
	
    private Integer id;
    private String ad;
    public EkranTip() {
    }
    public EkranTip(Integer id, String ad) {
        this.id = id;
        this.ad = ad;
    }
	public void setId(Integer id) {
        this.id=id;
    }    
	public Integer getId() {
        return this.id;
    }
	public void setAd(String ad) {
        this.ad=ad;
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
