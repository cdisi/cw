package com.zk.cw.util;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.zk.cw.uretici.Uretici;

public class GsmParser {
	
	private Document doc;
	
	public GsmParser(String url){  
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
	 
	public String cihazAdiBul(Uretici uretici){
		return doc.select("h1.specs-phone-name-title").first().text().replace(uretici.adAl(), "").trim();
	}
	
	public Integer duyurulmaYilBul(){
		String duyurulma = doc.select("a:contains(Announced)").first().parent().nextElementSibling().text();
		String pattern = "([0-9]{4})";
		Pattern r = Pattern.compile(pattern);
	    Matcher m = r.matcher(duyurulma);
	    if (m.find()) {
	    	return Integer.parseInt(m.group(0));
	    }else{
	    	return 0;
	    }
	}
	
	public String duyurulmaAyBul(){
		String duyurulma = doc.select("a:contains(Announced)").first().parent().nextElementSibling().text();
		String pattern = "([A-Za-z]+)";
		Pattern r = Pattern.compile(pattern);
	    Matcher m = r.matcher(duyurulma);
	    if (m.find()) {
	    	return m.group(0);
	    }else{
	    	return "";
	    }
	}
	
	public String simBul(){
		String sim = doc.select("a:contains(SIM)").first().parent().nextElementSibling().text().replace("Yes", "Var").replace("No", "Yok").replace("Single", "Tek").replace("Dual", "Çift").replace("Micro-SIM", "Mikro-SIM");		
		return sim;
	}

	public String ikigBantBul(){
		return doc.select("a:contains(2G bands)").first().parent().nextElementSibling().text().replaceAll("N/A","Yok");
	}
	
}

