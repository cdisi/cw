package com.zk.cw.cihaz_resim;

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

public class ResimView {
	
	private StandardDialog fStandardDialog;
	public JButton fButton;
	private Integer fId;
	JPanel mainPanel = new JPanel();
	public JPanel resimGaleriPanel;
	public JButton fSil;
	
	private List<Resim> resimGaleriList;
	
	ResimView(JFrame aParent, Cihaz selectedCihaz) {				    
		fId = selectedCihaz.getId();
		buildGui(aParent, "Cihaz Güncelle");
		fStandardDialog.display();
	}
	
	JPanel getResimGaleriPanel(){
		return resimGaleriPanel;
	}		
	
	Integer getCihazId(){
		return fId;
	}
	
	private void buildGui(JFrame aParent, String aDialogTitle) {
		fStandardDialog = new StandardDialog(
		      aParent, aDialogTitle, true, OnClose.DISPOSE, getUserInputArea(), getButtons()
		);
		fStandardDialog.setDefaultButton(fButton);
	}
	private JPanel getUserInputArea() {	    
	    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
	    mainPanel.setBorder(BorderFactory.createTitledBorder("RESİM GALERİSİ"));
	    JPanel galeriPanel = getGaleriInputArea();	    
	    mainPanel.add(galeriPanel); 
	    
	    UiUtil.alignAllX(mainPanel, UiUtil.AlignX.LEFT);
	    return mainPanel;	    
	}
	
	public JPanel getGaleriInputArea(){
		resimGaleriPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));	    
		try {
			resimGaleriList = ResimDAO.findByCihazId(fId);			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for(Resim resimGaleri: resimGaleriList)
			addResimPanelField(resimGaleri, resimGaleriPanel);
	    return resimGaleriPanel;
	}
	
	private void addResimPanelField(Resim aResimGaleri, JPanel aPanel) {
		JPanel result = new JPanel();
		result.setLayout(new BoxLayout(result, BoxLayout.Y_AXIS));
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(aResimGaleri.getOrtaResim()));
		result.add(label);
		    
		fSil = new JButton("Sil");
		fSil.addActionListener(new ResimControllerDelete(this, mainPanel));	
		result.add(fSil);
		aPanel.add(result);
	}
	
	private java.util.List<JButton> getButtons() {
	    java.util.List<JButton> result = new ArrayList<>();

	    fButton = new JButton("Resim Yükle");
	    fButton.addActionListener(new ResimControllerAdd(this, mainPanel));
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
