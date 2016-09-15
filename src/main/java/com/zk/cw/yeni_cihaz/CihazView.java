package com.zk.cw.yeni_cihaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.zk.cw.uretici.Uretici;
import com.zk.cw.util.Edit;
import com.zk.cw.util.GsmParser;
import com.zk.cw.util.ui.OnClose;
import com.zk.cw.util.ui.StandardDialog;
import com.zk.cw.util.ui.UiUtil;

public class CihazView {
	  
	// PRIVATE 
	private GsmParser gsmParser;
	private Uretici uretici;
	private StandardDialog fStandardDialog;
	private Edit fEdit;	
	private JButton fEditButton;	
	private JTextField url = new JTextField(); 
	private JTextField ad = new JTextField(); 
	private URL resimUrl;
	private BufferedImage resim;
	private ImageIcon resimIkon = new ImageIcon();
	private JTextField ikiGBant = new JTextField(); 
	private JTextField ucGBant = new JTextField(); 
	private JTextField dortGBant = new JTextField(); 
	private JTextField hiz = new JTextField(); 
	private JTextField gprs = new JTextField(); 
	private JTextField edge = new JTextField(); 
	private String aylarTr[]={"Ocak", "Şubat", "Mart ", "Nisan", "Mayıs", "Haziran", "Temmuz", "Agustos", "Eylül", "Ekim ", "Kasım", "Aralık"};
	private JComboBox duyurulmaYil = new JComboBox();
	private JComboBox duyurulmaAy = new JComboBox();
	private JTextField boyutlar = new JTextField(); 
	private JTextField agirlik = new JTextField(); 
	private JTextField sim = new JTextField(); 
	private JTextField ekranTipi = new JTextField(); 
	private JTextField ekranGen = new JTextField(); 
	private JTextField ekranCoz = new JTextField(); 
	private JTextField multiTouch = new JTextField(); 
	private JTextField ekranKor = new JTextField(); 
	private JTextField os = new JTextField(); 
	private JTextField yongaSeti = new JTextField(); 
	private JTextField cpu = new JTextField(); 
	private JTextField gpu = new JTextField(); 
	private JTextField hafizaKarti = new JTextField(); 
	private JTextField dahiliHafiza = new JTextField(); 
	
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
	public String getResimUrl(){
		return this.resimUrl.getPath();
	}
	public BufferedImage getResim(){
		return this.resim;
	}
	public String getIkiGBant(){
		return this.ikiGBant.getText();
	}
	public String getUcGBant(){
		return this.ucGBant.getText();
	}
	public String getDortGBant(){
		return this.dortGBant.getText();
	}	
	public String getHiz(){
		return this.hiz.getText();
	}
	public String getGprs(){
		return this.gprs.getText();
	}	
	public String getEdge(){
		return this.edge.getText();
	}		
	public String getDuyurulmaYil(){
		return this.duyurulmaYil.getSelectedItem().toString();
	}		
	public String getDuyurulmaAy(){
		return this.duyurulmaAy.getSelectedItem().toString();
	}	
	public String getBoyutlar(){
		return this.boyutlar.getText();
	}	
	public String getAgirlik(){
		return this.agirlik.getText();
	}	
	public String getSim(){
		return this.sim.getText();
	}
	public String getEkranTip(){
		return this.ekranTipi.getText();
	}	
	public String getEkranGen(){
		return this.ekranGen.getText();
	}		
	public String getEkranCoz(){
		return this.ekranCoz.getText();
	}		
	public String getMultiTouch(){
		return this.multiTouch.getText();
	}		
	public String getEkranKor(){
		return this.ekranKor.getText();
	}		
	public String getOs(){
		return this.os.getText();
	}		
	public String getYongaSeti(){
		return this.yongaSeti.getText();
	}		
	public String getCpu(){
		return this.cpu.getText();
	}		
	public String getGpu(){
		return this.gpu.getText();
	}
	public String getHafizaKarti(){
		return this.hafizaKarti.getText();
	}	
	public String getDahiliHafiza(){
		return this.dahiliHafiza.getText();
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
	    addTextField(this.ikiGBant, "2G Bant:", result);
	    addTextField(this.ucGBant, "3G Bant:", result);
	    addTextField(this.dortGBant, "4G Bant:", result);
	    addTextField(this.hiz, "Hız:", result);
	    addTextField("GPRS:",this.gprs, "EDGE:", this.edge, result);
	    addDuyurulmaField(this.duyurulmaYil,this.duyurulmaAy, "Duyurulma:", result);
	    addTextField("Boyutlar:", this.boyutlar, "Ağırlık:", this.agirlik, result);
	    addTextField(this.sim, "SIM:", result);
	    addTextField("Ekran Tipi:", this.ekranTipi,"Ekran Genişliği:", this.ekranGen, result);
	    addTextField("Ekran Çözünürlüğü:",this.ekranCoz,"Çoklu-dokunmatik", this.multiTouch, result);
	    addTextField(this.ekranKor, "Ekran Koruma", result);
	    addTextField("İşletim Sistemi",this.os,"Yonga Seti",this.yongaSeti, result);
	    addTextField( "CPU",this.cpu,"GPU",this.gpu, result);
	    addTextField( "Hafıza Kartı",this.hafizaKarti,"Dahili Hafıza",this.dahiliHafiza, result);
	    addPictureField(this.resimIkon,"Resim:", result);
	    UiUtil.alignAllX(result, UiUtil.AlignX.LEFT);
	    return result;
	}
	
	private void populateFields(YeniCihaz selectedCihaz) {
	    this.url.setText(selectedCihaz.getUrl());
	    this.ad.setText(gsmParser.cihazAdiBul(uretici));
	    this.ikiGBant.setText(gsmParser.ikiGBantBul());
	    this.ucGBant.setText(gsmParser.ucGBantBul());
	    this.dortGBant.setText(gsmParser.dortGBantBul());
	    this.hiz.setText(gsmParser.hizBul());
	    this.gprs.setText(gsmParser.gprsBul());
	    this.edge.setText(gsmParser.edgeBul());
		
	    for(int i = 1995; i < 2020; i++){
			duyurulmaYil.addItem(i);
			if(gsmParser.duyurulmaYilBul() == i){
				duyurulmaYil.setSelectedItem(i);;
			}
		}

	    String months[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		duyurulmaAy.setSelectedIndex(-1);
		for(int i=0; i<months.length; i++ ){
			if(months[i].equals(gsmParser.duyurulmaAyBul())){
				duyurulmaAy.setSelectedItem(aylarTr[i]);
			}
		}
		
		this.boyutlar.setText(gsmParser.boyutBul());
		this.agirlik.setText(gsmParser.agirlikBul());
		this.sim.setText(gsmParser.simBul());
		this.ekranTipi.setText(gsmParser.ekranTipBul());
		this.ekranGen.setText(gsmParser.ekranGenBul());
		this.ekranCoz.setText(gsmParser.ekranCozBul());
		this.multiTouch.setText(gsmParser.multiTouchBul());
		this.ekranKor.setText(gsmParser.ekranKorBul());
		this.os.setText(gsmParser.osBul());
		this.yongaSeti.setText(gsmParser.yongaSetiBul());
		this.cpu.setText(gsmParser.cpuBul());
		this.gpu.setText(gsmParser.gpuBul());
		this.hafizaKarti.setText(gsmParser.hafizaKartiBul());
		this.dahiliHafiza.setText(gsmParser.dahiliHafizaBul());
		
	    try {
			this.resimUrl = new URL( gsmParser.resimBul());
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.resim = ImageIO.read(resimUrl);
			resimIkon.setImage(this.resim);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	private void addTextField(JTextField aTextField, String aLabel, JPanel aPanel) {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    
		JLabel label = new JLabel(aLabel);
		label.setPreferredSize(new Dimension(100, 15));
		label.setFont(new Font("Verdana", Font.PLAIN, 12));
		panel.add(label);
		panel.add(aTextField);
	    aTextField.setPreferredSize(new Dimension(800, 18));
	    aTextField.setFont(new Font("Verdana", Font.PLAIN, 12));
	    aPanel.add(panel);
	}
	
	private void addTextField(String aLabel, JTextField aTextField,  String aLabel1, JTextField aTextField1, JPanel aPanel ) {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    
		JLabel label = new JLabel(aLabel);
		label.setPreferredSize(new Dimension(100, 15));
		label.setFont(new Font("Verdana", Font.PLAIN, 12));
		panel.add(label);
	    aTextField.setPreferredSize(new Dimension(350, 18));
	    aTextField.setFont(new Font("Verdana", Font.PLAIN, 12));
		panel.add(aTextField);
		
	    
		JLabel label1 = new JLabel(aLabel1);
		label1.setPreferredSize(new Dimension(100, 15));
		label1.setFont(new Font("Verdana", Font.PLAIN, 12));
		panel.add(label1);
		panel.add(aTextField1);
		
		aTextField1.setPreferredSize(new Dimension(350, 18));
		aTextField1.setFont(new Font("Verdana", Font.PLAIN, 12));	    
	    
	    aPanel.add(panel);
	}	
	
	private void addDuyurulmaField(JComboBox duyurulmaYil, JComboBox duyurulmaAy, String aLabel, JPanel aPanel) {
	    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel label = new JLabel(aLabel);
		label.setPreferredSize(new Dimension(100, 15));
		label.setFont(new Font("Verdana", Font.PLAIN, 12));
		panel.add(label);
		panel.add(duyurulmaYil);
		ComboBoxModel duyurulmaAyModel = new DefaultComboBoxModel(aylarTr);
		duyurulmaAy.setModel(duyurulmaAyModel);
		panel.add(duyurulmaAy);
		duyurulmaAy.setPreferredSize(new Dimension(100, 15));
		duyurulmaYil.setPreferredSize(new Dimension(100, 15));
		duyurulmaAy.setFont(new Font("Verdana", Font.PLAIN, 12));
		duyurulmaYil.setFont(new Font("Verdana", Font.PLAIN, 12));
		aPanel.add(panel);
	}	
	
	private void addPictureField(ImageIcon resimIkon, String aLabel, JPanel aPanel) {
	    JPanel panel = new JPanel();

		JLabel label = new JLabel(aLabel);
		label.setPreferredSize(new Dimension(120, 18));
		panel.add(label);
	    JLabel resimLabel = new JLabel(resimIkon);
	    resimLabel.setPreferredSize(new Dimension(500, 100));
	    resimLabel.setHorizontalAlignment(SwingConstants.LEFT);
	    panel.add(resimLabel);
	    aPanel.add(panel);
	}	

	private java.util.List<JButton> getButtons() {
	    java.util.List<JButton> result = new ArrayList<>();

	    fEditButton = new JButton(fEdit.toString());
	    fEditButton.addActionListener(new CihazController(this, fEdit, uretici));
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
