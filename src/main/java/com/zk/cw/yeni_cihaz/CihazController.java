package com.zk.cw.yeni_cihaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.zk.cw.edit.Movie;
import com.zk.cw.exception.InvalidInputException;
import com.zk.cw.main.MainWindow;
import com.zk.cw.util.Edit;

public class CihazController implements ActionListener {
	
	  // PRIVATE 
	  private final CihazView fView;
	  private Cihaz fCihaz;
	  private Edit fEdit;	
	
	CihazController(CihazView aView, Edit aEdit){
		fView = aView;
		fEdit = aEdit;
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
	      if( Edit.ADD == fEdit ) {
	        fDAO.add(fMovie);
	      }
	      else if (Edit.CHANGE == fEdit) {
	        fDAO.change(fMovie);
	      }
	      else {
	        throw new AssertionError();
	      }
	      fView.closeDialog();
	      MainWindow.getInstance().refreshView();
	    }
	}	
	
	private void createValidMovieFromUserInput() throws InvalidInputException {		    
		fCihaz = new Cihaz(
		      fView.getId(), fView.getTitle(), fView.getDateViewed(), 
		      fView.getRating(), fView.getComment()
		    );
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
