package com.zk.cw.cihaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.text.View;

import com.zk.cw.cihaz_resim.Resim;
import com.zk.cw.cihaz_resim.ResimDAO;
import com.zk.cw.cihaz_tur.CihazTur;
import com.zk.cw.cihaz_tur.CihazTurComboBoxRenderer;
import com.zk.cw.cpu.CekirdekHiz;
import com.zk.cw.cpu.CekirdekSayi;
import com.zk.cw.cpu.CpuSayiHizAta;
import com.zk.cw.cpu.CpuSayiHizAtaDAO;
import com.zk.cw.exception.InvalidInputException;
import com.zk.cw.hafiza.DahiliHafiza;
import com.zk.cw.hafiza.DahiliHafizaAta;
import com.zk.cw.hafiza.DahiliHafizaAtaDAO;
import com.zk.cw.kamera.ArkaKameraAta;
import com.zk.cw.kamera.ArkaKameraAtaDAO;
import com.zk.cw.kamera.OnKameraAta;
import com.zk.cw.kamera.OnKameraAtaDAO;
import com.zk.cw.main.MainWindow;
import com.zk.cw.ozellik_atama.OzellikAtama;
import com.zk.cw.ozellik_atama.OzellikAtamaDAO;
import com.zk.cw.ram.RamAta;
import com.zk.cw.ram.RamAtaDAO;
import com.zk.cw.ram.RamDAO;
import com.zk.cw.util.Edit;

public class CihazController implements ActionListener  {
	
	private final CihazView fView;
	private Cihaz fCihaz;
	private Edit fEdit;	
	private CihazDAO fDAO = new CihazDAO();	
	private List<OzellikAtama> ozellikAtamaList = new ArrayList<OzellikAtama>();
	private OzellikAtamaDAO ozellikAtamaDao = new OzellikAtamaDAO();
	
	public CihazController(CihazView aView, Edit aEdit){
		fView = aView;
		fEdit = aEdit;
	}

	@Override public void actionPerformed(ActionEvent aEvent){
		ozellikAtamaList.clear();    
		try {
		      createValidCihazFromUserInput();
		    }
		    catch(InvalidInputException ex){
		      informUserOfProblems(ex);
		    }
		    if ( isUserInputValid() ){
		      if( Edit.ADD == fEdit ) {
		        try {
					fDAO.add(fCihaz);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		      }
		      else if (Edit.CHANGE == fEdit) {
		        try {
		        	fDAO.change(fCihaz);
				} catch (SQLException e) {
					e.printStackTrace();
				}
		      }
		      else {
		        throw new AssertionError();
		      }
		      
		      if(fView.getEkranTip().getId() != null)
		    	  ozellikAtamaList.add(new OzellikAtama(null, fCihaz.getId(), 3, 10, fView.getEkranTip().getId().toString()));
		      if(fView.getEkranRenk().getId() != null)
		    	  ozellikAtamaList.add(new OzellikAtama(null, fCihaz.getId(), 3, 48, fView.getEkranRenk().getId().toString()));		      
		      if(fView.getEkranGenisligi().getId() != null)
		    	  ozellikAtamaList.add(new OzellikAtama(null, fCihaz.getId(), 3, 11, fView.getEkranGenisligi().getGenislik()));
		      if(fView.getEkranCozunurluk().getId() != null)
		    	  ozellikAtamaList.add(new OzellikAtama(null, fCihaz.getId(), 3, 12, fView.getEkranCozunurluk().getId().toString()));
		      if(fView.getEkranPPI().getId() != null)
		    	  ozellikAtamaList.add(new OzellikAtama(null, fCihaz.getId(), 3, 49, fView.getEkranPPI().getId().toString()));
		      if(fView.getEkranDiger() != null)
		    	  ozellikAtamaList.add(new OzellikAtama(null, fCihaz.getId(), 3, 44, fView.getEkranDiger()));
		      
		      if(fView.getCokluDokunmatik().getDeger() != "Seçiniz")
		    	  ozellikAtamaList.add(new OzellikAtama(null, fCihaz.getId(), 3, 13, fView.getCokluDokunmatik().getDeger()));
		      if(!fView.getEkranKor().equals(""))
		    	  ozellikAtamaList.add(new OzellikAtama(null, fCihaz.getId(), 3, 14, fView.getEkranKor()));
		      if(!(fView.getBoyut().equals("") || fView.getBoyut().equals("100 x 100 x 10 mm"))){
		    	  ozellikAtamaList.add(new OzellikAtama(null, fCihaz.getId(), 2, 7, fView.getBoyut()));
		      }else{
		    	  try {
					ozellikAtamaDao.delete(fCihaz,7);
				} catch (SQLException e) {
					e.printStackTrace();
				}
		      }
		      
		      if(!(fView.getAgirlik().equals("") || fView.getAgirlik().equals("100 gr"))){
		    	  ozellikAtamaList.add(new OzellikAtama(null, fCihaz.getId(), 2, 8, fView.getAgirlik()));
		      }else{
		    	  try {
					ozellikAtamaDao.delete(fCihaz,8);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		      }		      
		      if(!fView.getSimSayisi().getSayi().equals("Seçiniz")){
		    	  ozellikAtamaList.add(new OzellikAtama(null, fCihaz.getId(), 2, 9, fView.getSimSayisi().getId().toString()));
		      }else{
		    	  try {
					ozellikAtamaDao.delete(fCihaz,9);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		      }		      
		      if(!fView.getSim().getAd().equals("Seçiniz")){
		    	  ozellikAtamaList.add(new OzellikAtama(null, fCihaz.getId(), 2, 50, fView.getSim().getId().toString()));
		      }else{
		    	  try {
					ozellikAtamaDao.delete(fCihaz,50);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		      }	
		      
		      if(fView.getGovdeDiger() != null)
		    	  ozellikAtamaList.add(new OzellikAtama(null, fCihaz.getId(), 3, 47, fView.getGovdeDiger()));
		      
		      if(!fView.getOS().getAd().equals("Seçiniz")){
		    	  ozellikAtamaList.add(new OzellikAtama(null, fCihaz.getId(), 4, 15, fView.getOS().getId().toString()));
		      }else{
		    	  try {
					ozellikAtamaDao.delete(fCihaz,15);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		      }	
		      
		      if(!fView.getOSSurum().getAd().equals("Seçiniz")){
		    	  ozellikAtamaList.add(new OzellikAtama(null, fCihaz.getId(), 4, 43, fView.getOSSurum().getId().toString()));
		      }else{
		    	  try {
					ozellikAtamaDao.delete(fCihaz,43);
				} catch (SQLException e) {
					e.printStackTrace();
				}
		      }	
		      
		      if(!fView.getYongaSeti().getAd().equals("Seçiniz")){
		    	  ozellikAtamaList.add(new OzellikAtama(null, fCihaz.getId(), 4, 16, fView.getYongaSeti().getId().toString()));
		      }else{
		    	  try {
					ozellikAtamaDao.delete(fCihaz,16);
				} catch (SQLException e) {
					e.printStackTrace();
				}
		      }	
		      
		      if(!fView.getGpu().getAd().equals("Seçiniz")){
		    	  ozellikAtamaList.add(new OzellikAtama(null, fCihaz.getId(), 4, 18, fView.getGpu().getId().toString()));
		      }else{
		    	  try {
					ozellikAtamaDao.delete(fCihaz,18);
				} catch (SQLException e) {
					e.printStackTrace();
				}
		      }	
		      // 1.işlemci
	    	  CpuSayiHizAta cpuSayiHizAta = fView.getCpuSayiHizAta();
	    	  if(fView.getCpuSayiHizAta() == null){
	    		  cpuSayiHizAta = new CpuSayiHizAta(); 
	    	  }
    		  cpuSayiHizAta.setCihazId(fCihaz.getId());
    		  cpuSayiHizAta.setSayiId(fView.getCekirdekSayi().getId());
    		  cpuSayiHizAta.setHizId(fView.getCekirdekHiz().getId());

		      try {
				 if( (fView.getCekirdekHiz().getId() == null) && (fView.getCekirdekSayi().getId()==null) ){
				    	CpuSayiHizAtaDAO.delete(cpuSayiHizAta);
				 }else{
			    	  if(cpuSayiHizAta.getId() == null)
			    		  CpuSayiHizAtaDAO.add(cpuSayiHizAta);
			    	  else
			    		  CpuSayiHizAtaDAO.update(cpuSayiHizAta);
				 }
					
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		      
		      // 2.işlemci
	    	  CpuSayiHizAta cpuSayiHizAta2 = fView.getCpuSayiHizAta2();
	    	  if(fView.getCpuSayiHizAta2() == null){
	    		  cpuSayiHizAta2 = new CpuSayiHizAta(); 
	    	  }
    		  cpuSayiHizAta2.setCihazId(fCihaz.getId());
    		  cpuSayiHizAta2.setSayiId(fView.getCekirdekSayi2().getId());
    		  cpuSayiHizAta2.setHizId(fView.getCekirdekHiz2().getId());

		      try {
				 if( (fView.getCekirdekHiz2().getId() == null) && (fView.getCekirdekSayi2().getId()==null) ){
				    	if(cpuSayiHizAta2.getId() != null)
				    		CpuSayiHizAtaDAO.delete(cpuSayiHizAta2);
				 }else{
			    	  if(cpuSayiHizAta2.getId() == null)
			    		  CpuSayiHizAtaDAO.add(cpuSayiHizAta2);
			    	  else
			    		  CpuSayiHizAtaDAO.update(cpuSayiHizAta2);
				 }
			} catch (SQLException e1) {
				e1.printStackTrace();
			}		     
		      // dahili hafıza		     
		      DahiliHafizaAta dahiliHafizaAta = fView.getDahiliHafizaAta();
	    	  if(dahiliHafizaAta == null){
	    		  dahiliHafizaAta = new DahiliHafizaAta(); 
	    	  }
	    	  dahiliHafizaAta.setCihazId(fCihaz.getId());
	    	  dahiliHafizaAta.setDahiliHafizaId(fView.getDahiliHafiza().getId());
		      try {
				 if( (fView.getDahiliHafiza().getId() == null) ){
				    	if(dahiliHafizaAta.getId() != null)
				    		DahiliHafizaAtaDAO.delete(dahiliHafizaAta);
				 }else{
			    	  if(dahiliHafizaAta.getId() == null)
			    		  DahiliHafizaAtaDAO.add(dahiliHafizaAta);
			    	  else
			    		  DahiliHafizaAtaDAO.update(dahiliHafizaAta);
				 }
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		      // dahili hafıza 2	     
		      DahiliHafizaAta dahiliHafizaAta2 = fView.getDahiliHafizaAta2();
	    	  if(dahiliHafizaAta2 == null){
	    		  dahiliHafizaAta2 = new DahiliHafizaAta(); 
	    	  }
	    	  dahiliHafizaAta2.setCihazId(fCihaz.getId());
	    	  dahiliHafizaAta2.setDahiliHafizaId(fView.getDahiliHafiza2().getId());
		      try {
				 if( (fView.getDahiliHafiza2().getId() == null) ){
				    	if(dahiliHafizaAta2.getId() != null)
				    		DahiliHafizaAtaDAO.delete(dahiliHafizaAta2);
				 }else{
			    	  if(dahiliHafizaAta2.getId() == null)
			    		  DahiliHafizaAtaDAO.add(dahiliHafizaAta2);
			    	  else
			    		  DahiliHafizaAtaDAO.update(dahiliHafizaAta2);
				 }
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		      // dahili hafıza 3	     
		      DahiliHafizaAta dahiliHafizaAta3 = fView.getDahiliHafizaAta3();
	    	  if(dahiliHafizaAta3 == null){
	    		  dahiliHafizaAta3 = new DahiliHafizaAta(); 
	    	  }
	    	  dahiliHafizaAta3.setCihazId(fCihaz.getId());
	    	  dahiliHafizaAta3.setDahiliHafizaId(fView.getDahiliHafiza3().getId());
		      try {
				 if( (fView.getDahiliHafiza3().getId() == null) ){
				    	if(dahiliHafizaAta3.getId() != null)
				    		DahiliHafizaAtaDAO.delete(dahiliHafizaAta3);
				 }else{
			    	  if(dahiliHafizaAta3.getId() == null)
			    		  DahiliHafizaAtaDAO.add(dahiliHafizaAta3);
			    	  else
			    		  DahiliHafizaAtaDAO.update(dahiliHafizaAta3);
				 }
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		      
		      // ram	     
		      RamAta ramAta = fView.getRamAta();
	    	  if(ramAta == null){
	    		  ramAta = new RamAta(); 
	    	  }
	    	  ramAta.setCihazId(fCihaz.getId());
	    	  ramAta.setRamId(fView.getRam().getId());
		      try {
				 if( (fView.getRam().getId() == null) ){
				    	if(ramAta.getId() != null)
				    		RamAtaDAO.delete(ramAta);
				 }else{
			    	  if(ramAta.getId() == null)
			    		  RamAtaDAO.add(ramAta);
			    	  else
			    		  RamAtaDAO.update(ramAta);
				 }
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		      
		    // harici hafıza tipi 
		    if(fView.getHariciHafizaTipi().getId() != null){
		    	  ozellikAtamaList.add(new OzellikAtama(null, fCihaz.getId(), 5, 19, fView.getHariciHafizaTipi().getId().toString()));
		    }else{
		    	  try {
					ozellikAtamaDao.delete(fCihaz,19);
				} catch (SQLException e) {
					e.printStackTrace();
				}
		    }	

		    // harici hafıza büyüklük 
		    if(fView.getHariciHafizaBuyukluk().getId() != null){
		    	  ozellikAtamaList.add(new OzellikAtama(null, fCihaz.getId(), 5, 51, fView.getHariciHafizaBuyukluk().getId().toString()));
		    }else{
		    	  try {
					ozellikAtamaDao.delete(fCihaz,51);
				} catch (SQLException e) {
					e.printStackTrace();
				}
		    }
		    
		    //arka kamera çözünürlük		     
		    ArkaKameraAta arkaKameraAta = fView.getArkaKameraAta();
	    	if(arkaKameraAta == null){
	    		  arkaKameraAta = new ArkaKameraAta(); 
	    	}
	    	arkaKameraAta.setCihazId(fCihaz.getId());
	    	arkaKameraAta.setKameraCozunurlukId(fView.getArkaKameraCozunurluk().getId());
		    try {
				 if( (fView.getArkaKameraCozunurluk().getId() == null) ){
				    	if(arkaKameraAta.getId() != null)
				    		ArkaKameraAtaDAO.delete(arkaKameraAta);
				 }else{
			    	  if(arkaKameraAta.getId() == null)
			    		  ArkaKameraAtaDAO.add(arkaKameraAta);
			    	  else
			    		  ArkaKameraAtaDAO.update(arkaKameraAta);
				 }
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		    // arka kamera diyafram
		    try {
		    	arkaKameraAta.setDiyaframAcikligiId(fView.getArkaKameraDiyafram().getId());  
		    	ArkaKameraAtaDAO.update(arkaKameraAta);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		    
		    // arka kamera piksel büyüklüğü
		    try {
		    	arkaKameraAta.setPikselBuyukluguId(fView.getArkaKameraPikselBuyuklugu().getId());  
		    	ArkaKameraAtaDAO.update(arkaKameraAta);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		    
		    // 2.arka kamera çözünürlük		     
		    ArkaKameraAta arkaKameraAta2 = fView.getArkaKameraAta2();
	    	if(arkaKameraAta2 == null){
	    		  arkaKameraAta2 = new ArkaKameraAta(); 
	    	}
	    	arkaKameraAta2.setCihazId(fCihaz.getId());
	    	arkaKameraAta2.setKameraCozunurlukId(fView.getArkaKameraCozunurluk2().getId());
		    try {
				 if( (fView.getArkaKameraCozunurluk2().getId() == null) ){
				    	if(arkaKameraAta2.getId() != null)
				    		ArkaKameraAtaDAO.delete(arkaKameraAta2);
				 }else{
			    	  if(arkaKameraAta2.getId() == null)
			    		  ArkaKameraAtaDAO.add(arkaKameraAta2);
			    	  else
			    		  ArkaKameraAtaDAO.update(arkaKameraAta2);
				 }
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		    // 2.arka kamera diyafram
		    try {
		    	if(fView.getArkaKameraDiyafram2().getId() != null){
		    		arkaKameraAta2.setDiyaframAcikligiId(fView.getArkaKameraDiyafram2().getId());  
		    		ArkaKameraAtaDAO.update(arkaKameraAta2);
		    	}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		    
		    // 2.arka kamera piksel büyüklüğü
		    try {
		    	if(fView.getArkaKameraPikselBuyuklugu2().getId() != null){
		    		arkaKameraAta2.setPikselBuyukluguId(fView.getArkaKameraPikselBuyuklugu2().getId());  
		    		ArkaKameraAtaDAO.update(arkaKameraAta2);
		    	}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}	
		    
		    if(fView.getArkaKameraDiger() != null)
		    	ozellikAtamaList.add(new OzellikAtama(null, fCihaz.getId(), 6, 22, fView.getArkaKameraDiger()));
		    if(fView.getArkaKameraVideo() != null)
		    	ozellikAtamaList.add(new OzellikAtama(null, fCihaz.getId(), 6, 23, fView.getArkaKameraVideo()));

		    //ön kamera çözünürlük		     
		    OnKameraAta onKameraAta = fView.getOnKameraAta();
	    	if(onKameraAta == null){
	    		onKameraAta = new OnKameraAta(); 
	    	}
	    	onKameraAta.setCihazId(fCihaz.getId());
	    	onKameraAta.setKameraCozunurlukId(fView.getOnKameraCozunurluk().getId());
		    try {
				 if( (fView.getOnKameraCozunurluk().getId() == null) ){
				    	if(onKameraAta.getId() != null)
				    		OnKameraAtaDAO.delete(onKameraAta);
				 }else{
			    	  if(onKameraAta.getId() == null)
			    		  OnKameraAtaDAO.add(onKameraAta);
			    	  else
			    		  OnKameraAtaDAO.update(onKameraAta);
				 }
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		    // ön kamera diyafram
		    try {
		    	onKameraAta.setDiyaframAcikligiId(fView.getOnKameraDiyafram().getId());  
		    	OnKameraAtaDAO.update(onKameraAta);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		    
		    // ön kamera piksel büyüklüğü
		    try {
		    	onKameraAta.setPikselBuyukluguId(fView.getOnKameraPikselBuyuklugu().getId());  
		    	OnKameraAtaDAO.update(onKameraAta);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		    		    
		    //2.ön kamera çözünürlük		     
		    OnKameraAta onKameraAta2 = fView.getOnKameraAta2();
	    	if(onKameraAta2 == null){
	    		onKameraAta2 = new OnKameraAta(); 
	    	}
	    	onKameraAta2.setCihazId(fCihaz.getId());
	    	onKameraAta2.setKameraCozunurlukId(fView.getOnKameraCozunurluk2().getId());
		    try {
				 if( (fView.getOnKameraCozunurluk2().getId() == null) ){
				    	if(onKameraAta2.getId() != null)
				    		OnKameraAtaDAO.delete(onKameraAta2);
				 }else{
			    	  if(onKameraAta2.getId() == null)
			    		  OnKameraAtaDAO.add(onKameraAta2);
			    	  else
			    		  OnKameraAtaDAO.update(onKameraAta2);
				 }
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		    // 2.ön kamera diyafram
		    try {
		    	onKameraAta2.setDiyaframAcikligiId(fView.getOnKameraDiyafram2().getId());  
		    	OnKameraAtaDAO.update(onKameraAta2);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		    
		    // 2.ön kamera piksel büyüklüğü
		    try {
		    	onKameraAta2.setPikselBuyukluguId(fView.getOnKameraPikselBuyuklugu2().getId());  
		    	OnKameraAtaDAO.update(onKameraAta2);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		    		    		    
		    for(OzellikAtama ozellikAtama : ozellikAtamaList){
				  try {
					if( ozellikAtamaDao.find(ozellikAtama.getCihazId(), ozellikAtama.getOzellikId()) == null){
						ozellikAtamaDao.insert(ozellikAtama);
					  }else{
						  ozellikAtamaDao.update(ozellikAtama);
					  }
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			  }
		      fView.closeDialog();
		      CihazMainWindow.getInstance().refreshView();
		   }
	}
	 
	private void createValidCihazFromUserInput() throws InvalidInputException {
		fCihaz = new Cihaz();
		fCihaz.setId(fView.getId());
		boolean error = false;
		if(fView.getAd().isEmpty()){
			error=true;
			showErrorMessage("Ad bilgisi gereklidir!");
		}else{
			fCihaz.setAd(fView.getAd());
		}
		fCihaz.setDigerAd(fView.getDigerAd());
		fCihaz.setUreticiId(fView.getUretici().idAl());
		fCihaz.setTuru(fView.getCihazTur().getId());
		fCihaz.setDuyurulma(fView.getDuyurulmaYil() + '-' + fView.getDuyurulmaAy());
		if(fView.getAnasayfa()){
			fCihaz.setAnasayfa(1);
		}else{
			fCihaz.setAnasayfa(0);			
		}
		if(fView.getAktif()){
			fCihaz.setAktif(1);
		}else{
			fCihaz.setAktif(0);			
		}
		
		if(error){
			fCihaz=null;
		}
	}

	private boolean isUserInputValid(){
		    return fCihaz != null;
	}
		  
	private void informUserOfProblems(InvalidInputException aException) {
	    Object[] messages = aException.getErrorMessages().toArray();
	    JOptionPane.showMessageDialog(
	    fView.getDialog(), messages, 
		      "Cihaz cannot be saved", JOptionPane.ERROR_MESSAGE
	    );
	}
	 
	private void showErrorMessage(String messages) {
	    JOptionPane.showMessageDialog(
	    fView.getDialog(), messages, 
		      "Hata", JOptionPane.ERROR_MESSAGE
	    );
	}	 

}
