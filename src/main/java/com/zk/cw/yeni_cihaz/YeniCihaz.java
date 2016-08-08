package com.zk.cw.yeni_cihaz;

public class YeniCihaz {
	
	private Integer id;
	private Integer ureticiId;
	private String url;
	private Integer aktif;
	
	public YeniCihaz(Integer id, Integer ureticiId, String url, Integer aktif){
		this.id=id;
		this.ureticiId=ureticiId;
		this.url=url;
		this.aktif=aktif;
	}
	
	public Integer getId(){
		return this.id;
	}
	
	public void setId(Integer id){
		this.id=id;
	}
	
	public Integer getUreticiId(){
		return this.ureticiId;
	}
	
	public void setUreticiId(Integer ureticiId){
		this.ureticiId=ureticiId;
	}
	
	public String getUrl(){
		return this.url;
	}
	
	public void setUrl(String url){
		this.url=url;
	}

	public Integer getAktif(){
		return this.aktif;
	}
	
	public void setAktif(Integer aktif){
		this.aktif=aktif;
	}	
	
}
