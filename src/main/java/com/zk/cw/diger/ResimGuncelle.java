package com.zk.cw.diger;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import com.zk.cw.cihaz.Cihaz;
import com.zk.cw.cihaz.CihazDAO;
import com.zk.cw.cihaz_resim.Resim;
import com.zk.cw.cihaz_resim.ResimDAO;
import com.zk.cw.util.ImageResize;

public class ResimGuncelle {

	public static void main(String[] args) {		
		try {
			LinkedHashMap<Integer, Cihaz> lhm = CihazDAO.tumu();
			Set<Integer> ks = lhm.keySet();
			Iterator<Integer> itr = ks.iterator();
			while (itr.hasNext()){
				Integer key = itr.next();
				Cihaz cihaz = lhm.get(key);
				List<Resim> resimList = ResimDAO.findByCihazId(cihaz.getId());
				if(resimList.isEmpty()){
					com.zk.cw.cihaz_resim.Resim resimCihaz = com.zk.cw.cihaz_resim.ResimDAO.findById(cihaz.getResimId());
					Resim resim = new Resim();
					resim.setCihazId(cihaz.getId());
					resim.setKucukResim(ImageResize.reizeFromByte(resimCihaz.getResim(), 40, 0));
					resim.setOrtaResim(ImageResize.reizeFromByte(resimCihaz.getResim(), 160, 0));
					ResimDAO.add(resim);
				}else{
					for(Resim resim: resimList){
						resim.setKucukResim(ImageResize.reizeFromByte(resim.getBuyukResim(), 40, 0));
						resim.setOrtaResim(ImageResize.reizeFromByte(resim.getBuyukResim(), 160, 0));
						ResimDAO.add(resim);
					}
				}
			}			 
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

}
