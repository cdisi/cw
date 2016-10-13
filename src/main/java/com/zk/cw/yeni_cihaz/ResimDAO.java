package com.zk.cw.yeni_cihaz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zk.cw.dao_factory.DaoFactory;

public class ResimDAO {
	private static final String INSERT = "INSERT INTO cihaz_resim (resim) VALUES (?)";
	
	public static Cihaz add(Cihaz cihaz, byte[] resim) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

		pstmt.setBytes(1, resim);
		
		pstmt.executeUpdate();
		ResultSet rset = pstmt.getGeneratedKeys();

		rset.next();
		Integer idGenerated = rset.getInt(1);
		cihaz.setResimId(idGenerated);		

		pstmt.close();
		c.close();
		
		return cihaz;
	}
}
