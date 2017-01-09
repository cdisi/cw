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

public class CekirdekSayiDAO {
	private static final String FIND_BY_ID = "SELECT * FROM cpu_cekirdek_sayi WHERE id = ?";		
	private static final String TUM_OZELLIKLER = "SELECT * FROM cihaz_ozellik_atama WHERE ozellik_id=17";
	private static final String INSERT = "INSERT INTO cpu_cekirdek_sayi (sayi) VALUES (?)";
	private static final String INSERT_OZELLIK_ATA = "INSERT INTO cpu_cekirdek_sayi_ata (cihaz_id,cpu_cekirdek_sayi_id) VALUES (?,?)";
	private static final String FIND_BY_NAME = "SELECT * FROM cpu_cekirdek_sayi WHERE ad = ?";	
	private static final String ALL = "SELECT * FROM cpu_cekirdek_sayi ORDER BY id";

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
	
	public static CekirdekSayi findBy(CekirdekSayi cekirdekSayi) throws SQLException {
		Connection c = DaoFactory.openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_NAME);
		pstmt.setString(1, cekirdekSayi.getAd());
		
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			cekirdekSayi.setId(rset.getInt("id"));
			cekirdekSayi.setAd(rset.getString("ad"));
		}

		pstmt.close();
		c.close();

		return cekirdekSayi;
	}	
	
	public static CekirdekSayi add(CekirdekSayi cekirdekSayi) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, cekirdekSayi.getAd());
		
		pstmt.executeUpdate();
		ResultSet rset = pstmt.getGeneratedKeys();

		rset.next();
		Integer idGenerated = rset.getInt(1);
		cekirdekSayi.setId(idGenerated);		

		pstmt.close();
		c.close();
		
		return cekirdekSayi;
	}
	
	public static CekirdekSayiAta addOzellikAta(Cihaz cihaz, CekirdekSayi cekirdekSayi) throws SQLException {
		Connection c = DaoFactory.openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(INSERT_OZELLIK_ATA, PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setInt(1, cihaz.getId());
		pstmt.setInt(2, cekirdekSayi.getId());
		
		pstmt.executeUpdate();
		ResultSet rset = pstmt.getGeneratedKeys();
		CekirdekSayiAta  cekirdekSayiAta = new CekirdekSayiAta();
		rset.next();
		cekirdekSayiAta.setId(rset.getInt(1));
		cekirdekSayiAta.setCihazId(cihaz.getId());
		cekirdekSayiAta.setCpuCekirdekSayiId(cekirdekSayi.getId());

		pstmt.close();
		c.close();
		
		return cekirdekSayiAta;
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
	
	public static List<CekirdekSayi> all() throws SQLException {
		Connection c = DaoFactory.openConnection();
		PreparedStatement pstmt = c.prepareStatement(ALL);

		ResultSet rset = pstmt.executeQuery();
		List<CekirdekSayi> cekirdekSayilar = new ArrayList<CekirdekSayi>();
		while (rset.next()){
			CekirdekSayi cekirdekSayi = new CekirdekSayi(rset.getInt("id"), rset.getString("ad"));
			cekirdekSayilar.add(cekirdekSayi);
		}

		pstmt.close();
		c.close();
		return cekirdekSayilar;
	}
}
