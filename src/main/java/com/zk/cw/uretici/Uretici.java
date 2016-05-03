package com.zk.cw.uretici;

import com.zk.cw.exception.InvalidInputException;

public final class Uretici{
  // PRIVATE
  private String id;
  private final String ad;
  private final String logoUrl;
  private final String durum;
  private final String gsmArenaUrl;
  
  Uretici(
    String id, String ad, String logoUrl, String durum, String gsmArenaUrl
  ) throws InvalidInputException {
    this.id = id;
    this.ad = ad;
    this.logoUrl = logoUrl;
    this.durum = durum;
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
  String durumAl(){
	  return this.durum; 
  }
  String gsmArenaUrlAl(){
	  return this.gsmArenaUrl;
  }
  
}