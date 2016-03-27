package com.zk.cw.uretici;

import java.math.BigDecimal;
import java.util.*;

import com.zk.cw.exception.InvalidInputException;
import com.zk.cw.util.Util;

public final class Uretici implements Comparable<Uretici>{
  // PRIVATE
  private String id;
  private final String ad;
  private final String url;
  private final String logoUrl;
  private final String durum;
  private final String gsmArenaUrl;
  private static final int EQUAL = 0;
  private static final int DESCENDING = -1;

  
  Uretici(
    String id, String ad, String url, String logoUrl, String durum, String gsmArenaUrl
  ) throws InvalidInputException {
    this.id = id;
    this.ad = ad;
    this.url=url;
    this.logoUrl = logoUrl;
    this.durum = durum;
    this.gsmArenaUrl=gsmArenaUrl;
    validateState();
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

  String urlAl(){
	  return this.url;
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

  
  @Override public boolean equals(Object aThat){
    if ( this == aThat ) return true;
    if ( !(aThat instanceof Uretici) ) return false;
    Uretici that = (Uretici)aThat;
    return 
      areEqual(this.fTitle, that.fTitle) && 
      areEqual(this.fDateViewed, that.fDateViewed) && 
      areEqual(this.fRating, that.fRating) && 
      areEqual(this.fComment, that.fComment)
    ; 
  }
  
  @Override public int hashCode(){
    int result = 17;
    result = addHash(result, fTitle);
    result = addHash(result, fDateViewed);
    result = addHash(result, fRating);
    result = addHash(result, fComment);
    return result;
  }
  
  @Override public String toString(){
    return 
      "Movie  Id:" + fId + " Title:" + fTitle + " Date Viewed:" + fDateViewed + 
      " Rating:" + fRating + " Comment: " + fComment
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
  public static Comparator<Uretici> TITLE_SORT = new Comparator<Uretici>(){
    @Override public int compare(Uretici aThis, Uretici aThat) {
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
  public static Comparator<Uretici> RATING_SORT = new Comparator<Uretici>(){
    @Override public int compare(Uretici aThis, Uretici aThat) {
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
  public static Comparator<Uretici> COMMENT_SORT = new Comparator<Uretici>(){
    @Override public int compare(Uretici aThis, Uretici aThat) {
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

  
  private void validateState() throws InvalidInputException {
    InvalidInputException ex = new InvalidInputException();
    
    if( ! Util.textHasContent(fTitle) ) {
      ex.add("Title must have content");
    }
    if ( fRating != null ){
      if ( fRating.compareTo(BigDecimal.ZERO) < 0 ) {
        ex.add("Rating cannot be less than 0.");
      }
      if ( fRating.compareTo(TEN) > 0 ) {
        ex.add("Rating cannot be greater than 10.");
      }
    }
    if ( ex.hasErrors() ) {
      throw ex;
    }
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