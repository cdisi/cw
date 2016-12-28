package com.zk.cw.ekran;

public class EkranGenisligi {
    private Integer id;
    private String genislik;
    
    public EkranGenisligi() {
    }
    public EkranGenisligi(Integer id, String genislik) {
        this.id = id;
        this.genislik = genislik;
    }
	public void setId(Integer id) {
        this.id=id;
    }    
	public Integer getId() {
        return this.id;
    }
	public void setGenislik(String genislik) {
        this.genislik=genislik;
    }	
	public String getGenislik() {
        return this.genislik;
    }	
    
    @Override
    public String toString()
    {
        return genislik;
    } 
}
