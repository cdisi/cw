package com.zk.cw.yonga_seti;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

import com.zk.cw.ozellik_atama.OzellikAtama;
import com.zk.cw.ozellik_atama.OzellikAtamaDAO;

public class YongaSetiGuncelle {

	public static void main(String[] args) {
		System.exit(0);
		LinkedHashMap<Integer, OzellikAtama> lhm = null;
		OzellikAtamaDAO ozellikAtamaDAO = new OzellikAtamaDAO();
		try {
			//lhm = OzellikAtamaDAO.tumOzellikler();
			Set<Integer> ks = lhm.keySet();
			Iterator<Integer> itr = ks.iterator();
			while (itr.hasNext()){
				Integer key = itr.next();
				OzellikAtama ozellikAtama = lhm.get(key);
				YongaSeti yongaSeti = new YongaSeti();
				yongaSeti.setAd(ozellikAtama.getDeger());
				YongaSetiDAO.findBy(yongaSeti);
				if(yongaSeti.getId() == null){
					YongaSetiDAO.add(yongaSeti);
				}
	    		ozellikAtamaDAO.update(ozellikAtama,yongaSeti);					
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
