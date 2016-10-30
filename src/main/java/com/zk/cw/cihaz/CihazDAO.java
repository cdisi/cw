package com.zk.cw.cihaz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.zk.cw.dao_factory.DaoFactory;

public class CihazDAO {
	private static Map<Integer, Cihaz> table = new LinkedHashMap<>(); 
	private static final String ALL = "SELECT * FROM cihaz INNER JOIN uretici ON cihaz.uretici_id=uretici.id WHERE uretici.aktif=1 ORDER BY cihaz.duyurulma DESC,cihaz.created_at DESC ";
	
	static {
		try {
			all();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void all() throws SQLException {
		table.clear();
		Connection c = DaoFactory.openConnection();
		PreparedStatement pstmt = c.prepareStatement(ALL);
		ResultSet rset = pstmt.executeQuery();
		while (rset.next()){
			Cihaz cihaz = new Cihaz();
			cihaz.setId(rset.getInt("id"));
			cihaz.setAd(rset.getString("ad"));
			cihaz.setUreticiId(rset.getInt("uretici_id"));
			cihaz.setTuru(rset.getInt("turu"));
			table.put(cihaz.getId(), cihaz);
		}

		pstmt.close();
		c.close();
	}
	
	List<Cihaz> list() {
		List<Cihaz> result = new ArrayList<>(table.values());
		//Collections.sort(result);
		return result;
	}	

}
