package com.zk.cw.uretici;

import com.zk.cw.exception.InvalidInputException;
import com.zk.cw.util.Util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import com.zk.cw.dao_factory.*;
public final class UreticiDAO {
	
    private static Connection conn = null;
    private static Statement stmt = null;
    private static final Map<Integer, Uretici> fTable = new LinkedHashMap<>();
    private static int fNextId = 0;
    private static final String NULL = "NULL";
    private static final Logger fLogger = Util.getLogger(UreticiDAO.class);
    private final static Charset ENCODING = StandardCharsets.UTF_8;
	private static final String FIND_BY_ID = "SELECT * FROM uretici WHERE id = ?";		

    static {
      bul();
    }    

  public List<Uretici> list() {
    List<Uretici> result = new ArrayList<>(fTable.values());
    Collections.sort(result);
    return result;
  }

  void delete(String aMovieId) {
    fTable.remove(aMovieId);
  }
  
	public Uretici findById(Integer id) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_ID);
		pstmt.setInt(1, id);
		
		Uretici uretici = null;
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			try {
				uretici = new Uretici(rset.getInt("id"), rset.getString("ad"), rset.getString("logo_url"), rset.getString("gsm_arena_url"), rset.getInt("aktif"));
			} catch (InvalidInputException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		pstmt.close();
		c.close();

		return uretici;
	}
  
  public static ArrayList<Uretici> bul(int ureticiId) {
	  Properties configProps = new Properties();
	  InputStream input = null;
	  ArrayList<Uretici> sonuc = new ArrayList<Uretici>();
	  try{
		  input = new FileInputStream("resources/config.properties");
		  configProps.load(input);
		  Class.forName("com.mysql.jdbc.Driver");
	      conn = DriverManager.getConnection(configProps.getProperty("DB_URL"), configProps.getProperty("DB_USER"), configProps.getProperty("DB_PASS"));
	      stmt = conn.createStatement();
	      String sql= "SELECT * FROM uretici";
	      if(ureticiId != 0){
	    	  sql += " WHERE id="+ureticiId;
	      }
	      ResultSet rs = stmt.executeQuery(sql);	      
	      while(rs.next()){
	    	 Integer id = rs.getInt("id");
	    	 String ad = rs.getString("ad");
	    	 String logoUrl = rs.getString("logo_url");
		     int aktif = rs.getInt("aktif");
		     String gsmArenaUrl = rs.getString("gsm_arena_url");
		     Uretici uretici = new Uretici(id, ad, logoUrl, gsmArenaUrl, aktif);
		     sonuc.add(uretici);
	      }
	      rs.close();
	      stmt.close();
	      conn.close();
	   }catch(SQLException se){
	      se.printStackTrace();
	   }catch(Exception e){
	      e.printStackTrace();
	   }finally{
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){
	      }
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }
	   }
	  return sonuc;
  }  

  private static void bul() {
	  Properties configProps = new Properties();
	  InputStream input = null;
	  try{
		  input = new FileInputStream("resources/config.properties");
		  configProps.load(input);
		  Class.forName("com.mysql.jdbc.Driver");
	      conn = DriverManager.getConnection(configProps.getProperty("DB_URL"), configProps.getProperty("DB_USER"), configProps.getProperty("DB_PASS"));
	      stmt = conn.createStatement();
	      String sql= "SELECT * FROM uretici";
	      ResultSet rs = stmt.executeQuery(sql);	      
	      while(rs.next()){
	    	 Integer id = rs.getInt("id");
	    	 String ad = rs.getString("ad");
	    	 String logoUrl = rs.getString("logo_url");
		     int aktif = rs.getInt("aktif");
		     String gsmArenaUrl = rs.getString("gsm_arena_url");
		     Uretici uretici = new Uretici(id, ad, logoUrl, gsmArenaUrl, aktif);
		     fTable.put(uretici.idAl(), uretici);
	      }
	      rs.close();
	      stmt.close();
	      conn.close();
	   }catch(SQLException se){
	      se.printStackTrace();
	   }catch(Exception e){
	      e.printStackTrace();
	   }finally{
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){
	      }
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }
	   }
  }
  
  public static boolean bul(Uretici uretici) {
	  Properties configProps = new Properties();
	  InputStream input = null;
	  boolean donus = false;
	  try{
		  input = new FileInputStream("resources/config.properties");
		  configProps.load(input);
		  Class.forName("com.mysql.jdbc.Driver");
	      conn = DriverManager.getConnection(configProps.getProperty("DB_URL"), configProps.getProperty("DB_USER"), configProps.getProperty("DB_PASS"));
	      stmt = conn.createStatement();
	      String sql= "SELECT * FROM uretici";
	      if(uretici != null)
	    	  sql += " WHERE ad ='"+ uretici.adAl() +"'";
	      ResultSet rs = stmt.executeQuery(sql);	      
	      while(rs.next()){
	    	  donus = true;
	      }
	      rs.close();
	      stmt.close();
	      conn.close();
	   }catch(SQLException se){
	      se.printStackTrace();
	   }catch(Exception e){
	      e.printStackTrace();
	   }finally{
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){
	      }
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }
	   }
	   return donus;
  }
  
  public static void ekle(Uretici uretici) {
	  Properties configProps = new Properties();
	  InputStream input = null;	  
	  try{
		  input = new FileInputStream("resources/config.properties");
		  configProps.load(input);
		  Class.forName("com.mysql.jdbc.Driver");
	      conn = DriverManager.getConnection(configProps.getProperty("DB_URL"), configProps.getProperty("DB_USER"), configProps.getProperty("DB_PASS"));
	      PreparedStatement pstmt = conn.prepareStatement("INSERT INTO uretici (ad, logo_url, gsm_arena_url, aktif) VALUES (?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
	      pstmt.setString(1, uretici.adAl());
	      pstmt.setString(2, uretici.logoUrlAl());
	      pstmt.setString(3, uretici.gsmArenaUrlAl());
	      pstmt.setInt(4, uretici.aktifAl());
	      pstmt.executeUpdate();
	      conn.close();
	   }catch(SQLException se){
	      se.printStackTrace();
	   }catch(Exception e){
	      e.printStackTrace();
	   }finally{
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){
	      }
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }
	   }
  }    

  private static String nextId() {
    ++fNextId;
    return String.valueOf(fNextId);
  }

  private void appendTo(StringBuilder aText, Object aField, String aAppend) {
    if (Util.textHasContent(Util.format(aField))) {
      aText.append(Util.format(aField));
    }
    else {
      aText.append(NULL);
    }
    aText.append(aAppend);
  }

  private static String maybeNull(String aText) {
    return NULL.equals(aText) ? null : aText;
  }

}
