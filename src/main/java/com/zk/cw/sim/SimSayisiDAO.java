package com.zk.cw.sim;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zk.cw.dao_factory.DaoFactory;
import com.zk.cw.ekran.EkranPPI;

public class SimSayisiDAO {
	
	private static final String FIND_BY_ID = "SELECT * FROM sim_sayisi WHERE id = ?";	
	private static final String FIND_BY_NAME = "SELECT * FROM sim_sayisi WHERE sayi = ?";	
	private static final String INSERT = "INSERT INTO sim_sayisi (sayi) VALUES (?)";
	private static final String ALL = "SELECT * FROM sim_sayisi ORDER BY sayi";

	
	public static SimSayisi findBy(SimSayisi simSayisi) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_NAME);
		pstmt.setString(1, simSayisi.getSayi());
		
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			simSayisi.setId(rset.getInt("id"));
			simSayisi.setSayi(rset.getString("sayi"));
		}

		pstmt.close();
		c.close();

		return simSayisi;
	}
	
	public static SimSayisi add(SimSayisi simSayisi) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, simSayisi.getSayi());
		
		pstmt.executeUpdate();
		ResultSet rset = pstmt.getGeneratedKeys();

		rset.next();
		Integer idGenerated = rset.getInt(1);
		simSayisi.setId(idGenerated);		

		pstmt.close();
		c.close();
		
		return simSayisi;
	}	
}
