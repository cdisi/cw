package com.zk.cw.kamera;

public class Diyafram {
	private Integer id;
    private String aciklik;
    
    public Diyafram() {
    }
    public Diyafram(Integer id, String aciklik) {
        this.id = id;
        this.aciklik = aciklik;
    }
	public void setId(Integer id) {
        this.id=id;
    }    
	public Integer getId() {
        return this.id;
    }
	public void setAciklik(String aciklik) {
        this.aciklik=aciklik;
    }	
	public String getAciklik() {
        return this.aciklik;
    }	    
    @Override
    public String toString()
    {
        return aciklik;
    }
}
