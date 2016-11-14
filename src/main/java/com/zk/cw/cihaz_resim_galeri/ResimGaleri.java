package com.zk.cw.cihaz_resim_galeri;

public class ResimGaleri {
	private Integer fId;
	private Integer fCihazId;
	private byte[] fKucukResim;
	private byte[] fOrtaResim;
	private byte[] fBuyukResim;
	
	public ResimGaleri(){
		
	}
	
	public void setId(Integer aId){
		fId=aId;
	}
	
	public Integer getId(){
		return fId;
	}
	
	public void setCihazId(Integer aCihazId){
		fCihazId=aCihazId;
	}
	
	public Integer getCihazId(){
		return fCihazId;
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
