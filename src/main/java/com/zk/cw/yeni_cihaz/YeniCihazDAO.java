package com.zk.cw.yeni_cihaz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.zk.cw.dao_factory.*;

public class YeniCihazDAO {
	
	private static final Map<Integer, YeniCihaz> table = new LinkedHashMap<>();  
	private static final String ALL = "SELECT * FROM cihaz_url";
	
	static {
		try {
			all();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static List<YeniCihaz> all() throws SQLException {
		ArrayList<YeniCihaz> cihazlar = new ArrayList<YeniCihaz>();
		
		Connection c = DaoFactory.openConnection();
		PreparedStatement pstmt = c.prepareStatement(ALL);

		ResultSet rset = pstmt.executeQuery();
		while (rset.next()){
			YeniCihaz cihaz = new YeniCihaz(rset.getInt("id"),rset.getInt("uretici_id"),rset.getString("url"),
					 rset.getInt("aktif"));
			
			table.put(cihaz.getId(), cihaz);
		}

		pstmt.close();
		c.close();

		return cihazlar;
	}
	
	private static YeniCihaz createCihaz(ResultSet rset) throws SQLException{
		YeniCihaz cihaz = new YeniCihaz(rset.getInt("id"),rset.getInt("uretici_id"),rset.getString("url"),
							 rset.getInt("aktif"));
		return cihaz;
	}	
	
	List<YeniCihaz> list() {
		List<YeniCihaz> result = new ArrayList<>(table.values());
		//Collections.sort(result);
		return result;
	}
}
