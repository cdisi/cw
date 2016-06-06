package com.zk.cw.uretici;

import com.zk.cw.exception.InvalidInputException;
import com.zk.cw.main.MainWindow;
import com.zk.cw.util.Util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import org.ietf.jgss.GSSManager;

public final class UreticiDAO {
	
    private static Connection conn = null;
    private static Statement stmt = null;
    private static final Map<Integer, Uretici> fTable = new LinkedHashMap<>();
    private static int fNextId = 0;
    private static final String MOVIES_FILE_NAME = "movie_list_for_";
    private static final String DELIMITER = "|";
    private static final String NULL = "NULL";
    private static final Logger fLogger = Util.getLogger(UreticiDAO.class);
    private final static Charset ENCODING = StandardCharsets.UTF_8;
    
    static {
      bul();
      fLogger.config("Number of movies read in from file: " + fTable.size());
    }    

  public void shutdown() {
    fLogger.fine("Saving all movie records to file.");
    String fileContents = buildFileContents();
    writeStringToFile(fileContents);
  }

  void add(Uretici aMovie) {
    String id = nextId();
    aMovie.setId(id.toString());
    fTable.put(id, aMovie);
  }

  void change(Uretici aMovie) {
    fTable.put(aMovie.getId(), aMovie);
  }

  public List<Uretici> list() {
    List<Uretici> result = new ArrayList<>(fTable.values());
    Collections.sort(result);
    return result;
  }

  void delete(String aMovieId) {
    fTable.remove(aMovieId);
  }
  
  public static ArrayList<Uretici> bul(int ureticiId) {
	  Properties configProps = new Properties();
	  InputStream input = null;
	  ArrayList<Uretici> sonuc = null;
	  try{
		  input = new FileInputStream("resources/config.properties");
		  configProps.load(input);
		  Class.forName("com.mysql.jdbc.Driver");
	      conn = DriverManager.getConnection(configProps.getProperty("DB_URL"), configProps.getProperty("DB_USER"), configProps.getProperty("DB_PASS"));
	      stmt = conn.createStatement();
	      String sql= "SELECT * FROM uretici WHERE id="+ureticiId;
	      ResultSet rs = stmt.executeQuery(sql);	      
	      while(rs.next()){
	    	 Integer id = rs.getInt("id");
	    	 String ad = rs.getString("ad");
	    	 String logoUrl = rs.getString("logo_url");
		     int aktif = rs.getInt("aktif");
		     String gsmArenaUrl = rs.getString("gsm_arena_url");
		     Uretici uretici = new Uretici(id, ad, logoUrl, gsmArenaUrl, aktif);
		     sonuc = new ArrayList<Uretici>();
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
		  // load a properties file
		  configProps.load(input);
		  //System.out.println(configProps.getProperty("JDBC_DRIVER"));
		  Class.forName("com.mysql.jdbc.Driver");
	      conn = DriverManager.getConnection(configProps.getProperty("DB_URL"), configProps.getProperty("DB_USER"), configProps.getProperty("DB_PASS"));
	      //conn = DriverManager.getConnection("jdbc:mysql://cepworld.com/beta?useUnicode=true&characterEncoding=UTF-8","zihni", "nl2brr");
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
	      String sql= "SELECT * FROM uretici WHERE ad ='"+ uretici.adAl() +"'";
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

  private static String getMovieFileName() {
    return MOVIES_FILE_NAME + MainWindow.getInstance().getUserName().toLowerCase(Locale.ENGLISH) + ".txt";
  }

  private String buildFileContents() {
    String NEW_LINE = System.getProperty("line.separator");
    StringBuilder result = new StringBuilder();
    for (Uretici movie : fTable.values()) {
      appendTo(result, movie.getTitle(), DELIMITER);
      appendTo(result, movie.getDateViewed(), DELIMITER);
      appendTo(result, movie.getRating(), DELIMITER);
      appendTo(result, movie.getComment(), NEW_LINE);
    }
    return result.toString();
  }

  private void writeStringToFile(String aFileContents) {
    Path moviesPath = Paths.get(getMovieFileName());
    fLogger.fine("Writing movies to: " + moviesPath);
    try (BufferedWriter writer = Files.newBufferedWriter(moviesPath, ENCODING)){
      writer.write(aFileContents);
    }    
    catch (FileNotFoundException ex) {
      fLogger.severe("Cannot find movies text file.");
    }
    catch (IOException ex) {
      fLogger.severe("Problem while saving movies text file.");
    }
  }
}
