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
			while (itr.hasNext()){
				Integer key = itr.next();
				OzellikAtama ozellikAtama = lhm.get(key);
			    EkranTip ekranTip = new EkranTip();
				if(ozellikAtama.getDeger().contains(",")){
			    	String[] ekranTipiArr = ozellikAtama.getDeger().split(",");
			    	ekranTip.setAd(ekranTipiArr[0].trim());			    	
			    }else{
			    	ekranTip.setAd(ozellikAtama.getDeger().trim());	
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
		    		//özellik atama tablosunu güncelle
		    		ozellikAtamaDAO.update(ozellikAtama,ekranTip);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}			 
		} catch (SQLException e) {
			e.printStackTrace();
		}	

	}

}
