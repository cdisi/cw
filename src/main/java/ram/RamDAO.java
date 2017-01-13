package ram;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.zk.cw.cihaz.Cihaz;
import com.zk.cw.dao_factory.DaoFactory;
import com.zk.cw.hafiza.DahiliHafiza;
import com.zk.cw.hafiza.DahiliHafizaAta;
import com.zk.cw.ozellik_atama.OzellikAtama;

public class RamDAO {
		private static final String FIND_BY_ID = "SELECT * FROM ram WHERE id = ?";		
		private static final String INSERT = "INSERT INTO ram (buyukluk) VALUES (?)";
		private static final String INSERT_OZELLIK_ATA = "INSERT INTO ram_ata (cihaz_id,ram_id) VALUES (?,?)";
		private static final String FIND_BY_NAME = "SELECT * FROM ram WHERE buyukluk = ?";	
		private static final String ALL = "SELECT * FROM ram ORDER BY buyukluk";
		private static final String TUM_OZELLIKLER = "SELECT * FROM cihaz_ozellik_atama WHERE ozellik_id=46";

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
		
		public static Ram findBy(Ram ram) throws SQLException {
			Connection c = DaoFactory.openConnection();

			PreparedStatement pstmt = c.prepareStatement(FIND_BY_NAME);
			pstmt.setString(1, ram.getBuyukluk());
			
			ResultSet rset = pstmt.executeQuery();

			while (rset.next()){
				ram.setId(rset.getInt("id"));
				ram.setBuyukluk(rset.getString("buyukluk"));
			}

			pstmt.close();
			c.close();

			return ram;
		}	
		
		public static Ram findById(Ram ram) throws SQLException {
			Connection c = DaoFactory.openConnection();

			PreparedStatement pstmt = c.prepareStatement(FIND_BY_ID);
			pstmt.setInt(1, ram.getId());
			
			ResultSet rset = pstmt.executeQuery();

			while (rset.next()){
				ram.setId(rset.getInt("id"));
				ram.setBuyukluk(rset.getString("buyukluk"));
			}

			pstmt.close();
			c.close();

			return ram;
		}	
		
		public static Ram add(Ram ram) throws SQLException {
			Connection c = DaoFactory.openConnection();
			
			PreparedStatement pstmt = c.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, ram.getBuyukluk());
			
			pstmt.executeUpdate();
			ResultSet rset = pstmt.getGeneratedKeys();

			rset.next();
			Integer idGenerated = rset.getInt(1);
			ram.setId(idGenerated);		

			pstmt.close();
			c.close();
			
			return ram;
		}
		
		public static List<Ram> all() throws SQLException {
			Connection c = DaoFactory.openConnection();
			PreparedStatement pstmt = c.prepareStatement(ALL);

			ResultSet rset = pstmt.executeQuery();
			List<Ram> ramler = new ArrayList<Ram>();
			while (rset.next()){
				Ram ram = new Ram(rset.getInt("id"), rset.getString("buyukluk"));
				ramler.add(ram);
			}

			pstmt.close();
			c.close();
			return ramler;
		}
		
		public static RamAta addOzellikAta(Cihaz cihaz, Ram ram) throws SQLException {
			Connection c = DaoFactory.openConnection();
			
			PreparedStatement pstmt = c.prepareStatement(INSERT_OZELLIK_ATA, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, cihaz.getId());
			pstmt.setInt(2, ram.getId());
			
			pstmt.executeUpdate();
			ResultSet rset = pstmt.getGeneratedKeys();
			
			RamAta  RamAta = new RamAta();
			
			if(rset.next()){
				RamAta.setId(rset.getInt(1));
				RamAta.setCihazId(cihaz.getId());
				RamAta.setRamId(ram.getId());
			}else{
				System.out.println("ram eklenemedi");
			}

			pstmt.close();
			c.close();
			
			return RamAta;
		}	
		

	}

