package com.zk.cw.cihaz_resim;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zk.cw.cihaz.Cihaz;
import com.zk.cw.dao_factory.DaoFactory;

public class ResimDAO {
	private static final String INSERT = "INSERT INTO cihaz_resim (kucuk_resim,orta_resim,buyuk_resim) VALUES (?,?,?)";
	private static final String FIND_BY_ID = "SELECT * FROM cihaz_resim WHERE id=?";
	
	public static Cihaz add(Cihaz cihaz, Resim resim) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

		pstmt.setBytes(1, resim.getKucukResim());
		pstmt.setBytes(2, resim.getOrtaResim());
		pstmt.setBytes(3, resim.getBuyukResim());
		
		pstmt.executeUpdate();
		ResultSet rset = pstmt.getGeneratedKeys();

		rset.next();
		Integer idGenerated = rset.getInt(1);
		cihaz.setResimId(idGenerated);		

		pstmt.close();
		c.close();
		
		return cihaz;
	}
	
	public static Resim findById(Integer id) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_ID);
		pstmt.setInt(1, id);
		
		Resim resim = null;
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			resim = new Resim(rset.getInt("id"), rset.getBytes("resim"));
		}

		pstmt.close();
		c.close();

		return resim;
	}	
	
	
	
}
