package cpu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.zk.cw.cihaz.Cihaz;
import com.zk.cw.dao_factory.DaoFactory;
import com.zk.cw.ozellik_atama.OzellikAtama;

public class CekirdekHizDAO {
	private static final String FIND_BY_ID = "SELECT * FROM cpu_hiz WHERE id = ?";		
	private static final String TUM_OZELLIKLER = "SELECT * FROM cihaz_ozellik_atama WHERE ozellik_id=17";
	private static final String INSERT = "INSERT INTO cpu_hiz (hiz) VALUES (?)";
	private static final String INSERT_OZELLIK_ATA = "INSERT INTO cpu_hiz_ata (cihaz_id,cpu_hiz_id) VALUES (?,?)";
	private static final String FIND_BY_NAME = "SELECT * FROM cpu_hiz WHERE hiz = ?";	
	private static final String ALL = "SELECT * FROM cpu_hiz ORDER BY id";

	public static LinkedHashMap<Integer, OzellikAtama> tumOzellikler() throws SQLException {
		LinkedHashMap<Integer, OzellikAtama> lhm = new LinkedHashMap<>(); 
		Connection c = DaoFactory.openConnection();
		PreparedStatement pstmt = c.prepareStatement(TUM_OZELLIKLER);
		ResultSet rset = pstmt.executeQuery();
		while (rset.next()){
			OzellikAtama ozellikAtama = new OzellikAtama(rset.getInt("id"),rset.getInt("cihaz_id"),rset.getInt("kategori_id"),rset.getInt("ozellik_id"),rset.getString("deger"));
			lhm.put(rset.getInt("id"), ozellikAtama);
		}
		pstmt.close();
		c.close();
		return lhm;		
	}
	
	public static CekirdekHiz findBy(CekirdekHiz cekirdekHiz) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_NAME);
		pstmt.setString(1, cekirdekHiz.getHiz());
		
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			cekirdekHiz.setId(rset.getInt("id"));
			cekirdekHiz.setHiz(rset.getString("hiz"));
		}

		pstmt.close();
		c.close();

		return cekirdekHiz;
	}	
	
	public static CekirdekHiz add(CekirdekHiz cekirdekHiz) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, cekirdekHiz.getHiz());
		
		pstmt.executeUpdate();
		ResultSet rset = pstmt.getGeneratedKeys();

		rset.next();
		Integer idGenerated = rset.getInt(1);
		cekirdekHiz.setId(idGenerated);		

		pstmt.close();
		c.close();
		
		return cekirdekHiz;
	}
	
	public static CpuSayiHizAta addOzellikAta(Cihaz cihaz, CekirdekHiz cekirdekHiz) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(INSERT_OZELLIK_ATA, PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setInt(1, cihaz.getId());
		pstmt.setInt(2, cekirdekHiz.getId());
		
		pstmt.executeUpdate();
		ResultSet rset = pstmt.getGeneratedKeys();
		CpuSayiHizAta  cekirdekHizAta = new CpuSayiHizAta();
		rset.next();
		cekirdekHizAta.setId(rset.getInt(1));
		cekirdekHizAta.setCihazId(cihaz.getId());

		pstmt.close();
		c.close();
		
		return cekirdekHizAta;
	}
	public static CekirdekHiz findBy(Integer id) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_ID);
		pstmt.setInt(1, id);
		
		CekirdekHiz cekirdekHiz = null;
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			cekirdekHiz = new CekirdekHiz(rset.getInt("id"), rset.getString("hiz"));
		}

		pstmt.close();
		c.close();

		return cekirdekHiz;
	}	
	
	public static List<CekirdekHiz> all() throws SQLException {
		Connection c = DaoFactory.openConnection();
		PreparedStatement pstmt = c.prepareStatement(ALL);

		ResultSet rset = pstmt.executeQuery();
		List<CekirdekHiz> CekirdekHizlar = new ArrayList<CekirdekHiz>();
		while (rset.next()){
			CekirdekHiz CekirdekHiz = new CekirdekHiz(rset.getInt("id"), rset.getString("hiz"));
			CekirdekHizlar.add(CekirdekHiz);
		}

		pstmt.close();
		c.close();
		return CekirdekHizlar;
	}
}
