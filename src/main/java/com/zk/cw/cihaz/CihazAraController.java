package com.zk.cw.cihaz;

import com.zk.cw.exception.InvalidInputException;
import com.zk.cw.main.MainWindow;
import com.zk.cw.util.Edit;
import com.zk.cw.util.Util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

final class CihazAraController implements ActionListener {
  
  CihazAraController(MovieView aView, Edit aEdit){
    fView = aView;
    fEdit = aEdit;
  }
  
  @Override public void actionPerformed(ActionEvent aEvent){
    fLogger.fine("Editing movie " + fView.getTitle());
    try {
      createValidMovieFromUserInput();
    }
    catch(InvalidInputException ex){
      informUserOfProblems(ex);
    }
    if ( isUserInputValid() ){
      if( Edit.ADD == fEdit ) {
        fLogger.fine("Add operation.");
        fDAO.add(fMovie);
      }
      else if (Edit.CHANGE == fEdit) {
        fLogger.fine("Change operation.");
        fDAO.change(fMovie);
      }
      else {
        throw new AssertionError();
      }
      fView.closeDialog();
      MainWindow.getInstance().refreshView();
    }
  }

  // PRIVATE 
  private final MovieView fView;
  private Movie fMovie;
  private Edit fEdit;
  private MovieDAO fDAO = new MovieDAO();
  private static final Logger fLogger = Util.getLogger(CihazAraController.class);
  
  private void createValidMovieFromUserInput() throws InvalidInputException {
    fMovie = new Movie(
      fView.getId(), fView.getTitle(), fView.getDateViewed(), 
      fView.getRating(), fView.getComment()
    );
  }

  private boolean isUserInputValid(){
    return fMovie != null;
  }
  
  private void informUserOfProblems(InvalidInputException aException) {
    Object[] messages = aException.getErrorMessages().toArray();
    JOptionPane.showMessageDialog(
      fView.getDialog(), messages, 
      "Movie cannot be saved", JOptionPane.ERROR_MESSAGE
    );
  }
  
}