package com.zk.cw.cihaz_resim_galeri;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zk.cw.cihaz.Cihaz;
import com.zk.cw.dao_factory.DaoFactory;

public class ResimGaleriDAO {
	private static final String INSERT = "INSERT INTO cihaz_resim_galeri (cihaz_id,kucuk_resim,orta_resim,buyuk_resim) VALUES (?,?,?,?)";
	private static final String UPDATE = "UPDATE cihaz_resim_galeri set kucuk_resim=?,orta_resim=?, buyuk_resim=? WHERE id=?";
	private static final String FIND_BY_CIHAZ_ID = "SELECT * FROM cihaz_resim_galeri WHERE cihaz_id=?";
	
	public static Resim add(Cihaz cihaz, Resim resim) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

		pstmt.setBytes(1, resim.getResim());
		pstmt.setBytes(2, resim.getKucukResim());
		pstmt.setBytes(3, resim.getBuyukResim());
		
		pstmt.executeUpdate();
		ResultSet rset = pstmt.getGeneratedKeys();

		rset.next();
		Integer idGenerated = rset.getInt(1);
		resim.setId(idGenerated);		

		pstmt.close();
		c.close();
		
		return resim;
	}
	
	public static Resim update(Cihaz cihaz, Resim resim) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(UPDATE);

		pstmt.setBytes(1, resim.getResim());
		pstmt.setBytes(2, resim.getKucukResim());
		pstmt.setBytes(3, resim.getBuyukResim());
		pstmt.setInt(4, resim.getId());
		
		pstmt.executeUpdate();

		pstmt.close();
		c.close();
		
		return resim;
	}	
	
	public static List<ResimGaleri> findByCihazId(Integer id) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_CIHAZ_ID);
		pstmt.setInt(1, id);
		
		List resimGaleriList = new ArrayList<ResimGaleri>();
		ResultSet rset = pstmt.executeQuery();
		ResimGaleri resimGaleri = null;
		while (rset.next()){
			resimGaleri = new ResimGaleri();
			resimGaleri.setId(rset.getInt("id"));
			resimGaleri.setCihazId(rset.getInt("cihaz_id"));
			resimGaleri.setKucukResim(rset.getBytes("kucuk_resim"));
			resimGaleri.setOrtaResim(rset.getBytes("orta_resim"));
			resimGaleri.setBuyukResim(rset.getBytes("buyuk_resim"));
			resimGaleriList.add(resimGaleri);
		}

		pstmt.close();
		c.close();

		return resimGaleriList;
	}	
	
	
	
}
