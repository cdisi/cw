package com.zk.cw.sim;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.zk.cw.dao_factory.DaoFactory;
import com.zk.cw.ozellik_atama.OzellikAtama;

public class SimDAO {
	
	private static final String FIND_BY_ID = "SELECT * FROM sim WHERE id = ?";		
	private static final String TUM_OZELLIKLER = "SELECT * FROM cihaz_ozellik_atama WHERE ozellik_id=9";
	private static final String INSERT = "INSERT INTO sim (ad) VALUES (?)";
	private static final String FIND_BY_NAME = "SELECT * FROM sim WHERE ad = ?";	
	private static final String ALL = "SELECT * FROM sim ORDER BY ad";

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
	
	public static Sim findBy(Sim sim) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_NAME);
		pstmt.setString(1, sim.getAd());
		
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			sim.setId(rset.getInt("id"));
			sim.setAd(rset.getString("ad"));
		}

		pstmt.close();
		c.close();

		return sim;
	}	
	
	public static Sim add(Sim sim) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, sim.getAd());
		
		pstmt.executeUpdate();
		ResultSet rset = pstmt.getGeneratedKeys();

		rset.next();
		Integer idGenerated = rset.getInt(1);
		sim.setId(idGenerated);		

		pstmt.close();
		c.close();
		
		return sim;
	}
	public static Sim findBy(Integer id) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_ID);
		pstmt.setInt(1, id);
		
		Sim sim = null;
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			sim = new Sim(rset.getInt("id"), rset.getString("ad"));
		}

		pstmt.close();
		c.close();

		return sim;
	}	
	
	public static List<Sim> all() throws SQLException {
		Connection c = DaoFactory.openConnection();
		PreparedStatement pstmt = c.prepareStatement(ALL);

		ResultSet rset = pstmt.executeQuery();
		List<Sim> simler = new ArrayList<Sim>();
		while (rset.next()){
			Sim sim = new Sim(rset.getInt("id"), rset.getString("ad"));
			simler.add(sim);
		}

		pstmt.close();
		c.close();
		return simler;
	}	
}
