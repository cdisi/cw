package com.zk.cw.ozellik_atama;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zk.cw.dao_factory.DaoFactory;


public class OzellikAtamaDAO {
	
	private static final String FIND = "SELECT * FROM cihaz_ozellik_atama WHERE cihaz_id=? AND ozellik_id=?";	

	
	public OzellikAtama find(Integer cihazId, Integer ozellikId) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND);
		pstmt.setInt(1, cihazId);
		pstmt.setInt(2, ozellikId);
		
		OzellikAtama ozellikAtama = null;
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			ozellikAtama = new OzellikAtama(rset.getInt("id"),rset.getInt("cihaz_id"), rset.getInt("ozellik_id"),rset.getString("deger"));
		}

		pstmt.close();
		c.close();

		return ozellikAtama;
	}
}
