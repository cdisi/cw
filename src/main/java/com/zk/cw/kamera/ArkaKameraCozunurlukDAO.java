package com.zk.cw.kamera;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.zk.cw.dao_factory.DaoFactory;
import com.zk.cw.ozellik_atama.OzellikAtama;

public class ArkaKameraCozunurlukDAO {
	private static final String FIND_BY_ID = "SELECT * FROM arka_kamera_cozunurluk WHERE id = ?";		
	private static final String INSERT = "INSERT INTO arka_kamera_cozunurluk (cozunurluk) VALUES (?)";
	private static final String FIND_BY_NAME = "SELECT * FROM arka_kamera_cozunurluk WHERE cozunurluk = ?";	
	private static final String ALL = "SELECT * FROM arka_kamera_cozunurluk ORDER BY cozunurluk";
	
	public static ArkaKameraCozunurluk findBy(ArkaKameraCozunurluk arkaKamera) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_NAME);
		pstmt.setString(1, arkaKamera.getCozunurluk());
		
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			arkaKamera.setId(rset.getInt("id"));
			arkaKamera.setCozunurluk(rset.getString("cozunurluk"));
		}

		pstmt.close();
		c.close();

		return arkaKamera;
	}	
	
	public static ArkaKameraCozunurluk findById(ArkaKameraCozunurluk arkaKamera) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_ID);
		pstmt.setInt(1, arkaKamera.getId());
		
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			arkaKamera.setId(rset.getInt("id"));
			arkaKamera.setCozunurluk(rset.getString("cozunurluk"));
		}

		pstmt.close();
		c.close();

		return arkaKamera;
	}	
	
	public static ArkaKameraCozunurluk add(ArkaKameraCozunurluk arkaKamera) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, arkaKamera.getCozunurluk());
		
		pstmt.executeUpdate();
		ResultSet rset = pstmt.getGeneratedKeys();

		rset.next();
		Integer idGenerated = rset.getInt(1);
		arkaKamera.setId(idGenerated);		

		pstmt.close();
		c.close();
		
		return arkaKamera;
	}
	
	public static List<ArkaKameraCozunurluk> all() throws SQLException {
		Connection c = DaoFactory.openConnection();
		PreparedStatement pstmt = c.prepareStatement(ALL);

		ResultSet rset = pstmt.executeQuery();
		List<ArkaKameraCozunurluk> kamler = new ArrayList<ArkaKameraCozunurluk>();
		while (rset.next()){
			ArkaKameraCozunurluk arkaKamera = new ArkaKameraCozunurluk(rset.getInt("id"), rset.getString("cozunurluk"));
			kamler.add(arkaKamera);
		}

		pstmt.close();
		c.close();
		return kamler;
	}
	
}

