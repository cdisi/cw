package com.zk.cw.cihaz_resim_galeri;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.zk.cw.cihaz.Cihaz;
import com.zk.cw.util.ui.OnClose;
import com.zk.cw.util.ui.StandardDialog;
import com.zk.cw.util.ui.UiUtil;

public class ResimGaleriView {
	
	private StandardDialog fStandardDialog;
	private JButton fButton;
	private Integer fId;
	
	private List<ResimGaleri> resimGaleriList;
	
	ResimGaleriView(JFrame aParent, Cihaz selectedCihaz) {				    
		fId = selectedCihaz.getId();
		try {
			resimGaleriList = ResimGaleriDAO.findByCihazId(fId);			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		buildGui(aParent, "Cihaz Güncelle");
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
	    JPanel galeriPanel = getGaleriInputArea();	    
	    mainPanel.add(galeriPanel); 
	    
	    UiUtil.alignAllX(mainPanel, UiUtil.AlignX.LEFT);
	    return mainPanel;	    
	}
	
	private JPanel getGaleriInputArea(){
		JPanel result = new JPanel(new FlowLayout(FlowLayout.LEFT));	    
		result.setBorder(BorderFactory.createTitledBorder("GALERİ"));
		for(ResimGaleri resimGaleri: resimGaleriList)
			addResimPanelField(resimGaleri, result);
	    return result;
	}	
	
	private void addResimPanelField(ResimGaleri aResimGaleri, JPanel aPanel) {
		JPanel result = new JPanel();
		result.setLayout(new BoxLayout(result, BoxLayout.Y_AXIS));
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(aResimGaleri.getKucukResim()));
		result.add(label);
		    
		JButton sil = new JButton("Sil");
		//sil.setAlignmentX(Component.RIGHT_ALIGNMENT);
		sil.addActionListener(new ActionListener() {
		      @Override public void actionPerformed(ActionEvent arg0) {
		        
		      }
		});		
		result.add(sil);
		aPanel.add(result);
	}
	
	private java.util.List<JButton> getButtons() {
	    java.util.List<JButton> result = new ArrayList<>();

	    fButton = new JButton("Resim Yükle");
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
