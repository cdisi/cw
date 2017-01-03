package com.zk.cw.sim;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

import com.zk.cw.dao_factory.DaoFactory;
import com.zk.cw.ozellik_atama.OzellikAtama;
import com.zk.cw.ozellik_atama.OzellikAtamaDAO;

public class SimDegistir {
	
	public static void main(String[] args) {
		LinkedHashMap<Integer, OzellikAtama> lhm = null;
		OzellikAtamaDAO ozellikAtamaDAO = new OzellikAtamaDAO();
		try {
			lhm = SimDAO.tumOzellikler();
			Set<Integer> ks = lhm.keySet();
			Iterator<Integer> itr = ks.iterator();
			while (itr.hasNext()){
				Integer key = itr.next();
				OzellikAtama ozellikAtama = lhm.get(key);
				SimSayisi simSayisi = new SimSayisi();
				Sim sim = new Sim();
				if(ozellikAtama.getDeger().contains("veya")){
					simSayisi.setSayi("Tek SIM veya Çift SIM");
				}else if(ozellikAtama.getDeger().contains("Çift SIM")){
					simSayisi.setSayi("Çift SIM");
				}else if(ozellikAtama.getDeger().contains("Yok")){
					simSayisi.setSayi("Yok");
				}else if(ozellikAtama.getDeger().contains("Üç SIM")){
					simSayisi.setSayi("Üç SIM");
				}else{
					simSayisi.setSayi("Tek SIM");
				}
				
				if(ozellikAtama.getDeger().contains("Mikro-SIM")){
					sim.setAd("Mikro-SIM");
				}else if(ozellikAtama.getDeger().contains("Nano-SIM")){
					sim.setAd("Nano-SIM");
				}else if(ozellikAtama.getDeger().contains("Mini-SIM")){
					sim.setAd("Mini-SIM");
				}else if(ozellikAtama.getDeger().contains("Electronic SIM")){
					sim.setAd("e-SIM");
				}else if(ozellikAtama.getDeger().contains("e-SIM")){
					sim.setAd("e-SIM");
				}
				
				if(simSayisi.getSayi() != null){
					simSayisi = SimSayisiDAO.findBy(simSayisi);
					if(simSayisi.getId() == null){
						SimSayisiDAO.add(simSayisi);
					}
				}else{
					System.out.println("Sim sayısı bulunamadı:");
					System.exit(0);;
				}
				
	    		if(ozellikAtamaDAO.find(ozellikAtama.getCihazId(), 9) == null)
	    			ozellikAtamaDAO.insert(ozellikAtama,simSayisi);
	    		else
	    			ozellikAtamaDAO.update(ozellikAtama,simSayisi);
	    		
	    		
				if(sim.getAd() != null){
					sim = SimDAO.findBy(sim);
					if(sim.getId() == null){
						SimDAO.add(sim);
					}
		    		if(ozellikAtamaDAO.find(ozellikAtama.getCihazId(), 50) == null)
		    			ozellikAtamaDAO.insert(ozellikAtama,sim);
		    		else
		    			ozellikAtamaDAO.update(ozellikAtama,sim);					
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}

}
