package com.zk.cw.ekran;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zk.cw.ozellik_atama.OzellikAtama;
import com.zk.cw.ozellik_atama.OzellikAtamaDAO;

public class EkranCozunurlukDegistir {
	/*
	public static void main(String[] args) {
		try {
			LinkedHashMap<Integer, OzellikAtama> lhm = EkranDAO.tumOzellikler();
			Set<Integer> ks = lhm.keySet();
			Iterator<Integer> itr = ks.iterator();
			String tip;
			OzellikAtamaDAO ozellikAtamaDAO = new OzellikAtamaDAO();
			EkranCozunurlukDAO ekranCozunurlukDAO = new EkranCozunurlukDAO();
			EkranPPIDAO ekranPPIDAO = new EkranPPIDAO();
			while (itr.hasNext()){
				Integer key = itr.next();
				OzellikAtama ozellikAtama = lhm.get(key);
				EkranCozunurluk ekranCozunurluk = new EkranCozunurluk();
				EkranPPI ekranPPI = new EkranPPI();
			    String pattern = "([0-9]+ x [0-9]+ piksel)";
				Pattern r = Pattern.compile(pattern);
			    Matcher m = r.matcher(ozellikAtama.getDeger());
			    if (m.find()) {
			    	ekranCozunurluk.setCozunurluk(m.group(0).trim());
			    	System.out.println(ekranCozunurluk.getCozunurluk());
			    }else{
			    	System.out.println("çözünürlük bulunamadı");
			    }
			    String pattern2 = "([0-9]+ ppi)";
				Pattern r2 = Pattern.compile(pattern2);
			    Matcher m2 = r2.matcher(ozellikAtama.getDeger());
			    
			    m2 = r2.matcher(ozellikAtama.getDeger());
			    if (m2.find()) {
			    	ekranPPI.setPpi(m2.group(0).trim());
			    	System.out.println(ekranPPI.getPpi());
			    }else{
			    	System.out.println("ppi bulunamadı");
			    }
				
		    	try {		    		
		    		if(ekranCozunurlukDAO.findByName(ekranCozunurluk.getCozunurluk()) == null){
		    			ekranCozunurluk = ekranCozunurlukDAO.add(ekranCozunurluk);
						if(ekranCozunurluk.getId() == null){
							System.out.println("Ekran tipi eklenemdi");
						}
					}else{
						ekranCozunurluk = ekranCozunurlukDAO.findByName(ekranCozunurluk.getCozunurluk());
					}
		    		//ekran çözünürlüğü için özellik atama tablosunu güncelle
		    		ozellikAtamaDAO.update(ozellikAtama,ekranCozunurluk);
		    		//ekran ppi
		    		if(ekranPPI.getPpi() != null){
			    		if(ekranPPIDAO.findByName(ekranPPI.getPpi()) == null){
			    			ekranPPI = ekranPPIDAO.add(ekranPPI);
							if(ekranPPI.getId() == null){
								System.out.println("Ekran ppi eklenemdi");
							}
						}else{
							ekranPPI = ekranPPIDAO.findByName(ekranPPI.getPpi());
						}
			    		if(ozellikAtamaDAO.find(ozellikAtama.getCihazId(), 49) == null)
			    			ozellikAtamaDAO.insert(ozellikAtama,ekranPPI);
			    		else
			    			ozellikAtamaDAO.update(ozellikAtama,ekranPPI);
		    		}		    		
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}			 
		} catch (SQLException e) {
			e.printStackTrace();
		}	

	}
*/
}
