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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import org.apache.commons.io.FilenameUtils;

import com.zk.cw.batarya.BataryaDegisir;
import com.zk.cw.batarya.BataryaDegisirDAO;
import com.zk.cw.batarya.BataryaKapasite;
import com.zk.cw.batarya.BataryaKapasiteDAO;
import com.zk.cw.batarya.BataryaTeknoloji;
import com.zk.cw.batarya.BataryaTeknolojiDAO;
import com.zk.cw.cihaz_resim.Resim;
import com.zk.cw.cihaz_resim.ResimDAO;
import com.zk.cw.cpu.CekirdekHiz;
import com.zk.cw.cpu.CekirdekHizDAO;
import com.zk.cw.cpu.CekirdekSayi;
import com.zk.cw.cpu.CekirdekSayiDAO;
import com.zk.cw.cpu.CpuSayiHizAta;
import com.zk.cw.cpu.CpuSayiHizAtaDAO;
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
import com.zk.cw.gpu.Gpu;
import com.zk.cw.gpu.GpuDAO;
import com.zk.cw.hafiza.DahiliHafiza;
import com.zk.cw.hafiza.DahiliHafizaDAO;
import com.zk.cw.harici_hafiza.HariciHafizaBuyukluk;
import com.zk.cw.harici_hafiza.HariciHafizaBuyuklukDAO;
import com.zk.cw.harici_hafiza.HariciHafizaTipi;
import com.zk.cw.harici_hafiza.HariciHafizaTipiDAO;
import com.zk.cw.kamera.ArkaKameraAta;
import com.zk.cw.kamera.ArkaKameraAtaDAO;
import com.zk.cw.kamera.KameraCozunurluk;
import com.zk.cw.kamera.KameraCozunurlukDAO;
import com.zk.cw.kamera.OnKameraAta;
import com.zk.cw.kamera.OnKameraAtaDAO;
import com.zk.cw.kamera.Diyafram;
import com.zk.cw.kamera.DiyaframDAO;
import com.zk.cw.kamera.PikselBuyuklugu;
import com.zk.cw.kamera.PikselBuyukluguDAO;
import com.zk.cw.main.MainWindow;
import com.zk.cw.ram.Ram;
import com.zk.cw.ram.RamDAO;
import com.zk.cw.sensor.Sensor;
import com.zk.cw.sensor.SensorAta;
import com.zk.cw.sensor.SensorAtaDAO;
import com.zk.cw.sensor.SensorDAO;
import com.zk.cw.sim.Sim;
import com.zk.cw.sim.SimDAO;
import com.zk.cw.sim.SimSayisi;
import com.zk.cw.sim.SimSayisiDAO;
import com.zk.cw.uretici.Uretici;
import com.zk.cw.util.Edit;
import com.zk.cw.util.ImageResize;
import com.zk.cw.util.Mobile91Parser;
import com.zk.cw.util.Util;
import com.zk.cw.yonga_seti.YongaSeti;
import com.zk.cw.yonga_seti.YongaSetiDAO;

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
				String[] simArr = fView.getSim().split(",");
				if(fView.getSim().contains(",")){
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
					if(simArr.length > 1){
						if(simArr[1] != null){
							sim.setAd(simArr[1].trim());
							try {
								SimDAO.findBy(sim);
								if(sim.getId() == null){
									SimDAO.add(sim);
								}
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						cihazOzellikAtamaList.add(new CihazOzellikAtama(2,50, sim.getId().toString()));						
					}



				}
			}
			if(!fView.getEkranTip().equals("")){
				EkranDAO ekranDao = new EkranDAO();
				EkranTip ekranTip = new EkranTip();	
				EkranRenkDAO ekranRenkDAO = new EkranRenkDAO();
				EkranRenk ekranRenk = new EkranRenk();
				String[] ekranTipiArr=null;
		    	ekranTipiArr = fView.getEkranTip().split(",");
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
				YongaSeti yongaSeti = new YongaSeti();
				yongaSeti.setAd(fView.getYongaSeti().trim());
				try {
					YongaSetiDAO.findBy(yongaSeti);
					if(yongaSeti.getId() == null){
						YongaSetiDAO.add(yongaSeti);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

				cihazOzellikAtamaList.add(new CihazOzellikAtama(4,16,yongaSeti.getId().toString() ));
				
			}

			if(!fView.getGpu().equals("")){
				Gpu gpu = new Gpu();
				gpu.setAd(fView.getGpu().trim());
				try {
					GpuDAO.findBy(gpu);
					if(gpu.getId() == null){
						GpuDAO.add(gpu);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				cihazOzellikAtamaList.add(new CihazOzellikAtama(4,18, gpu.getId().toString()));
			}
			if(!fView.getHafizaKarti().equals("Yok")){
				HariciHafizaTipi hariciHafizaTipi = new HariciHafizaTipi();
				HariciHafizaBuyukluk hariciHafizaBuyukluk = new HariciHafizaBuyukluk();
				String[] hariciHafiza = fView.getHafizaKarti().split(",");
				if(hariciHafiza[0] != null){
					hariciHafizaTipi.setAd(hariciHafiza[0].trim());
					try {
						HariciHafizaTipiDAO.findBy(hariciHafizaTipi);
						if(hariciHafizaTipi.getId() == null){
							HariciHafizaTipiDAO.add(hariciHafizaTipi);
						}
						cihazOzellikAtamaList.add(new CihazOzellikAtama(5,19, hariciHafizaTipi.getId().toString()));

					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if(hariciHafiza.length > 1){
					if(hariciHafiza[1] != null){
						hariciHafizaBuyukluk.setBuyukluk(hariciHafiza[1].trim());
						try {
							HariciHafizaBuyuklukDAO.findBy(hariciHafizaBuyukluk);
							if(hariciHafizaBuyukluk.getId() == null){
								HariciHafizaBuyuklukDAO.add(hariciHafizaBuyukluk);
							}
							cihazOzellikAtamaList.add(new CihazOzellikAtama(5,51, hariciHafizaBuyukluk.getId().toString()));
	
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
			}

			if(!fView.getArkaKamOz().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(6,22, fView.getArkaKamOz().trim()));
			}
			if(!fView.getVideo().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(6,23, fView.getVideo().trim()));
			}

			if(!fView.getOnKam().equals("")){
				if(fView.getOnKam().contains(";")){
					String[] kamArr = fView.getOnKam().split(";");
					cihazOzellikAtamaList.add(new CihazOzellikAtama(6,52, kamArr[1].trim()));
				}
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

			if(!fView.getBekSure().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(9,37, fView.getBekSure().trim()));
			}
			if(!fView.getKonSure().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(9,38, fView.getKonSure().trim()));
			}
			if(!fView.getRenk().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(10,39, fView.getRenk().trim()));
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
    		  
  			if(!fView.getCpu().equals("")){
  				
				CekirdekSayi cekirdekSayi = new CekirdekSayi();
				CekirdekHiz cekirdekHiz = new CekirdekHiz();
				com.zk.cw.cihaz.Cihaz cihaz = new com.zk.cw.cihaz.Cihaz();
				CpuSayiHizAta cpuSayiHizAta = new CpuSayiHizAta();
				cihaz.setId(fCihaz.getId());
				cpuSayiHizAta.setCihazId(fCihaz.getId());
				if(fView.getCpu().contains("&")){
					String pattern = "\\b[0-9]x[0-9\\.]+ .Hz\\b";
					Pattern r = Pattern.compile(pattern);
				    Matcher m = r.matcher(fView.getCpu());
				    while (m.find()) {
				    	String[] arr=null;
				    	arr = m.group().trim().split("x");
				    	cekirdekSayi.setId(Integer.parseInt(arr[0].trim()));
				    	try {
							CekirdekSayiDAO.findBy(cekirdekSayi.getId());
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
				    	cpuSayiHizAta.setSayiId(cekirdekSayi.getId());
						cekirdekHiz.setHiz(arr[1].trim());
				    	try {
							CekirdekHizDAO.findBy(cekirdekHiz);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						if(cekirdekHiz.getId() == null){
							try {
								CekirdekHizDAO.add(cekirdekHiz);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						cpuSayiHizAta.setHizId(cekirdekHiz.getId());
						try {
							CpuSayiHizAtaDAO.add(cpuSayiHizAta);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				    }
				}else{
					try {
						for(CekirdekSayi  cekSayi: CekirdekSayiDAO.all() ){
							if(fView.getCpu().contains(cekSayi.getAd())){
								cekirdekSayi.setId(cekSayi.getId());
							}
						}
						cpuSayiHizAta.setSayiId(cekirdekSayi.getId());
						String pattern = "([0-9\\.]+ .Hz)";
						Pattern r = Pattern.compile(pattern);
					    Matcher m = r.matcher(fView.getCpu());
					    if (m.find()) {
					    	cekirdekHiz.setHiz(m.group());
					    	try {
								CekirdekHizDAO.findBy(cekirdekHiz);
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							
							if(cekirdekHiz.getId() == null){
								try {
									CekirdekHizDAO.add(cekirdekHiz);
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							cpuSayiHizAta.setHizId(cekirdekHiz.getId());
					    }

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					try {
						CpuSayiHizAtaDAO.add(cpuSayiHizAta);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
  			
			if(!fView.getDahiliHafiza().equals("")){
				
				String[] buyuklukArr = fView.getDahiliHafiza().split("/");
				for(String buyukluk: buyuklukArr){
					DahiliHafiza dahiliHafiza = new DahiliHafiza();
					dahiliHafiza.setBuyukluk(buyukluk);
					
					if(fView.getDahiliHafiza().contains("GB")){
						if(!dahiliHafiza.getBuyukluk().contains("GB")){
							dahiliHafiza.setBuyukluk(buyukluk+" GB");
						}
					}
					
					if(fView.getDahiliHafiza().contains("MB")){
						if(!dahiliHafiza.getBuyukluk().contains("MB")){
							dahiliHafiza.setBuyukluk(buyukluk+" MB");
						}
					}
					
					if(fView.getDahiliHafiza().contains("kB")){
						if(!dahiliHafiza.getBuyukluk().contains("kB")){
							dahiliHafiza.setBuyukluk(buyukluk+" KB");
						}
					}					
						
					try {
						DahiliHafizaDAO.findBy(dahiliHafiza);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(dahiliHafiza.getId() == null){
						try {
							DahiliHafizaDAO.add(dahiliHafiza);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					com.zk.cw.cihaz.Cihaz cihaz = new com.zk.cw.cihaz.Cihaz();
					cihaz.setId(fCihaz.getId());
					try {
						DahiliHafizaDAO.addOzellikAta(cihaz, dahiliHafiza);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				}
				cihazOzellikAtamaList.add(new CihazOzellikAtama(5,20, fView.getDahiliHafiza().trim()));
			}
			
			if(!fView.getRam().equals("")){
				
				Ram ram = new Ram();
				ram.setBuyukluk(fView.getRam().trim());
				try {
					RamDAO.findBy(ram);
					if(ram.getId() == null){
						RamDAO.add(ram);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

				com.zk.cw.cihaz.Cihaz cihaz = new com.zk.cw.cihaz.Cihaz();
				cihaz.setId(fCihaz.getId());
				try {
					RamDAO.addOzellikAta(cihaz, ram);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//cihazOzellikAtamaList.add(new CihazOzellikAtama(10,46, ram.getId().toString()));
		
			}
			
			if(!fView.getArkaKam().equals("")){
				
				String patternCoz = "\\b([0-9\\.\\s]+MP)\\b";
				Pattern rCoz = Pattern.compile(patternCoz);
			    Matcher mCoz = rCoz.matcher(fView.getArkaKam());
				
			    String patternDiy = "\\b(f/[0-9\\.]+)\\b";
				Pattern rDiy = Pattern.compile(patternDiy);
			    Matcher mDiy = rDiy.matcher(fView.getArkaKam());
				
			    String patternPik = "\\b([0-9]+mm)\\b";
				Pattern rPik = Pattern.compile(patternPik);
			    Matcher mPik = rPik.matcher(fView.getArkaKam());
			    int count= 0;
			    while (mCoz.find()) {
					KameraCozunurluk arkaKamera = new KameraCozunurluk();
					Diyafram diyafram = new Diyafram();
					ArkaKameraAta arkaKameraAta = new ArkaKameraAta();
					PikselBuyuklugu pikselBuyuklugu = new PikselBuyuklugu();
					arkaKameraAta.setCihazId(fCihaz.getId());

			    	//çözünürlük
			    	arkaKamera.setCozunurluk(mCoz.group().trim());
			    	KameraCozunurlukDAO.findBy(arkaKamera);
					if(arkaKamera.getId() == null){
						KameraCozunurlukDAO.add(arkaKamera);
					}
					arkaKameraAta.setKameraCozunurlukId(arkaKamera.getId());
					// diyafram
			    	if(mDiy.find()){
				    	diyafram.setAciklik(mDiy.group().trim());
				    	DiyaframDAO.findBy(diyafram);
						if(diyafram.getId() == null){
							DiyaframDAO.add(diyafram);
						}
				    	arkaKameraAta.setDiyaframAcikligiId(diyafram.getId());
			    	}
					// piksel büyüklüğü
			    	if(mPik.find()){
				    	pikselBuyuklugu.setBuyukluk(mPik.group().trim());
				    	PikselBuyukluguDAO.findBy(pikselBuyuklugu);
						if(pikselBuyuklugu.getId() == null){
							PikselBuyukluguDAO.add(pikselBuyuklugu);
						}
				    	arkaKameraAta.setPikselBuyukluguId(pikselBuyuklugu.getId());
			    	}
					ArkaKameraAtaDAO.add(arkaKameraAta);
			    }
				
				cihazOzellikAtamaList.add(new CihazOzellikAtama(6,21, fView.getArkaKam().trim()));
				
			}
			
			if(!fView.getOnKam().equals("")){
				String patternCoz = "\\b([0-9\\.\\s]+MP)\\b";
				Pattern rCoz = Pattern.compile(patternCoz);
			    Matcher mCoz = rCoz.matcher(fView.getOnKam());
				
			    String patternDiy = "\\b(f/[0-9\\.]+)\\b";
				Pattern rDiy = Pattern.compile(patternDiy);
			    Matcher mDiy = rDiy.matcher(fView.getOnKam());
				
			    String patternPik = "\\b([0-9]+mm)\\b";
				Pattern rPik = Pattern.compile(patternPik);
			    Matcher mPik = rPik.matcher(fView.getOnKam());
			    int count= 0;
			    while (mCoz.find()) {
					KameraCozunurluk onKamera = new KameraCozunurluk();
					Diyafram diyafram = new Diyafram();
					OnKameraAta onKameraAta = new OnKameraAta();
					PikselBuyuklugu pikselBuyuklugu = new PikselBuyuklugu();
					onKameraAta.setCihazId(fCihaz.getId());

			    	//çözünürlük
					onKamera.setCozunurluk(mCoz.group().trim());
			    	KameraCozunurlukDAO.findBy(onKamera);
					if(onKamera.getId() == null){
						KameraCozunurlukDAO.add(onKamera);
					}
					onKameraAta.setKameraCozunurlukId(onKamera.getId());
					// diyafram
			    	if(mDiy.find()){
				    	diyafram.setAciklik(mDiy.group().trim());
				    	DiyaframDAO.findBy(diyafram);
						if(diyafram.getId() == null){
							DiyaframDAO.add(diyafram);
						}
						onKameraAta.setDiyaframAcikligiId(diyafram.getId());
			    	}
					// piksel büyüklüğü
			    	if(mPik.find()){
				    	pikselBuyuklugu.setBuyukluk(mPik.group().trim());
				    	PikselBuyukluguDAO.findBy(pikselBuyuklugu);
						if(pikselBuyuklugu.getId() == null){
							PikselBuyukluguDAO.add(pikselBuyuklugu);
						}
						onKameraAta.setPikselBuyukluguId(pikselBuyuklugu.getId());
			    	}
					OnKameraAtaDAO.add(onKameraAta);
			    }
				
				cihazOzellikAtamaList.add(new CihazOzellikAtama(6,24, fView.getOnKam().trim()));
			}
			
			if(!fView.getPil().equals("")){
				BataryaKapasite kapasite = new BataryaKapasite();
				BataryaTeknoloji teknoloji = new BataryaTeknoloji();
				BataryaDegisir degisir = new BataryaDegisir();
				
				String pattern = "([0-9]+) mAh\\b";
				Pattern r = Pattern.compile(pattern);
			    Matcher m = r.matcher(fView.getPil());
			    if (m.find()) {
			    	kapasite.setKapasite(m.group(1).trim());
			    	BataryaKapasiteDAO.findBy(kapasite);
					if(kapasite.getId() == null){
						BataryaKapasiteDAO.add(kapasite);
					}
					cihazOzellikAtamaList.add(new CihazOzellikAtama(9,53, kapasite.getId().toString()));
			    }
			    
			    if(fView.getPil().contains("Li-Po")){
			    	teknoloji.setAd("Li-Po");
			    }else if(fView.getPil().contains("Li-Ion")){
			    	teknoloji.setAd("Li-Ion");
			    }
			    if(teknoloji.getAd() != null){
			    	BataryaTeknolojiDAO.findBy(teknoloji);
					if(teknoloji.getId() == null){
						BataryaTeknolojiDAO.add(teknoloji);
					}
					cihazOzellikAtamaList.add(new CihazOzellikAtama(9,54, teknoloji.getId().toString()));
			    }
			    
			    if(fView.getPil().contains("Çıkarılmaz")){
			    	degisir.setAd("Yok");
			    }else if(fView.getPil().contains("Çıkarılabilir ")){
			    	degisir.setAd("Var");
			    }
			    if(degisir.getAd() != null){
			    	BataryaDegisirDAO.findBy(degisir);
			    	if(degisir.getId() == null){
						BataryaDegisirDAO.add(degisir);
					}
					cihazOzellikAtamaList.add(new CihazOzellikAtama(9,55, degisir.getId().toString()));
			    }

				cihazOzellikAtamaList.add(new CihazOzellikAtama(9,36, fView.getPil().trim()));
			  }
			
			if(!fView.getSensor().equals("")){
				String[] sensorler = fView.getSensor().split(",");
				for(String sensorAd:sensorler){
					Sensor sensor = new Sensor();
					sensor.setAd(sensorAd.trim());
			    	SensorDAO.findByName(sensor);
					if(sensor.getId() == null){
						SensorDAO.add(sensor);
					}
					com.zk.cw.cihaz.Cihaz cihaz = new com.zk.cw.cihaz.Cihaz();
					cihaz.setId(fCihaz.getId());
					
	    			SensorAta sensorAta = SensorAtaDAO.findBy(cihaz,sensor);
					if(sensorAta == null){
						SensorAtaDAO.add(cihaz, sensor);
					}

				}
				cihazOzellikAtamaList.add(new CihazOzellikAtama(10,40, fView.getSensor().trim()));
			}	
    		  
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
				  resim.setKucukResim(ImageResize.reizeFromByte(fView.getResim(),40,53));
				  resim.setOrtaResim(ImageResize.reizeFromByte(fView.getResim(),160,212));
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
