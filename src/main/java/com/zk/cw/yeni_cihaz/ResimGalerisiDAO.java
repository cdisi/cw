package com.zk.cw.yeni_cihaz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zk.cw.dao_factory.DaoFactory;


public class ResimGalerisiDAO {
	
	private static final String INSERT = "INSERT INTO cihaz_resim_galeri (cihaz_id,kucuk_resim,buyuk_resim) VALUES (?,?,?)";
	
	public static Cihaz add(Cihaz cihaz, byte[] kucukResim, byte[] buyukResim) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

		pstmt.setInt(1, cihaz.getId());
		pstmt.setBytes(2, kucukResim);
		pstmt.setBytes(3, buyukResim);
		
		pstmt.executeUpdate();

		pstmt.close();
		c.close();
		
		return cihaz;
	}	
}
