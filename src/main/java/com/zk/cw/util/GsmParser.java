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
	    	deger = elm.parent().nextElementSibling().text().replace("Yes", "Var").replace("No", "Yok").replace("Single", "Tek").replace("Dual", "Çift").replace("Micro-SIM", "Mikro-SIM").replace("or", "veya").replace("Triple", "Üç").replace(", dual stand-by", "");		
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
	    	deger = elm.parent().nextElementSibling().text().replaceAll("Quad-core","Dört Çekirdek").replaceAll("Dual-core","Çift Çekirdek").replaceAll("Hexa-core","Altı Çekirdek").trim();		
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
	    	deger = elm.parent().nextElementSibling().text().replace(", check quality", "").replace("No", "Yok").replace("Yes", "Var").replaceAll(".ensor size", "sensör genişliği").replaceAll(".ixel size", "piksel genişliği").replaceAll(".ace detection", "yüz bulma").replaceAll(".ace/smile detection", "yüz/gülümseme algılama").replaceAll(".ouch focus", "dokunmatik odaklama").replaceAll(".eo-tagging", "coğrafi konum etiketleme").replaceAll(".aser & phase detection autofocus", "lazer ve faz algılama otofokus").replaceAll(".utofocus", "otofokus").replaceAll(".ual-LED flash", "Çift LED flaş").replaceAll(".ED flash", "LED flaş").replaceAll(".ptical zoom", "optik zum").trim();		
	    }
		return deger;
	}	
	public String arkaKamOzBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(Features)").first();
	    if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().replace("No", "Yok").replace("Yes", "Var").replaceAll(".ensor size", "sensör genişliği").replaceAll(".ixel size", "piksel genişliği").replaceAll(".ace detection", "yüz bulma").replaceAll(".ace/smile detection", "yüz/gülümseme algılama").replaceAll(".ouch focus", "dokunmatik odaklama").replaceAll(".eo-tagging", "coğrafi konum etiketleme").replaceAll(".aser & phase detection autofocus", "lazer ve faz algılama otofokus").replaceAll(".utofocus", "otofokus").replaceAll(".ual-LED flash", "Çift LED flaş").replaceAll(".ED flash", "LED flaş").replaceAll(".ptical zoom", "optik zum").trim();		
	    }
		return deger;
	}	
	public String videoBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(Video)").first();
	    if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().replace(", check quality", "").replace("No", "Yok").replace("Yes", "Var").trim();		
	    }
		return deger;
	}	
	public String onKamBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(Secondary)").first();
	    if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().replace(", check quality", "").replace("No", "Yok").replace("Yes", "Var").replaceAll(".ensor size", "sensör genişliği").replaceAll(".ixel size", "piksel genişliği").replaceAll(".ace detection", "yüz bulma").replaceAll(".ace/smile detection", "yüz/gülümseme algılama").replaceAll(".ouch focus", "dokunmatik odaklama").replaceAll(".eo-tagging", "coğrafi konum etiketleme").replaceAll(".aser & phase detection autofocus", "lazer ve faz algılama otofokus").replaceAll(".utofocus", "otofokus").replaceAll(".ual-LED flash", "Çift LED flaş").replaceAll(".ED flash", "LED flaş").replaceAll(".ptical zoom", "optik zum").trim();		
	    }
		return deger;
	}	
	public String uyariTipBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(Alert types)").first();
	    if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().replaceAll(".ibration", "Titreşim").replaceAll(".ingtones", "zil sesleri").trim();		
	    }
		return deger;
	}	
	
	public String hoparlorBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(Loudspeaker)").first();
	    if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().replaceAll("Yes", "Var").replaceAll("No", "Yok").trim();		
	    }
		return deger;
	}	
	public String kulGirBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(3.5mm jack)").first();
	    if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().replaceAll("Yes", "Var").replaceAll("No", "Yok").trim();		
	    }
		return deger;
	}	
	public String wlanBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(WLAN)").first();
	    if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().replaceAll("dual-band", "çift anten").replaceAll("Yes", "Var").replaceAll("No", "Yok").trim();		
	    }
		return deger;
	}	
	public String bluetoothBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(Bluetooth)").first();
	    if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().replaceAll("dual-band", "çift anten").replaceAll("Yes", "Var").replaceAll("No", "Yok").trim();		
	    }
		return deger;
	}	
	public String gpsBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(GPS)").first();
	    if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().replaceAll(" with", "").replaceAll("Yes", "Var").replaceAll("No", "Yok").trim();		
	    }
		return deger;
	}	
	public String nfcBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(NFC)").first();
	    if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().replaceAll("Yes", "Var").replaceAll("No", "Yok").trim();		
	    }
		return deger;
	}	
	public String kizilOtBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(Infrared port)").first();
	    if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().replaceAll("Yes", "Var").replaceAll("No", "Yok").trim();		
	    }
		return deger;
	}	
	public String radyoBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(Radio)").first();
	    if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().replaceAll("Yes", "Var").replaceAll("No", "Yok").replaceAll(".adio", "Radyo").replace("To be confirmed", "").trim();		
	    }
		return deger;
	}		
	public String usbBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(USB)").first();
	    if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().replaceAll("Yes", "Var").replaceAll("No", "Yok").replaceAll(" reversible connector", "").trim();		
	    }
		return deger;
	}		
	public String pilBul(){
	    String deger=null;
		Element elm = doc.select("th:contains(Battery)").first();
		if(elm != null){
	    	deger = elm.nextElementSibling().nextElementSibling().text().replaceAll("Non-removable", "Çıkarılamaz").replaceAll("Removable", "Çıkarılabilir").replaceAll("battery", "batarya").trim();		
	    }
		return deger;
	}	
	public String bekSureBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(Stand-by)").first();
		if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().replace("(multimedia)", "").replaceAll("Up to ", "").replaceAll("h", "saat ").replaceAll("min", "dakika").trim();		
	    }
		return deger;
	}	
	public String konSureBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(Talk time)").first();
		if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().replace("(multimedia)", "").replaceAll("Up to ", "").replaceAll("h", "saat ").replaceAll("min", "dakika").replaceAll("(multimedia)", "").trim();		
	    }
		return deger;
	}	
	
	public String sensorBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(Sensors)").first();
		if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().toLowerCase(Locale.ENGLISH).replace("accelerometer", "İvmeölçer").replace("proximity", "Yakınlık Sensörü").replace("compass", "Pusula").replace("fingerprint", "Parmak İzi").replace("color spectrum", "Renk Spektrumu").replace("gyro", "Jiroskop").trim();		
	    }
		return deger;
	}	
	public String mesajBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(Messaging)").first();
		if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().replace("(threaded view)", "").trim();		
	    }
		return deger;
	}	
	public String javaBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(Java)").first();
		if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().replace("Yes", "Var").replace("No", "Yok").trim();		
	    }
		return deger;
	}		
	
}

