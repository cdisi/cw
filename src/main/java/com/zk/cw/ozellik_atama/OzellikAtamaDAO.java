package com.zk.cw.ozellik_atama;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zk.cw.dao_factory.DaoFactory;
import com.zk.cw.ekran.EkranTip;


public class OzellikAtamaDAO {
	
	private static final String FIND = "SELECT * FROM cihaz_ozellik_atama WHERE cihaz_id=? AND ozellik_id=?";	
	private static final String UPDATE = "UPDATE cihaz_ozellik_atama SET cihaz_id=?,kategori_id=?,ozellik_id=?,deger=? WHERE id=?";	

	
	public OzellikAtama find(Integer cihazId, Integer ozellikId) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND);
		pstmt.setInt(1, cihazId);
		pstmt.setInt(2, ozellikId);
		
		OzellikAtama ozellikAtama = null;
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			ozellikAtama = new OzellikAtama(rset.getInt("id"),rset.getInt("cihaz_id"), rset.getInt("kategori_id"), rset.getInt("ozellik_id"),rset.getString("deger"));
		}

		pstmt.close();
		c.close();

		return ozellikAtama;
	}
	
	public OzellikAtama update(OzellikAtama ozellikAtama, EkranTip ekranTip) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(UPDATE);
		pstmt.setInt(1, ozellikAtama.getCihazId());
		pstmt.setInt(2, ozellikAtama.getKategoriId());
		pstmt.setInt(3, ozellikAtama.getOzellikId());
		pstmt.setInt(4, ekranTip.getId());
		pstmt.setInt(5, ozellikAtama.getId());
		
		pstmt.executeUpdate();

		pstmt.close();
		c.close();
		
		return ozellikAtama;
	}	
}
