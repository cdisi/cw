package com.zk.cw.yeni_cihaz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.zk.cw.dao_factory.*;
import com.zk.cw.uretici.Uretici;

public class YeniCihazDAO {
	
	private static Map<Integer, YeniCihaz> table = new LinkedHashMap<>();  
	private static final String ALL = "SELECT * FROM cihaz_url INNER JOIN uretici ON cihaz_url.uretici_id=uretici.id WHERE uretici.aktif=1 AND cihaz_url.aktif=0";
	private static final String UPDATE = "UPDATE cihaz_url SET aktif=? WHERE url=?";
	private static final String INSERT = "INSERT INTO cihaz (ad,diger_ad,uretici_id,aktif,resim,duyurulma,created_at,turu) VALUES (?,?,?,?,?,?,?,?)";
		
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
			YeniCihaz cihaz = new YeniCihaz(rset.getInt("id"),rset.getInt("uretici_id"),rset.getString("url"),
					 rset.getInt("aktif"));
			table.put(cihaz.getId(), cihaz);
		}

		pstmt.close();
		c.close();

	}
	
	List<YeniCihaz> list() {
		List<YeniCihaz> result = new ArrayList<>(table.values());
		//Collections.sort(result);
		return result;
	}
	
	public Cihaz add(Cihaz cihaz, Uretici uretici) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

		pstmt.setString(1, cihaz.getAd());
		pstmt.setString(2, cihaz.getDigerAd());
		pstmt.setInt(3, uretici.idAl());
		pstmt.setInt(4, 1);
		pstmt.setBytes(5, cihaz.getResim());
		pstmt.setString(6, cihaz.getDuyurulma());
		
	    Date dNow = new Date( );
	    SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");	      
		pstmt.setString(7, ft.format(dNow));
		pstmt.setInt(8, cihaz.getTuru());
		pstmt.executeUpdate();
		ResultSet rset = pstmt.getGeneratedKeys();

		rset.next();
		Integer idGenerated = rset.getInt(1);
		cihaz.setId(idGenerated);

		pstmt.close();
		c.close();
		
		return cihaz;
	}	
	
	public void update(String url) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(UPDATE);

		pstmt.setInt(1, 1);
		pstmt.setString(2, url);
		pstmt.executeUpdate();

		pstmt.close();
		c.close();
	}		
}
