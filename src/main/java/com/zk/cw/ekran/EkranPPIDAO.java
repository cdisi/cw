package com.zk.cw.ekran;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zk.cw.dao_factory.DaoFactory;

public class EkranPPIDAO {
	private static final String FIND_BY_NAME = "SELECT * FROM ekran_ppi WHERE ppi = ?";	
	private static final String INSERT = "INSERT INTO ekran_ppi (ppi) VALUES (?)";

	public EkranPPI findByName(String ppi) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_NAME);
		pstmt.setString(1, ppi);
		
		EkranPPI ekranPPI = null;
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			ekranPPI = new EkranPPI(rset.getInt("id"), rset.getString("ppi"));
		}

		pstmt.close();
		c.close();

		return ekranPPI;
	}
	
	public EkranPPI add(EkranPPI ekranPPI) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, ekranPPI.getPpi());
		
		pstmt.executeUpdate();
		ResultSet rset = pstmt.getGeneratedKeys();

		rset.next();
		Integer idGenerated = rset.getInt(1);
		ekranPPI.setId(idGenerated);		

		pstmt.close();
		c.close();
		
		return ekranPPI;
	}
}
