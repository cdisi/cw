package com.zk.cw.yeni_cihaz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zk.cw.dao_factory.DaoFactory;

public class CihazOSSurum {
	private static final String
	INSERT = "INSERT INTO os_surum (os_id,ad) VALUES (?,?)";
	private static final String
	FIND = "SELECT * FROM os_surum WHERE os_id=? AND ad = ?";
	
	public static String insert(String osId, String ad) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

		pstmt.setString(1, osId);
		pstmt.setString(2, ad);
		pstmt.executeUpdate();

		ResultSet rset = pstmt.getGeneratedKeys();
		rset.next();
		String idGenerated = rset.getString(1);
		

		pstmt.close();
		c.close();
		return idGenerated;
		
	}
	
	
	public static String find(String osId, String ad) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND);
		pstmt.setString(1, osId);
		pstmt.setString(2, ad);
		ResultSet rset = pstmt.executeQuery();
		String sonuc = null; 
		while (rset.next()){
			sonuc=rset.getString(1);
		}

		pstmt.close();
		c.close();

		return sonuc;
	}
}
