package com.zk.cw.cihaz;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.zk.cw.cihaz_resim.Resim;
import com.zk.cw.cihaz_resim.ResimDAO;
import com.zk.cw.cihaz_tur.CihazTur;
import com.zk.cw.cihaz_tur.CihazTurCombBoxModel;
import com.zk.cw.cihaz_tur.CihazTurComboBoxRenderer;
import com.zk.cw.cihaz_tur.CihazTurDAO;
import com.zk.cw.uretici.Uretici;
import com.zk.cw.util.Edit;
import com.zk.cw.util.ui.OnClose;
import com.zk.cw.util.ui.StandardDialog;
import com.zk.cw.util.ui.UiUtil;

import com.zk.cw.uretici.UreticiCombBoxModel;
import com.zk.cw.uretici.UreticiComboBoxRenderer;
import com.zk.cw.uretici.UreticiDAO;

public class CihazView {
	private StandardDialog fStandardDialog;
	private Edit fEdit;	
	private JButton fEditButton;
	private Integer fId;
	private JTextField fAd = new JTextField();
	private JTextField fDigerAd = new JTextField();
	private UreticiCombBoxModel ureticiCombBoxModel = new UreticiCombBoxModel();
	private JComboBox<Uretici> fUretici = new JComboBox<Uretici>(ureticiCombBoxModel);
	private ComboBoxModel<CihazTur> cihazTurCombBoxModel = new CihazTurCombBoxModel();
	private JComboBox<CihazTur> fCihazTur = new JComboBox<CihazTur>(cihazTurCombBoxModel);
	final String[] yilar = {"2010","2011", "2012", "2013", "2014", "2015", "2016", "2017"};
	final DefaultComboBoxModel duyurulmaYilCombBoxModel = new DefaultComboBoxModel(yilar);
	final JComboBox<Integer> fDuyurulmaYil = new JComboBox<Integer>(duyurulmaYilCombBoxModel);      
	final String[] aylar = {"","01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
	final DefaultComboBoxModel duyurulmaAyCombBoxModel = new DefaultComboBoxModel(aylar);
	final JComboBox<String> fDuyurulmaAy = new JComboBox<String>(duyurulmaAyCombBoxModel); 
	private JCheckBox fAnasayfa = new JCheckBox();
	private JCheckBox fAktif = new JCheckBox();
	JFileChooser fResimChooser= new JFileChooser();
	private ImageIcon fResimIkon = new ImageIcon();
	private byte[] fResim;

	private UreticiDAO ureticiDAO = new UreticiDAO(); 
	private Uretici selectedUretici;
	
	private CihazTurDAO cihazTurDAO = new CihazTurDAO(); 
	private CihazTur selectedCihazTur;
	
	private ResimDAO resimDAO = new ResimDAO();
	
	CihazView(JFrame aParent) {				    
		fEdit = Edit.ADD;		
		buildGui(aParent, "Cihaz Ekle");
		fStandardDialog.display();
	}
	
	CihazView(JFrame aParent, Cihaz selectedCihaz) {				    
		fEdit = Edit.CHANGE;		
		fId = selectedCihaz.getId();
		buildGui(aParent, "Cihaz Güncelle");
		try {
			selectedUretici = ureticiDAO.findById(selectedCihaz.getUreticiId());
			selectedCihazTur = cihazTurDAO.findById(selectedCihaz.getTuru());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		populateFields(selectedCihaz);
		fStandardDialog.display();
	}
	
	Integer getId() {
	    return fId;
	}	
	String getAd() {
	    return fAd.getText();
	}	
	String getDigerAd() {
	    return fDigerAd.getText();
	}	
	Uretici getUretici() {
		return (Uretici) fUretici.getModel().getSelectedItem();
	}		
	CihazTur getCihazTur() {
		return (CihazTur) fCihazTur.getModel().getSelectedItem();
	}
	String getDuyurulmaYil() {
		return   (String) fDuyurulmaYil.getModel().getSelectedItem();
	}
	String getDuyurulmaAy() {
		return  (String) fDuyurulmaAy.getModel().getSelectedItem();
	}	
	public Boolean getAnasayfa(){
		return this.fAnasayfa.isSelected();
	}	
	public Boolean getAktif(){
		return this.fAktif.isSelected();
	}		
	public byte[] getResim(){
		return fResim;
	}	
	
	private void buildGui(JFrame aParent, String aDialogTitle) {
		fStandardDialog = new StandardDialog(
		      aParent, aDialogTitle, true, OnClose.DISPOSE, getUserInputArea(), getButtons()
		);
		fStandardDialog.setDefaultButton(fEditButton);
	}	
	
	private JPanel getUserInputArea() {
	    JPanel mainPanel = new JPanel();
	    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
	    JPanel genelPanel = getGenelInputArea();	    
	    mainPanel.add(genelPanel); 
	    
	    JPanel resimPanel = getResimInputArea();
	    mainPanel.add(resimPanel);
	    
	    UiUtil.alignAllX(mainPanel, UiUtil.AlignX.LEFT);
	    return mainPanel;	    
	}
	
	private JPanel getGenelInputArea(){
		JPanel result = new JPanel(new FlowLayout(FlowLayout.LEFT));	    
		result.setBorder(BorderFactory.createTitledBorder("GENEL"));
		addTextField(fAd, "Ad", result);
	    addTextField(fDigerAd, "Diğer Ad", result);
	    addUreticiComboField(fUretici, "Üretici", result);
	    addCihazTurComboField(fCihazTur, "Türü", result);
	    addDuyurulmaYilComboField(fDuyurulmaYil, "Duyurulma Yıl", result);
	    addDuyurulmaAyComboField(fDuyurulmaAy,"Ay",result);
	    addCheckField(fAnasayfa,"Anasayfa",result);
	    addCheckField(fAktif,"Aktif",result);
	    return result;
	}
	
	private JPanel getResimInputArea(){
		JPanel result = new JPanel(new FlowLayout(FlowLayout.LEFT));	    
		result.setBorder(BorderFactory.createTitledBorder("RESİM"));
		addResimIconField(fResimIkon, "Resim", result);
	    return result;
	}	
	
	private void addTextField(JTextField aTextField, String aLabel, JPanel aPanel) {
		  JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		  JLabel label = new JLabel(aLabel);
		  panel.add(label);
		  panel.add(aTextField);
		  aTextField.setColumns(15);
		  
		  aPanel.add(panel);		  
	}	
	
	private void addUreticiComboField(JComboBox<Uretici> aComboField, String aLabel, JPanel aPanel) {
  	    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		JLabel label = new JLabel(aLabel);
		panel.add(label);
		  
		try {
			for(Uretici uretici : ureticiDAO.all()){
				fUretici.addItem(uretici);
				fUretici.setRenderer(new UreticiComboBoxRenderer());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		

	    panel.add(aComboField);		 
		aPanel.add(panel);		  
	}	
	
	
	private void addCihazTurComboField(JComboBox<CihazTur> aComboField, String aLabel, JPanel aPanel) {
  	    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		JLabel label = new JLabel(aLabel);
		panel.add(label);
		  
		try {
			for(CihazTur cihazTur : cihazTurDAO.all()){
				fCihazTur.addItem(cihazTur);
				fCihazTur.setRenderer(new CihazTurComboBoxRenderer());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		

	    panel.add(aComboField);		 
		aPanel.add(panel);		  
	}	
	
	private void addDuyurulmaYilComboField(JComboBox<Integer> aComboField, String aLabel, JPanel aPanel) {
  	    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		JLabel label = new JLabel(aLabel);
		panel.add(label);

	    aComboField.setSelectedItem("2016");
	    panel.add(aComboField);		 
		aPanel.add(panel);		  
	}	
	
	private void addDuyurulmaAyComboField(JComboBox<String> aComboField, String aLabel, JPanel aPanel) {
  	    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel label = new JLabel(aLabel);
		panel.add(label);		  
	    panel.add(aComboField);		 
		aPanel.add(panel);		  
	}	
	
	private void addCheckField(JCheckBox aCheckField, String aLabel, JPanel aPanel) {
  	    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel label = new JLabel(aLabel);
		panel.add(label);		  
	    panel.add(aCheckField);		 
		aPanel.add(panel);		  
	}	
	
	// resim fields
	private void addResimIconField(ImageIcon resimIkon, String aLabel, JPanel aPanel) {

  	    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel label = new JLabel(aLabel);
		panel.add(label);		  
	    
	    JLabel resimLabel = new JLabel(resimIkon);
	    resimLabel.setPreferredSize(new Dimension(110, 146));
	    panel.add(resimLabel);
		
	    aPanel.add(panel);		  

	}
	
	private void populateFields(Cihaz aSelectedCihaz) {
		fAd.setText(aSelectedCihaz.getAd());
		fDigerAd.setText(aSelectedCihaz.getDigerAd());
		fUretici.getModel().setSelectedItem(selectedUretici);
		fCihazTur.getModel().setSelectedItem(selectedCihazTur);
		if(!aSelectedCihaz.getDuyurulma().substring(0, 4).isEmpty()){
			aSelectedCihaz.setDuyurulmaYil(aSelectedCihaz.getDuyurulma().substring(0, 4));
		}
		fDuyurulmaYil.getModel().setSelectedItem(aSelectedCihaz.getDuyurulmaYil());
		
		if(!aSelectedCihaz.getDuyurulma().substring(5).isEmpty()){
			aSelectedCihaz.setDuyurulmaAy(aSelectedCihaz.getDuyurulma().substring(5));
		}
		fDuyurulmaAy.getModel().setSelectedItem(aSelectedCihaz.getDuyurulmaAy());
		if(aSelectedCihaz.getAnasayfa() == 1){
			fAnasayfa.setSelected(true);
		}
		if(aSelectedCihaz.getAktif() == 1){
			fAktif.setSelected(true);
		}
		Resim resim = null;
		try {
			resim = resimDAO.findById(aSelectedCihaz.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InputStream in = new ByteArrayInputStream(resim.getResim());
		BufferedImage bufImage = null;
		try {
			bufImage = ImageIO.read(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fResimIkon.setImage(bufImage);
	}
	
	private java.util.List<JButton> getButtons() {
	    java.util.List<JButton> result = new ArrayList<>();

	    fEditButton = new JButton(fEdit.toString());
	    fEditButton.addActionListener(new CihazController(this, fEdit));
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
	
	  /** Return the underlying dialog. */
	JDialog getDialog() {
	    return fStandardDialog.getDialog();
	}	
}
