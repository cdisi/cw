package com.zk.cw.gpu;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

import com.zk.cw.ozellik_atama.OzellikAtama;
import com.zk.cw.ozellik_atama.OzellikAtamaDAO;

public class GpuGuncelle {
	public static void main(String[] args) {
		System.exit(0);
		LinkedHashMap<Integer, OzellikAtama> lhm = null;
		OzellikAtamaDAO ozellikAtamaDAO = new OzellikAtamaDAO();
		try {
			lhm = GpuDAO.tumOzellikler();
			Set<Integer> ks = lhm.keySet();
			Iterator<Integer> itr = ks.iterator();
			while (itr.hasNext()){
				Integer key = itr.next();
				OzellikAtama ozellikAtama = lhm.get(key);
				Gpu gpu = new Gpu();
				gpu.setAd(ozellikAtama.getDeger().trim());
				GpuDAO.findBy(gpu);
				if(gpu.getId() == null){
					GpuDAO.add(gpu);
				}
	    		ozellikAtamaDAO.update(ozellikAtama,gpu);					
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
