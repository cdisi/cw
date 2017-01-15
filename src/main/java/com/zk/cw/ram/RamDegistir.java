package com.zk.cw.ram;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

import com.zk.cw.cihaz.Cihaz;
import com.zk.cw.hafiza.DahiliHafiza;
import com.zk.cw.hafiza.DahiliHafizaDAO;
import com.zk.cw.ozellik_atama.OzellikAtama;

public class RamDegistir {

	public static void main(String[] args) {
		System.exit(0);
		LinkedHashMap<Integer, OzellikAtama> lhm = null;
		RamDAO ramDAO = new RamDAO();
		try {
			lhm = RamDAO.tumOzellikler();
			Set<Integer> ks = lhm.keySet();
			Iterator<Integer> itr = ks.iterator();
			while (itr.hasNext()){
				Integer key = itr.next();
				OzellikAtama ozellikAtama = lhm.get(key);
				Cihaz cihaz = new Cihaz();
				cihaz.setId(ozellikAtama.getCihazId());
				Ram ram = new Ram();
				ram.setBuyukluk(ozellikAtama.getDeger().trim());
				
				RamDAO.findBy(ram);
				if(ram.getId() == null){
					RamDAO.add(ram);
				}
				RamDAO.addOzellikAta(cihaz, ram);	
			}
				
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
