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
import com.zk.cw.yonga_seti.YongaSetiDAO;

public class CpuGuncelle {

	public static void main(String[] args) {
		System.exit(0);
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
				CekirdekHiz cekirdekHiz = new CekirdekHiz();
				Cihaz cihaz = new Cihaz();
				cihaz.setId(ozellikAtama.getCihazId());
				System.out.println(ozellikAtama.getDeger());
				if(ozellikAtama.getDeger().contains("&")){
				    String pattern = "\\b[0-9]x[0-9\\.]+ .Hz\\b";
					Pattern r = Pattern.compile(pattern);
				    Matcher m = r.matcher(ozellikAtama.getDeger());
				    while (m.find()) {
				    	System.out.println(m.group());
				    	String[] arr=null;
				    	arr = m.group().trim().split("x");
				    	cekirdekSayi.setId(Integer.parseInt(arr[0].trim()));
				    	
						cekirdekHiz.setHiz(arr[1].trim());;
				    	CekirdekHizDAO.findBy(cekirdekHiz);
						if(cekirdekHiz.getId() == null){
							CekirdekHizDAO.add(cekirdekHiz);
						}
						CpuSayiHizAtaDAO.add(cihaz,cekirdekSayi,cekirdekHiz);
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
			    	
				    String pattern = "([0-9\\.]+ .Hz)";
					Pattern r = Pattern.compile(pattern);
				    Matcher m = r.matcher(ozellikAtama.getDeger());
				    if (m.find()) {
				    	System.out.println(m.group());
				    	cekirdekHiz.setHiz(m.group(0).trim());
				    	
				    	CekirdekHizDAO.findBy(cekirdekHiz);						
				    	if(cekirdekHiz.getId() == null){
							CekirdekHizDAO.add(cekirdekHiz);
						}
				    	CpuSayiHizAtaDAO.add(cihaz,cekirdekSayi,cekirdekHiz);
				    }

				}
		    	

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
