package com.zk.cw.batarya;

public class BataryaKapasite {
	private Integer id;
    private String kapasite;
    
    public BataryaKapasite() {
    }
    public BataryaKapasite(Integer id, String kapasite) {
        this.id = id;
        this.kapasite = kapasite;
    }
	public void setId(Integer id) {
        this.id=id;
    }    
	public Integer getId() {
        return this.id;
    }
	public void setKapasite(String kapasite) {
        this.kapasite=kapasite;
    }	
	public String getKapasite() {
        return this.kapasite;
    }	
    
    @Override
    public String toString()
    {
        return kapasite;
    }
}
