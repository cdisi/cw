package com.zk.cw.util;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.zk.cw.ekran.EkranDAO;
import com.zk.cw.ekran.EkranTip;
import com.zk.cw.uretici.Uretici;
import com.zk.cw.yeni_cihaz.RenkDAO;

public class GsmParser {
	
	private Document doc;
	private Boolean arkaKam = false;
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
	public String digerAdBul(Uretici uretici){
	    String deger=null;
		Element elm = doc.select("div#specs-list p").first();
	    if(elm != null){
	    	deger = elm.text().replaceAll("Also known as ", "").replaceAll(uretici.adAl(), "").replaceAll("This is not a.+", "").replaceAll("Tablet .+", "").trim();
	    }
		return deger;
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
		return doc.select("a:contains(2G bands)").first().parent().nextElementSibling().text().replaceAll("N/A","Yok").replaceAll("\\(.+\\)", "");
	}
	public String ucGBantBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(3G bands)").first();
	    if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().replaceAll("N/A","Yok").replaceAll("-.+", "").replace("(AWS)", "");
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
	    	deger = elm.parent().nextElementSibling().text().replaceAll("N/A","Yok").replaceAll("Yes", "Var").replaceAll("No", "Yok").replace("Up to ", "");
	    }
		return deger;
	}	
	public String edgeBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(EDGE)").first();
	    if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().replaceAll("N/A","Yok").replaceAll("Yes", "Var").replaceAll("No", "Yok").replace("Up to ", "");
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
	    	deger = elm.parent().nextElementSibling().text().replace("Yes", "Var").replace("No", "Yok").replace("Single", "Tek").replace("Dual", "Çift").replace("Micro-SIM", "Mikro-SIM").replace("or", "veya").replace("Triple", "Üç").replace(", dual stand-by", "").replace(" (", ",").replace(")","").replace(" SIM", " Hat");		
	    }
		return deger;
	}
	
	public String ekranTipBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(Type)").first();
	    if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().replaceAll(".apacitive", "Kapasitif").replace("touchscreen", "dokunmatik").replace("M ", " Milyon").replace("K ", " Bin").replace("colors", "").replaceAll(".esistive", "rezistif");		
	    }
	    if(deger.contains(",")){
	    	String[] ekranTipiArr = deger.split(",");
	    	EkranTip ekranTip = new EkranTip(null, ekranTipiArr[0].trim());
	    	EkranDAO ekranDao = new EkranDAO();
	    	try {
	    		if(ekranDao.findByName(ekranTip.getAd()) == null){
					ekranTip = ekranDao.add(ekranTip);
					if(ekranTip.getId() == null){
						System.out.println("Ekran tipi eklenemdi");
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
		return deger;
	}	
	public String ekranGenBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(Size)").first();
	    if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().replaceAll("inches.*", "").trim();		
	    }
		return deger;
	}	
	public String ekranCozBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(Resolution)").first();
	    if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().replace("pixels ", "piksel").replace("~", "").replace(" pixel density", "").replace("(", ", ").replace(")", "").trim();		
	    }
		return deger;
	}	
	public String multiTouchBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(Multitouch)").first();
	    if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().replace("Yes", "Var").replace("No", "Yok").replaceAll(", up to.+", "").trim();		
	    }
		return deger;
	}	
	public String ekranKorBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(Protection)").first();
	    if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().replace("(unspecified version)", "").replace("Yes", "Var").replace("No", "Yok").trim();		
	    }
		return deger;
	}	
	public String[] osBul(){
	    String deger[]=null;
		Element elm = doc.select("td.ttl a:contains(OS)").first();
	    if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().replaceAll(", upgradable.+", "").replace("based wearable", "tabanlı giyilebilir").trim().split(",");	
	    	
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
	    	deger = elm.parent().nextElementSibling().text().replaceAll("Deca-core", "On Çekirdek").replaceAll("Quad-core","Dört Çekirdek").replaceAll("Dual-core","Çift Çekirdek").replaceAll("Hexa-core","Altı Çekirdek").replace("Octa-core", "Sekiz Çekirdek").trim();		
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
	    	deger = elm.parent().nextElementSibling().text().replaceAll("\\(.+\\)", "").replace("No", "Yok").replace("up to", "").trim();		
	    }
		return deger;
	}	
	public String dahiliHafizaBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(Internal)").first();
	    if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().replaceAll("\\(.+\\)", "").replace("or", "veya").trim();		
	    }
		if(deger != null){
		    String pattern = "([0-9\\/?]+.{2}B)";
			Pattern r = Pattern.compile(pattern);
		    Matcher m = r.matcher(deger);
		    if (m.find()) {
		    	deger = m.group(0).trim();
		    }
		}
		return deger;
	}	
	
	public String ramBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(Internal)").first();
	    if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().replaceAll("\\(.+\\)", "").replace("or", "veya").trim();		
	    }
	    if(deger != null){
		    String pattern = "([0-9]+.{2}B) RAM";
			Pattern r = Pattern.compile(pattern);
		    Matcher m = r.matcher(deger);
		    if (m.find()) {
		    	deger = m.group(1).trim();
		    }
	    }
	    
		return deger;
	}
	
	public String arkaKamBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(Primary)").first();
	    if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().replace("(dual tone)", "").replace(", check quality", "").replace("No", "Yok").replace("Yes", "Var").replaceAll("optical zoom", "optik zum").replaceAll(".ensor size", "sensör genişliği").replaceAll(".ixel size", "piksel genişliği").replaceAll(".ace detection", "yüz bulma").replaceAll(".ace/smile detection", "yüz/gülümseme algılama").replaceAll(".ouch focus", "dokunmatik odaklama").replaceAll(".eo-tagging", "coğrafi konum etiketleme").replaceAll(".aser & phase detection autofocus", "lazer ve faz algılama otofokus").replaceAll("phase detection", "faz algılama").replaceAll(".utofocus", "otofokus").replaceAll(".ual-LED", "Çift LED").replaceAll(".ED flash", "LED flaş").replaceAll(".ptical zoom", "optik zum").replace("flash", "flaş").replace("Dual", "Çift").replaceAll(".ixed focus", "Sabit odaklı").trim();		
	    }
		return deger;
	}	
	public String arkaKamOzBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(Features)").first();
	    if(elm != null){
	    	arkaKam = true;
	    	deger = elm.parent().nextElementSibling().text().replace("No", "Yok").replace("Yes", "Var").replaceAll(".ensor size", "sensör genişliği").replaceAll(".ixel size", "piksel genişliği").replaceAll(".ace detection", "yüz bulma").replaceAll(".ace/smile detection", "yüz/gülümseme algılama").replaceAll(".ouch focus", "dokunmatik odaklama").replaceAll(".eo-tagging", "coğrafi konum etiketleme").replaceAll(".aser & phase detection autofocus", "lazer ve faz algılama otofokus").replaceAll(".utofocus", "otofokus").replaceAll(".ual-LED", "Çift LED").replaceAll("flash", "flaş").replaceAll(".ptical zoom", "optik zum").trim();		
	    }
		return deger;
	}	
	public String videoBul(){
	    if(arkaKam == false) return null;
		String deger=null;
	    Element elm = null;
		elm = doc.select("a:contains(Video)").get(1);
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
	    	deger = elm.parent().nextElementSibling().text().replaceAll(".ibration", "Titreşim").replaceAll(".ingtones", "zil sesleri").replace("Polyphonic", "polifonik").trim();		
	    }
		return deger;
	}	
	
	public String hoparlorBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(Loudspeaker)").first();
	    if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().replaceAll(", with.+", "").replaceAll("Yes", "Var").replaceAll("No", "Yok").replaceAll(".ual speakers", "çift hoparlör").trim();		
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
	
	public String sesDigerBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(3.5mm jack)").first();
		if(elm != null){
			elm = elm.parent().parent().nextElementSibling();
	    	if(elm != null)
	    		deger = elm.child(1).text().replaceAll("Active noise cancellation with dedicated mic", "Gürültü önleyici ikinci mikrofon").trim();		
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
	    	deger = elm.parent().nextElementSibling().text().replace("only", "").replaceAll(" with", "").replaceAll("Yes", "Var").replaceAll("No", "Yok").replaceAll("\\(.+\\)", "").trim();		
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
	    	deger = elm.parent().nextElementSibling().text().replaceAll("Yes", "Var").replaceAll("No", "Yok").replaceAll(".adio", "Radyo").replace("To be confirmed", "").replace(", recording", "").trim();		
	    }
		return deger;
	}		
	public String usbBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(USB)").first();
	    if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().replaceAll("Yes", "Var").replaceAll("No", "Yok").replaceAll(" reversible connector", "").replaceAll("\\(.+\\)", "").trim();		
	    }
		return deger;
	}		
	public String pilBul(){
	    String deger=null;
		Element elm = doc.select("th:contains(Battery)").first();
		if(elm != null){
	    	deger = elm.nextElementSibling().nextElementSibling().text().replaceAll("Non-removable", "Çıkarılmaz").replaceAll("Removable", "Çıkarılabilir").replaceAll("battery", "batarya").trim();		
	    }
		return deger;
	}	
	public String bekSureBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(Stand-by)").first();
		if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().replaceAll("\\(.+\\)", "").replaceAll("Up to ", "").replaceAll("h", "saat ").replaceAll("min", "dakika").trim();		
	    }
		return deger;
	}	
	public String konSureBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(Talk time)").first();
		if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().replaceAll("Up to ", "").replaceAll("h", "saat ").replaceAll("min", "dakika").replaceAll("\\(.+\\)", "").trim();		
	    }
		return deger;
	}	
	
	public String renkBul(){
	    String deger=null;
	    String[] renkler=null;
		Element elm = doc.select("a:contains(Colors)").first();
		if(elm != null){
			renkler = elm.parent().nextElementSibling().text().trim().split(",");		
	    }
		String renkTr=null;
		StringBuffer  strBuffer = new StringBuffer ();
		String ayrac="";
		for(String renk:renkler){
			try {
				renkTr = RenkDAO.findByNameEn(renk.trim());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(renkTr != null){
				strBuffer.append(ayrac).append(renkTr);
			}
			ayrac=", ";
		}
		deger = strBuffer.toString();
		return deger;
	}		
	
	public String sensorBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(Sensors)").first();
		if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().toLowerCase(Locale.ENGLISH).replaceAll(".hermometer", "Termometre").replaceAll(".ccelerometer", "İvmeölçer").replaceAll(".roximity", "Yakınlık").replace("compass", "Pusula").replaceAll(".ingerprint", "Parmak İzi").replaceAll(".olor spectrum", "Renk Spektrumu").replaceAll(".yro", "Jiroskop").replaceAll(".eart rate", "Kalp Atış Hızı").replaceAll(".ltimeter", "Yükseklik Ölçer").replaceAll("\\(.+\\)", "").replaceAll(".arometer", "Basınçölçer").replaceAll(".ris scanner", "Göz Tarama").replaceAll(".emperature", "Isı Ölçer").replace("yes","").trim();		
	    }
		return deger;
	}	
	public String mesajBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(Messaging)").first();
		if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().replaceAll(" Instant Messaging", "Anlık Mesajlaşma").replace("(threaded view)", "").trim();		
	    }
		return deger;
	}	
	public String javaBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(Java)").first();
		if(elm != null){
	    	deger = elm.parent().nextElementSibling().text().replace("Yes", "Var").replace("No", "Yok").replaceAll(", via Java.+", "").trim();		
	    }
		return deger;
	}	
	
	public String digerBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(Java)").first();
		if(elm != null){
			elm = elm.parent().parent().nextElementSibling();
	    	if(elm != null)
	    		deger = elm.child(1).text().replaceAll(".redictive text input", "Akıllı metin girişi").replace("Organizer", "Ajanda").replace("player", "oynatıcı").replaceAll(".hoto", "Fotoğraf").replaceAll(".ocument viewer", "Belge görüntüleyici").replaceAll(".ocument", "Döküman").replaceAll("editor", "editörü").replaceAll(".ast battery charging", "Hızlı pil şarzı").replace("Voice memo/dial", "Sesli notlar").replace("viewer", "görüntüleyici").replace("cloud storage", "bulut depolama").replace("Voice dial/commands", "Sesli arama/komut").replaceAll(".oice memo", "Sesli notlar").replace("commands", "komutlar").replace(" -", "<br />- ").replace("wireless charging", "kablosuz şarz").trim();		
	    }
		return deger;
	}
	
	public String fiyatGrupBul(){
	    String deger=null;
		Element elm = doc.select("a:contains(Price group)").first();
	    if(elm != null){
	    	deger = elm.parent().nextElementSibling().child(0).text().trim();		
	    }
		return deger;
	}
	
	
}

