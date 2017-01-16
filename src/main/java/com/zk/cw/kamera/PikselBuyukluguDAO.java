package com.zk.cw.kamera;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.zk.cw.dao_factory.DaoFactory;

public class PikselBuyukluguDAO {
	private static final String FIND_BY_ID = "SELECT * FROM kamera_piksel_buyuklugu WHERE id = ?";		
	private static final String INSERT = "INSERT INTO kamera_piksel_buyuklugu (buyukluk) VALUES (?)";
	private static final String FIND_BY_NAME = "SELECT * FROM kamera_piksel_buyuklugu WHERE buyukluk = ?";	
	private static final String ALL = "SELECT * FROM kamera_piksel_buyuklugu ORDER BY buyukluk";

	public static PikselBuyuklugu findBy(PikselBuyuklugu pikselBuyuklugu) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_NAME);
		pstmt.setString(1, pikselBuyuklugu.getBuyukluk());
		
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			pikselBuyuklugu.setId(rset.getInt("id"));
			pikselBuyuklugu.setBuyukluk(rset.getString("buyukluk"));
		}

		pstmt.close();
		c.close();

		return pikselBuyuklugu;
	}	
	
	public static PikselBuyuklugu findById(PikselBuyuklugu pikselBuyuklugu) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_ID);
		pstmt.setInt(1, pikselBuyuklugu.getId());
		
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			pikselBuyuklugu.setId(rset.getInt("id"));
			pikselBuyuklugu.setBuyukluk(rset.getString("buyukluk"));
		}

		pstmt.close();
		c.close();

		return pikselBuyuklugu;
	}	
	
	public static PikselBuyuklugu add(PikselBuyuklugu pikselBuyuklugu) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, pikselBuyuklugu.getBuyukluk());
		
		pstmt.executeUpdate();
		ResultSet rset = pstmt.getGeneratedKeys();

		rset.next();
		Integer idGenerated = rset.getInt(1);
		pikselBuyuklugu.setId(idGenerated);		

		pstmt.close();
		c.close();
		
		return pikselBuyuklugu;
	}
	
	public static List<PikselBuyuklugu> all() throws SQLException {
		Connection c = DaoFactory.openConnection();
		PreparedStatement pstmt = c.prepareStatement(ALL);

		ResultSet rset = pstmt.executeQuery();
		List<PikselBuyuklugu> buyuklukler = new ArrayList<PikselBuyuklugu>();
		while (rset.next()){
			PikselBuyuklugu pikselBuyuklugu = new PikselBuyuklugu(rset.getInt("id"), rset.getString("buyukluk"));
			buyuklukler.add(pikselBuyuklugu);
		}

		pstmt.close();
		c.close();
		return buyuklukler;
	}
	
}

