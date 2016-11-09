package com.zk.cw.cihaz_resim;

public class Resim {
	
	private Integer fId;
	private byte[] fResim;
	
	public Resim(Integer aId, byte[] aResim){
		fId=aId;
		fResim=aResim;
	}
	
	public void setId(Integer aId){
		fId=aId;
	}
	
	public Integer getId(){
		return fId;
	}
	
	public void setResim(byte[] aResim){
		fResim = aResim;
	}
	
	public byte[] getResim(){
		return fResim;
	}	
	
}
