package com.zk.cw.hafiza;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.zk.cw.cihaz.Cihaz;
import com.zk.cw.dao_factory.DaoFactory;
import com.zk.cw.ozellik_atama.OzellikAtama;

public class DahiliHafizaDAO {
	private static final String FIND_BY_ID = "SELECT * FROM dahili_hafiza WHERE id = ?";		
	private static final String INSERT = "INSERT INTO dahili_hafiza (buyukluk) VALUES (?)";
	private static final String INSERT_OZELLIK_ATA = "INSERT INTO dahili_hafiza_ata (cihaz_id,dahili_hafiza_id) VALUES (?,?)";
	private static final String FIND_BY_NAME = "SELECT * FROM dahili_hafiza WHERE buyukluk = ?";	
	private static final String ALL = "SELECT * FROM dahili_hafiza ORDER BY buyukluk";
	private static final String TUM_OZELLIKLER = "SELECT * FROM cihaz_ozellik_atama WHERE ozellik_id=20";

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
	
	public static DahiliHafiza findBy(DahiliHafiza dahiliHafiza) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_NAME);
		pstmt.setString(1, dahiliHafiza.getBuyukluk());
		
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			dahiliHafiza.setId(rset.getInt("id"));
			dahiliHafiza.setBuyukluk(rset.getString("buyukluk"));
		}

		pstmt.close();
		c.close();

		return dahiliHafiza;
	}	
	
	public static DahiliHafiza findById(DahiliHafiza dahiliHafiza) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_ID);
		pstmt.setInt(1, dahiliHafiza.getId());
		
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			dahiliHafiza.setId(rset.getInt("id"));
			dahiliHafiza.setBuyukluk(rset.getString("buyukluk"));
		}

		pstmt.close();
		c.close();

		return dahiliHafiza;
	}	
	
	public static DahiliHafiza add(DahiliHafiza dahiliHafiza) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, dahiliHafiza.getBuyukluk());
		
		pstmt.executeUpdate();
		ResultSet rset = pstmt.getGeneratedKeys();

		rset.next();
		Integer idGenerated = rset.getInt(1);
		dahiliHafiza.setId(idGenerated);		

		pstmt.close();
		c.close();
		
		return dahiliHafiza;
	}
	
	public static List<DahiliHafiza> all() throws SQLException {
		Connection c = DaoFactory.openConnection();
		PreparedStatement pstmt = c.prepareStatement(ALL);

		ResultSet rset = pstmt.executeQuery();
		List<DahiliHafiza> dahiliHafizalar = new ArrayList<DahiliHafiza>();
		while (rset.next()){
			DahiliHafiza dahiliHafiza = new DahiliHafiza(rset.getInt("id"), rset.getString("buyukluk"));
			dahiliHafizalar.add(dahiliHafiza);
		}

		pstmt.close();
		c.close();
		return dahiliHafizalar;
	}
	
	public static DahiliHafizaAta addOzellikAta(Cihaz cihaz, DahiliHafiza dahiliHafiza) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(INSERT_OZELLIK_ATA, PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setInt(1, cihaz.getId());
		pstmt.setInt(2, dahiliHafiza.getId());
		
		pstmt.executeUpdate();
		ResultSet rset = pstmt.getGeneratedKeys();
		
		DahiliHafizaAta  dahiliHafizaAta = new DahiliHafizaAta();
		
		if(rset.next()){
			dahiliHafizaAta.setId(rset.getInt(1));
			dahiliHafizaAta.setCihazId(cihaz.getId());
			dahiliHafizaAta.setDahiliHafizaId(dahiliHafiza.getId());
		}else{
			System.out.println("dahili hafıza eklenemedi");
		}

		pstmt.close();
		c.close();
		
		return dahiliHafizaAta;
	}	
	

}

