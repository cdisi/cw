package com.zk.cw.hafiza;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zk.cw.cihaz.Cihaz;
import com.zk.cw.dao_factory.DaoFactory;

public class DahiliHafizaAtaDAO {
	private static final String FIND_BY_CIHAZ_ID = "SELECT * FROM dahili_hafiza_ata WHERE cihaz_id = ? ORDER BY id LIMIT 1 OFFSET ?";		
	private static final String DELETE = "DELETE FROM dahili_hafiza_ata WHERE id=?";	

	private static final String INSERT = "INSERT INTO dahili_hafiza_ata (cihaz_id,dahili_hafiza_id) VALUES (?,?)";
	private static final String UPDATE = "UPDATE dahili_hafiza_ata SET cihaz_id=?,dahili_hafiza_id=? WHERE id=?";

	public static void add(DahiliHafizaAta dahiliHafizaAta) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(INSERT);
		pstmt.setInt(1, dahiliHafizaAta.getCihazId());
		pstmt.setInt(2, dahiliHafizaAta.getDahiliHafizaId());
		
		pstmt.executeUpdate();

		pstmt.close();
		c.close();
	}
	
	public static void update(DahiliHafizaAta dahiliHafizaAta) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(UPDATE);
		pstmt.setInt(1, dahiliHafizaAta.getCihazId());
		pstmt.setInt(2, dahiliHafizaAta.getDahiliHafizaId());
		pstmt.setInt(3, dahiliHafizaAta.getId());
		
		pstmt.executeUpdate();

		pstmt.close();
		c.close();
	}
	
	public static DahiliHafizaAta findBy(Cihaz cihaz, Integer offset) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_CIHAZ_ID);
		pstmt.setInt(1, cihaz.getId());
		pstmt.setInt(2, offset);
		
		DahiliHafizaAta dahiliHafizaAta = null;
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			dahiliHafizaAta = new DahiliHafizaAta(rset.getInt("id"),rset.getInt("cihaz_id"),rset.getInt("dahili_hafiza_id"));
		}

		pstmt.close();
		c.close();

		return dahiliHafizaAta;
	}	
	
	public static void delete(DahiliHafizaAta dahiliHafizaAta) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(DELETE);
		pstmt.setInt(1, dahiliHafizaAta.getId());
		pstmt.executeUpdate();

		pstmt.close();
		c.close();
		
	}	
}
