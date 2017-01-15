package com.zk.cw.cpu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zk.cw.cihaz.Cihaz;
import com.zk.cw.dao_factory.DaoFactory;

public class CpuSayiHizAtaDAO {
	private static final String FIND_BY_CIHAZ_ID = "SELECT * FROM cpu_sayi_hiz_ata WHERE cihaz_id = ? ORDER BY id LIMIT 1 OFFSET ?";		
	private static final String INSERT = "INSERT INTO cpu_sayi_hiz_ata (cihaz_id,sayi_id,hiz_id) VALUES (?,?,?)";
	private static final String UPDATE = "UPDATE cpu_sayi_hiz_ata SET cihaz_id=?,sayi_id=?,hiz_id=? WHERE id=?";
	private static final String DELETE = "DELETE FROM cpu_sayi_hiz_ata WHERE id=?";	

	public static void add(CpuSayiHizAta cpuSayiHizAta) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(INSERT);
		pstmt.setInt(1, cpuSayiHizAta.getCihazId());
		pstmt.setInt(2, cpuSayiHizAta.getSayiId());
		pstmt.setInt(3, cpuSayiHizAta.getHizId());
		
		pstmt.executeUpdate();

		pstmt.close();
		c.close();
	}
	
	public static void update(CpuSayiHizAta cpuSayiHizAta) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(UPDATE);
		pstmt.setInt(1, cpuSayiHizAta.getCihazId());
		if(cpuSayiHizAta.getSayiId() == null)
			pstmt.setNull(2, java.sql.Types.INTEGER);
		else			
			pstmt.setInt(2, cpuSayiHizAta.getSayiId());
		
		if(cpuSayiHizAta.getHizId() != null)
			pstmt.setInt(3, cpuSayiHizAta.getHizId());
		else
			pstmt.setNull(3, java.sql.Types.INTEGER);
		pstmt.setInt(4, cpuSayiHizAta.getId());
		
		pstmt.executeUpdate();

		pstmt.close();
		c.close();
	}	
	
	public static CpuSayiHizAta findBy(Cihaz cihaz, Integer offset) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_CIHAZ_ID);
		pstmt.setInt(1, cihaz.getId());
		pstmt.setInt(2, offset);
		
		CpuSayiHizAta cpuSayiHizAta = null;
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			cpuSayiHizAta = new CpuSayiHizAta(rset.getInt("id"),rset.getInt("cihaz_id"),rset.getInt("sayi_id"), rset.getInt("hiz_id"));
		}

		pstmt.close();
		c.close();

		return cpuSayiHizAta;
	}
	
	public static void delete(CpuSayiHizAta cpuSayiHizAta) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(DELETE);
		pstmt.setInt(1, cpuSayiHizAta.getId());
		pstmt.executeUpdate();

		pstmt.close();
		c.close();
		
	}	
}
