package com.zk.cw.yeni_cihaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import org.apache.commons.io.FilenameUtils;

import com.zk.cw.cihaz_resim.Resim;
import com.zk.cw.cihaz_resim.ResimDAO;
import com.zk.cw.ekran.EkranCozunurluk;
import com.zk.cw.ekran.EkranCozunurlukDAO;
import com.zk.cw.ekran.EkranDAO;
import com.zk.cw.ekran.EkranGenisligi;
import com.zk.cw.ekran.EkranGenisligiDAO;
import com.zk.cw.ekran.EkranPPI;
import com.zk.cw.ekran.EkranPPIDAO;
import com.zk.cw.ekran.EkranRenk;
import com.zk.cw.ekran.EkranRenkDAO;
import com.zk.cw.ekran.EkranTip;
import com.zk.cw.exception.InvalidInputException;
import com.zk.cw.main.MainWindow;
import com.zk.cw.sim.Sim;
import com.zk.cw.sim.SimDAO;
import com.zk.cw.sim.SimSayisi;
import com.zk.cw.sim.SimSayisiDAO;
import com.zk.cw.uretici.Uretici;
import com.zk.cw.util.Edit;
import com.zk.cw.util.ImageResize;
import com.zk.cw.util.Mobile91Parser;
import com.zk.cw.util.Util;

public class CihazController implements ActionListener {
	
	// PRIVATE 
	private final CihazView fView;
	private Cihaz fCihaz;
	private Edit fEdit;	
	private JButton fEditButton;
	private YeniCihazDAO cihazDAO = new YeniCihazDAO();
	private Uretici uretici;
	private static List<CihazOzellikAtama> cihazOzellikAtamaList = new ArrayList<CihazOzellikAtama>();
	
	CihazController(CihazView aView, JButton aEditButton, Edit aEdit, Uretici uretici){
		fView = aView;
		fEdit = aEdit;
		fEditButton = aEditButton;
		this.uretici = uretici;
	}
	
	@Override 
	public void actionPerformed(ActionEvent aEvent){
		fEditButton.setEnabled(false);
		cihazOzellikAtamaList.clear();
		try {
	      createValidCihazFromUserInput();
	        if(!fView.getDigerAd().equals("")){
				fCihaz.setDigerAd(fView.getDigerAd());
			}
			
	        if(!fView.getIkiGBant().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(1,1, fView.getIkiGBant().trim()));
			}
			if(!fView.getUcGBant().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(1,2, fView.getUcGBant().trim()));
			}
			if(!fView.getDortGBant().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(1,3, fView.getDortGBant().trim()));
			}
			if(!fView.getHiz().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(1,4, fView.getHiz().trim()));
			}
			if(!fView.getGprs().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(1,5, fView.getGprs().trim()));
			}
			if(!fView.getGprs().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(1,6, fView.getEdge().trim()));
			}
			if(!fView.getDuyurulmaYil().equals("")){
				fCihaz.setDuyurulma(fView.getDuyurulmaYil());
			}
			if(!fView.getDuyurulmaAy().equals("")){
				if(fCihaz.getDuyurulma().equals(""))
					fCihaz.setDuyurulma(fView.getDuyurulmaYil());
				else
					fCihaz.setDuyurulma(fCihaz.getDuyurulma()+"-"+fView.getDuyurulmaAy());
			}
			Object cTur = fView.getCihazTur();
			fCihaz.setTuru(((CihazTur)cTur).getValue());
			
			if(!fView.getBoyutlar().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(2,7, fView.getBoyutlar().trim()));
			}
			if(!fView.getAgirlik().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(2,8, fView.getAgirlik().trim()));
			}
			if(!fView.getSim().equals("")){
				SimSayisi simSayisi = new SimSayisi();
				Sim sim = new Sim();
				if(fView.getSim().contains(",")){
					String[] simArr = fView.getSim().split(",");
					try {
						simSayisi.setSayi(simArr[0].trim());
						SimSayisiDAO.findBy(simSayisi);
						if(simSayisi.getId() == null){
							SimSayisiDAO.add(simSayisi);
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					cihazOzellikAtamaList.add(new CihazOzellikAtama(2,9, simSayisi.getId().toString()));
					sim.setAd(simArr[1].trim());
					try {
						SimDAO.findBy(sim);
						if(sim.getId() == null){
							SimDAO.add(sim);
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					cihazOzellikAtamaList.add(new CihazOzellikAtama(2,50, sim.getId().toString()));


				}
			}
			if(!fView.getEkranTip().equals("")){
				EkranDAO ekranDao = new EkranDAO();
				EkranTip ekranTip = new EkranTip();	
				EkranRenkDAO ekranRenkDAO = new EkranRenkDAO();
				EkranRenk ekranRenk = new EkranRenk();
				String[] ekranTipiArr=null;
				if(fView.getEkranTip().contains(",")){
			    	ekranTipiArr = fView.getEkranTip().split(",");
			    	ekranRenk.setAd(ekranTipiArr[1].trim());
			    	try {
			    		if(ekranRenkDAO.findByName(ekranRenk.getAd()) == null){
							ekranRenk = ekranRenkDAO.add(ekranRenk);
							if(ekranRenk.getId() == null){
								System.out.println("Ekran rengi eklenemdi");
							}
						}else{
							ekranRenk = ekranRenkDAO.findByName(ekranRenk.getAd());
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					cihazOzellikAtamaList.add(new CihazOzellikAtama(3,48, ekranRenk.getId().toString()));
			    }
				//ekran tip
		    	ekranTip.setAd(ekranTipiArr[0]);
		    	try {
					if(ekranDao.findByName(ekranTip.getAd()) == null){
						ekranTip = ekranDao.add(ekranTip);
						if(ekranTip.getId() == null){
							System.out.println("Ekran tipi eklenemdi");
						}
					}else{
						ekranTip = ekranDao.findByName(ekranTip.getAd());
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
				cihazOzellikAtamaList.add(new CihazOzellikAtama(3,10, ekranTip.getId().toString()));
			}
			// ekran genişliği
			if(!fView.getEkranGen().equals("")){
				EkranGenisligiDAO ekranGenisligiDAO = new EkranGenisligiDAO();
				EkranGenisligi ekranGenisligi = new EkranGenisligi();	
				ekranGenisligi.setGenislik(fView.getEkranGen().trim());
				try {
					ekranGenisligiDAO.findByGenislik(ekranGenisligi.getGenislik());
					if(ekranGenisligi == null){
						ekranGenisligiDAO.add(ekranGenisligi);
						if(ekranGenisligi.getId() == null){
							System.out.println("Ekran tipi eklenemdi");
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				cihazOzellikAtamaList.add(new CihazOzellikAtama(3,11, ekranGenisligi.getGenislik()));
			}
			if(!fView.getEkranCoz().equals("")){
				EkranCozunurlukDAO ekranCozunurlukDAO = new EkranCozunurlukDAO();
				EkranCozunurluk ekranCozunurluk = new EkranCozunurluk();	
				EkranPPIDAO ekranPPIDAO = new EkranPPIDAO();
				EkranPPI ekranPPI = new EkranPPI();
				String[] ekranCozArr=null;
				String ekranCoz = fView.getEkranCoz();
				if(fView.getEkranCoz().contains(",")){
					ekranCozArr = fView.getEkranCoz().split(",");
					ekranCoz = ekranCozArr[0];
					ekranPPI.setPpi(ekranCozArr[1].trim());
			    	try {
			    		if(ekranPPIDAO.findBy(ekranPPI.getPpi()) == null){
			    			ekranPPIDAO.add(ekranPPI);
							if(ekranPPI.getId() == null){
								System.out.println("Ekran ppi eklenemedi");
							}
						}else{
							ekranPPI = ekranPPIDAO.findBy(ekranPPI.getPpi());
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					cihazOzellikAtamaList.add(new CihazOzellikAtama(3,49, ekranPPI.getId().toString()));
			    }
				//ekran çöz
		    	try {
		    		ekranCozunurluk.setCozunurluk(ekranCoz);
		    		if(ekranCozunurlukDAO.findBy(ekranCozunurluk.getCozunurluk()) == null){
						ekranCozunurlukDAO.add(ekranCozunurluk);
						if(ekranCozunurluk.getId() == null){
							System.out.println("Ekran çöz eklenemedi");
						}
					}else{
						ekranCozunurluk = ekranCozunurlukDAO.findBy(ekranCozunurluk.getCozunurluk());
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				cihazOzellikAtamaList.add(new CihazOzellikAtama(3,12, ekranCozunurluk.getId().toString()));
			}
			if(!fView.getMultiTouch().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(3,13, fView.getMultiTouch().trim()));
			}
			if(!fView.getEkranKor().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(3,14, fView.getEkranKor().trim()));
			}
			if(!fView.getEkranDig().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(3,44, fView.getEkranDig().trim()));
			}
			
			String osId=null;
			if(!fView.getOs().equals("")){
	  			  try {
	  				  osId = CihazOS.find(fView.getOs());
	  				  if(osId == null){						
	 	  				 osId = CihazOS.insert(fView.getOs());
					  }
					  cihazOzellikAtamaList.add(new CihazOzellikAtama(4,15, osId));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
			String osSurumId=null;
			if(!fView.getOsSurum().equals("")){
	  			  try {
	  				  osSurumId = CihazOSSurum.find(osId, fView.getOsSurum());
	  				  if(osSurumId == null){
	  					  osSurumId = CihazOSSurum.insert(osId, fView.getOsSurum());
					  }
	  				  cihazOzellikAtamaList.add(new CihazOzellikAtama(4,43, osSurumId));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
			if(!fView.getYongaSeti().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(4,16, fView.getYongaSeti().trim()));
			}
			if(!fView.getCpu().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(4,17, fView.getCpu().trim()));
			}
			if(!fView.getGpu().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(4,18, fView.getGpu().trim()));
			}
			if(!fView.getHafizaKarti().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(5,19, fView.getHafizaKarti().trim()));
			}
			if(!fView.getDahiliHafiza().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(5,20, fView.getDahiliHafiza().trim()));
			}
			if(!fView.getArkaKam().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(6,21, fView.getArkaKam().trim()));
			}
			if(!fView.getArkaKamOz().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(6,22, fView.getArkaKamOz().trim()));
			}
			if(!fView.getVideo().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(6,23, fView.getVideo().trim()));
			}
			if(!fView.getOnKam().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(6,24, fView.getOnKam().trim()));
			}
			if(!fView.getUyariTip().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(7,25, fView.getUyariTip().trim()));
			}
			if(!fView.getHoparlor().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(7,26, fView.getHoparlor().trim()));
			}
			if(!fView.getKulGir().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(7,27, fView.getKulGir().trim()));
			}
			if(!fView.getSesDiger().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(7,28, fView.getSesDiger().trim()));
			}
			
			if(!fView.getWlan().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(8,29, fView.getWlan().trim()));
			}
			if(!fView.getBluetooth().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(8,30, fView.getBluetooth().trim()));
			}
			if(!fView.getGps().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(8,31, fView.getGps().trim()));
			}
			if(!fView.getNfc().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(8,32, fView.getNfc().trim()));
			}
			if(!fView.getKizilOt().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(8,33, fView.getKizilOt().trim()));
			}
			if(!fView.getRadyo().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(8,34, fView.getRadyo().trim()));
			}
			if(!fView.getUsb().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(8,35, fView.getUsb().trim()));
			}
			if(!fView.getPil().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(9,36, fView.getPil().trim()));
			}
			if(!fView.getBekSure().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(9,37, fView.getBekSure().trim()));
			}
			if(!fView.getKonSure().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(9,38, fView.getKonSure().trim()));
			}
			if(!fView.getRenk().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(10,39, fView.getRenk().trim()));
			}

			if(!fView.getSensor().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(10,40, fView.getSensor().trim()));
			}
			if(!fView.getMesaj().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(10,41, fView.getMesaj().trim()));
			}
			if(!fView.getJava().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(10,42, fView.getJava().trim()));
			}
			if(!fView.getDiger().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(10,45, fView.getDiger().trim()));
			}
			if(!fView.getRam().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(10,46, fView.getRam().trim()));
			}
			if(!fView.getGovdeDiger().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(10,47, fView.getGovdeDiger().trim()));
			}
			if(fView.getAnasayfa()){
				fCihaz.setAnasayfa(1);
			}
			if(!fView.getFiyatGrubu().equals("")){
				fCihaz.setFiyatGrubu(fView.getFiyatGrubu());
			}


			
		}
	    catch(InvalidInputException ex){
	      informUserOfProblems(ex);
	    }
		//fCihaz.setResimAdi( ((uretici.adAl()+' '+fCihaz.getAd()).replaceAll("[^\\w-]", "_")).toLowerCase(Locale.ENGLISH)+".jpg");
	    //fCihaz.setResim(fView.getResim());
	    if ( isUserInputValid() ){
    	  try {
    		  fCihaz = cihazDAO.add(fCihaz,uretici);
    		  for(CihazOzellikAtama cihazOzellikAtama : cihazOzellikAtamaList){
					CihazOzellikAtamaDAO.insert(fCihaz, cihazOzellikAtama);
			  }
	  		  if(!fView.getEkranGen().equals("")){
	  			  if(!EkranBuyukluguDAO.find(fView.getEkranGen())){
	  				  EkranBuyukluguDAO.insert(fView.getEkranGen());
	  			  }
	  		  }
			  if(!fView.getMobile91Url().equals("")){
					Mobile91Parser mobile91Parser = new Mobile91Parser(fView.getMobile91Url());
					mobile91Parser.resimleriBul(fCihaz);
			  }else{
				  Resim resim = new Resim();
				  resim.setCihazId(fCihaz.getId());
				  resim.setKucukResim(ImageResize.reizeFromByte(fView.getResim(), 40, 0));
				  resim.setOrtaResim(ImageResize.reizeFromByte(fView.getResim(), 160, 0));				  
				  ResimDAO.add(resim);				  
			  }
	  		  cihazDAO.update(fView.getUrl());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	/*
    	try {
			ImageIO.write(fView.getResim(), "jpg", new File("resources/cihaz/"+fCihaz.getResimAdi()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	*/
	   
    	  fView.closeDialog();
	      CihazMainWindow.getInstance().refreshView();
	    }
	}	
	
	private void createValidCihazFromUserInput() throws InvalidInputException {		    
		fCihaz = new Cihaz(fView.getAd());		    
	}

	private boolean isUserInputValid(){
	    return fCihaz != null;
	}
		  
	private void informUserOfProblems(InvalidInputException aException) {
	    Object[] messages = aException.getErrorMessages().toArray();
	    //JOptionPane.showMessageDialog(
	      //fView.getDialog(), messages, 
	      //"Movie cannot be saved", JOptionPane.ERROR_MESSAGE
	    //);
	}	
}
