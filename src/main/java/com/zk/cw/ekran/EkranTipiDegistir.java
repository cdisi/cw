package com.zk.cw.ekran;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import com.zk.cw.ekran.EkranDAO;
import com.zk.cw.ozellik_atama.OzellikAtama;
import com.zk.cw.ozellik_atama.OzellikAtamaDAO;

public class EkranTipiDegistir {

	public static void main(String[] args) {
		try {
			LinkedHashMap<Integer, OzellikAtama> lhm = EkranDAO.tumOzellikler();
			Set<Integer> ks = lhm.keySet();
			Iterator<Integer> itr = ks.iterator();
			String tip;
			OzellikAtamaDAO ozellikAtamaDAO = new OzellikAtamaDAO();
			EkranDAO ekranDao = new EkranDAO();
			EkranRenkDAO ekranRenkDAO = new EkranRenkDAO();
			while (itr.hasNext()){
				Integer key = itr.next();
				OzellikAtama ozellikAtama = lhm.get(key);
			    EkranTip ekranTip = new EkranTip();
			    EkranRenk ekranRenk = new EkranRenk();
				if(ozellikAtama.getDeger().contains(",")){
			    	String[] ekranTipiArr = ozellikAtama.getDeger().split(",");
			    	ekranTip.setAd(ekranTipiArr[0].trim());	
			    	ekranRenk.setAd(ekranTipiArr[1].trim());
			    }else{
			    	ekranTip.setAd(ozellikAtama.getDeger().trim());	
			    	ekranRenk.setAd("");
			    }
				
		    	try {		    		
		    		if(ekranDao.findByName(ekranTip.getAd()) == null){
						ekranTip = ekranDao.add(ekranTip);
						if(ekranTip.getId() == null){
							System.out.println("Ekran tipi eklenemdi");
						}
					}else{
						ekranTip = ekranDao.findByName(ekranTip.getAd());
					}
		    		//ekran tip özellik atama tablosunu güncelle
		    		ozellikAtamaDAO.update(ozellikAtama,ekranTip);
		    		//ekran renk
		    		if(!ekranRenk.getAd().equals("")){
			    		if(ekranRenkDAO.findByName(ekranRenk.getAd()) == null){
							ekranRenk = ekranRenkDAO.add(ekranRenk);
							if(ekranRenk.getId() == null){
								System.out.println("Ekran rengi eklenemdi");
							}
						}else{
							ekranRenk = ekranRenkDAO.findByName(ekranRenk.getAd());
						}
			    		if(ozellikAtamaDAO.find(ozellikAtama.getCihazId(), 48) == null)
			    			ozellikAtamaDAO.insert(ozellikAtama,ekranRenk);
			    		else
			    			ozellikAtamaDAO.update(ozellikAtama,ekranRenk);
		    		}		    		
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}			 
		} catch (SQLException e) {
			e.printStackTrace();
		}	

	}

}
