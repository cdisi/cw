package com.zk.cw.cihaz_url;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.zk.cw.uretici.Uretici;

public class CihazURLDAO {
    private static Connection conn = null;
    private static Statement stmt = null;
    
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
    
}
