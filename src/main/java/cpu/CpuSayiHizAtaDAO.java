package cpu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zk.cw.cihaz.Cihaz;
import com.zk.cw.dao_factory.DaoFactory;

public class CpuSayiHizAtaDAO {
	private static final String FIND_BY_CIHAZ_ID = "SELECT * FROM cpu_sayi_hiz_ata WHERE cihaz_id = ?";		
	private static final String INSERT = "INSERT INTO cpu_sayi_hiz_ata (cihaz_id,sayi_id,hiz_id) VALUES (?,?,?)";
	private static final String UPDATE = "UPDATE cpu_sayi_hiz_ata SET sayi_id=?,hiz_id=? WHERE cihaz_id=?";
	
	public static void add(Cihaz cihaz, CekirdekSayi cekirdekSayi, CekirdekHiz cekirdekHiz) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(INSERT);
		pstmt.setInt(1, cihaz.getId());
		pstmt.setInt(2, cekirdekSayi.getId());
		pstmt.setInt(3, cekirdekHiz.getId());
		
		pstmt.executeUpdate();

		pstmt.close();
		c.close();
	}
	
	public static void update(Cihaz cihaz, CekirdekSayi cekirdekSayi, CekirdekHiz cekirdekHiz) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(UPDATE);
		pstmt.setInt(1, cihaz.getId());
		pstmt.setInt(2, cekirdekSayi.getId());
		pstmt.setInt(3, cekirdekHiz.getId());
		
		pstmt.executeUpdate();

		pstmt.close();
		c.close();
	}	
	
	public static CpuSayiHizAta findBy(Cihaz cihaz) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_CIHAZ_ID);
		pstmt.setInt(1, cihaz.getId());
		
		CpuSayiHizAta cpuSayiHizAta = null;
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			cpuSayiHizAta = new CpuSayiHizAta(rset.getInt("id"),rset.getInt("cihaz_id"),rset.getInt("sayi_id"), rset.getInt("hiz_id"));
		}

		pstmt.close();
		c.close();

		return cpuSayiHizAta;
	}
}
