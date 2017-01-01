package com.zk.cw.ekran;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zk.cw.dao_factory.DaoFactory;

public class EkranCozunurlukDAO {
	
	private static final String FIND_BY_COZUNURLUK = "SELECT * FROM ekran_cozunurluk WHERE cozunurluk = ?";	
	private static final String FIND_BY_ID = "SELECT * FROM ekran_cozunurluk WHERE id = ?";	
	private static final String INSERT = "INSERT INTO ekran_cozunurluk (cozunurluk) VALUES (?)";
	private static final String ALL = "SELECT * FROM ekran_cozunurluk ORDER BY cozunurluk";

	public EkranCozunurluk findBy(String cozunurluk) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_COZUNURLUK);
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
	
	public EkranCozunurluk findBy(Integer id) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_ID);
		pstmt.setInt(1, id);
		
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
	
	public List<EkranCozunurluk> all() throws SQLException {
		Connection c = DaoFactory.openConnection();
		PreparedStatement pstmt = c.prepareStatement(ALL);

		ResultSet rset = pstmt.executeQuery();
		List<EkranCozunurluk> ekranCozunurlukleri = new ArrayList<EkranCozunurluk>();
		while (rset.next()){
			EkranCozunurluk ekranCozunurluk = new EkranCozunurluk(rset.getInt("id"), rset.getString("cozunurluk"));
					
			ekranCozunurlukleri.add(ekranCozunurluk);
		}

		pstmt.close();
		c.close();
		return ekranCozunurlukleri;
	}	
}
