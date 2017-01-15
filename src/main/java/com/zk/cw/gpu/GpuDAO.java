package com.zk.cw.gpu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.zk.cw.dao_factory.DaoFactory;
import com.zk.cw.ozellik_atama.OzellikAtama;
import com.zk.cw.yonga_seti.YongaSeti;

public class GpuDAO {
	private static final String FIND_BY_ID = "SELECT * FROM gpu WHERE id = ?";		
	private static final String TUM_OZELLIKLER = "SELECT * FROM cihaz_ozellik_atama WHERE ozellik_id=18";
	private static final String INSERT = "INSERT INTO gpu (ad) VALUES (?)";
	private static final String FIND_BY_NAME = "SELECT * FROM gpu WHERE ad = ?";	
	private static final String ALL = "SELECT * FROM gpu ORDER BY ad";

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
	
	public static Gpu findBy(Gpu gpu) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_NAME);
		pstmt.setString(1, gpu.getAd());
		
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			gpu.setId(rset.getInt("id"));
			gpu.setAd(rset.getString("ad"));
		}

		pstmt.close();
		c.close();

		return gpu;
	}	
	
	public static Gpu add(Gpu gpu) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, gpu.getAd());
		
		pstmt.executeUpdate();
		ResultSet rset = pstmt.getGeneratedKeys();

		rset.next();
		Integer idGenerated = rset.getInt(1);
		gpu.setId(idGenerated);		

		pstmt.close();
		c.close();
		
		return gpu;
	}
	public static Gpu findBy(Integer id) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_ID);
		pstmt.setInt(1, id);
		
		Gpu gpu = null;
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			gpu = new Gpu(rset.getInt("id"), rset.getString("ad"));
		}

		pstmt.close();
		c.close();

		return gpu;
	}	
	
	public static List<Gpu> all() throws SQLException {
		Connection c = DaoFactory.openConnection();
		PreparedStatement pstmt = c.prepareStatement(ALL);

		ResultSet rset = pstmt.executeQuery();
		List<Gpu> gpular = new ArrayList<Gpu>();
		while (rset.next()){
			Gpu gpu = new Gpu(rset.getInt("id"), rset.getString("ad"));
			gpular.add(gpu);
		}

		pstmt.close();
		c.close();
		return gpular;
	}
}
