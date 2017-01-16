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
		ArkaKameraAtaDAO arkaKameraAtaDAO = new ArkaKameraAtaDAO();
		try {
			lhm = arkaKameraAtaDAO.tumOzellikler();
			Set<Integer> ks = lhm.keySet();
			Iterator<Integer> itr = ks.iterator();
			int i=1;
			while (itr.hasNext()){
				Integer key = itr.next();
				OzellikAtama ozellikAtama = lhm.get(key);
				ArkaKameraCozunurluk arkaKamera = new ArkaKameraCozunurluk();
				Diyafram diyafram = new Diyafram();
				ArkaKameraAta arkaKameraAta = new ArkaKameraAta();
				PikselBuyuklugu pikselBuyuklugu = new PikselBuyuklugu();
				arkaKameraAta.setCihazId(ozellikAtama.getCihazId());
				String pattern = "\\b([0-9\\.\\s]+MP).*(f/[0-9\\.]+).*([0-9]+mm)\\b";
				Pattern r = Pattern.compile(pattern);
			    Matcher m = r.matcher(ozellikAtama.getDeger());
			    while (m.find()) {
			    	// çözünürlük
			    	System.out.println(i+":"+ozellikAtama.getDeger()+":"+m.group(1)+":"+m.group(2)+":"+m.group(3));
			    	arkaKamera.setCozunurluk(m.group(1).trim());
			    	ArkaKameraCozunurlukDAO.findBy(arkaKamera);
					if(arkaKamera.getId() == null){
						ArkaKameraCozunurlukDAO.add(arkaKamera);
					}
					arkaKameraAta.setKameraCozunurlukId(arkaKamera.getId());
					// diyafram
			    	diyafram.setAciklik(m.group(2));
			    	DiyaframDAO.findBy(diyafram);
					if(diyafram.getId() == null){
						DiyaframDAO.add(diyafram);
					}
			    	arkaKameraAta.setDiyaframAcikligiId(diyafram.getId());
					// piksel büyüklüğü
			    	pikselBuyuklugu.setBuyukluk(m.group(3));
			    	PikselBuyukluguDAO.findBy(pikselBuyuklugu);
					if(pikselBuyuklugu.getId() == null){
						PikselBuyukluguDAO.add(pikselBuyuklugu);
					}
			    	arkaKameraAta.setPikselBuyukluguId(pikselBuyuklugu.getId());
			    	
					ArkaKameraAtaDAO.add(arkaKameraAta);
			    }
			    /*
				String pattern2 = "\\bf/[0-9\\.]+\\b";
				Pattern r2 = Pattern.compile(pattern2);
			    Matcher m2 = r2.matcher(ozellikAtama.getDeger());
			    while (m.find()) {
			    	System.out.println(i+":"+ozellikAtama.getDeger()+":"+m.group());
			    	diyafram.setAciklik(m.group());
			    	DiyaframDAO.findBy(diyafram);
					if(diyafram.getId() == null){
						DiyaframDAO.add(diyafram);
					}
			    	arkaKameraAta.setDiyaframAcikligiId(diyafram.getId());
			    	ArkaKameraAtaDAO.update(arkaKameraAta);
			    }
			    */
			    i++;
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
