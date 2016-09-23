package com.zk.cw.yeni_cihaz;

public class Cihaz {

	private Integer id;
	private String ad;
	private String digerAd;
	private Integer ureticiId;
	private byte[] resim;
	private String ikiGBant;
	private String duyurulma;
	private String duyurulmaYil;
	private String duyurulmaAy;
	private String sim;
	private Integer turu;
	
	public Cihaz(){
	}	
	
	public Cihaz(String ad){
		this.ad=ad;
	}
	
	
	public Integer getId(){
		return this.id;
	}
	
	public void setId(Integer id){
		this.id=id;
	}	
	
	public String getAd(){
		return this.ad;
	}
	
	public void setAd(String ad){
		this.ad=ad;
	}
	
	public String getDigerAd(){
		return this.digerAd;
	}
	
	public void setDigerAd(String ad){
		this.digerAd=ad;
	}	
	
	public Integer getUreticiId(){
		return this.ureticiId;
	}
	
	public void setUreticiId(Integer ureticiId){
		this.ureticiId=ureticiId;
	}
	
	public byte[] getResim(){
		return this.resim;
	}
	
	public void setResim(byte[] resim){
		this.resim=resim;
	}
	
	public String getIkiGBant(){
		return ikiGBant;
	}
	
	public void setIkiGBant(String ikiGBant){
		this.ikiGBant=ikiGBant;
	}
	
	public void setDuyurulmaYil(String y){
		this.duyurulmaYil=y;
	}
	
	public String getDuyurulmaYil(){
		return this.duyurulmaYil;
	}
	
	public void setDuyurulmaAy(String a){
		this.duyurulmaAy=a;
	}
	
	public String getDuyurulmaAy(){
		return this.duyurulmaAy;
	}
	
	public void setDuyurulma(String d){
		this.duyurulma=d;
	}
	
	public String getDuyurulma(){
		return this.duyurulma;
	}
	
	public void setSim(String s){
		this.sim=s;
	}
	
	public String getSim(){
		return this.sim;
	}
	public void setTuru(Integer s){
		this.turu=s;
	}
	
	public Integer getTuru(){
		return this.turu;
	}		
}
