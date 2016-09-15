package com.zk.cw.yeni_cihaz;

public class CihazOzellikAtama {
	public Integer ozellikKategoriId;
	public Integer ozellikId;
	public String deger;
	
	public CihazOzellikAtama(Integer ozellikKategoriId, Integer ozellikId, String deger){
		this.ozellikKategoriId=ozellikKategoriId;
		this.ozellikId=ozellikId;
		this.deger=deger;
	}
	public Integer getOzellikKategoriId(){
		return this.ozellikKategoriId;
	}	
	
	public Integer getOzellikId(){
		return this.ozellikId;
	}
	
	public String getDeger(){
		return this.deger;
	}
}
