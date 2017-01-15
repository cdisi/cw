package com.zk.cw.harici_hafiza;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zk.cw.dao_factory.DaoFactory;

public class HariciHafizaBuyuklukDAO {
	private static final String FIND_BY_ID = "SELECT * FROM harici_hafiza_buyukluk WHERE id = ?";		
	private static final String INSERT = "INSERT INTO harici_hafiza_buyukluk (buyukluk) VALUES (?)";
	private static final String FIND_BY_NAME = "SELECT * FROM harici_hafiza_buyukluk WHERE buyukluk = ?";	
	private static final String ALL = "SELECT * FROM harici_hafiza_buyukluk ORDER BY buyukluk";

	public static HariciHafizaBuyukluk findBy(HariciHafizaBuyukluk hariciHafizaBuyukluk) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_NAME);
		pstmt.setString(1, hariciHafizaBuyukluk.getBuyukluk());
		
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			hariciHafizaBuyukluk.setId(rset.getInt("id"));
			hariciHafizaBuyukluk.setBuyukluk(rset.getString("buyukluk"));
		}

		pstmt.close();
		c.close();

		return hariciHafizaBuyukluk;
	}	
	
	public static HariciHafizaBuyukluk add(HariciHafizaBuyukluk hariciHafizaBuyukluk) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, hariciHafizaBuyukluk.getBuyukluk());
		
		pstmt.executeUpdate();
		ResultSet rset = pstmt.getGeneratedKeys();

		rset.next();
		Integer idGenerated = rset.getInt(1);
		hariciHafizaBuyukluk.setId(idGenerated);		

		pstmt.close();
		c.close();
		
		return hariciHafizaBuyukluk;
	}
	public static HariciHafizaBuyukluk findBy(Integer id) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_ID);
		pstmt.setInt(1, id);
		
		HariciHafizaBuyukluk hariciHafizaBuyukluk = null;
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			hariciHafizaBuyukluk = new HariciHafizaBuyukluk(rset.getInt("id"), rset.getString("buyukluk"));
		}

		pstmt.close();
		c.close();

		return hariciHafizaBuyukluk;
	}	
	
	public static List<HariciHafizaBuyukluk> all() throws SQLException {
		Connection c = DaoFactory.openConnection();
		PreparedStatement pstmt = c.prepareStatement(ALL);

		ResultSet rset = pstmt.executeQuery();
		List<HariciHafizaBuyukluk> buyuklukler = new ArrayList<HariciHafizaBuyukluk>();
		while (rset.next()){
			HariciHafizaBuyukluk hariciHafizaBuyukluk = new HariciHafizaBuyukluk(rset.getInt("id"), rset.getString("buyukluk"));
			buyuklukler.add(hariciHafizaBuyukluk);
		}

		pstmt.close();
		c.close();
		return buyuklukler;
	}
}