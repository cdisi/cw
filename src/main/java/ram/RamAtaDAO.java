package ram;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zk.cw.cihaz.Cihaz;
import com.zk.cw.dao_factory.DaoFactory;

public class RamAtaDAO {
	private static final String DELETE = "DELETE FROM ram_ata WHERE id=?";	
	private static final String FIND_BY_CIHAZ_ID = "SELECT * FROM ram_ata WHERE cihaz_id = ?";		
	private static final String INSERT = "INSERT INTO ram_ata (cihaz_id,ram_id) VALUES (?,?)";
	private static final String UPDATE = "UPDATE ram_ata SET cihaz_id=?,ram_id=? WHERE id=?";

	public static void add(RamAta ramAta) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(INSERT);
		pstmt.setInt(1, ramAta.getCihazId());
		pstmt.setInt(2, ramAta.getRamId());
		
		pstmt.executeUpdate();

		pstmt.close();
		c.close();
	}
	
	public static void update(RamAta ramAta) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(UPDATE);
		pstmt.setInt(1, ramAta.getCihazId());
		pstmt.setInt(2, ramAta.getRamId());
		pstmt.setInt(3, ramAta.getId());
		
		pstmt.executeUpdate();

		pstmt.close();
		c.close();
	}
	
	public static RamAta findBy(Cihaz cihaz) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_CIHAZ_ID);
		pstmt.setInt(1, cihaz.getId());
		
		RamAta ramAta = null;
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			ramAta = new RamAta(rset.getInt("id"),rset.getInt("cihaz_id"),rset.getInt("ram_id"));
		}

		pstmt.close();
		c.close();

		return ramAta;
	}	
	
	public static void delete(RamAta ramAta) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(DELETE);
		pstmt.setInt(1, ramAta.getId());
		pstmt.executeUpdate();

		pstmt.close();
		c.close();
		
	}	
}
