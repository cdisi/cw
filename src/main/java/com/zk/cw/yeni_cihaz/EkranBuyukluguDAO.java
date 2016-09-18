package com.zk.cw.yeni_cihaz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zk.cw.dao_factory.DaoFactory;


public class EkranBuyukluguDAO {
	private static final String
	INSERT = "INSERT INTO cihaz_ekran_buyuklugu (buyukluk) VALUES (?)";
	private static final String
	FIND = "SELECT * FROM cihaz_ekran_buyuklugu WHERE buyukluk = ?";
	
	public static void insert(String buyukluk) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(INSERT);

		pstmt.setString(1, buyukluk);
		pstmt.executeUpdate();

		pstmt.close();
		c.close();
		
	}
	
	
	public static Boolean find(String buyukluk) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND);
		pstmt.setString(1, buyukluk);
		ResultSet rset = pstmt.executeQuery();
		Boolean sonuc = false; 
		while (rset.next()){
			sonuc=true;
		}

		pstmt.close();
		c.close();

		return sonuc;
	}
}
