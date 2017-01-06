package com.zk.cw.denemeler;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import com.zk.cw.cihaz.Cihaz;
import com.zk.cw.cihaz.CihazDAO;
import com.zk.cw.cihaz_resim.Resim;
import com.zk.cw.cihaz_resim.ResimDAO;
import com.zk.cw.util.ImageResize;

public class ResimGuncelle {

	public static void main(String[] args) {	
        System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
		try {
			LinkedHashMap<Integer, Cihaz> lhm = CihazDAO.tumu();
			Set<Integer> ks = lhm.keySet();
			Iterator<Integer> itr = ks.iterator();
			while (itr.hasNext()){
				Integer key = itr.next();
				Cihaz cihaz = lhm.get(key);
				List<Resim> resimList = ResimDAO.findByCihazId(cihaz.getId());
				if(!resimList.isEmpty()){
					for(Resim resim: resimList){
						if(resim.getBuyukResim() != null){
							resim.setKucukResim(ImageResize.resizeKeepAspectRatio(resim.getBuyukResim(),40,53));
							resim.setOrtaResim(ImageResize.resizeKeepAspectRatio(resim.getBuyukResim(),160,212));
							ResimDAO.update(cihaz,resim);
						}
					}
				}
			}			 
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

}
