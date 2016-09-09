package com.zk.cw.yeni_cihaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.zk.cw.edit.Movie;
import com.zk.cw.exception.InvalidInputException;
import com.zk.cw.main.MainWindow;
import com.zk.cw.uretici.Uretici;
import com.zk.cw.util.Edit;

public class CihazController implements ActionListener {
	
	// PRIVATE 
	private final CihazView fView;
	private Cihaz fCihaz;
	private Edit fEdit;	
	private YeniCihazDAO DAO = new YeniCihazDAO();
	private Uretici uretici;
	
	CihazController(CihazView aView, Edit aEdit, Uretici uretici){
		fView = aView;
		fEdit = aEdit;
		this.uretici = uretici;
	}
	
	@Override 
	public void actionPerformed(ActionEvent aEvent){
	    try {
	      createValidMovieFromUserInput();
	    }
	    catch(InvalidInputException ex){
	      informUserOfProblems(ex);
	    }
	    if ( isUserInputValid() ){
    	  try {
			DAO.add(fCihaz,uretici);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			DAO.update(fView.getUrl());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  fView.closeDialog();
	      CihazMainWindow.getInstance().refreshView();
	    }
	}	
	
	private void createValidMovieFromUserInput() throws InvalidInputException {		    
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
