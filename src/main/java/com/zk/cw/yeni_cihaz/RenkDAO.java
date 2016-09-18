package com.zk.cw.yeni_cihaz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zk.cw.dao_factory.DaoFactory;

public class RenkDAO {
	private static final String FINDA_BY_NAME_EN = "SELECT * FROM renk WHERE ad_en=?";
	
	public static String findByNameEn(String ad) throws SQLException {
		Connection c = DaoFactory.openConnection();
		PreparedStatement pstmt = c.prepareStatement(FINDA_BY_NAME_EN);
		pstmt.setString(1, ad);
		ResultSet rset = pstmt.executeQuery();
		String adTr = null;
		while (rset.next()){
			adTr = rset.getString("ad_tr");
		}
		pstmt.close();
		c.close();
		return adTr;
	}	
}
