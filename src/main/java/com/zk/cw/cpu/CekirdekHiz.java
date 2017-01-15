package com.zk.cw.cpu;

public class CekirdekHiz {
	private Integer id;
    private String hiz;
    
    public CekirdekHiz() {
    }
    public CekirdekHiz(Integer id, String hiz) {
        this.id = id;
        this.hiz=hiz;
    }
	public void setId(Integer id) {
        this.id=id;
    }    
	public Integer getId() {
        return this.id;
    }
	public void setHiz(String hiz) {
        this.hiz=hiz;
    }	
	public String getHiz() {
        return this.hiz;
    }	
    
    @Override
    public String toString()
    {
        return hiz;
    }
}
