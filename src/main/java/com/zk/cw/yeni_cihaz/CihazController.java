package com.zk.cw.yeni_cihaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import org.apache.commons.io.FilenameUtils;

import com.zk.cw.edit.Movie;
import com.zk.cw.exception.InvalidInputException;
import com.zk.cw.main.MainWindow;
import com.zk.cw.uretici.Uretici;
import com.zk.cw.util.Edit;
import com.zk.cw.util.Util;

public class CihazController implements ActionListener {
	
	// PRIVATE 
	private final CihazView fView;
	private Cihaz fCihaz;
	private Edit fEdit;	
	private YeniCihazDAO cihazDAO = new YeniCihazDAO();
	private Uretici uretici;
	private static List<CihazOzellikAtama> cihazOzellikAtamaList = new ArrayList<CihazOzellikAtama>();
	
	CihazController(CihazView aView, Edit aEdit, Uretici uretici){
		fView = aView;
		fEdit = aEdit;
		this.uretici = uretici;
	}
	
	@Override 
	public void actionPerformed(ActionEvent aEvent){

		try {
	      createValidCihazFromUserInput();
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
					fCihaz.setDuyurulma(fCihaz.getDuyurulma()+", "+fView.getDuyurulmaAy());
			}
			if(!fView.getBoyutlar().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(2,7, fView.getBoyutlar().trim()));
			}
			if(!fView.getAgirlik().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(2,8, fView.getAgirlik().trim()));
			}
			if(!fView.getSim().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(2,9, fView.getSim().trim()));
			}
			if(!fView.getEkranTip().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(3,10, fView.getEkranTip().trim()));
			}
			if(!fView.getEkranGen().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(3,11, fView.getEkranGen().trim()));
			}
			if(!fView.getEkranCoz().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(3,12, fView.getEkranCoz().trim()));
			}
			if(!fView.getMultiTouch().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(3,13, fView.getMultiTouch().trim()));
			}
			if(!fView.getEkranKor().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(3,14, fView.getEkranKor().trim()));
			}
			if(!fView.getOs().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(4,15, fView.getOs().trim()));
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

			if(!fView.getSensor().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(10,40, fView.getSensor().trim()));
			}
			if(!fView.getMesaj().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(10,41, fView.getMesaj().trim()));
			}
			if(!fView.getJava().equals("")){
				cihazOzellikAtamaList.add(new CihazOzellikAtama(10,42, fView.getJava().trim()));
			}

			
		}
	    catch(InvalidInputException ex){
	      informUserOfProblems(ex);
	    }
		fCihaz.setResimAdi( ((uretici.adAl()+' '+fCihaz.getAd()).replaceAll("[^\\w-]", "_")).toLowerCase(Locale.ENGLISH)+".jpg");
	    if ( isUserInputValid() ){
    	  try {
    		  fCihaz = cihazDAO.add(fCihaz,uretici);
	  		  for(CihazOzellikAtama cihazOzellikAtama : cihazOzellikAtamaList){
					CihazOzellikAtamaDAO.insert(fCihaz, cihazOzellikAtama);
			  }
    		  cihazDAO.update(fView.getUrl());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	try {
			ImageIO.write(fView.getResim(), "jpg", new File("resources/cihaz/"+fCihaz.getResimAdi()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	   
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
	    JOptionPane.showMessageDialog(
	      fView.getDialog(), messages, 
	      "Movie cannot be saved", JOptionPane.ERROR_MESSAGE
	    );
	}	
}
