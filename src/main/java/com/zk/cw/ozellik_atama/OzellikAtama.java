package com.zk.cw.ozellik_atama;

public class OzellikAtama {
	
	private Integer id;
	private Integer cihazId;
	private Integer ozellikId;
	private Integer kategoriId;
	private String deger;
	
	public OzellikAtama(Integer id, Integer cihazId, Integer kategoriId,Integer ozellikId, String deger){
		this.id=id;
		this.cihazId=cihazId;
		this.ozellikId=ozellikId;
		this.kategoriId = kategoriId;
		this.deger=deger;
	}
	
	public Integer getId(){
		return this.id;		
	}
	public void setId(Integer id){
		this.id=id;		
	}
	public Integer getCihazId(){
		return this.cihazId;		
	}
	public Integer getOzellikId(){
		return this.ozellikId;		
	}
	public void setOzellikId(Integer ozellikId){
		this.ozellikId=ozellikId;		
	}
	public Integer getKategoriId(){
		return this.kategoriId;		
	}	
	public String getDeger(){
		return this.deger;		
	}	
	public void setDeger(String deger){
		this.deger=deger;		
	}	
}
