package com.zk.cw.harici_hafiza;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.zk.cw.dao_factory.DaoFactory;
import com.zk.cw.ozellik_atama.OzellikAtama;

public class HariciHafizaTipiDAO {
	private static final String FIND_BY_ID = "SELECT * FROM harici_hafiza_tipi WHERE id = ?";		
	private static final String FIND_OZELLIK = "SELECT * FROM cihaz_ozellik_atama WHERE ozellik_id=?";	
	private static final String INSERT = "INSERT INTO harici_hafiza_tipi (ad) VALUES (?)";
	private static final String FIND_BY_NAME = "SELECT * FROM harici_hafiza_tipi WHERE ad = ?";	
	private static final String ALL = "SELECT * FROM harici_hafiza_tipi ORDER BY ad";
	private static final String INSERT_OZELLIK_ATAMA = "INSERT INTO cihaz_ozellik_atama (cihaz_id,kategori_id,ozellik_id,deger) VALUES (?,?,?,?)";	
	private static final String UPDATE_OZELLİK_ATAMA = "UPDATE cihaz_ozellik_atama SET kategori_id=?,deger=? WHERE cihaz_id=? AND ozellik_id=?";	

	public static HariciHafizaTipi findBy(HariciHafizaTipi hariciHafizaTipi) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_NAME);
		pstmt.setString(1, hariciHafizaTipi.getAd());
		
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			hariciHafizaTipi.setId(rset.getInt("id"));
			hariciHafizaTipi.setAd(rset.getString("ad"));
		}

		pstmt.close();
		c.close();

		return hariciHafizaTipi;
	}	
	
	public static HariciHafizaTipi add(HariciHafizaTipi hariciHafizaTipi) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, hariciHafizaTipi.getAd());
		
		pstmt.executeUpdate();
		ResultSet rset = pstmt.getGeneratedKeys();

		rset.next();
		Integer idGenerated = rset.getInt(1);
		hariciHafizaTipi.setId(idGenerated);		

		pstmt.close();
		c.close();
		
		return hariciHafizaTipi;
	}
	public static HariciHafizaTipi findBy(Integer id) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_ID);
		pstmt.setInt(1, id);
		
		HariciHafizaTipi hariciHafizaTipi = null;
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			hariciHafizaTipi = new HariciHafizaTipi(rset.getInt("id"), rset.getString("ad"));
		}

		pstmt.close();
		c.close();

		return hariciHafizaTipi;
	}	
	
	public static List<HariciHafizaTipi> all() throws SQLException {
		Connection c = DaoFactory.openConnection();
		PreparedStatement pstmt = c.prepareStatement(ALL);

		ResultSet rset = pstmt.executeQuery();
		List<HariciHafizaTipi> tipler = new ArrayList<HariciHafizaTipi>();
		while (rset.next()){
			HariciHafizaTipi hariciHafizaTipi = new HariciHafizaTipi(rset.getInt("id"), rset.getString("ad"));
			tipler.add(hariciHafizaTipi);
		}

		pstmt.close();
		c.close();
		return tipler;
	}
	
	public static LinkedHashMap<Integer, OzellikAtama> find() throws SQLException {
		LinkedHashMap<Integer, OzellikAtama> lhm = new LinkedHashMap<>(); 
		Connection c = DaoFactory.openConnection();
		PreparedStatement pstmt = c.prepareStatement(FIND_OZELLIK);
		pstmt.setInt(1, 19);
		ResultSet rset = pstmt.executeQuery();
		while (rset.next()){
			OzellikAtama ozellikAtama = new OzellikAtama(rset.getInt("id"),rset.getInt("cihaz_id"),rset.getInt("kategori_id"),rset.getInt("ozellik_id"),rset.getString("deger"));
			lhm.put(rset.getInt("id"), ozellikAtama);
		}
		pstmt.close();
		c.close();
		return lhm;		
	}
	
	public OzellikAtama insert(OzellikAtama ozellikAtama) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(INSERT_OZELLIK_ATAMA);
		pstmt.setInt(1, ozellikAtama.getCihazId());
		pstmt.setInt(2, ozellikAtama.getKategoriId());
		pstmt.setInt(3, ozellikAtama.getOzellikId());
		pstmt.setString(4, ozellikAtama.getDeger());
		
		pstmt.executeUpdate();

		pstmt.close();
		c.close();
		
		return ozellikAtama;
	}
	
	public OzellikAtama update(OzellikAtama ozellikAtama) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(UPDATE_OZELLİK_ATAMA);
		pstmt.setInt(1, ozellikAtama.getKategoriId());
		pstmt.setString(2, ozellikAtama.getDeger());
		pstmt.setInt(3, ozellikAtama.getCihazId());
		pstmt.setInt(4, ozellikAtama.getOzellikId());
		
		pstmt.executeUpdate();

		pstmt.close();
		c.close();
		
		return ozellikAtama;
	}		
}