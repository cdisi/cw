package com.zk.cw.yeni_cihaz;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.zk.cw.dao_factory.DaoFactory;

public class CihazTurDAO {
	private static final String ALL = "SELECT * FROM cihaz_tur";
	
	public static List<CihazTur> all() throws SQLException {
		Connection c = DaoFactory.openConnection();
		PreparedStatement pstmt = c.prepareStatement(ALL);

		ResultSet rset = pstmt.executeQuery();
		List<CihazTur> cihazTurleri = new ArrayList<CihazTur>();
		while (rset.next()){
			CihazTur cihazTur = new CihazTur(rset.getInt("id"), rset.getString("ad"));
					
			cihazTurleri.add(cihazTur);
		}

		pstmt.close();
		c.close();
		return cihazTurleri;
	}
}
