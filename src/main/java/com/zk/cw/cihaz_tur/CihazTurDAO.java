package com.zk.cw.cihaz_tur;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.zk.cw.dao_factory.DaoFactory;
import com.zk.cw.exception.InvalidInputException;
import com.zk.cw.uretici.Uretici;

public class CihazTurDAO {
	private static final String ALL = "SELECT * FROM cihaz_tur";
	private static final String FIND_BY_ID = "SELECT * FROM cihaz_tur WHERE id = ?";		
	
	
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
	
	public CihazTur findById(Integer id) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_ID);
		pstmt.setInt(1, id);
		
		CihazTur cihazTur = null;
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			cihazTur = new CihazTur(rset.getInt("id"), rset.getString("ad"));
		}

		pstmt.close();
		c.close();

		return cihazTur;
	}	
}
