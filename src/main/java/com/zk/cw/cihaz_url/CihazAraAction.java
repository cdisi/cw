package com.zk.cw.cihaz_url;

import com.zk.cw.uretici.Uretici;
import com.zk.cw.uretici.UreticiDAO;
import com.zk.cw.util.Util;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.swing.AbstractAction;
import javax.swing.JFrame;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public final class CihazAraAction extends AbstractAction  {
  
  /** Constructor. */
  public CihazAraAction(JFrame aFrame){
    super("Cihaz ara", null );
    putValue(SHORT_DESCRIPTION, "Cihaz ara..."); 
    putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_A) );
    fFrame = aFrame;
  }
  
  @Override public void actionPerformed(ActionEvent aActionEvent) {
	  UreticiDAO  ureticiDAO = new UreticiDAO();
	  ArrayList<Uretici> ureticiler = ureticiDAO.bul(1);
	  for (Uretici uretici : ureticiler) {
		  System.out.println(uretici);
		  Document doc;
		  try {
			doc = Jsoup.connect(uretici.gsmArenaUrlAl())
					.data("query", "Java")
					  .userAgent("Mozilla")
					  .cookie("auth", "token")
					  .timeout(3000)
					  .post();
			//System.out.println(doc.html());
			Elements elms = doc.select("div.makers > ul > li > a");
			CihazURLDAO dao = new CihazURLDAO();
			for (Element elm : elms) {
				System.out.println("link : " + elm.attr("href"));
				System.out.println("text : " + elm.text());
			}
		  } catch (IOException e) {
			e.printStackTrace();
		  }   
	  }
  }
  
  // PRIVATE
  private JFrame fFrame;
  private static final Logger fLogger = Util.getLogger(CihazAraAction.class);
  
}
