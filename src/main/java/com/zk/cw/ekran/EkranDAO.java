package com.zk.cw.ekran;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.zk.cw.cihaz.Cihaz;
import com.zk.cw.cihaz_tur.CihazTur;
import com.zk.cw.dao_factory.DaoFactory;

public class EkranDAO {
	private static final String ALL = "SELECT * FROM ekran_tipi";
	private static final String ALL_OZELLIKLER = "SELECT * FROM cihaz_ozellik_atama WHERE ozellik_id=10";
	private static final String FIND_BY_NAME = "SELECT * FROM ekran_tipi WHERE ad = ?";	
	private static final String INSERT = "INSERT INTO ekran_tipi (ad) VALUES (?)";

	public List<EkranTip> all() throws SQLException {
		Connection c = DaoFactory.openConnection();
		PreparedStatement pstmt = c.prepareStatement(ALL);

		ResultSet rset = pstmt.executeQuery();
		List<EkranTip> ekranTipleri = new ArrayList<EkranTip>();
		while (rset.next()){
			EkranTip ekranTip = new EkranTip(rset.getInt("id"), rset.getString("ad"));
					
			ekranTipleri.add(ekranTip);
		}

		pstmt.close();
		c.close();
		return ekranTipleri;
	}	
	
	public static LinkedHashMap<Integer, EkranTip> allOzellikler() throws SQLException {
			LinkedHashMap<Integer, EkranTip> lhm = new LinkedHashMap<>(); 
			Connection c = DaoFactory.openConnection();
			PreparedStatement pstmt = c.prepareStatement(ALL_OZELLIKLER);
			ResultSet rset = pstmt.executeQuery();
			while (rset.next()){
				EkranTip ekranTip = new EkranTip(rset.getInt("cihaz_id"),rset.getString("deger"));
				lhm.put(rset.getInt("cihaz_id"), ekranTip);
			}

			pstmt.close();
			c.close();
			return lhm;		
	}
		
	public EkranTip findByName(String ad) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_NAME);
		pstmt.setString(1, ad);
		
		EkranTip ekranTip = null;
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			ekranTip = new EkranTip(rset.getInt("id"), rset.getString("ad"));
		}

		pstmt.close();
		c.close();

		return ekranTip;
	}
	
	public EkranTip add(EkranTip ekranTip) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, ekranTip.getAd());
		
		pstmt.executeUpdate();
		ResultSet rset = pstmt.getGeneratedKeys();

		rset.next();
		Integer idGenerated = rset.getInt(1);
		ekranTip.setId(idGenerated);		

		pstmt.close();
		c.close();
		
		return ekranTip;
	}	
}
