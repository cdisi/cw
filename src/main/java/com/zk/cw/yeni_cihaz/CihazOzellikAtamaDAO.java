package com.zk.cw.yeni_cihaz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zk.cw.dao_factory.DaoFactory;


public class CihazOzellikAtamaDAO {
	private static final String
	INSERT = "INSERT INTO cihaz_ozellik_atama (cihaz_id,ozellik_kategori_id,ozellik_id,deger) VALUES (?,?,?,?)";
	private static final String
	UPDATE = "UPDATE cihaz_ozellik_atama SET deger=? WHERE cihaz_id=? AND ozellik_id=?";
	private static final String
	FIND = "SELECT * FROM cihaz_ozellik_atama WHERE cihaz_id = ? AND ozellik_id = ?";
	
	public static void insert(Cihaz cihaz, CihazOzellikAtama cihazOzellikAtama) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(INSERT);

		pstmt.setInt(1, cihaz.getId());
		pstmt.setInt(2, cihazOzellikAtama.getOzellikKategoriId());
		pstmt.setInt(3, cihazOzellikAtama.getOzellikId());
		pstmt.setString(4, cihazOzellikAtama.getDeger());
		pstmt.executeUpdate();

		pstmt.close();
		c.close();
		
	}
	
	public CihazOzellikAtama update(Cihaz cihaz, CihazOzellikAtama cihazOzellikAtama) throws SQLException {
		Connection c = DaoFactory.getDatabase().openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(UPDATE, PreparedStatement.RETURN_GENERATED_KEYS);

		pstmt.setInt(1, cihaz.getId());
		pstmt.setInt(2, cihazOzellikAtama.getOzellikId());
		pstmt.setString(3, cihazOzellikAtama.getDeger());
		pstmt.executeUpdate();

		pstmt.close();
		c.close();
		
		return cihazOzellikAtama;
	}	
	
	public Boolean find(Cihaz cihaz, CihazOzellikAtama cihazOzellikAtama) throws SQLException {
		Connection c = DaoFactory.getDatabase().openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND);
		pstmt.setInt(1, cihaz.getId());
		pstmt.setInt(2, cihazOzellikAtama.getOzellikId());
		System.out.println(pstmt);
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			return true;
		}

		pstmt.close();
		c.close();

		return false;
	}
}
