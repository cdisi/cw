package com.zk.cw.kamera;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import com.zk.cw.cihaz.Cihaz;
import com.zk.cw.dao_factory.DaoFactory;
import com.zk.cw.hafiza.DahiliHafiza;
import com.zk.cw.hafiza.DahiliHafizaAta;
import com.zk.cw.kamera.ArkaKameraAta;
import com.zk.cw.ozellik_atama.OzellikAtama;

public class ArkaKameraAtaDAO {
	private static final String FIND_BY_CIHAZ_ID = "SELECT * FROM arka_kamera_ata WHERE cihaz_id = ? ORDER BY id LIMIT 1 OFFSET ?";		
	private static final String DELETE = "DELETE FROM arka_kamera_ata WHERE id=?";	
	private static final String INSERT = "INSERT INTO arka_kamera_ata (cihaz_id,arka_kamera_cozunurluk_id,diyafram_acikligi_id,piksel_buyuklugu_id) VALUES (?,?,?,?)";
	private static final String UPDATE = "UPDATE arka_kamera_ata SET cihaz_id=?,arka_kamera_cozunurluk_id=?,diyafram_acikligi_id=?,piksel_buyuklugu_id=? WHERE id=?";
	private static final String TUM_OZELLIKLER = "SELECT * FROM cihaz_ozellik_atama WHERE ozellik_id=21";

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

	
	public static ArkaKameraAta add(ArkaKameraAta arkaKameraAta) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(INSERT);
		pstmt.setInt(1, arkaKameraAta.getCihazId());
		pstmt.setInt(2, arkaKameraAta.getArkaKameraCozunurlukId());
		pstmt.setInt(3, arkaKameraAta.getDiyaframAcikligiIdId());
		pstmt.setInt(4, arkaKameraAta.getPikselBuyukluguId());
		
		pstmt.executeUpdate();

		pstmt.close();
		c.close();
		return arkaKameraAta;
	}
	
	public static void update(ArkaKameraAta arkaKameraAta) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(UPDATE);
		pstmt.setInt(1, arkaKameraAta.getCihazId());
		pstmt.setInt(2, arkaKameraAta.getArkaKameraCozunurlukId());
		pstmt.setInt(3, arkaKameraAta.getDiyaframAcikligiIdId());
		pstmt.setInt(4, arkaKameraAta.getPikselBuyukluguId());
		pstmt.setInt(5, arkaKameraAta.getId());
		
		pstmt.executeUpdate();

		pstmt.close();
		c.close();
	}
	
	public static ArkaKameraAta findBy(Cihaz cihaz, Integer offset) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_CIHAZ_ID);
		pstmt.setInt(1, cihaz.getId());
		pstmt.setInt(2, offset);
		
		ArkaKameraAta arkaKameraAta = null;
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			arkaKameraAta = new ArkaKameraAta(rset.getInt("id"),rset.getInt("cihaz_id"),rset.getInt("arka_kamera_cozunurluk_id"),rset.getInt("diyafram_acikligi_id"),rset.getInt("piksel_buyuklugu_id"));
		}

		pstmt.close();
		c.close();

		return arkaKameraAta;
	}	
	
	public static void delete(ArkaKameraAta arkaKameraAta) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(DELETE);
		pstmt.setInt(1, arkaKameraAta.getId());
		pstmt.executeUpdate();

		pstmt.close();
		c.close();
		
	}	
}
