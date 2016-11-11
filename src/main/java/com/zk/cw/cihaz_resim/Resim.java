package com.zk.cw.cihaz_resim;

public class Resim {
	
	private Integer fId;
	private byte[] fKucukResim;
	private byte[] fOrtaResim;
	private byte[] fBuyukResim;
	
	public Resim(Integer aId, byte[] aKucukResim,byte[] aOrtaResim,byte[] aBuyukResim){
		fId=aId;
		fKucukResim=aKucukResim;
		fOrtaResim = aOrtaResim;
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
	public void setOrtaResim(byte[] aOrtaResim){
		fOrtaResim = aOrtaResim;
	}
	
	public byte[] getOrtaResim(){
		return fOrtaResim;
	}
	public void setBuyukResim(byte[] aBuyukResim){
		fBuyukResim = aBuyukResim;
	}
	
	public byte[] getBuyukResim(){
		return fBuyukResim;
	}	
	
}
