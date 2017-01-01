package com.zk.cw.ekran;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zk.cw.dao_factory.DaoFactory;

public class EkranPPIDAO {
	private static final String FIND_BY_ID = "SELECT * FROM ekran_ppi WHERE id = ?";	
	private static final String FIND_BY_PPI = "SELECT * FROM ekran_ppi WHERE ppi = ?";	
	private static final String INSERT = "INSERT INTO ekran_ppi (ppi) VALUES (?)";
	private static final String ALL = "SELECT * FROM ekran_ppi ORDER BY ppi";
	

	public EkranPPI findBy(String ppi) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_PPI);
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
	
	public EkranPPI findBy(Integer id) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_ID);
		pstmt.setInt(1, id);
		
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
	
	public List<EkranPPI> all() throws SQLException {
		Connection c = DaoFactory.openConnection();
		PreparedStatement pstmt = c.prepareStatement(ALL);

		ResultSet rset = pstmt.executeQuery();
		List<EkranPPI> ekranPPIlar = new ArrayList<EkranPPI>();
		while (rset.next()){
			EkranPPI ekranPPI = new EkranPPI(rset.getInt("id"), rset.getString("ppi"));
					
			ekranPPIlar.add(ekranPPI);
		}

		pstmt.close();
		c.close();
		return ekranPPIlar;
	}	
}
