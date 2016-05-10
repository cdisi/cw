package com.zk.cw.uretici;

import com.zk.cw.exception.InvalidInputException;

public final class Uretici{
	  private String id;
	  private final String ad;
	  private String logoUrl;
	  private final int aktif;
	  private final String gsmArenaUrl;
	  
	  public Uretici(String id, String ad, String logoUrl, int aktif, String gsmArenaUrl) throws InvalidInputException {
	    this.id = id;
	    this.ad = ad;
	    this.logoUrl = logoUrl;
	    this.aktif = aktif;
	    this.gsmArenaUrl=gsmArenaUrl;
	  }
	  
	  String idAl(){ 
		  return id; 
	  }
	  void idVer(String id){
		  this.id = id; 
	  }
	  String adAl(){
		  return this.ad; 
	  }	
	  String logoUrlAl(){
		  return this.logoUrl; 
	  }
	  public void logoUrlVer(String logoUrl){
		  this.logoUrl=logoUrl ; 
	  }
	  int aktifAl(){
		  return this.aktif; 
	  }
	  String gsmArenaUrlAl(){
		  return this.gsmArenaUrl;
	  }
	  
}