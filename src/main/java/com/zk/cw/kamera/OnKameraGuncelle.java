package com.zk.cw.kamera;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zk.cw.ozellik_atama.OzellikAtama;

public class OnKameraGuncelle {

	public static void main(String[] args) {
		System.exit(0);
		LinkedHashMap<Integer, OzellikAtama> lhm = null;
		OnKameraAtaDAO onKameraAtaDAO = new OnKameraAtaDAO();
		try {
			lhm = onKameraAtaDAO.tumOzellikler();
			Set<Integer> ks = lhm.keySet();
			Iterator<Integer> itr = ks.iterator();
			while (itr.hasNext()){
				Integer key = itr.next();
				OzellikAtama ozellikAtama = lhm.get(key);
				
				String patternCoz = "\\b([0-9\\.\\s]+MP)\\b";
				Pattern rCoz = Pattern.compile(patternCoz);
			    Matcher mCoz = rCoz.matcher(ozellikAtama.getDeger());
				
			    String patternDiy = "\\b(f/[0-9\\.]+)\\b";
				Pattern rDiy = Pattern.compile(patternDiy);
			    Matcher mDiy = rDiy.matcher(ozellikAtama.getDeger());
				
			    String patternPik = "\\b([0-9]+mm)\\b";
				Pattern rPik = Pattern.compile(patternPik);
			    Matcher mPik = rPik.matcher(ozellikAtama.getDeger());
			    int count= 0;
			    while (mCoz.find()) {
					KameraCozunurluk arkaKamera = new KameraCozunurluk();
					Diyafram diyafram = new Diyafram();
					OnKameraAta onKameraAta = new OnKameraAta();
					PikselBuyuklugu pikselBuyuklugu = new PikselBuyuklugu();
					onKameraAta.setCihazId(ozellikAtama.getCihazId());

			    	//çözünürlük
			    	System.out.println(ozellikAtama.getCihazId()+":"+ozellikAtama.getDeger()+":"+mCoz.group());
			    	arkaKamera.setCozunurluk(mCoz.group().trim());
			    	KameraCozunurlukDAO.findBy(arkaKamera);
					if(arkaKamera.getId() == null){
						KameraCozunurlukDAO.add(arkaKamera);
					}
					onKameraAta.setKameraCozunurlukId(arkaKamera.getId());
					// diyafram
			    	if(mDiy.find()){
						System.out.println(ozellikAtama.getCihazId()+":"+ozellikAtama.getDeger()+":"+mDiy.group());
				    	diyafram.setAciklik(mDiy.group().trim());
				    	DiyaframDAO.findBy(diyafram);
						if(diyafram.getId() == null){
							DiyaframDAO.add(diyafram);
						}
						onKameraAta.setDiyaframAcikligiId(diyafram.getId());
			    	}
					// piksel büyüklüğü
			    	if(mPik.find()){
						System.out.println(ozellikAtama.getCihazId()+":"+ozellikAtama.getDeger()+":"+mPik.group());
				    	pikselBuyuklugu.setBuyukluk(mPik.group().trim());
				    	PikselBuyukluguDAO.findBy(pikselBuyuklugu);
						if(pikselBuyuklugu.getId() == null){
							PikselBuyukluguDAO.add(pikselBuyuklugu);
						}
						onKameraAta.setPikselBuyukluguId(pikselBuyuklugu.getId());
			    	}
					OnKameraAtaDAO.add(onKameraAta);
					count++;
			    }
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
