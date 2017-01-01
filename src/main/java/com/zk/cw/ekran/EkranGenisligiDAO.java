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
	private static final String FIND_BY_ID = "SELECT * FROM cihaz_ekran_buyuklugu WHERE id = ?";	
	private static final String FIND_BY_GENISLIK = "SELECT * FROM cihaz_ekran_buyuklugu WHERE buyukluk = ?";	
	private static final String INSERT = "INSERT INTO cihaz_ekran_buyuklugu (buyukluk) VALUES (?)";


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
	
	public EkranGenisligi findById(Integer id) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_ID);
		pstmt.setInt(1, id);
		
		EkranGenisligi ekranGenisligi = null;
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			ekranGenisligi = new EkranGenisligi(rset.getInt("id"), rset.getString("buyukluk"));
		}

		pstmt.close();
		c.close();

		return ekranGenisligi;
	}	
	
	public EkranGenisligi add(EkranGenisligi ekranGenisligi) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, ekranGenisligi.getGenislik());
		
		pstmt.executeUpdate();
		ResultSet rset = pstmt.getGeneratedKeys();

		rset.next();
		Integer idGenerated = rset.getInt(1);
		ekranGenisligi.setId(idGenerated);		

		pstmt.close();
		c.close();
		
		return ekranGenisligi;
	}		
}
