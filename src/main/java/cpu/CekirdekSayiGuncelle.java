package cpu;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zk.cw.cihaz.Cihaz;
import com.zk.cw.ozellik_atama.OzellikAtama;
import com.zk.cw.ozellik_atama.OzellikAtamaDAO;

public class CekirdekSayiGuncelle {

	public static void main(String[] args) {
		//System.exit(0);
		LinkedHashMap<Integer, OzellikAtama> lhm = null;
		OzellikAtamaDAO ozellikAtamaDAO = new OzellikAtamaDAO();
		try {
			lhm = CekirdekSayiDAO.tumOzellikler();
			Set<Integer> ks = lhm.keySet();
			Iterator<Integer> itr = ks.iterator();
			while (itr.hasNext()){
				Integer key = itr.next();
				OzellikAtama ozellikAtama = lhm.get(key);
				CekirdekSayi cekirdekSayi = new CekirdekSayi();
				Cihaz cihaz = new Cihaz();
				cihaz.setId(ozellikAtama.getCihazId());
				if(ozellikAtama.getDeger().contains("&")){
				    String pattern = "\\b[0-9]x[0-9\\.]+\\b";
					Pattern r = Pattern.compile(pattern);
				    Matcher m = r.matcher(ozellikAtama.getDeger());
				    while (m.find()) {
				    	String[] arr=null;
				    	arr = m.group().trim().split("x");
				    	cekirdekSayi.setId(Integer.parseInt(arr[0].trim()));
				    	CekirdekSayiDAO.addOzellikAta(cihaz, cekirdekSayi);
				    }
				}else{
					if(ozellikAtama.getDeger().contains("Çift Çekirdek")){
						cekirdekSayi.setId(2);					
					}else if(ozellikAtama.getDeger().contains("Dört Çekirdek")){
						cekirdekSayi.setId(4);
					}else if(ozellikAtama.getDeger().contains("Altı Çekirdek")){
						cekirdekSayi.setId(6);
					}else if(ozellikAtama.getDeger().contains("Sekiz Çekirdek")){
						cekirdekSayi.setId(8);
					}else if(ozellikAtama.getDeger().contains("On Çekirdek")){
						cekirdekSayi.setId(10);
					}else{
						cekirdekSayi.setId(1);
					}
					CekirdekSayiDAO.addOzellikAta(cihaz, cekirdekSayi);
				}
		    	

	    		//ozellikAtamaDAO.update(ozellikAtama,cekirdekSayi);					
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
