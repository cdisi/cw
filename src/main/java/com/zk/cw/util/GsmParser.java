package com.zk.cw.util;

import java.io.IOException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

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
	
	public String resimBul(){
	    String deger=null;
		Element elm = doc.select("div.specs-photo-main a img").first();
	    if(elm != null){
	    	deger = elm.attr("src");
	    }else{
	    	elm = doc.select("div.specs-photo-main img").first();
	    	deger = elm.attr("src");
	    }
		return deger;
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
	

	public String ikiGBantBul(){
		return doc.select("a:contains(2G bands)").first().parent().nextElementSibling().text().replaceAll("N/A","Yok").replaceAll("-.+", "");
	}
	public String ucGBantBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(3G bands)").first();
	    if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().replaceAll("N/A","Yok").replaceAll("-.+", "");
	    }
		return deger;
		
	}	
	public String dortGBantBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(4G bands)").first();
	    if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().replaceAll("N/A","Yok").replaceAll("-.+", "");
	    }
		return deger;
	}	
	public String hizBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(Speed)").first();
	    if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().replaceAll("N/A","Yok");
	    }
		return deger;
	}	
	public String gprsBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(GPRS)").first();
	    if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().replaceAll("N/A","Yok").replaceAll("Yes", "Var").replaceAll("No", "Yok");
	    }
		return deger;
	}	
	public String edgeBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(EDGE)").first();
	    if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().replaceAll("N/A","Yok").replaceAll("Yes", "Var").replaceAll("No", "Yok");
	    }
		return deger;
	}	
	public String boyutBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(Dimensions)").first();
	    if(elm != null){
	    	deger = elm.parent().nextElementSibling().text();	    	
	    }
		String pattern = "(.+mm)";
		Pattern r = Pattern.compile(pattern);
	    Matcher m = r.matcher(deger);
	    if (m.find()) {
	    	return m.group(0);
	    }else{
	    	return "";
	    }
	}
	
	public String agirlikBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(Weight)").first();
	    if(elm != null){
	    	deger = elm.parent().nextElementSibling().text();	    	
	    }
		String pattern = "(.+g)";
		Pattern r = Pattern.compile(pattern);
	    Matcher m = r.matcher(deger);
	    if (m.find()) {
	    	return m.group(0)+"r";
	    }else{
	    	return "";
	    }
	}	
	
	public String simBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(SIM)").first();
	    if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().replace("Yes", "Var").replace("No", "Yok").replace("Single", "Tek").replace("Dual", "Çift").replace("Micro-SIM", "Mikro-SIM").replace("or", "veya").replace("Triple", "Üç");		
	    }
		return deger;
	}
	
	public String ekranTipBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(Type)").first();
	    if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().replace("capacitive", "kapasitif").replace("touchscreen", "dokunmatik").replace("M", " Milyon").replace("colors", "renk");		
	    }
		return deger;
	}	
	public String ekranGenBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(Size)").first();
	    if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().replace("inches", "inç").replaceAll("\\(.+", "").trim();		
	    }
		return deger;
	}	
	public String ekranCozBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(Resolution)").first();
	    if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().replace("pixels", "piksel").replace("~", "piksel yoğunluğu ").replace("pixel density", "").trim();		
	    }
		return deger;
	}	
	public String multiTouchBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(Multitouch)").first();
	    if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().replace("Yes", "Var").replace("No", "Yok").trim();		
	    }
		return deger;
	}	
	public String ekranKorBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(Protection)").first();
	    if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().replace("Yes", "Var").replace("No", "Yok").trim();		
	    }
		return deger;
	}	
	public String osBul(){
	    String deger=null;
		Element elm = doc.select("td.ttl a:contains(OS)").first();
	    if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().replaceAll(", upgradable.+", "").trim();		
	    }
		return deger;
	}	
	public String yongaSetiBul(){
	    String deger=null;
		Element elm = doc.select("td.ttl a:contains(Chipset)").first();
	    if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().trim();		
	    }
		return deger;
	}	
	public String cpuBul(){
	    String deger=null;
		Element elm = doc.select("td.ttl a:contains(CPU)").first();
	    if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().trim();		
	    }
		return deger;
	}	
	
	public String gpuBul(){
	    String deger=null;
		Element elm = doc.select("td.ttl a:contains(GPU)").first();
	    if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().trim();		
	    }
		return deger;
	}	
	public String hafizaKartiBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(Card slot)").first();
	    if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().replace("up to ", "").replaceAll("\\(.+\\)", "").replace("No", "Yok").trim();		
	    	if(deger != "Yok"){
	    		deger += "'a kadar";
	    	}
	    }
		return deger;
	}	
	public String dahiliHafizaBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(Internal)").first();
	    if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().replaceAll("\\(.+\\)", "").replace("or", "veya").trim();		
	    }
		return deger;
	}	
	public String arkaKamBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(Primary)").first();
	    if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().toLowerCase(Locale.ENGLISH).replace("no", "Yok").replace("laser & phase detection autofocus", "lazer ve faz algılama otofokus").replace("autofocus", "otofokus").replace("dual-LED flash", "Çift LED flaş").replace("LED flash", "LED flaş").replace("optical zoom", "optik zum").trim();		
	    }
		return deger;
	}	
	public String arkaKamOzBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(Features)").first();
	    if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().replace("No", "Yok").replace("Yes", "Var").toLowerCase(Locale.ENGLISH).replace("sensor size", "sensör genişliği").replace("pixel size", "piksel genişliği").replace("face detection", "yüz bulma").replace("face/smile detection", "yüz/gülümseme algılama").replace("touch focus", "dokunmatik odaklama").replace("geo-tagging", "coğrafi konum etiketleme").replace("laser & phase detection autofocus", "lazer ve faz algılama otofokus").replace("autofocus", "otofokus").replace("dual-LED flash", "Çift LED flaş").replace("LED flash", "LED flaş").replace("optical zoom", "optik zum").trim();		
	    }
		return deger;
	}	
	public String videoBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(Video)").first();
	    if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().replace("No", "Yok").replace("Yes", "Var").trim();		
	    }
		return deger;
	}	
	public String onKamBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(Secondary)").first();
	    if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().replace("No", "Yok").toLowerCase(Locale.ENGLISH).replace("laser & phase detection autofocus", "lazer ve faz algılama otofokus").replace("autofocus", "otofokus").replace("dual-LED flash", "Çift LED flaş").replace("LED flash", "LED flaş").replace("optical zoom", "optik zum").trim();		
	    }
		return deger;
	}	
	
}

