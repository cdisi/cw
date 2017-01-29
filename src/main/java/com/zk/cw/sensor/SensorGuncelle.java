package com.zk.cw.sensor;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

import com.zk.cw.cihaz.Cihaz;
import com.zk.cw.ozellik_atama.OzellikAtama;
import com.zk.cw.ozellik_atama.OzellikAtamaDAO;

public class SensorGuncelle {

	public static void main(String[] args) {
		System.exit(0);
		LinkedHashMap<Integer, OzellikAtama> lhm = null;
		OzellikAtamaDAO ozellikAtamaDAO = new OzellikAtamaDAO();
		try {
			lhm = ozellikAtamaDAO.find(40);
			Set<Integer> ks = lhm.keySet();
			Iterator<Integer> itr = ks.iterator();
			while (itr.hasNext()){
				Integer key = itr.next();
				OzellikAtama ozellikAtama = lhm.get(key);
				Cihaz cihaz = new Cihaz();
				cihaz.setId(ozellikAtama.getCihazId());
				String[] sensorler = ozellikAtama.getDeger().split(",");
				for(String sensorAd:sensorler){
					Sensor sensor = new Sensor();
					sensor.setAd(sensorAd.trim());
			    	SensorDAO.findByName(sensor);
					if(sensor.getId() == null){
						SensorDAO.add(sensor);
					}
					SensorAtaDAO.add(cihaz,sensor);
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
