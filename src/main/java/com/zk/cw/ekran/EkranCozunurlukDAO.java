package com.zk.cw.ekran;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zk.cw.dao_factory.DaoFactory;

public class EkranCozunurlukDAO {
	
	private static final String FIND_BY_NAME = "SELECT * FROM ekran_cozunurluk WHERE cozunurluk = ?";	
	private static final String INSERT = "INSERT INTO ekran_cozunurluk (cozunurluk) VALUES (?)";

	public EkranCozunurluk findByName(String cozunurluk) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_NAME);
		pstmt.setString(1, cozunurluk);
		
		EkranCozunurluk ekranCozunurluk = null;
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			ekranCozunurluk = new EkranCozunurluk(rset.getInt("id"), rset.getString("cozunurluk"));
		}

		pstmt.close();
		c.close();

		return ekranCozunurluk;
	}
	
	public EkranCozunurluk add(EkranCozunurluk ekranCozunurluk) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, ekranCozunurluk.getCozunurluk());
		
		pstmt.executeUpdate();
		ResultSet rset = pstmt.getGeneratedKeys();

		rset.next();
		Integer idGenerated = rset.getInt(1);
		ekranCozunurluk.setId(idGenerated);		

		pstmt.close();
		c.close();
		
		return ekranCozunurluk;
	}	
}
