package com.zk.cw.cihaz;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
import com.zk.cw.ekran.EkranDAO;
import com.zk.cw.ekran.EkranTip;
import com.zk.cw.ozellik_atama.OzellikAtama;
import com.zk.cw.ozellik_atama.OzellikAtamaDAO;
import com.zk.cw.uretici.Uretici;
import com.zk.cw.util.Edit;
import com.zk.cw.util.ImageResize;
import com.zk.cw.util.ui.OnClose;
import com.zk.cw.util.ui.StandardDialog;
import com.zk.cw.util.ui.UiUtil;

import com.zk.cw.uretici.UreticiCombBoxModel;
import com.zk.cw.uretici.UreticiComboBoxRenderer;
import com.zk.cw.uretici.UreticiDAO;
import com.zk.cw.ekran.*;
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
	
	private UreticiDAO ureticiDAO = new UreticiDAO(); 
	private Uretici selectedUretici;
	
	private CihazTurDAO cihazTurDAO = new CihazTurDAO(); 
	private CihazTur selectedCihazTur;
	
	private ResimDAO resimDAO;
	private Resim selectedResim;
	
	private OzellikAtama ozellikAtama;
	private OzellikAtamaDAO ozellikAtamaDao = new OzellikAtamaDAO();	
	
	//ekran tip
	private ComboBoxModel<EkranTip> ekranTipCombBoxModel = new EkranTipComboBoxModel();
	private JComboBox<EkranTip> fEkranTip = new JComboBox<EkranTip>(ekranTipCombBoxModel);	
	private EkranDAO ekranDAO = new EkranDAO();	
	private EkranTip selectedEkranTip;
	
	//ekran renk
	private ComboBoxModel<EkranRenk> ekranRenkCombBoxModel = new EkranRenkComboBoxModel();
	private JComboBox<EkranRenk> fEkranRenk = new JComboBox<EkranRenk>(ekranRenkCombBoxModel);	
	private EkranRenkDAO ekranRenkDAO = new EkranRenkDAO();	
	private EkranRenk selectedEkranRenk;	
	
	//ekran genişliği
	private ComboBoxModel<EkranGenisligi> ekranGenisligiCombBoxModel = new EkranGenisligiComboBoxModel();
	private JComboBox<EkranGenisligi> fEkranGenisligi = new JComboBox<EkranGenisligi>(ekranGenisligiCombBoxModel);	
	private EkranGenisligiDAO ekranGenisligiDAO = new EkranGenisligiDAO();	
	private EkranGenisligi selectedEkranGenisligi;

	//ekran çözünürlüğü
	private ComboBoxModel<EkranCozunurluk> ekranCozunurlukCombBoxModel = new EkranCozunurlukComboBoxModel();
	private JComboBox<EkranCozunurluk> fEkranCozunurluk = new JComboBox<EkranCozunurluk>(ekranCozunurlukCombBoxModel);	
	private EkranCozunurlukDAO ekranCozunurlukDAO = new EkranCozunurlukDAO();	
	private EkranCozunurluk selectedEkranCozunurluk;
	
	//ekran piksel yoğunluğu
	private ComboBoxModel<EkranPPI> ekranPPICombBoxModel = new EkranPPIComboBoxModel();
	private JComboBox<EkranPPI> fEkranPPI = new JComboBox<EkranPPI>(ekranPPICombBoxModel);	
	private EkranPPIDAO ekranPPIDAO = new EkranPPIDAO();	
	private EkranPPI selectedEkranPPI;
	
	//çoklu dokunmatik
	private ComboBoxModel<CokluDokunmatik> cokluDokunmatikCombBoxModel = new CokluDokunmatikComboBoxModel();
	private JComboBox<CokluDokunmatik> fCokluDokunmatik = new JComboBox<CokluDokunmatik>(cokluDokunmatikCombBoxModel);	
	private CokluDokunmatik selectedCokluDokunmatik = new CokluDokunmatik();	
	
	private JTextField fEkranKor = new JTextField();
	private JTextField fBoyut = new JTextField("100 x 100 x 10 mm");
	private JTextField fAgirlik = new JTextField("100 gr");

	
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
			//ekran tipi
			ozellikAtama = ozellikAtamaDao.find(fId,10);
			if(ozellikAtama != null)
				selectedEkranTip = ekranDAO.findById(Integer.parseInt(ozellikAtama.getDeger())); 
			//ekran renk
			ozellikAtama = ozellikAtamaDao.find(fId,48);
			if(ozellikAtama!=null)
				selectedEkranRenk = ekranRenkDAO.findById(Integer.parseInt(ozellikAtama.getDeger()));
			//ekran genişlik
			ozellikAtama = ozellikAtamaDao.find(fId,11);
			if(ozellikAtama!=null){
				selectedEkranGenisligi = ekranGenisligiDAO.findByGenislik(ozellikAtama.getDeger());			
			}
			//ekran çözünürlük
			ozellikAtama = ozellikAtamaDao.find(fId,12);
			if(ozellikAtama!=null)
				selectedEkranCozunurluk = ekranCozunurlukDAO.findBy(Integer.parseInt(ozellikAtama.getDeger()));			
			//ekran ppi
			ozellikAtama = ozellikAtamaDao.find(fId,49);
			if(ozellikAtama!=null)
				selectedEkranPPI = ekranPPIDAO.findBy(Integer.parseInt(ozellikAtama.getDeger()));			
			//çoklu dokunmatik
			ozellikAtama = ozellikAtamaDao.find(fId,13);
			if(ozellikAtama!=null)
				selectedCokluDokunmatik.settDeger(ozellikAtama.getDeger());			
			//ekran koruma
			ozellikAtama = ozellikAtamaDao.find(fId,14);
			if(ozellikAtama!=null)
				fEkranKor.setText(ozellikAtama.getDeger());		
			
		} catch (SQLException e) {
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
	
	// ekran
	EkranTip getEkranTip() {
		return (EkranTip) fEkranTip.getModel().getSelectedItem();
	}
	
	EkranRenk getEkranRenk() {
		return (EkranRenk) fEkranRenk.getModel().getSelectedItem();
	}	
	EkranGenisligi getEkranGenisligi() {
		return (EkranGenisligi) fEkranGenisligi.getModel().getSelectedItem();
	}	
	EkranCozunurluk getEkranCozunurluk() {
		return (EkranCozunurluk) fEkranCozunurluk.getModel().getSelectedItem();
	}	
	EkranPPI getEkranPPI() {
		return (EkranPPI) fEkranPPI.getModel().getSelectedItem();
	}
	CokluDokunmatik getCokluDokunmatik() {
		return (CokluDokunmatik) fCokluDokunmatik.getModel().getSelectedItem();
	}
	String getEkranKor() {
	    return fEkranKor.getText();
	}			
	//gövde
	String getBoyut() {
	    return fBoyut.getText();
	}
	String getAgirlik() {
	    return fAgirlik.getText();
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
	    
	    JPanel ekranPanel = getEkranInputArea();
	    mainPanel.add(ekranPanel);
	    JPanel govdePanel = getGovdeInputArea();
	    mainPanel.add(govdePanel);
	    
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
	
	private JPanel getEkranInputArea(){
		JPanel result = new JPanel(new FlowLayout(FlowLayout.LEFT));	    
		result.setBorder(BorderFactory.createTitledBorder("EKRAN"));
	    addEkranTipComboField(fEkranTip, "Tipi", result);	    
	    addEkranRenkComboField(fEkranRenk, "Renk", result);	    
	    addEkranGenisligiComboField(fEkranGenisligi, "Genişlik", result);	    
	    addEkranCozunurlukComboField(fEkranCozunurluk, "Çözünürlük", result);	    
	    addEkranPPIComboField(fEkranPPI, "PPI", result);	    
	    addCokluDokunmatikComboField(fCokluDokunmatik, "ÇD", result);	    
		addTextField(fEkranKor, "EK", result);
		return result;
	}	
	private JPanel getGovdeInputArea(){
		JPanel result = new JPanel(new FlowLayout(FlowLayout.LEFT));	    
		result.setBorder(BorderFactory.createTitledBorder("GÖVDE"));
		addTextField(fBoyut, "Boyutlar", result);
		addTextField(fAgirlik, "Ağırlık", result);
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
	
	// Ekran
	private void addEkranTipComboField(JComboBox<EkranTip> aComboField, String aLabel, JPanel aPanel) {
  	    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		JLabel label = new JLabel(aLabel);
		panel.add(label);
		fEkranTip.addItem(new EkranTip(null, "Seçiniz"));		
		try {
			for(EkranTip ekranTip : ekranDAO.all()){
				fEkranTip.addItem(ekranTip);
				fEkranTip.setRenderer(new EkranTipComboBoxRenderer());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		aComboField.setPreferredSize(new Dimension(200, aComboField.getPreferredSize().height));
	    panel.add(aComboField);		 
		aPanel.add(panel);		  
	}	
	
	private void addEkranRenkComboField(JComboBox<EkranRenk> aComboField, String aLabel, JPanel aPanel) {
  	    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		JLabel label = new JLabel(aLabel);
		panel.add(label);
		fEkranRenk.addItem(new EkranRenk(null, "Seçiniz"));		
  		
		try {
			for(EkranRenk ekranRenk : ekranRenkDAO.all()){
				fEkranRenk.addItem(ekranRenk);
				fEkranRenk.setRenderer(new EkranRenkComboBoxRenderer());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		

	    panel.add(aComboField);		 
		aPanel.add(panel);		  
	}	
	
	private void addEkranGenisligiComboField(JComboBox<EkranGenisligi> aComboField, String aLabel, JPanel aPanel) {
  	    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		JLabel label = new JLabel(aLabel);
		panel.add(label);
		fEkranGenisligi.addItem(new EkranGenisligi(null, "Seçiniz"));  		
		try {
			for(EkranGenisligi ekranGenisligi : ekranGenisligiDAO.all()){
				fEkranGenisligi.addItem(ekranGenisligi);
				fEkranGenisligi.setRenderer(new EkranGenisligiComboBoxRenderer());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		

	    panel.add(aComboField);		 
		aPanel.add(panel);		  
	}	
	
	private void addEkranCozunurlukComboField(JComboBox<EkranCozunurluk> aComboField, String aLabel, JPanel aPanel) {
  	    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		JLabel label = new JLabel(aLabel);
		panel.add(label);
		fEkranCozunurluk.addItem(new EkranCozunurluk(null, "Seçiniz"));  	  		
		try {
			for(EkranCozunurluk ekranCozunurluk : ekranCozunurlukDAO.all()){
				fEkranCozunurluk.addItem(ekranCozunurluk);
				fEkranCozunurluk.setRenderer(new EkranCozunurlukComboBoxRenderer());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		

	    panel.add(aComboField);		 
		aPanel.add(panel);		  
	}	
	private void addEkranPPIComboField(JComboBox<EkranPPI> aComboField, String aLabel, JPanel aPanel) {
  	    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		JLabel label = new JLabel(aLabel);
		panel.add(label);
		fEkranPPI.addItem(new EkranPPI(null, "Seçiniz"));  	  		
		  		
		try {
			for(EkranPPI ekranPPI : ekranPPIDAO.all()){
				fEkranPPI.addItem(ekranPPI);
				fEkranPPI.setRenderer(new EkranPPIComboBoxRenderer());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		

	    panel.add(aComboField);		 
		aPanel.add(panel);		  
	}	
	
	private void addCokluDokunmatikComboField(JComboBox<CokluDokunmatik> aComboField, String aLabel, JPanel aPanel) {
  	    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		JLabel label = new JLabel(aLabel);
		panel.add(label);
		fCokluDokunmatik.addItem(new CokluDokunmatik("Seçiniz"));  	  		
		fCokluDokunmatik.addItem(new CokluDokunmatik("Var"));  	  		
		fCokluDokunmatik.addItem(new CokluDokunmatik("Yok"));  	  		
		fCokluDokunmatik.setRenderer(new CokluDokunmatikComboBoxRenderer());		  			

	    panel.add(aComboField);		 
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
		if(selectedEkranTip != null)
			fEkranTip.getModel().setSelectedItem(selectedEkranTip);
		if(selectedEkranRenk != null)
			fEkranRenk.getModel().setSelectedItem(selectedEkranRenk);
		if(selectedEkranGenisligi != null){
			fEkranGenisligi.getModel().setSelectedItem(selectedEkranGenisligi);
			selectedEkranGenisligi.toString();
		}
		if(selectedEkranCozunurluk != null)
			fEkranCozunurluk.getModel().setSelectedItem(selectedEkranCozunurluk);
		if(selectedEkranPPI != null)
			fEkranPPI.getModel().setSelectedItem(selectedEkranPPI);
		if(selectedCokluDokunmatik.getDeger()!=null)
			fCokluDokunmatik.getModel().setSelectedItem(selectedCokluDokunmatik);
		
		try {
			ozellikAtama = ozellikAtamaDao.find(fId,7);
			if(ozellikAtama!=null)
				fBoyut.setText(ozellikAtama.getDeger());	
			
			ozellikAtama = ozellikAtamaDao.find(fId,8);
			if(ozellikAtama!=null)
				fAgirlik.setText(ozellikAtama.getDeger());			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
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
