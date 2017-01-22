package com.zk.cw.batarya;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zk.cw.ozellik_atama.OzellikAtama;
import com.zk.cw.ozellik_atama.OzellikAtamaDAO;

public class BataryaGuncelle {

	public static void main(String[] args) {
		System.exit(0);
		LinkedHashMap<Integer, OzellikAtama> lhm = null;
		OzellikAtamaDAO ozellikAtamaDAO = new OzellikAtamaDAO();
		try {
			lhm = ozellikAtamaDAO.find(36);
			Set<Integer> ks = lhm.keySet();
			Iterator<Integer> itr = ks.iterator();
			while (itr.hasNext()){
				Integer key = itr.next();
				OzellikAtama ozellikAtama = lhm.get(key);
				BataryaKapasite kapasite = new BataryaKapasite();
				BataryaTeknoloji teknoloji = new BataryaTeknoloji();
				BataryaDegisir degisir = new BataryaDegisir();
				
				String pattern = "([0-9]+) mAh\\b";
				Pattern r = Pattern.compile(pattern);
			    Matcher m = r.matcher(ozellikAtama.getDeger());
			    if (m.find()) {
			    	kapasite.setKapasite(m.group(1).trim());
			    	BataryaKapasiteDAO.findBy(kapasite);
					if(kapasite.getId() == null){
						BataryaKapasiteDAO.add(kapasite);
					}
					ozellikAtamaDAO.insert(ozellikAtama,kapasite);
			    }
			    
			    if(ozellikAtama.getDeger().contains("Li-Po")){
			    	teknoloji.setAd("Li-Po");
			    }else if(ozellikAtama.getDeger().contains("Li-Ion")){
			    	teknoloji.setAd("Li-Ion");
			    }
			    if(teknoloji.getAd() != null){
			    	BataryaTeknolojiDAO.findBy(teknoloji);
					if(teknoloji.getId() == null){
						BataryaTeknolojiDAO.add(teknoloji);
					}
					ozellikAtamaDAO.insert(ozellikAtama,teknoloji);

			    }
			    
			    if(ozellikAtama.getDeger().contains("Çıkarılmaz")){
			    	degisir.setAd("Yok");
			    }else if(ozellikAtama.getDeger().contains("Çıkarılabilir ")){
			    	degisir.setAd("Var");
			    }
			    if(degisir.getAd() != null){
			    	BataryaDegisirDAO.findBy(degisir);
					System.out.println(degisir.getId());
			    	if(degisir.getId() == null){
						BataryaDegisirDAO.add(degisir);
					}
					ozellikAtamaDAO.insert(ozellikAtama,degisir);
			    }
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
