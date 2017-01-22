package com.zk.cw.batarya;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zk.cw.dao_factory.DaoFactory;

public class BataryaTeknolojiDAO {
	private static final String FIND_BY_ID = "SELECT * FROM batarya_teknoloji WHERE id = ?";		
	private static final String INSERT = "INSERT INTO batarya_teknoloji (ad) VALUES (?)";
	private static final String FIND_BY_NAME = "SELECT * FROM batarya_teknoloji WHERE ad = ?";	
	private static final String ALL = "SELECT * FROM batarya_teknoloji ORDER BY ad";
	
	public static BataryaTeknoloji findBy(BataryaTeknoloji teknoloji) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_NAME);
		pstmt.setString(1, teknoloji.getAd());
		
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			teknoloji.setId(rset.getInt("id"));
			teknoloji.setAd(rset.getString("ad"));
		}

		pstmt.close();
		c.close();

		return teknoloji;
	}	
	
	public static BataryaTeknoloji findById(BataryaTeknoloji teknoloji) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_ID);
		pstmt.setInt(1, teknoloji.getId());
		
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			teknoloji.setId(rset.getInt("id"));
			teknoloji.setAd(rset.getString("ad"));
		}

		pstmt.close();
		c.close();

		return teknoloji;
	}	
	
	public static BataryaTeknoloji add(BataryaTeknoloji teknoloji) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, teknoloji.getAd());
		
		pstmt.executeUpdate();
		ResultSet rset = pstmt.getGeneratedKeys();

		rset.next();
		Integer idGenerated = rset.getInt(1);
		teknoloji.setId(idGenerated);		

		pstmt.close();
		c.close();
		
		return teknoloji;
	}
	
	public static List<BataryaTeknoloji> all() throws SQLException {
		Connection c = DaoFactory.openConnection();
		PreparedStatement pstmt = c.prepareStatement(ALL);

		ResultSet rset = pstmt.executeQuery();
		List<BataryaTeknoloji> teknolojiler = new ArrayList<BataryaTeknoloji>();
		while (rset.next()){
			BataryaTeknoloji teknoloji = new BataryaTeknoloji(rset.getInt("id"), rset.getString("ad"));
			teknolojiler.add(teknoloji);
		}

		pstmt.close();
		c.close();
		return teknolojiler;
	}
	
}

