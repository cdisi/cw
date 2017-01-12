package com.zk.cw.hafiza;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

import com.zk.cw.cihaz.Cihaz;
import com.zk.cw.ozellik_atama.OzellikAtama;

public class DahiliHafizaGuncelle {
	public static void main(String[] args) {
		System.exit(0);
		LinkedHashMap<Integer, OzellikAtama> lhm = null;
		DahiliHafizaDAO dahiliHafizaDAO = new DahiliHafizaDAO();
		try {
			lhm = DahiliHafizaDAO.tumOzellikler();
			Set<Integer> ks = lhm.keySet();
			Iterator<Integer> itr = ks.iterator();
			while (itr.hasNext()){
				Integer key = itr.next();
				OzellikAtama ozellikAtama = lhm.get(key);
				Cihaz cihaz = new Cihaz();
				cihaz.setId(ozellikAtama.getCihazId());
				String[] buyuklukArr = ozellikAtama.getDeger().split("/");
				for(String buyukluk: buyuklukArr){
					DahiliHafiza dahiliHafiza = new DahiliHafiza();
					dahiliHafiza.setBuyukluk(buyukluk);
					
					if(ozellikAtama.getDeger().contains("GB")){
						if(!dahiliHafiza.getBuyukluk().contains("GB")){
							dahiliHafiza.setBuyukluk(buyukluk+" GB");
						}
					}
					
					if(ozellikAtama.getDeger().contains("MB")){
						if(!dahiliHafiza.getBuyukluk().contains("MB")){
							dahiliHafiza.setBuyukluk(buyukluk+" MB");
						}
					}
					
					if(ozellikAtama.getDeger().contains("kB")){
						if(!dahiliHafiza.getBuyukluk().contains("kB")){
							dahiliHafiza.setBuyukluk(buyukluk+" KB");
						}
					}					
						
					DahiliHafizaDAO.findBy(dahiliHafiza);
					if(dahiliHafiza.getId() == null){
						DahiliHafizaDAO.add(dahiliHafiza);
					}
					DahiliHafizaDAO.addOzellikAta(cihaz, dahiliHafiza);	
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
