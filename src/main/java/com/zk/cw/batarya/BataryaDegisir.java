package com.zk.cw.batarya;

public class BataryaDegisir {
	private Integer id;
    private String ad;
    
    public BataryaDegisir() {
    }
    public BataryaDegisir(Integer id, String ad) {
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