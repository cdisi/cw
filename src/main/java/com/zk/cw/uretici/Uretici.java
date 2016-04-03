package com.zk.cw.uretici;

import java.math.BigDecimal;
import java.util.*;

import com.zk.cw.exception.InvalidInputException;
import com.zk.cw.util.Util;

public final class Uretici{
  // PRIVATE
  private String id;
  private final String ad;
  private final String logoUrl;
  private final String durum;
  private final String gsmArenaUrl;
  private static final int EQUAL = 0;
  private static final int DESCENDING = -1;
  
  Uretici(String id, String ad, String url, String logoUrl, String durum, String gsmArenaUrl) throws InvalidInputException {
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