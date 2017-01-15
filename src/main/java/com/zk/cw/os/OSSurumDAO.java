package com.zk.cw.os;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zk.cw.dao_factory.DaoFactory;

public class OSSurumDAO {
	private static final String FIND_BY_ID = "SELECT * FROM os_surum WHERE id = ?";		
	private static final String INSERT = "INSERT INTO os_surum (os_id,ad) VALUES (?,?)";
	private static final String FIND_BY_NAME = "SELECT * FROM os_surum WHERE os_id = ? AND ad = ?";	
	private static final String ALL = "SELECT * FROM os_surum WHERE os_id=? ORDER BY ad";

	public static OSSurum findBy(OSSurum osSurum) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_NAME);
		pstmt.setInt(1, osSurum.getOsId());
		pstmt.setString(2, osSurum.getAd());
		
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			osSurum.setId(rset.getInt("id"));
			osSurum.setOsId(rset.getInt("os_id"));
			osSurum.setAd(rset.getString("ad"));
		}

		pstmt.close();
		c.close();

		return osSurum;
	}	
	
	public static OSSurum add(OSSurum osSurum) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setInt(1, osSurum.getOsId());
		pstmt.setString(2, osSurum.getAd());
		
		pstmt.executeUpdate();
		ResultSet rset = pstmt.getGeneratedKeys();

		rset.next();
		Integer idGenerated = rset.getInt(1);
		osSurum.setId(idGenerated);		

		pstmt.close();
		c.close();
		
		return osSurum;
	}
	public static OSSurum findBy(Integer id) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_ID);
		pstmt.setInt(1, id);
		
		OSSurum osSurum = null;
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			osSurum = new OSSurum(rset.getInt("id"), rset.getInt("os_id"),rset.getString("ad"));
		}

		pstmt.close();
		c.close();

		return osSurum;
	}	
	
	public static List<OSSurum> all(OS os) throws SQLException {
		Connection c = DaoFactory.openConnection();
		PreparedStatement pstmt = c.prepareStatement(ALL);
		pstmt.setInt(1, os.getId());
		ResultSet rset = pstmt.executeQuery();
		List<OSSurum> osSurumler = new ArrayList<OSSurum>();
		while (rset.next()){
			OSSurum osSurum = new OSSurum(rset.getInt("id"),rset.getInt("os_id"), rset.getString("ad"));
			osSurumler.add(osSurum);
		}

		pstmt.close();
		c.close();
		return osSurumler;
	}
}
