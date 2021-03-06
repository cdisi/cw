package com.zk.cw.batarya;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zk.cw.dao_factory.DaoFactory;

public class BataryaDegisirDAO {
	private static final String FIND_BY_ID = "SELECT * FROM batarya_degisir WHERE id = ?";		
	private static final String INSERT = "INSERT INTO batarya_degisir (ad) VALUES (?)";
	private static final String FIND_BY_NAME = "SELECT * FROM batarya_degisir WHERE ad = ?";	
	private static final String ALL = "SELECT * FROM batarya_degisir ORDER BY ad";
	
	public static BataryaDegisir findBy(BataryaDegisir degisir) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_NAME);
		pstmt.setString(1, degisir.getAd());
		
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			degisir.setId(rset.getInt("id"));
			degisir.setAd(rset.getString("ad"));
		}

		pstmt.close();
		c.close();

		return degisir;
	}	
	
	public static BataryaDegisir findById(BataryaDegisir degisir) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_ID);
		pstmt.setInt(1, degisir.getId());
		
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			degisir.setId(rset.getInt("id"));
			degisir.setAd(rset.getString("ad"));
		}

		pstmt.close();
		c.close();

		return degisir;
	}	
	
	public static BataryaDegisir add(BataryaDegisir degisir) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, degisir.getAd());
		
		pstmt.executeUpdate();
		ResultSet rset = pstmt.getGeneratedKeys();

		rset.next();
		Integer idGenerated = rset.getInt(1);
		degisir.setId(idGenerated);		

		pstmt.close();
		c.close();
		
		return degisir;
	}
	
	public static List<BataryaDegisir> all() throws SQLException {
		Connection c = DaoFactory.openConnection();
		PreparedStatement pstmt = c.prepareStatement(ALL);

		ResultSet rset = pstmt.executeQuery();
		List<BataryaDegisir> degisirler = new ArrayList<BataryaDegisir>();
		while (rset.next()){
			BataryaDegisir degisir = new BataryaDegisir(rset.getInt("id"), rset.getString("ad"));
			degisirler.add(degisir);
		}

		pstmt.close();
		c.close();
		return degisirler;
	}
	
}

