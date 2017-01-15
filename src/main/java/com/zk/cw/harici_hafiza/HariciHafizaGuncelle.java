package com.zk.cw.harici_hafiza;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zk.cw.ozellik_atama.OzellikAtama;
import com.zk.cw.ozellik_atama.OzellikAtamaDAO;

public class HariciHafizaGuncelle {

	public static void main(String[] args) {
		//System.exit(0);
		LinkedHashMap<Integer, OzellikAtama> lhm = null;
		OzellikAtamaDAO ozellikAtamaDAO = new OzellikAtamaDAO();
		HariciHafizaTipiDAO hariciHafizaTipiDAO = new HariciHafizaTipiDAO();
		try {
			lhm = HariciHafizaTipiDAO.find();
			Set<Integer> ks = lhm.keySet();
			Iterator<Integer> itr = ks.iterator();
			while (itr.hasNext()){
				Integer key = itr.next();
				OzellikAtama ozellikAtama = lhm.get(key);
				HariciHafizaTipi hariciHafizaTipi = new HariciHafizaTipi();
				String deger = ozellikAtama.getDeger();
				if(deger.contains("microSD")){
					ozellikAtama.setDeger("1");
					hariciHafizaTipiDAO.update(ozellikAtama);
				}
			    String pattern = "([0-9]+ .B)";
				Pattern r = Pattern.compile(pattern);
			    Matcher m = r.matcher(deger);
			    if (m.find()) {
			    	HariciHafizaBuyukluk hariciHafizaBuyukluk = new HariciHafizaBuyukluk();
			    	hariciHafizaBuyukluk.setBuyukluk(m.group(0).trim());
			    	
			    	HariciHafizaBuyuklukDAO.findBy(hariciHafizaBuyukluk);						
			    	if(hariciHafizaBuyukluk.getId() == null){
			    		HariciHafizaBuyuklukDAO.add(hariciHafizaBuyukluk);
					}
			    	ozellikAtama.setOzellikId(51);
			    	ozellikAtama.setDeger(hariciHafizaBuyukluk.getId().toString());
			    	hariciHafizaTipiDAO.insert(ozellikAtama);
			    }
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
