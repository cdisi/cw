package com.zk.cw.uretici;

import com.zk.cw.util.ui.OnClose;
import com.zk.cw.util.Edit;
import com.zk.cw.util.Util;
import com.zk.cw.util.ui.UiUtil;
import com.zk.cw.util.ui.StandardDialog;

import java.util.logging.Logger;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
  Dialog allowing user to input {@link Movie} information.

  <P>This view can be used to either add a new Movie, or to change an existing one.
  <P>It's important to note that validation of user entries is <em>not</em> 
  performed by this class. Rather, it's performed by the {@link Movie} class.
*/
final class UreticiView {

  /**
   Constructor.
    
   <P>Called when adding a new {@link Movie}.
  */
  UreticiView(JFrame aParent) {
    fEdit = Edit.ADD;
    buildGui(aParent, "Add Movie");
    fStandardDialog.display();
  }

  String getId() {
    return fId;
  }

  /** The title of the movie, as entered by the user. */
  String getTitle() {
    return fTitle.getText();
  }

  /** The date the movie was viewed, as entered by the user. */
  String getDateViewed() {
    return fDateViewed.getText();
  }

  /** The movie rating, as entered by the user. */
  String getRating() {
    return fRating.getText();
  }

  /** The comment on the movie, as entered by the user. */
  String getComment() {
    return fComment.getText();
  }

  /** Close the view. */
  void closeDialog() {
    fStandardDialog.dispose();
  }

  /** Return the underlying dialog. */
  JDialog getDialog() {
    return fStandardDialog.getDialog();
  }

  // PRIVATE 
  private StandardDialog fStandardDialog;
  private Edit fEdit;
  private String fId;
  private JTextField fTitle = new JTextField();
  private JTextField fDateViewed = new JTextField();
  private JTextField fRating = new JTextField();
  private JTextField fComment = new JTextField();
  private JButton fEditButton;
  private static final Logger fLogger = Util.getLogger(UreticiView.class);

  private void buildGui(JFrame aParent, String aDialogTitle) {
    fStandardDialog = new StandardDialog(
      aParent, aDialogTitle, true, OnClose.DISPOSE, getUserInputArea(), getButtons()
    );
    fStandardDialog.setDefaultButton(fEditButton);
  }

  private JPanel getUserInputArea() {
    JPanel result = new JPanel();
    result.setLayout(new BoxLayout(result, BoxLayout.Y_AXIS));

    addTextField(fTitle, "Title", result);
    addTextField(fDateViewed, "Date Viewed", result);
    addTextField(fRating, "Rating", result);
    addTextField(fComment, "Comment", result);
    UiUtil.alignAllX(result, UiUtil.AlignX.LEFT);
    return result;
  }

  private void addTextField(JTextField aTextField, String aLabel, JPanel aPanel) {
    JLabel label = new JLabel(aLabel);
    aPanel.add(label);
    aPanel.add(aTextField);
    aTextField.setColumns(15);
  }

  private java.util.List<JButton> getButtons() {
    java.util.List<JButton> result = new ArrayList<>();

    fEditButton = new JButton(fEdit.toString());
    //fEditButton.addActionListener(new UreticiController(this, fEdit));
    result.add(fEditButton);

    JButton cancel = new JButton("Cancel");
    cancel.addActionListener(new ActionListener() {
      @Override public void actionPerformed(ActionEvent arg0) {
        closeDialog();
      }
    });
    result.add(cancel);
    return result;
  }
}
