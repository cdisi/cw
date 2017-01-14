package harici_hafiza;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zk.cw.dao_factory.DaoFactory;

import os.OS;

public class HariciHafizaTipiDAO {
	private static final String FIND_BY_ID = "SELECT * FROM harici_hafiza_tipi WHERE id = ?";		
	private static final String INSERT = "INSERT INTO harici_hafiza_tipi (ad) VALUES (?)";
	private static final String FIND_BY_NAME = "SELECT * FROM harici_hafiza_tipi WHERE ad = ?";	
	private static final String ALL = "SELECT * FROM harici_hafiza_tipi ORDER BY ad";

	public static OS findBy(OS os) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_NAME);
		pstmt.setString(1, os.getAd());
		
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			os.setId(rset.getInt("id"));
			os.setAd(rset.getString("ad"));
		}

		pstmt.close();
		c.close();

		return os;
	}	
	
	public static OS add(OS os) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, os.getAd());
		
		pstmt.executeUpdate();
		ResultSet rset = pstmt.getGeneratedKeys();

		rset.next();
		Integer idGenerated = rset.getInt(1);
		os.setId(idGenerated);		

		pstmt.close();
		c.close();
		
		return os;
	}
	public static OS findBy(Integer id) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_ID);
		pstmt.setInt(1, id);
		
		OS os = null;
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			os = new OS(rset.getInt("id"), rset.getString("ad"));
		}

		pstmt.close();
		c.close();

		return os;
	}	
	
	public static List<OS> all() throws SQLException {
		Connection c = DaoFactory.openConnection();
		PreparedStatement pstmt = c.prepareStatement(ALL);

		ResultSet rset = pstmt.executeQuery();
		List<OS> osler = new ArrayList<OS>();
		while (rset.next()){
			OS os = new OS(rset.getInt("id"), rset.getString("ad"));
			osler.add(os);
		}

		pstmt.close();
		c.close();
		return osler;
	}
}