package com.zk.cw.kamera;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zk.cw.dao_factory.DaoFactory;

public class DiyaframDAO {
	private static final String FIND_BY_ID = "SELECT * FROM kamera_diyafram_acikligi WHERE id = ?";		
	private static final String INSERT = "INSERT INTO kamera_diyafram_acikligi (aciklik) VALUES (?)";
	private static final String FIND_BY_NAME = "SELECT * FROM kamera_diyafram_acikligi WHERE aciklik = ?";	
	private static final String ALL = "SELECT * FROM kamera_diyafram_acikligi ORDER BY aciklik";

	
	public static Diyafram findBy(Diyafram diyafram) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_NAME);
		pstmt.setString(1, diyafram.getAciklik());
		
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			diyafram.setId(rset.getInt("id"));
			diyafram.setAciklik(rset.getString("aciklik"));
		}

		pstmt.close();
		c.close();

		return diyafram;
	}	
	
	public static Diyafram findById(Diyafram diyafram) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_ID);
		pstmt.setInt(1, diyafram.getId());
		
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			diyafram.setId(rset.getInt("id"));
			diyafram.setAciklik(rset.getString("aciklik"));
		}

		pstmt.close();
		c.close();

		return diyafram;
	}	
	
	public static Diyafram add(Diyafram diyafram) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, diyafram.getAciklik());
		
		pstmt.executeUpdate();
		ResultSet rset = pstmt.getGeneratedKeys();

		rset.next();
		Integer idGenerated = rset.getInt(1);
		diyafram.setId(idGenerated);		

		pstmt.close();
		c.close();
		
		return diyafram;
	}
	
	public static List<Diyafram> all() throws SQLException {
		Connection c = DaoFactory.openConnection();
		PreparedStatement pstmt = c.prepareStatement(ALL);

		ResultSet rset = pstmt.executeQuery();
		List<Diyafram> diyaframlar = new ArrayList<Diyafram>();
		while (rset.next()){
			Diyafram diyafram = new Diyafram(rset.getInt("id"), rset.getString("aciklik"));
			diyaframlar.add(diyafram);
		}

		pstmt.close();
		c.close();
		return diyaframlar;
	}
	
}

