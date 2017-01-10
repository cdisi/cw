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
import com.zk.cw.exception.InvalidInputException;
import com.zk.cw.main.MainWindow;
import com.zk.cw.ozellik_atama.OzellikAtama;
import com.zk.cw.ozellik_atama.OzellikAtamaDAO;
import com.zk.cw.util.Edit;

import cpu.CpuSayiHizAtaDAO;

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
					// TODO Auto-generated catch block
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
		      
		      if(fView.getCekirdekSayi().getId() != null){
		    	  if(CpuSayiHizAtaDAO.findBy(fCihaz) == null)
		    	  ozellikAtamaList.add(new OzellikAtama(null, fCihaz.getId(), 4, 18, fView.getGpu().getId().toString()));
		      }else{
		    	  try {
					ozellikAtamaDao.delete(fCihaz,18);
				} catch (SQLException e) {
					e.printStackTrace();
				}
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
