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

import com.zk.cw.yeni_cihaz.Cihaz;
import com.zk.cw.yeni_cihaz.ResimGalerisiDAO;

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
		byte[] buyukResim=null;
		byte[] kucukResim =null;
		if(elms != null){
	    	for(Element elm:elms){
				try {
					BufferedImage originalImage = ImageIO.read(new URL(elm.attr("data-thumb-src")));
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					ImageIO.write( originalImage, "jpg", baos );
					baos.flush();
					kucukResim = baos.toByteArray();
					baos.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					BufferedImage originalImage = ImageIO.read(new URL(elm.attr("data-large-src")));
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					ImageIO.write( originalImage, "jpg", baos );
					baos.flush();
					buyukResim = baos.toByteArray();
					baos.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				
				try {
					ResimGalerisiDAO.add(cihaz, kucukResim, buyukResim);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
	    	
	    }
	}	
	
}
