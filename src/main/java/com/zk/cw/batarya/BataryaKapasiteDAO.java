package com.zk.cw.batarya;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zk.cw.dao_factory.DaoFactory;

public class BataryaKapasiteDAO {
	private static final String FIND_BY_ID = "SELECT * FROM batarya_kapasite WHERE id = ?";		
	private static final String INSERT = "INSERT INTO batarya_kapasite (kapasite) VALUES (?)";
	private static final String FIND_BY_NAME = "SELECT * FROM batarya_kapasite WHERE kapasite = ?";	
	private static final String ALL = "SELECT * FROM batarya_kapasite ORDER BY kapasite";

	public static BataryaKapasite findBy(BataryaKapasite kapasite) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_NAME);
		pstmt.setString(1, kapasite.getKapasite());
		
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			kapasite.setId(rset.getInt("id"));
			kapasite.setKapasite(rset.getString("kapasite"));
		}

		pstmt.close();
		c.close();

		return kapasite;
	}	
	
	public static BataryaKapasite findById(BataryaKapasite kapasite) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_ID);
		pstmt.setInt(1, kapasite.getId());
		
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			kapasite.setId(rset.getInt("id"));
			kapasite.setKapasite(rset.getString("kapasite"));
		}

		pstmt.close();
		c.close();

		return kapasite;
	}	
	
	public static BataryaKapasite add(BataryaKapasite kapasite) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, kapasite.getKapasite());
		
		pstmt.executeUpdate();
		ResultSet rset = pstmt.getGeneratedKeys();

		rset.next();
		Integer idGenerated = rset.getInt(1);
		kapasite.setId(idGenerated);		

		pstmt.close();
		c.close();
		
		return kapasite;
	}
	
	public static List<BataryaKapasite> all() throws SQLException {
		Connection c = DaoFactory.openConnection();
		PreparedStatement pstmt = c.prepareStatement(ALL);

		ResultSet rset = pstmt.executeQuery();
		List<BataryaKapasite> kapasiteler = new ArrayList<BataryaKapasite>();
		while (rset.next()){
			BataryaKapasite kapasite = new BataryaKapasite(rset.getInt("id"), rset.getString("kapasite"));
			kapasiteler.add(kapasite);
		}

		pstmt.close();
		c.close();
		return kapasiteler;
	}
	
}

