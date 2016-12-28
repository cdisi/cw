package com.zk.cw.ekran;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zk.cw.dao_factory.DaoFactory;

public class EkranGenisligiDAO {
	
	private static final String ALL = "SELECT * FROM cihaz_ekran_buyuklugu ORDER BY buyukluk";
	private static final String FIND_BY_GENISLIK = "SELECT * FROM cihaz_ekran_buyuklugu WHERE buyukluk = ?";	


	public List<EkranGenisligi> all() throws SQLException {
		Connection c = DaoFactory.openConnection();
		PreparedStatement pstmt = c.prepareStatement(ALL);

		ResultSet rset = pstmt.executeQuery();
		List<EkranGenisligi> ekranGenislikleri = new ArrayList<EkranGenisligi>();
		while (rset.next()){
			EkranGenisligi ekranGenisligi = new EkranGenisligi(rset.getInt("id"), rset.getString("buyukluk"));
					
			ekranGenislikleri.add(ekranGenisligi);
		}

		pstmt.close();
		c.close();
		return ekranGenislikleri;
	}
	
	public EkranGenisligi findByGenislik(String genislik) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_GENISLIK);
		pstmt.setString(1, genislik);
		
		EkranGenisligi ekranGenisligi = null;
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			ekranGenisligi = new EkranGenisligi(rset.getInt("id"), rset.getString("buyukluk"));
		}

		pstmt.close();
		c.close();

		return ekranGenisligi;
	}	
}
