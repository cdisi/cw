package com.zk.cw.uretici;

import java.util.Comparator;

import com.zk.cw.edit.Movie;
import com.zk.cw.exception.InvalidInputException;

public final class Uretici implements Comparable<Uretici>{
	  private String id;
	  private final String ad;
	  private String logoUrl;
	  private final int aktif;
	  private final String gsmArenaUrl;
	  private static final int EQUAL = 0;
	  private static final int DESCENDING = -1;	  
	  
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
		      " Aktif:" + this.aktif
		    ; 
	 }
		  
	/** 
	   Default sort by Date Viewed, then Title. 
	   Dates have the most recent items listed first. 
	*/
	@Override public int compareTo(Uretici aThat) {
		    if ( this == aThat ) return EQUAL;
		   
		    int comparison = DESCENDING*comparePossiblyNull(this.fDateViewed, aThat.fDateViewed);
		    if ( comparison != EQUAL ) return comparison;
		    
		    comparison = this.fTitle.compareTo(aThat.fTitle);
		    if ( comparison != EQUAL ) return comparison;
		    
		    comparison = comparePossiblyNull(this.fRating, aThat.fRating);
		    if ( comparison != EQUAL ) return comparison;
		   
		    comparison = comparePossiblyNull(this.fComment, aThat.fComment);
		    if ( comparison != EQUAL ) return comparison;
		    
		    return EQUAL;
		  }
		  
		  /** Sort by Title. */
		  public static Comparator<Movie> TITLE_SORT = new Comparator<Movie>(){
		    @Override public int compare(Movie aThis, Movie aThat) {
		      if ( aThis == aThat ) return EQUAL;

		      int comparison = aThis.fTitle.compareTo(aThat.fTitle);
		      if ( comparison != EQUAL ) return comparison;
		      
		      comparison = DESCENDING*comparePossiblyNull(aThis.fDateViewed, aThat.fDateViewed);
		      if ( comparison != EQUAL ) return comparison;
		      
		      comparison = comparePossiblyNull(aThis.fRating, aThat.fRating);
		      if ( comparison != EQUAL ) return comparison;
		     
		      comparison = comparePossiblyNull(aThis.fComment, aThat.fComment);
		      if ( comparison != EQUAL ) return comparison;
		      
		      return EQUAL;
		    };
		  };
		  
		  /** Sort by Rating (descending), then Date Viewed (descending). */
		  public static Comparator<Movie> RATING_SORT = new Comparator<Movie>(){
		    @Override public int compare(Movie aThis, Movie aThat) {
		      if ( aThis == aThat ) return EQUAL;

		      int comparison = DESCENDING*comparePossiblyNull(aThis.fRating, aThat.fRating);
		      if ( comparison != EQUAL ) return comparison;

		      comparison = DESCENDING*comparePossiblyNull(aThis.fDateViewed, aThat.fDateViewed);
		      if ( comparison != EQUAL ) return comparison;
		      
		      comparison = aThis.fTitle.compareTo(aThat.fTitle);
		      if ( comparison != EQUAL ) return comparison;
		      
		      comparison = comparePossiblyNull(aThis.fComment, aThat.fComment);
		      if ( comparison != EQUAL ) return comparison;
		      
		      return EQUAL;
		    };
		  };
		  
		  /** Sort by Comment. */
		  public static Comparator<Movie> COMMENT_SORT = new Comparator<Movie>(){
		    @Override public int compare(Movie aThis, Movie aThat) {
		      if ( aThis == aThat ) return EQUAL;

		      int comparison = comparePossiblyNull(aThis.fComment, aThat.fComment);
		      if ( comparison != EQUAL ) return comparison;
		      
		      comparison = aThis.fTitle.compareTo(aThat.fTitle);
		      if ( comparison != EQUAL ) return comparison;
		      
		      comparison = comparePossiblyNull(aThis.fRating, aThat.fRating);
		      if ( comparison != EQUAL ) return comparison;

		      comparison = DESCENDING*comparePossiblyNull(aThis.fDateViewed, aThat.fDateViewed);
		      if ( comparison != EQUAL ) return comparison;
		      
		      return EQUAL;
		    };
		  };
		  
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