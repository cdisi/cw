package com.zk.cw.kamera;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import com.zk.cw.cihaz.Cihaz;
import com.zk.cw.dao_factory.DaoFactory;
import com.zk.cw.ozellik_atama.OzellikAtama;

public class OnKameraAtaDAO {
	private static final String FIND_BY_CIHAZ_ID = "SELECT * FROM on_kamera_ata WHERE cihaz_id = ? ORDER BY id LIMIT 1 OFFSET ?";		
	private static final String DELETE = "DELETE FROM on_kamera_ata WHERE id=?";	
	private static final String INSERT = "INSERT INTO on_kamera_ata (cihaz_id,kamera_cozunurluk_id,diyafram_acikligi_id,piksel_buyuklugu_id) VALUES (?,?,?,?)";
	private static final String UPDATE = "UPDATE on_kamera_ata SET cihaz_id=?,kamera_cozunurluk_id=?,diyafram_acikligi_id=?,piksel_buyuklugu_id=? WHERE id=?";
	private static final String TUM_OZELLIKLER = "SELECT * FROM cihaz_ozellik_atama WHERE ozellik_id=24";

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

	
	public static OnKameraAta add(OnKameraAta onKameraAta) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
		
		
		pstmt.setInt(1, onKameraAta.getCihazId());
		pstmt.setInt(2, onKameraAta.getKameraCozunurlukId());
		if(onKameraAta.getDiyaframAcikligiIdId() == null)
			pstmt.setNull(3, java.sql.Types.INTEGER);
		else			
			pstmt.setInt(3, onKameraAta.getDiyaframAcikligiIdId());
		if(onKameraAta.getPikselBuyukluguId() == null)
			pstmt.setNull(4, java.sql.Types.INTEGER);
		else
			pstmt.setInt(4, onKameraAta.getPikselBuyukluguId());
		
		pstmt.executeUpdate();
		ResultSet rset = pstmt.getGeneratedKeys();
		rset.next();
		Integer idGenerated = rset.getInt(1);		
		onKameraAta.setId(idGenerated);
		
		pstmt.close();
		c.close();
		return onKameraAta;
	}
	
	public static void update(OnKameraAta onKameraAta) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(UPDATE);
		pstmt.setInt(1, onKameraAta.getCihazId());
		if(onKameraAta.getKameraCozunurlukId() != null)
			pstmt.setInt(2, onKameraAta.getKameraCozunurlukId());
		else
			pstmt.setNull(2, java.sql.Types.INTEGER);
		if(onKameraAta.getDiyaframAcikligiIdId() == null)
			pstmt.setNull(3, java.sql.Types.INTEGER);
		else			
			pstmt.setInt(3, onKameraAta.getDiyaframAcikligiIdId());
		if(onKameraAta.getPikselBuyukluguId() == null)
			pstmt.setNull(4, java.sql.Types.INTEGER);
		else
			pstmt.setInt(4, onKameraAta.getPikselBuyukluguId());
		pstmt.setInt(5, onKameraAta.getId());
		
		pstmt.executeUpdate();

		pstmt.close();
		c.close();
	}
	
	public static OnKameraAta findBy(Cihaz cihaz, Integer offset) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_CIHAZ_ID);
		pstmt.setInt(1, cihaz.getId());
		pstmt.setInt(2, offset);
		
		OnKameraAta onKameraAta = null;
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			onKameraAta = new OnKameraAta(rset.getInt("id"),rset.getInt("cihaz_id"),rset.getInt("kamera_cozunurluk_id"),rset.getInt("diyafram_acikligi_id"),rset.getInt("piksel_buyuklugu_id"));
		}

		pstmt.close();
		c.close();

		return onKameraAta;
	}	
	
	public static void delete(OnKameraAta onKameraAta) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(DELETE);
		pstmt.setInt(1, onKameraAta.getId());
		pstmt.executeUpdate();

		pstmt.close();
		c.close();
		
	}	
}
