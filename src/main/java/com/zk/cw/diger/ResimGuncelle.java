package com.zk.cw.diger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import com.zk.cw.cihaz.Cihaz;
import com.zk.cw.cihaz.CihazDAO;
import com.zk.cw.cihaz_resim_galeri.ResimGaleri;
import com.zk.cw.cihaz_resim_galeri.ResimGaleriDAO;

public class ResimGuncelle {

	public static void main(String[] args) {		
		try {
			LinkedHashMap<Integer, Cihaz> lhm = CihazDAO.tumu();
			Set<Integer> ks = lhm.keySet();
			Iterator<Integer> itr = ks.iterator();
			while (itr.hasNext()){
				Integer key = itr.next();
				Cihaz cihaz = lhm.get(key);
				List<ResimGaleri> resimGaleriList = ResimGaleriDAO.findByCihazId(cihaz.getId());
				for(ResimGaleri resimGaleri: resimGaleriList){
					System.out.print(resimGaleri.getId());
				}
			}			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
