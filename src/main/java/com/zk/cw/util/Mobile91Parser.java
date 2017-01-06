package com.zk.cw.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import javax.imageio.ImageIO;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.zk.cw.cihaz_resim.Resim;
import com.zk.cw.cihaz_resim.ResimDAO;
import com.zk.cw.yeni_cihaz.Cihaz;

public class Mobile91Parser {
	private Document doc;
	
	public Mobile91Parser(String url){
		try {
			doc = Jsoup.connect(url)
				.data("query", "Java")
				  .userAgent("Mozilla")
				  .cookie("auth", "token")
				  .timeout(3000)
				  .post();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void resimleriBul(Cihaz cihaz){
		Elements elms = doc.select("img.product_photos_thumb");
		Resim resim = new Resim();
		if(elms != null){
	    	for(Element elm:elms){
				try {
					URL url = new URL(elm.attr("data-large-src"));
					BufferedImage originalImage = ImageIO.read(url);
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					ImageIO.write( originalImage, "jpg", baos );
					baos.flush();
					resim.setBuyukResim(baos.toByteArray());
					baos.close();
					resim.setKucukResim(ImageResize.resizeKeepAspectRatio(resim.getBuyukResim(),40,53));
					resim.setOrtaResim(ImageResize.resizeKeepAspectRatio(resim.getBuyukResim(),160,212));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				
				try {
					resim.setCihazId(cihaz.getId());
					ResimDAO.add(resim);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
	    	
	    }
	}	
	
}
