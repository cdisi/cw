package com.zk.cw.cihaz_url;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.zk.cw.dao_factory.DaoFactory;
import com.zk.cw.uretici.Uretici;

public class CihazURLDAO {
    private static Connection conn = null;
    private static Statement stmt = null;
    
    public static boolean bul(CihazURL cihazURL) {
  	  boolean donus = false;
  	  try{
	      Connection conn = DaoFactory.openConnection();
  	      stmt = conn.createStatement();
  	      String sql= "SELECT * FROM cihaz_url WHERE url ='"+ cihazURL.getUrl() +"'";
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
    
    public static void ekle(CihazURL cihazURL,Uretici uretici) {
  	  try{
	      Connection conn = DaoFactory.openConnection();
  	      PreparedStatement pstmt = conn.prepareStatement("INSERT INTO cihaz_url (uretici_id, url) VALUES (?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
  	      pstmt.setInt(1, uretici.idAl());
  	      pstmt.setString(2, cihazURL.getUrl());
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
    
}
