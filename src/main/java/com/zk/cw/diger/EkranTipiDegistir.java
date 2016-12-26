package com.zk.cw.diger;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import com.zk.cw.ekran.EkranDAO;
import com.zk.cw.ekran.EkranTip;

public class EkranTipiDegistir {

	public static void main(String[] args) {
		try {
			LinkedHashMap<Integer, EkranTip> lhm = EkranDAO.allOzellikler();
			Set<Integer> ks = lhm.keySet();
			Iterator<Integer> itr = ks.iterator();
			String tip;
			while (itr.hasNext()){
				Integer key = itr.next();
				EkranTip ekranTip = lhm.get(key);
			    if(ekranTip.getAd().contains(",")){
			    	String[] ekranTipiArr = ekranTip.getAd().split(",");
			    	tip = ekranTipiArr[0];			    	
			    }else{
			    	tip = ekranTip.getAd().trim();
			    }
		    	EkranDAO ekranDao = new EkranDAO();
		    	try {
		    		ekranTip = ekranDao.findByName(tip);
		    		if(ekranDao.findByName(tip) == null){
						ekranTip = ekranDao.add(ekranTip);
						if(ekranTip.getId() == null){
							System.out.println("Ekran tipi eklenemdi");
						}
					}
		    		//özellik atama tablosunu güncelle
		    		
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
