package com.zk.cw.kamera;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zk.cw.cihaz.Cihaz;
import com.zk.cw.cpu.CekirdekHizDAO;
import com.zk.cw.cpu.CpuSayiHizAtaDAO;
import com.zk.cw.ozellik_atama.OzellikAtama;

public class ArkaKameraGuncelle {

	public static void main(String[] args) {
		//System.exit(0);
		LinkedHashMap<Integer, OzellikAtama> lhm = null;
		ArkaKameraCozunurlukDAO arkaKameraDAO = new ArkaKameraCozunurlukDAO();
		try {
			lhm = arkaKameraDAO.tumOzellikler();
			Set<Integer> ks = lhm.keySet();
			Iterator<Integer> itr = ks.iterator();
			int i=1;
			while (itr.hasNext()){
				Integer key = itr.next();
				OzellikAtama ozellikAtama = lhm.get(key);
				ArkaKameraCozunurluk arkaKamera = new ArkaKameraCozunurluk();
				Cihaz cihaz = new Cihaz();
				cihaz.setId(ozellikAtama.getCihazId());
				String pattern = "\\b[0-9\\.\\s]+MP\\b";
				Pattern r = Pattern.compile(pattern);
			    Matcher m = r.matcher(ozellikAtama.getDeger());
			    while (m.find()) {
			    	System.out.println(i+":"+ozellikAtama.getDeger()+":"+m.group());
			    	arkaKamera.setCozunurluk(m.group());
			    	ArkaKameraCozunurlukDAO.findBy(arkaKamera);
					if(arkaKamera.getId() == null){
						ArkaKameraCozunurlukDAO.add(arkaKamera);
					}
					ArkaKameraAtaDAO.add(cihaz,arkaKamera);
			    }
			    i++;
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
