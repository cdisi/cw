package com.zk.cw.ekran;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zk.cw.dao_factory.DaoFactory;

public class EkranRenkDAO {
	private static final String ALL = "SELECT * FROM ekran_renk ORDER BY ad";
	private static final String TUM_OZELLIKLER = "SELECT * FROM cihaz_ozellik_atama WHERE ozellik_id=10";
	private static final String FIND_BY_ID = "SELECT * FROM ekran_renk WHERE id = ?";	
	private static final String FIND_BY_NAME = "SELECT * FROM ekran_renk WHERE ad = ?";	
	private static final String INSERT = "INSERT INTO ekran_renk (ad) VALUES (?)";

	public List<EkranRenk> all() throws SQLException {
		Connection c = DaoFactory.openConnection();
		PreparedStatement pstmt = c.prepareStatement(ALL);

		ResultSet rset = pstmt.executeQuery();
		List<EkranRenk> ekranRenkleri = new ArrayList<EkranRenk>();
		while (rset.next()){
			EkranRenk ekranRenk = new EkranRenk(rset.getInt("id"), rset.getString("ad"));
					
			ekranRenkleri.add(ekranRenk);
		}

		pstmt.close();
		c.close();
		return ekranRenkleri;
	}
	
	public EkranRenk findByName(String ad) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_NAME);
		pstmt.setString(1, ad);
		
		EkranRenk ekranRenk = null;
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			ekranRenk = new EkranRenk(rset.getInt("id"), rset.getString("ad"));
		}

		pstmt.close();
		c.close();

		return ekranRenk;
	}	
	
	public EkranRenk findById(Integer id) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_ID);
		pstmt.setInt(1, id);
		
		EkranRenk ekranRenk = null;
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			ekranRenk = new EkranRenk(rset.getInt("id"), rset.getString("ad"));
		}

		pstmt.close();
		c.close();

		return ekranRenk;
	}
	
	public EkranRenk add(EkranRenk ekranRenk) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, ekranRenk.getAd());
		
		pstmt.executeUpdate();
		ResultSet rset = pstmt.getGeneratedKeys();

		rset.next();
		Integer idGenerated = rset.getInt(1);
		ekranRenk.setId(idGenerated);		

		pstmt.close();
		c.close();
		
		return ekranRenk;
	}	
}
