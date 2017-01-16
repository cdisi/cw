package com.zk.cw.kamera;

public class ArkaKameraCozunurluk {
	private Integer id;
    private String cozunurluk;
    
    public ArkaKameraCozunurluk() {
    }
    public ArkaKameraCozunurluk(Integer id, String cozunurluk) {
        this.id = id;
        this.cozunurluk = cozunurluk;
    }
	public void setId(Integer id) {
        this.id=id;
    }    
	public Integer getId() {
        return this.id;
    }
	public void setCozunurluk(String cozunurluk) {
        this.cozunurluk=cozunurluk;
    }	
	public String getCozunurluk() {
        return this.cozunurluk;
    }	
    @Override
    public String toString()
    {
        return cozunurluk;
    }
}
