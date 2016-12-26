package com.zk.cw.ozellik_atama;

public class OzellikAtama {
	
	private Integer id;
	private Integer cihazId;
	private Integer ozellikId;
	private String deger;
	
	public OzellikAtama(Integer id, Integer cihazId, Integer ozellikId, String deger){
		this.id=id;
		this.cihazId=cihazId;
		this.ozellikId=ozellikId;
		this.deger=deger;
	}
	
	public Integer getId(){
		return this.id;		
	}
	public Integer getCihazId(){
		return this.cihazId;		
	}
	public Integer getOzellikId(){
		return this.ozellikId;		
	}
	public String getDeger(){
		return this.deger;		
	}	
}
