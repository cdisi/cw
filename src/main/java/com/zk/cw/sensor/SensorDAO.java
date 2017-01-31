package com.zk.cw.sensor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zk.cw.dao_factory.DaoFactory;

public class SensorDAO {
	private static final String FIND_BY_ID = "SELECT * FROM sensor WHERE id = ?";		
	private static final String INSERT = "INSERT INTO sensor (ad) VALUES (?)";
	private static final String FIND_BY_NAME = "SELECT * FROM sensor WHERE ad = ?";	
	private static final String ALL = "SELECT * FROM sensor ORDER BY ad";

	public static Sensor findByName(Sensor sensor) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_NAME);
		pstmt.setString(1, sensor.getAd());
		
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			sensor.setId(rset.getInt("id"));
			sensor.setAd(rset.getString("ad"));
		}

		pstmt.close();
		c.close();

		return sensor;
	}	
	
	public static Sensor findById(Sensor sensor) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_ID);
		pstmt.setInt(1, sensor.getId());
		
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			sensor.setId(rset.getInt("id"));
			sensor.setAd(rset.getString("ad"));
		}

		pstmt.close();
		c.close();

		return sensor;
	}	
	
	public static Sensor findById(Integer id) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_ID);
		pstmt.setInt(1, id);
		
		ResultSet rset = pstmt.executeQuery();
		Sensor sensor = new Sensor();
		while (rset.next()){
			sensor.setId(rset.getInt("id"));
			sensor.setAd(rset.getString("ad"));
		}

		pstmt.close();
		c.close();

		return sensor;
	}
	
	public static Sensor add(Sensor sensor) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, sensor.getAd());
		
		pstmt.executeUpdate();
		ResultSet rset = pstmt.getGeneratedKeys();

		rset.next();
		Integer idGenerated = rset.getInt(1);
		sensor.setId(idGenerated);		

		pstmt.close();
		c.close();
		
		return sensor;
	}
	
	public static List<Sensor> all() throws SQLException {
		Connection c = DaoFactory.openConnection();
		PreparedStatement pstmt = c.prepareStatement(ALL);

		ResultSet rset = pstmt.executeQuery();
		List<Sensor> sensorler = new ArrayList<Sensor>();
		while (rset.next()){
			Sensor sensor = new Sensor(rset.getInt("id"), rset.getString("ad"));
			sensorler.add(sensor);
		}

		pstmt.close();
		c.close();
		return sensorler;
	}
	
}



