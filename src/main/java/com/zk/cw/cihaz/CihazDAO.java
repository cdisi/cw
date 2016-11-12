package com.zk.cw.cihaz;

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

import com.zk.cw.dao_factory.DaoFactory;

public class CihazDAO {
	private static Map<Integer, Cihaz> table = new LinkedHashMap<>(); 
	private static final String ALL = "SELECT * FROM cihaz INNER JOIN uretici ON cihaz.uretici_id=uretici.id WHERE uretici.aktif=1 ORDER BY cihaz.duyurulma DESC,cihaz.created_at DESC ";
	private static final String INSERT = "INSERT INTO cihaz (ad,diger_ad,uretici_id,aktif,resim_id,duyurulma,created_at,turu,anasayfa) VALUES (?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE cihaz SET ad=?,diger_ad=?,uretici_id=?,aktif=?,resim_id=?,duyurulma=?,updated_at=?,turu=?,anasayfa=? WHERE id=?";
	
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
			cihaz.setDigerAd(rset.getString("diger_ad"));
			cihaz.setUreticiId(rset.getInt("uretici_id"));
			cihaz.setTuru(rset.getInt("turu"));
			cihaz.setDuyurulma(rset.getString("duyurulma"));
			cihaz.setAnasayfa(rset.getInt("anasayfa"));
			cihaz.setAktif(rset.getInt("aktif"));
			cihaz.setResimId(rset.getInt("resim_id"));
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
	
	public Cihaz add(Cihaz cihaz) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

		pstmt.setString(1, cihaz.getAd());
		pstmt.setString(2, cihaz.getDigerAd());
		pstmt.setInt(3, cihaz.getUreticiId());
		pstmt.setInt(4, cihaz.getAktif());
		pstmt.setInt(5, cihaz.getResimId());
		pstmt.setString(6, cihaz.getDuyurulma());
		
	    Date dNow = new Date( );
	    SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");	      
		pstmt.setString(7, ft.format(dNow));
		pstmt.setInt(8, cihaz.getTuru());
		pstmt.setInt(9, cihaz.getAnasayfa());
		pstmt.executeUpdate();
		ResultSet rset = pstmt.getGeneratedKeys();

		rset.next();
		Integer idGenerated = rset.getInt(1);
		cihaz.setId(idGenerated);

		pstmt.close();
		c.close();
		
		return cihaz;
	}	
	
	public void change(Cihaz cihaz) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(UPDATE);

		pstmt.setString(1, cihaz.getAd());
		pstmt.setString(2, cihaz.getDigerAd());
		pstmt.setInt(3, cihaz.getUreticiId());
		pstmt.setInt(4, cihaz.getAktif());
		pstmt.setInt(5, cihaz.getResimId());
		pstmt.setString(6, cihaz.getDuyurulma());
		
	    Date dNow = new Date( );
	    SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");	      
		pstmt.setString(7, ft.format(dNow));
		pstmt.setInt(8, cihaz.getTuru());
		pstmt.setInt(9, cihaz.getAnasayfa());
		pstmt.setInt(10, cihaz.getId());
		pstmt.executeUpdate();

		pstmt.close();
		c.close();		
	}	

}
