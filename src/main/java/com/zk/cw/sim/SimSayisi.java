package com.zk.cw.sim;

public class SimSayisi {
	private Integer id;
    private String sayi;
    
    public SimSayisi() {
    }
    public SimSayisi(Integer id, String sayi) {
        this.id = id;
        this.sayi = sayi;
    }
	public void setId(Integer id) {
        this.id=id;
    }    
	public Integer getId() {
        return this.id;
    }
	public void setSayi(String sayi) {
        this.sayi=sayi;
    }	
	public String getSayi() {
        return this.sayi;
    }	
    
    @Override
    public String toString()
    {
        return sayi;
    }
}
