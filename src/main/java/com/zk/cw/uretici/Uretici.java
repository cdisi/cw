package com.zk.cw.uretici;

import java.util.Comparator;

import com.zk.cw.edit.Movie;
import com.zk.cw.exception.InvalidInputException;

public final class Uretici implements Comparable<Uretici>{
	  private Integer id;
	  private String ad;
	  private String logoUrl;
	  private String gsmArenaUrl;
	  private Integer aktif;
	  private static final int EQUAL = 0;
	  private static final int DESCENDING = -1;	  
	  
	  public Uretici(Integer id, String ad, String logoUrl, String gsmArenaUrl,int aktif) throws InvalidInputException {
	    this.id = id;
	    this.ad = ad;
	    this.logoUrl = logoUrl;
	    this.aktif = aktif;
	    this.gsmArenaUrl=gsmArenaUrl;
	  }
	  
	  public Uretici() {
		// TODO Auto-generated constructor stub
	}

	public Integer idAl(){ 
		  return id; 
	  }
	  void idVer(Integer id){
		  this.id = id; 
	  }
	  public String adAl(){
		  return this.ad; 
	  }	
	  public String logoUrlAl(){
		  return this.logoUrl; 
	  }
	  public void logoUrlVer(String logoUrl){
		  this.logoUrl=logoUrl ; 
	  }
	  public int aktifAl(){
		  return this.aktif; 
	  }
	  public String gsmArenaUrlAl(){
		  return this.gsmArenaUrl;
	  }
	  
	  @Override public boolean equals(Object aThat){
		    if ( this == aThat ) return true;
		    if ( !(aThat instanceof Uretici) ) return false;
		    Uretici that = (Uretici)aThat;
		    return 
		      areEqual(this.id, that.id) && 
		      areEqual(this.ad, that.ad) && 
		      areEqual(this.logoUrl, that.logoUrl) && 
		      areEqual(this.aktif, that.aktif)
		    ; 
	  }
		  
	 @Override public int hashCode(){
		    int result = 17;
		    result = addHash(result, id);
		    result = addHash(result, ad);
		    result = addHash(result, logoUrl);
		    result = addHash(result, aktif);
		    return result;
	 }
		  
	 @Override public String toString(){
		    return 
		      "Uretici  Id:" + this.id + " Ad:" + this.ad + " Logo url:" + this.logoUrl + 
		      "GSM Arena URL:"+this.gsmArenaUrl+" Aktif:" + this.aktif
		    ; 
	 }
		  
	/** 
	   Default sort by Date Viewed, then Title. 
	   Dates have the most recent items listed first. 
	*/
	@Override public int compareTo(Uretici aThat) {
		    if ( this == aThat ) return EQUAL;
		   
		    int comparison = this.id.compareTo(aThat.id);
		    if ( comparison != EQUAL ) return comparison;
		    
		    comparison = this.ad.compareTo(aThat.ad);
		    if ( comparison != EQUAL ) return comparison;
		    
		    comparison = this.logoUrl.compareTo(aThat.ad);
		    if ( comparison != EQUAL ) return comparison;
		   
		    comparison = this.aktif.compareTo(aThat.aktif);
		    if ( comparison != EQUAL ) return comparison;
		    
		    return EQUAL;
		  }
		  
		  private boolean areEqual(Object aThis, Object aThat){
			    return aThis == null ? aThat == null : aThis.equals(aThat);
		  }
			  
		  private int addHash(int aHash, Object aField){
			    int result = 37*aHash;
			    if (aField != null){
			      result = result + aField.hashCode();
			    }
			    return result;
		  }	
		  /** Utility method.  */
		  private static <T extends Comparable<T>> int comparePossiblyNull(T aThis, T aThat){
		    int result = EQUAL;
		    int BEFORE = -1;
		    int AFTER = 1;
		    
		    if(aThis != null && aThat != null){ 
		      result = aThis.compareTo(aThat);
		    }
		    else {
		      //at least one reference is null - special handling
		      if(aThis == null && aThat == null) {
		        //do nothing - they are not distinct 
		      }
		      else if(aThis == null && aThat != null) {
		        result = BEFORE;
		      }
		      else if( aThis != null && aThat == null) {
		        result = AFTER;
		      }
		    }
		    return result;
		  }		  
}