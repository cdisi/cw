package com.zk.cw.ozellik_atama;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zk.cw.dao_factory.DaoFactory;
import com.zk.cw.ekran.EkranCozunurluk;
import com.zk.cw.ekran.EkranPPI;
import com.zk.cw.ekran.EkranRenk;
import com.zk.cw.ekran.EkranTip;


public class OzellikAtamaDAO {
	
	private static final String FIND = "SELECT * FROM cihaz_ozellik_atama WHERE cihaz_id=? AND ozellik_id=?";	
	private static final String INSERT = "INSERT INTO cihaz_ozellik_atama (cihaz_id,kategori_id,ozellik_id,deger) VALUES (?,?,?,?)";	
	private static final String OZELLIK_IDDEN_UPDATE = "UPDATE cihaz_ozellik_atama SET cihaz_id=?,kategori_id=?,ozellik_id=?,deger=? WHERE id=?";	
	private static final String UPDATE = "UPDATE cihaz_ozellik_atama SET kategori_id=?,deger=? WHERE cihaz_id=? AND ozellik_id=?";	
	
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
		
		PreparedStatement pstmt = c.prepareStatement(OZELLIK_IDDEN_UPDATE);
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
	
	public OzellikAtama update(OzellikAtama ozellikAtama, EkranRenk ekranRenk) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(OZELLIK_IDDEN_UPDATE);
		pstmt.setInt(1, ozellikAtama.getCihazId());
		pstmt.setInt(2, ozellikAtama.getKategoriId());
		pstmt.setInt(3, 48);
		pstmt.setInt(4, ekranRenk.getId());
		pstmt.setInt(5, ozellikAtama.getId());
		
		pstmt.executeUpdate();

		pstmt.close();
		c.close();
		
		return ozellikAtama;
	}
	
	public OzellikAtama update(OzellikAtama ozellikAtama, EkranCozunurluk ekranCozunurluk) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(OZELLIK_IDDEN_UPDATE);
		pstmt.setInt(1, ozellikAtama.getCihazId());
		pstmt.setInt(2, ozellikAtama.getKategoriId());
		pstmt.setInt(3, 12);
		pstmt.setInt(4, ekranCozunurluk.getId());
		pstmt.setInt(5, ozellikAtama.getId());
		
		pstmt.executeUpdate();

		pstmt.close();
		c.close();
		
		return ozellikAtama;
	}	
	
	public OzellikAtama update(OzellikAtama ozellikAtama, EkranPPI ekranPPI) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(OZELLIK_IDDEN_UPDATE);
		pstmt.setInt(1, ozellikAtama.getCihazId());
		pstmt.setInt(2, ozellikAtama.getKategoriId());
		pstmt.setInt(3, 12);
		pstmt.setInt(4, ekranPPI.getId());
		pstmt.setInt(5, ozellikAtama.getId());
		
		pstmt.executeUpdate();

		pstmt.close();
		c.close();
		
		return ozellikAtama;
	}	
	
	public OzellikAtama update(OzellikAtama ozellikAtama) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(UPDATE);
		pstmt.setInt(1, ozellikAtama.getKategoriId());
		pstmt.setString(2, ozellikAtama.getDeger());
		pstmt.setInt(3, ozellikAtama.getCihazId());
		pstmt.setInt(4, ozellikAtama.getOzellikId());
		
		pstmt.executeUpdate();

		pstmt.close();
		c.close();
		
		return ozellikAtama;
	}	
	
	public OzellikAtama insert(OzellikAtama ozellikAtama) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(INSERT);
		pstmt.setInt(1, ozellikAtama.getCihazId());
		pstmt.setInt(2, ozellikAtama.getKategoriId());
		pstmt.setInt(3, ozellikAtama.getOzellikId());
		pstmt.setString(4, ozellikAtama.getDeger());
		
		pstmt.executeUpdate();

		pstmt.close();
		c.close();
		
		return ozellikAtama;
	}	
	
	public OzellikAtama insert(OzellikAtama ozellikAtama, EkranRenk ekranRenk) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(INSERT);
		pstmt.setInt(1, ozellikAtama.getCihazId());
		pstmt.setInt(2, ozellikAtama.getKategoriId());
		pstmt.setInt(3, 48);
		pstmt.setInt(4, ekranRenk.getId());
		
		pstmt.executeUpdate();

		pstmt.close();
		c.close();
		
		return ozellikAtama;
	}
	
	public OzellikAtama insert(OzellikAtama ozellikAtama, EkranPPI ekranPPI) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(INSERT);
		pstmt.setInt(1, ozellikAtama.getCihazId());
		pstmt.setInt(2, ozellikAtama.getKategoriId());
		pstmt.setInt(3, 49);
		pstmt.setInt(4, ekranPPI.getId());
		
		pstmt.executeUpdate();

		pstmt.close();
		c.close();
		
		return ozellikAtama;
	}	
}
