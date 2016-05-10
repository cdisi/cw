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
    private static final Map<String, Uretici> fTable = new LinkedHashMap<>();
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

  /**
    Save all data to a text file. Must be called explicitly when the
    app shuts down, in order to save all edits.
  */
  public void shutdown() {
    fLogger.fine("Saving all movie records to file.");
    String fileContents = buildFileContents();
    writeStringToFile(fileContents);
  }

  /** Add a new {@link Uretici}. */
  void add(Uretici aMovie) {
    String id = nextId();
    aMovie.setId(id.toString());
    fTable.put(id, aMovie);
  }

  /** Change an existing {@link Uretici}. */
  void change(Uretici aMovie) {
    fTable.put(aMovie.getId(), aMovie);
  }

  /**
   * List all {@link Uretici}s. Order is the natural order of the {@link Uretici} class
   * (descending date, then title).
   */
  List<Uretici> list() {
    List<Uretici> result = new ArrayList<>(fTable.values());
    Collections.sort(result);
    return result;
  }

  /** Delete an existing {@link Uretici}, given the movie id. */
  void delete(String aMovieId) {
    fTable.remove(aMovieId);
  }

  private static void bul() {
	  Properties configProps = new Properties();
	  InputStream input = null;
	  try{
		  input = new FileInputStream("resources/config.properties");
		  // load a properties file
		  configProps.load(input);
		  System.out.println(configProps.getProperty("JDBC_DRIVER"));
		  Class.forName("com.mysql.jdbc.Driver");
	      conn = DriverManager.getConnection(configProps.getProperty("DB_URL"), configProps.getProperty("DB_USER"), configProps.getProperty("DB_PASS"));
	      //conn = DriverManager.getConnection("jdbc:mysql://cepworld.com/beta?useUnicode=true&characterEncoding=UTF-8","zihni", "nl2brr");
	      stmt = conn.createStatement();
	      String sql= "SELECT * FROM uretici";
	      ResultSet rs = stmt.executeQuery(sql);	      
	      while(rs.next()){
	    	 String id = rs.getString("id");
	    	 String ad = rs.getString("ad");
	    	 String baslik = rs.getString("baslik");
	    	 String url = rs.getString("url");
	    	 String logoUrl = rs.getString("logo_url");
		     String durum = rs.getString("durum");
		     String gsmArenaUrl = rs.getString("gsm_arena_url");
		     Uretici uretici = new Uretici(id, ad, baslik, url, logoUrl, durum, gsmArenaUrl);
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
		  System.out.println(configProps.getProperty("JDBC_DRIVER"));
		  Class.forName("com.mysql.jdbc.Driver");
	      conn = DriverManager.getConnection(configProps.getProperty("DB_URL"), configProps.getProperty("DB_USER"), configProps.getProperty("DB_PASS"));
	      stmt = conn.createStatement();
	      String sql= "SELECT * FROM uretici WHERE ad ='"+ uretici.adAl()+"'";
	      ResultSet rs = stmt.executeQuery(sql);	      
	      while(rs.next()){
	    	  donus= true;
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
	      PreparedStatement pstmt = conn.prepareStatement("INSERT INTO uretici (ad, logoUrl, gsmArenaUrl, Aktif) VALUES (?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
	      pstmt.setString(1, uretici.adAl());
	      pstmt.setString(2, uretici.logoUrlAl());
	      pstmt.setString(3,uretici.gsmArenaUrlAl());
	      pstmt.setInt(4,uretici.aktifAl());
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
  

  private static void parseLine(String aLine) throws InvalidInputException {
    Scanner scanner = new Scanner(aLine);
    // note how the quoting is needed here, since '|' is a special character in
    // regular expressions :
    scanner.useDelimiter(Pattern.quote(DELIMITER));
    scanner.useLocale(Locale.US);
    if (scanner.hasNext()) {
      String title = scanner.next();
      Date viewed = Util.parseDate(maybeNull(scanner.next()), "Date Viewed");
      BigDecimal rating = Util.parseBigDecimal(maybeNull(scanner.next()), "Rating");
      String comment = maybeNull(scanner.next());
      Uretici movie = new Uretici(nextId().toString(), title, viewed, rating, comment);
      fTable.put(movie.getId(), movie);
    }
    scanner.close();
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

  /** Create a string, holding all movie records. */
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

  /** Write string containing all movie records to a file - overwrite the whole file. */
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
