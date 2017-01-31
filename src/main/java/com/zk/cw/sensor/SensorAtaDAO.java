package com.zk.cw.sensor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.zk.cw.cihaz.Cihaz;
import com.zk.cw.cpu.CekirdekSayi;
import com.zk.cw.cpu.CekirdekSayiAta;
import com.zk.cw.cpu.CpuSayiHizAta;
import com.zk.cw.dao_factory.DaoFactory;
import com.zk.cw.ozellik_atama.OzellikAtama;

public class SensorAtaDAO {
	private static final String FIND_BY_ID = "SELECT * FROM sensor_ata WHERE id = ?";		
	private static final String FIND_BY_CIHAZ_ID = "SELECT * FROM sensor_ata WHERE cihaz_id = ?";		
	private static final String FIND_BY_CIHAZ_SENSOR_ID = "SELECT * FROM sensor_ata WHERE cihaz_id = ? AND sensor_id=?";		
	private static final String INSERT = "INSERT INTO sensor_ata (cihaz_id,sensor_id) VALUES (?,?)";
	private static final String FIND_BY_NAME = "SELECT * FROM cpu_cekirdek_sayi WHERE ad = ?";	
	private static final String ALL = "SELECT * FROM cpu_cekirdek_sayi ORDER BY id";
	private static final String DELETE = "DELETE FROM sensor_ata WHERE cihaz_id = ? AND sensor_id=?";		
	
	public static SensorAta add(Cihaz cihaz, Sensor sensor) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setInt(1, cihaz.getId());
		pstmt.setInt(2, sensor.getId());
		
		pstmt.executeUpdate();
		ResultSet rset = pstmt.getGeneratedKeys();
		SensorAta  sensorAta = new SensorAta();
		rset.next();
		sensorAta.setId(rset.getInt(1));
		sensorAta.setCihazId(cihaz.getId());
		sensorAta.setSensorId( sensor.getId());

		pstmt.close();
		c.close();
		
		return sensorAta;
	}
	public static CekirdekSayi findBy(Integer id) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_ID);
		pstmt.setInt(1, id);
		
		CekirdekSayi cekirdekSayi = null;
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			cekirdekSayi = new CekirdekSayi(rset.getInt("id"), rset.getString("ad"));
		}

		pstmt.close();
		c.close();

		return cekirdekSayi;
	}	
	
	public static SensorAta findBy(Cihaz cihaz, Sensor sensor) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_CIHAZ_SENSOR_ID);
		pstmt.setInt(1, cihaz.getId());
		pstmt.setInt(2, sensor.getId());
		
		SensorAta sensorAta = null;
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			sensorAta = new SensorAta(rset.getInt("id"), rset.getInt("cihaz_id"), rset.getInt("sensor_id"));
		}

		pstmt.close();
		c.close();

		return sensorAta;
	}

	public static List<SensorAta> findByCihazId(Integer cihazId) throws SQLException {
		Connection c = DaoFactory.openConnection();
		PreparedStatement pstmt = c.prepareStatement(FIND_BY_CIHAZ_ID);
		pstmt.setInt(1, cihazId);
		ResultSet rset = pstmt.executeQuery();
		List<SensorAta> sensorler = new ArrayList<SensorAta>();
		while (rset.next()){
			SensorAta sensorAta = new SensorAta(rset.getInt("id"), rset.getInt("cihaz_id"), rset.getInt("sensor_id"));
			sensorler.add(sensorAta);
		}

		pstmt.close();
		c.close();
		return sensorler;
	}
	
	public static void delete(Cihaz cihaz, Sensor sensor) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(DELETE);
		pstmt.setInt(1, cihaz.getId());
		pstmt.setInt(2, sensor.getId());
		pstmt.executeUpdate();

		pstmt.close();
		c.close();
		
	}	
}
