package com.zk.cw.hafiza;

public class DahiliHafiza {
	private Integer id;
    private String buyukluk;
    
    public DahiliHafiza() {
    }
    public DahiliHafiza(Integer id, String buyukluk) {
        this.id = id;
        this.buyukluk = buyukluk;
    }
	public void setId(Integer id) {
        this.id=id;
    }    
	public Integer getId() {
        return this.id;
    }
	public void setBuyukluk(String buyukluk) {
        this.buyukluk=buyukluk;
    }	
	public String getBuyukluk() {
        return this.buyukluk;
    }	
    
    @Override
    public String toString()
    {
        return buyukluk;
    }
}
