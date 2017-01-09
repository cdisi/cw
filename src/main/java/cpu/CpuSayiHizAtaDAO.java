package cpu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.zk.cw.cihaz.Cihaz;
import com.zk.cw.dao_factory.DaoFactory;

public class CpuSayiHizAtaDAO {
	
	private static final String INSERT = "INSERT INTO cpu_sayi_hiz_ata (cihaz_id,sayi_id,hiz_id) VALUES (?,?,?)";
	
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
}
