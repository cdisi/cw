package com.zk.cw.yonga_seti;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.zk.cw.dao_factory.DaoFactory;
import com.zk.cw.ozellik_atama.OzellikAtama;

public class YongaSetiDAO {
	private static final String FIND_BY_ID = "SELECT * FROM yonga_seti WHERE id = ?";		
	private static final String TUM_OZELLIKLER = "SELECT * FROM cihaz_ozellik_atama WHERE ozellik_id=16";
	private static final String INSERT = "INSERT INTO yonga_seti (ad) VALUES (?)";
	private static final String FIND_BY_NAME = "SELECT * FROM yonga_seti WHERE ad = ?";	
	private static final String ALL = "SELECT * FROM yonga_seti ORDER BY ad";

	public static LinkedHashMap<Integer, OzellikAtama> tumOzellikler() throws SQLException {
		LinkedHashMap<Integer, OzellikAtama> lhm = new LinkedHashMap<>(); 
		Connection c = DaoFactory.openConnection();
		PreparedStatement pstmt = c.prepareStatement(TUM_OZELLIKLER);
		ResultSet rset = pstmt.executeQuery();
		while (rset.next()){
			OzellikAtama ozellikAtama = new OzellikAtama(rset.getInt("id"),rset.getInt("cihaz_id"),rset.getInt("kategori_id"),rset.getInt("ozellik_id"),rset.getString("deger"));
			lhm.put(rset.getInt("id"), ozellikAtama);
		}
		pstmt.close();
		c.close();
		return lhm;		
	}
	
	public static YongaSeti findBy(YongaSeti yongaSeti) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_NAME);
		pstmt.setString(1, yongaSeti.getAd());
		
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			yongaSeti.setId(rset.getInt("id"));
			yongaSeti.setAd(rset.getString("ad"));
		}

		pstmt.close();
		c.close();

		return yongaSeti;
	}	
	
	public static YongaSeti add(YongaSeti yongaSeti) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, yongaSeti.getAd());
		
		pstmt.executeUpdate();
		ResultSet rset = pstmt.getGeneratedKeys();

		rset.next();
		Integer idGenerated = rset.getInt(1);
		yongaSeti.setId(idGenerated);		

		pstmt.close();
		c.close();
		
		return yongaSeti;
	}
	public static YongaSeti findBy(Integer id) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_ID);
		pstmt.setInt(1, id);
		
		YongaSeti yongaSeti = null;
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			yongaSeti = new YongaSeti(rset.getInt("id"), rset.getString("ad"));
		}

		pstmt.close();
		c.close();

		return yongaSeti;
	}	
	
	public static List<YongaSeti> all() throws SQLException {
		Connection c = DaoFactory.openConnection();
		PreparedStatement pstmt = c.prepareStatement(ALL);

		ResultSet rset = pstmt.executeQuery();
		List<YongaSeti> yongaSetleri = new ArrayList<YongaSeti>();
		while (rset.next()){
			YongaSeti yongaSeti = new YongaSeti(rset.getInt("id"), rset.getString("ad"));
			yongaSetleri.add(yongaSeti);
		}

		pstmt.close();
		c.close();
		return yongaSetleri;
	}
}
