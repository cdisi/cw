package com.zk.cw.cihaz_resim;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zk.cw.cihaz.Cihaz;
import com.zk.cw.dao_factory.DaoFactory;

public class ResimDAO {
	
	private static final String INSERT = "INSERT INTO cihaz_goruntu (cihaz_id,kucuk_resim,orta_resim,buyuk_resim) VALUES (?,?,?,?)";
	private static final String UPDATE = "UPDATE cihaz_goruntu set kucuk_resim=?,orta_resim=?, buyuk_resim=? WHERE id=?";
	private static final String FIND_BY_CIHAZ_ID = "SELECT * FROM cihaz_goruntu WHERE cihaz_id=?";
	private static final String DELETE = "DELETE FROM cihaz_goruntu WHERE id=?";
	
	public static Resim add(Resim resimGaleri) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

		pstmt.setInt(1, resimGaleri.getCihazId());
		pstmt.setBytes(2, resimGaleri.getKucukResim());
		pstmt.setBytes(3, resimGaleri.getOrtaResim());
		pstmt.setBytes(4, resimGaleri.getBuyukResim());
		
		pstmt.executeUpdate();
		ResultSet rset = pstmt.getGeneratedKeys();

		rset.next();
		Integer idGenerated = rset.getInt(1);
		resimGaleri.setId(idGenerated);		

		pstmt.close();
		c.close();
		
		return resimGaleri;
	}
	
	public static Resim update(Cihaz cihaz, Resim resim) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(UPDATE);

		pstmt.setBytes(1, resim.getKucukResim());
		pstmt.setBytes(2, resim.getOrtaResim());
		pstmt.setBytes(3, resim.getBuyukResim());
		pstmt.setInt(4, resim.getId());
		
		pstmt.executeUpdate();

		pstmt.close();
		c.close();
		
		return resim;
	}	
	
	public static List<Resim> findByCihazId(Integer id) throws SQLException {
		Connection c = DaoFactory.openConnection();
		PreparedStatement pstmt = c.prepareStatement(FIND_BY_CIHAZ_ID);
		pstmt.setInt(1, id);		
		List resimGaleriList = new ArrayList<Resim>();
		ResultSet rset = pstmt.executeQuery();
		Resim resimGaleri = null;
		while (rset.next()){
			resimGaleri = new Resim();
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
	
	public static void delete(int id) throws SQLException {
		Connection c = DaoFactory.openConnection();
		PreparedStatement pstmt = c.prepareStatement(DELETE);
		pstmt.execute();
		pstmt.close();
		c.close();
	}	
	
}
