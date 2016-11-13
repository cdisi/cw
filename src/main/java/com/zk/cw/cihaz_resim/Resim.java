package com.zk.cw.cihaz_resim;

public class Resim {
	
	private Integer fId;
	private byte[] fResim;
	private byte[] fKucukResim;
	private byte[] fBuyukResim;
	
	public Resim(){
		
	}
	public Resim(Integer aId, byte[] aKucukResim, byte[] aResim, byte[] aBuyukResim){
		fId=aId;
		fKucukResim=aKucukResim;
		fResim = aResim;
		fBuyukResim = aBuyukResim;
	}
	
	public void setId(Integer aId){
		fId=aId;
	}
	
	public Integer getId(){
		return fId;
	}
	
	public void setKucukResim(byte[] aKucukResim){
		fKucukResim = aKucukResim;
	}
	
	public byte[] getKucukResim(){
		return fKucukResim;
	}	
	public void setResim(byte[] aResim){
		fResim = aResim;
	}
	
	public byte[] getResim(){
		return fResim;
	}
	public void setBuyukResim(byte[] aBuyukResim){
		fBuyukResim = aBuyukResim;
	}
	
	public byte[] getBuyukResim(){
		return fBuyukResim;
	}	
	
}
