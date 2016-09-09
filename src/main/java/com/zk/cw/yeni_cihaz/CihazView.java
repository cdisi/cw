package com.zk.cw.yeni_cihaz;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.zk.cw.uretici.Uretici;
import com.zk.cw.util.Edit;
import com.zk.cw.util.GsmParser;
import com.zk.cw.util.ui.OnClose;
import com.zk.cw.util.ui.StandardDialog;
import com.zk.cw.util.ui.UiUtil;

public class CihazView {
	  
	// PRIVATE 
	private StandardDialog fStandardDialog;
	private Edit fEdit;	
	private JButton fEditButton;	
	private JTextField url = new JTextField(); 
	private JTextField ad = new JTextField(); 
	private GsmParser gsmParser;
	private Uretici uretici;
	
	CihazView(JFrame aParent, YeniCihaz selectedCihaz, Uretici uretici) {				    
		fEdit = Edit.ADD;		
		this.uretici=uretici;
		gsmParser = new GsmParser(selectedCihaz.getUrl());
		buildGui(aParent, "Cihaz Ekle");
		populateFields(selectedCihaz);
		fStandardDialog.display();
	}
	
	public String getAd(){
		return this.ad.getText();
	}
	public String getUrl(){
		return this.url.getText();
	}
	
	private void buildGui(JFrame aParent, String aDialogTitle) {
		fStandardDialog = new StandardDialog(
		      aParent, aDialogTitle, true, OnClose.DISPOSE, getUserInputArea(), getButtons()
		);
		fStandardDialog.setDefaultButton(fEditButton);
	}	
	
	private JPanel getUserInputArea() {
	    JPanel result = new JPanel();
	    result.setLayout(new BoxLayout(result, BoxLayout.Y_AXIS));

	    addTextField(this.url, "Cihaz URL:", result);
	    addTextField(this.ad, "Ad:", result);
	    UiUtil.alignAllX(result, UiUtil.AlignX.LEFT);
	    return result;
	}
	
	private void populateFields(YeniCihaz selectedCihaz) {
	    this.url.setText(selectedCihaz.getUrl());
	    this.ad.setText(gsmParser.cihazAdiBul(uretici));
	}	

	private void addTextField(JTextField aTextField, String aLabel, JPanel aPanel) {
	    JPanel panel = new JPanel();
	    
		JLabel label = new JLabel(aLabel);
		label.setPreferredSize(new Dimension(80, 20));
		panel.add(label);
		panel.add(aTextField);
	    aTextField.setColumns(25);
	    aPanel.add(panel);
	}

	private java.util.List<JButton> getButtons() {
	    java.util.List<JButton> result = new ArrayList<>();

	    fEditButton = new JButton(fEdit.toString());
	    fEditButton.addActionListener(new CihazController(this, fEdit,uretici));
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
	
	  /** Close the view. */
	  void closeDialog() {
	    fStandardDialog.dispose();
	  }	
}
