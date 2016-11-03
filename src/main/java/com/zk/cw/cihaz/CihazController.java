package com.zk.cw.cihaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import com.zk.cw.exception.InvalidInputException;
import com.zk.cw.main.MainWindow;
import com.zk.cw.util.Edit;

public class CihazController implements ActionListener  {
	
	private final CihazView fView;
	private Cihaz fCihaz;
	private Edit fEdit;	
	private CihazDAO fDAO = new CihazDAO();	
	
	public CihazController(CihazView aView, Edit aEdit){
		fView = aView;
		fEdit = aEdit;
	}

	@Override public void actionPerformed(ActionEvent aEvent){
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		      }
		      else {
		        throw new AssertionError();
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
