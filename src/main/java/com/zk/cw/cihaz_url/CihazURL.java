package com.zk.cw.cihaz_url;

public class CihazURL {
	private Integer id;
	private Integer ureticiId;
	private String url;
	private Integer aktif;
	
	public void setId(Integer id){
		this.id=id;
	}
	
	public Integer getId(){
		return this.id;
	}	
	
	public void setUreticiId(Integer ureticiId){
		this.ureticiId=ureticiId;
	}
	
	public Integer setUreticiId(){
		return this.ureticiId;
	}	
	
	public void setUrl(String url){
		this.url=url;
	}
	
	public String getUrl(){
		return this.url;
	}	
	
	public void setAktif(Integer aktif){
		this.aktif=aktif;
	}
	
	public Integer getAktif(){
		return this.aktif;
	}	
	
}
