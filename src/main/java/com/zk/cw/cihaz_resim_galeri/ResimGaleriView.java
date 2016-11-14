package com.zk.cw.cihaz_resim_galeri;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.zk.cw.cihaz.Cihaz;
import com.zk.cw.cihaz.CihazController;
import com.zk.cw.util.Edit;
import com.zk.cw.util.ui.OnClose;
import com.zk.cw.util.ui.StandardDialog;
import com.zk.cw.util.ui.UiUtil;

public class ResimGaleriView {
	
	private StandardDialog fStandardDialog;
	private JButton fButton;
	private Integer fId;
	
	private ResimGaleriDAO resimGaleriDAO;
	private List<ResimGaleri> selectedResimGaleri;
	
	ResimGaleriView(JFrame aParent, Cihaz selectedCihaz) {				    
		fId = selectedCihaz.getId();
		buildGui(aParent, "Cihaz Güncelle");
		try {
			selectedResimGaleri = ResimGaleriDAO.findByCihazId(fId);			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//populateFields(selectedCihaz);
		fStandardDialog.display();
	}
	
	private void buildGui(JFrame aParent, String aDialogTitle) {
		fStandardDialog = new StandardDialog(
		      aParent, aDialogTitle, true, OnClose.DISPOSE, getUserInputArea(), getButtons()
		);
		fStandardDialog.setDefaultButton(fButton);
	}
	private JPanel getUserInputArea() {
	    JPanel mainPanel = new JPanel();
	    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
	    //JPanel genelPanel = getGenelInputArea();	    
	    //mainPanel.add(genelPanel); 
	    
	    UiUtil.alignAllX(mainPanel, UiUtil.AlignX.LEFT);
	    return mainPanel;	    
	}
	private java.util.List<JButton> getButtons() {
	    java.util.List<JButton> result = new ArrayList<>();

	    fButton = new JButton("Kaydet");
	    fButton.addActionListener(new ResimGaleriController(this));
	    result.add(fButton);
	    
	    JButton cancel = new JButton("İptal");
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
	
	  /** Return the underlying dialog. */
	JDialog getDialog() {
	    return fStandardDialog.getDialog();
	}	
}
