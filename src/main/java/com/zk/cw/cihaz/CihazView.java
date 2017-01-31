package com.zk.cw.cihaz;

import java.awt.Component;
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
import javax.swing.ButtonGroup;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.zk.cw.batarya.BataryaDegisir;
import com.zk.cw.batarya.BataryaDegisirCombBoxModel;
import com.zk.cw.batarya.BataryaDegisirDAO;
import com.zk.cw.batarya.BataryaKapasite;
import com.zk.cw.batarya.BataryaKapasiteCombBoxModel;
import com.zk.cw.batarya.BataryaKapasiteDAO;
import com.zk.cw.batarya.BataryaTeknoloji;
import com.zk.cw.batarya.BataryaTeknolojiCombBoxModel;
import com.zk.cw.batarya.BataryaTeknolojiDAO;
import com.zk.cw.cihaz_resim.Resim;
import com.zk.cw.cihaz_resim.ResimDAO;
import com.zk.cw.cihaz_tur.CihazTur;
import com.zk.cw.cihaz_tur.CihazTurCombBoxModel;
import com.zk.cw.cihaz_tur.CihazTurComboBoxRenderer;
import com.zk.cw.cihaz_tur.CihazTurDAO;
import com.zk.cw.cpu.CekirdekHiz;
import com.zk.cw.cpu.CekirdekHizCombBoxModel;
import com.zk.cw.cpu.CekirdekHizComboBoxRenderer;
import com.zk.cw.cpu.CekirdekHizDAO;
import com.zk.cw.cpu.CekirdekSayi;
import com.zk.cw.cpu.CekirdekSayiCombBoxModel;
import com.zk.cw.cpu.CekirdekSayiComboBoxRenderer;
import com.zk.cw.cpu.CekirdekSayiDAO;
import com.zk.cw.cpu.CpuSayiHizAta;
import com.zk.cw.cpu.CpuSayiHizAtaDAO;
import com.zk.cw.ekran.EkranDAO;
import com.zk.cw.ekran.EkranTip;
import com.zk.cw.gpu.Gpu;
import com.zk.cw.gpu.GpuCombBoxModel;
import com.zk.cw.gpu.GpuComboBoxRenderer;
import com.zk.cw.gpu.GpuDAO;
import com.zk.cw.hafiza.DahiliHafiza;
import com.zk.cw.hafiza.DahiliHafizaAta;
import com.zk.cw.hafiza.DahiliHafizaAtaDAO;
import com.zk.cw.hafiza.DahiliHafizaCombBoxModel;
import com.zk.cw.hafiza.DahiliHafizaComboBoxRenderer;
import com.zk.cw.hafiza.DahiliHafizaDAO;
import com.zk.cw.harici_hafiza.HariciHafizaBuyukluk;
import com.zk.cw.harici_hafiza.HariciHafizaBuyuklukCombBoxModel;
import com.zk.cw.harici_hafiza.HariciHafizaBuyuklukComboBoxRenderer;
import com.zk.cw.harici_hafiza.HariciHafizaBuyuklukDAO;
import com.zk.cw.harici_hafiza.HariciHafizaTipi;
import com.zk.cw.harici_hafiza.HariciHafizaTipiCombBoxModel;
import com.zk.cw.harici_hafiza.HariciHafizaTipiComboBoxRenderer;
import com.zk.cw.harici_hafiza.HariciHafizaTipiDAO;
import com.zk.cw.kamera.ArkaKameraAta;
import com.zk.cw.kamera.ArkaKameraAtaDAO;
import com.zk.cw.kamera.KameraCozunurluk;
import com.zk.cw.kamera.KameraCozunurlukCombBoxModel;
import com.zk.cw.kamera.KameraCozunurlukComboBoxRenderer;
import com.zk.cw.kamera.KameraCozunurlukDAO;
import com.zk.cw.kamera.Diyafram;
import com.zk.cw.kamera.DiyaframCombBoxModel;
import com.zk.cw.kamera.DiyaframComboBoxRenderer;
import com.zk.cw.kamera.DiyaframDAO;
import com.zk.cw.kamera.OnKameraAta;
import com.zk.cw.kamera.OnKameraAtaDAO;
import com.zk.cw.kamera.PikselBuyuklugu;
import com.zk.cw.kamera.PikselBuyukluguCombBoxModel;
import com.zk.cw.kamera.PikselBuyukluguComboBoxRenderer;
import com.zk.cw.kamera.PikselBuyukluguDAO;
import com.zk.cw.os.OS;
import com.zk.cw.os.OSChangeListener;
import com.zk.cw.os.OSComboBoxModel;
import com.zk.cw.os.OSComboBoxRenderer;
import com.zk.cw.os.OSDAO;
import com.zk.cw.os.OSSurum;
import com.zk.cw.os.OSSurumBoxModel;
import com.zk.cw.os.OSSurumComboBoxRenderer;
import com.zk.cw.os.OSSurumDAO;
import com.zk.cw.ozellik_atama.OzellikAtama;
import com.zk.cw.ozellik_atama.OzellikAtamaDAO;
import com.zk.cw.ram.Ram;
import com.zk.cw.ram.RamAta;
import com.zk.cw.ram.RamAtaDAO;
import com.zk.cw.ram.RamCombBoxModel;
import com.zk.cw.ram.RamComboBoxRenderer;
import com.zk.cw.ram.RamDAO;
import com.zk.cw.sensor.Sensor;
import com.zk.cw.sensor.SensorAta;
import com.zk.cw.sensor.SensorAtaDAO;
import com.zk.cw.sensor.SensorDAO;
import com.zk.cw.sensor.SensorJCheckBox;
import com.zk.cw.sensor.SensorListener;
import com.zk.cw.sim.Sim;
import com.zk.cw.sim.SimComboBoxModel;
import com.zk.cw.sim.SimComboBoxRenderer;
import com.zk.cw.sim.SimDAO;
import com.zk.cw.sim.SimSayisi;
import com.zk.cw.sim.SimSayisiComboBoxModel;
import com.zk.cw.sim.SimSayisiComboBoxRenderer;
import com.zk.cw.sim.SimSayisiDAO;
import com.zk.cw.uretici.Uretici;
import com.zk.cw.util.Edit;
import com.zk.cw.util.ImageResize;
import com.zk.cw.util.ui.OnClose;
import com.zk.cw.util.ui.StandardDialog;
import com.zk.cw.util.ui.UiUtil;
import com.zk.cw.yonga_seti.YongaSeti;
import com.zk.cw.yonga_seti.YongaSetiBoxModel;
import com.zk.cw.yonga_seti.YongaSetiComboBoxRenderer;
import com.zk.cw.yonga_seti.YongaSetiDAO;
import com.zk.cw.uretici.UreticiCombBoxModel;
import com.zk.cw.uretici.UreticiComboBoxRenderer;
import com.zk.cw.uretici.UreticiDAO;
import com.zk.cw.ekran.*;
public class CihazView {
	JPanel mainPanel;
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
	private JTextArea fEkranDiger = new JTextArea(2,70); 

	// gövde
	private JTextField fBoyut = new JTextField("100 x 100 x 10 mm");
	private JTextField fAgirlik = new JTextField("100 gr");
	//hat sayısı
	private ComboBoxModel<SimSayisi> simSayisiCombBoxModel = new SimSayisiComboBoxModel();
	private JComboBox<SimSayisi> fSimSayisi = new JComboBox<SimSayisi>(simSayisiCombBoxModel);	
	private SimSayisi selectedSimSayisi = new SimSayisi();	
	//sim türü
	private ComboBoxModel<Sim> simCombBoxModel = new SimComboBoxModel();
	private JComboBox<Sim> fSim = new JComboBox<Sim>(simCombBoxModel);	
	private Sim selectedSim = new Sim();	
	private JTextArea fGovdeDiger = new JTextArea(2,30); 
	//os
	private ComboBoxModel<OS> osCombBoxModel = new OSComboBoxModel();
	private JComboBox<OS> fOS = new JComboBox<OS>(osCombBoxModel);	
	private OS selectedOS = new OS();	
	//os sürüm
	private ComboBoxModel<OSSurum> osSurumCombBoxModel = new OSSurumBoxModel();
	private JComboBox<OSSurum> fOSSurum = new JComboBox<OSSurum>(osSurumCombBoxModel);	
	private OSSurum selectedOSSurum = new OSSurum();	
	//yongaseti
	private ComboBoxModel<YongaSeti> yongaSetiCombBoxModel = new YongaSetiBoxModel();
	private JComboBox<YongaSeti> fYongaSeti = new JComboBox<YongaSeti>(yongaSetiCombBoxModel);	
	private YongaSeti selectedYongaSeti = new YongaSeti();	
	//gpu
	private ComboBoxModel<Gpu> gpuCombBoxModel = new GpuCombBoxModel();
	private JComboBox<Gpu> fGpu = new JComboBox<Gpu>(gpuCombBoxModel);	
	private Gpu selectedGpu = new Gpu();
	
	CpuSayiHizAta cpuSayiHizAta;
	CpuSayiHizAta cpuSayiHizAta2;
	//Cpu Islemci Sayisi
	private ComboBoxModel<CekirdekSayi> cekirdekSayiComboBoxModel = new CekirdekSayiCombBoxModel();
	private JComboBox<CekirdekSayi> fCekirdekSayi = new JComboBox<CekirdekSayi>(cekirdekSayiComboBoxModel);	
	private CekirdekSayi selectedCekirdekSayi= new CekirdekSayi();
	//Cpu Islemci Sayisi 2
	private ComboBoxModel<CekirdekSayi> cekirdekSayi2ComboBoxModel = new CekirdekSayiCombBoxModel();
	private JComboBox<CekirdekSayi> fCekirdekSayi2 = new JComboBox<CekirdekSayi>(cekirdekSayi2ComboBoxModel);	
	private CekirdekSayi selectedCekirdekSayi2= new CekirdekSayi();
	//Cpu işlemci hızı
	private ComboBoxModel<CekirdekHiz> cekirdekHizComboBoxModel = new CekirdekHizCombBoxModel();
	private JComboBox<CekirdekHiz> fCekirdekHiz= new JComboBox<CekirdekHiz>(cekirdekHizComboBoxModel);	
	private CekirdekHiz selectedCekirdekHiz= new CekirdekHiz();
	//Cpu işlemci hızı 2
	private ComboBoxModel<CekirdekHiz> cekirdekHiz2ComboBoxModel = new CekirdekHizCombBoxModel();
	private JComboBox<CekirdekHiz> fCekirdekHiz2= new JComboBox<CekirdekHiz>(cekirdekHiz2ComboBoxModel);	
	private CekirdekHiz selectedCekirdekHiz2= new CekirdekHiz();
	//Dahili Hafıza
	DahiliHafizaAta dahiliHafizaAta;
	private ComboBoxModel<DahiliHafiza> dahiliHafizaComboBoxModel = new DahiliHafizaCombBoxModel();
	private JComboBox<DahiliHafiza> fDahiliHafiza= new JComboBox<DahiliHafiza>(dahiliHafizaComboBoxModel);	
	private DahiliHafiza selectedDahiliHafiza= new DahiliHafiza();
	//Dahili Hafıza 2
	DahiliHafizaAta dahiliHafizaAta2;
	private ComboBoxModel<DahiliHafiza> dahiliHafiza2ComboBoxModel = new DahiliHafizaCombBoxModel();
	private JComboBox<DahiliHafiza> fDahiliHafiza2= new JComboBox<DahiliHafiza>(dahiliHafiza2ComboBoxModel);	
	private DahiliHafiza selectedDahiliHafiza2= new DahiliHafiza();
	//Dahili Hafıza 3
	DahiliHafizaAta dahiliHafizaAta3;
	private ComboBoxModel<DahiliHafiza> dahiliHafiza3ComboBoxModel = new DahiliHafizaCombBoxModel();
	private JComboBox<DahiliHafiza> fDahiliHafiza3= new JComboBox<DahiliHafiza>(dahiliHafiza3ComboBoxModel);	
	private DahiliHafiza selectedDahiliHafiza3= new DahiliHafiza();
	
	//RAM
	RamAta ramAta;
	private ComboBoxModel<Ram> ramComboBoxModel = new RamCombBoxModel();
	private JComboBox<Ram> fRam= new JComboBox<Ram>(ramComboBoxModel);	
	private Ram selectedRam= new Ram();
	
	//harici hafıza tipi
	private ComboBoxModel<HariciHafizaTipi> hariciHafizaTipiComboBoxModel = new HariciHafizaTipiCombBoxModel();
	private JComboBox<HariciHafizaTipi> fHariciHafizaTipi= new JComboBox<HariciHafizaTipi>(hariciHafizaTipiComboBoxModel);	
	private HariciHafizaTipi selectedHariciHafizaTipi= new HariciHafizaTipi();
	
	//harici hafıza tipi
	private ComboBoxModel<HariciHafizaBuyukluk> hariciHafizaBuyuklukComboBoxModel = new HariciHafizaBuyuklukCombBoxModel();
	private JComboBox<HariciHafizaBuyukluk> fHariciHafizaBuyukluk= new JComboBox<HariciHafizaBuyukluk>(hariciHafizaBuyuklukComboBoxModel);	
	private HariciHafizaBuyukluk selectedHariciHafizaBuyukluk= new HariciHafizaBuyukluk();

	//arka kamera çözünürlük
	ArkaKameraAtaDAO arkaKameraAtaDAO;
	ArkaKameraAta arkaKameraAta;
	private ComboBoxModel<KameraCozunurluk> arkaKameraCozunurlukComboBoxModel = new KameraCozunurlukCombBoxModel();
	private JComboBox<KameraCozunurluk> fArkaKameraCozunurluk= new JComboBox<KameraCozunurluk>(arkaKameraCozunurlukComboBoxModel);	
	private KameraCozunurluk selectedArkaKameraCozunurluk= new KameraCozunurluk();
	
	//arka kamera diyafram
	private ComboBoxModel<Diyafram> arkaKameraDiyaframComboBoxModel = new DiyaframCombBoxModel();
	private JComboBox<Diyafram> fArkaKameraDiyafram= new JComboBox<Diyafram>(arkaKameraDiyaframComboBoxModel);	
	private Diyafram selectedArkaKameraDiyafram= new Diyafram();
	//arka kamera piksel büyüklüğü
	private ComboBoxModel<PikselBuyuklugu> arkaKameraPikselBuyukluguComboBoxModel = new PikselBuyukluguCombBoxModel();
	private JComboBox<PikselBuyuklugu> fArkaKameraPikselBuyuklugu= new JComboBox<PikselBuyuklugu>(arkaKameraPikselBuyukluguComboBoxModel);	
	private PikselBuyuklugu selectedArkaKameraPikselBuyuklugu= new PikselBuyuklugu();

	//2.arka kamera çözünürlük
	ArkaKameraAtaDAO arkaKameraAta2DAO;
	ArkaKameraAta arkaKameraAta2;
	private ComboBoxModel<KameraCozunurluk> arkaKameraCozunurluk2ComboBoxModel = new KameraCozunurlukCombBoxModel();
	private JComboBox<KameraCozunurluk> fArkaKameraCozunurluk2= new JComboBox<KameraCozunurluk>(arkaKameraCozunurluk2ComboBoxModel);	
	private KameraCozunurluk selectedArkaKameraCozunurluk2= new KameraCozunurluk();	

	//2.arka kamera diyafram
	private ComboBoxModel<Diyafram> arkaKameraDiyafram2ComboBoxModel = new DiyaframCombBoxModel();
	private JComboBox<Diyafram> fArkaKameraDiyafram2= new JComboBox<Diyafram>(arkaKameraDiyafram2ComboBoxModel);	
	private Diyafram selectedArkaKameraDiyafram2= new Diyafram();
	//2.arka kamera piksel büyüklüğü
	private ComboBoxModel<PikselBuyuklugu> arkaKameraPikselBuyuklugu2ComboBoxModel = new PikselBuyukluguCombBoxModel();
	private JComboBox<PikselBuyuklugu> fArkaKameraPikselBuyuklugu2= new JComboBox<PikselBuyuklugu>(arkaKameraPikselBuyuklugu2ComboBoxModel);	
	private PikselBuyuklugu selectedArkaKameraPikselBuyuklugu2= new PikselBuyuklugu();

	private JTextArea fArkaKameraDiger = new JTextArea(2,30); 
	private JTextField fArkaKameraVideo = new JTextField();
	
	//Ön kamera çözünürlük
	OnKameraAtaDAO onKameraAtaDAO;
	OnKameraAta onKameraAta;
	private ComboBoxModel<KameraCozunurluk> onKameraCozunurlukComboBoxModel = new KameraCozunurlukCombBoxModel();
	private JComboBox<KameraCozunurluk> fOnKameraCozunurluk= new JComboBox<KameraCozunurluk>(onKameraCozunurlukComboBoxModel);	
	private KameraCozunurluk selectedOnKameraCozunurluk= new KameraCozunurluk();	
	//ön kamera diyafram
	private ComboBoxModel<Diyafram> onKameraDiyaframComboBoxModel = new DiyaframCombBoxModel();
	private JComboBox<Diyafram> fOnKameraDiyafram= new JComboBox<Diyafram>(onKameraDiyaframComboBoxModel);	
	private Diyafram selectedOnKameraDiyafram= new Diyafram();
	//arka kamera piksel büyüklüğü
	private ComboBoxModel<PikselBuyuklugu> onKameraPikselBuyukluguComboBoxModel = new PikselBuyukluguCombBoxModel();
	private JComboBox<PikselBuyuklugu> fOnKameraPikselBuyuklugu= new JComboBox<PikselBuyuklugu>(onKameraPikselBuyukluguComboBoxModel);	
	private PikselBuyuklugu selectedOnKameraPikselBuyuklugu= new PikselBuyuklugu();

	//Ön kamera2 çözünürlük
	OnKameraAtaDAO onKameraAta2DAO;
	OnKameraAta onKameraAta2;
	private ComboBoxModel<KameraCozunurluk> onKameraCozunurluk2ComboBoxModel = new KameraCozunurlukCombBoxModel();
	private JComboBox<KameraCozunurluk> fOnKameraCozunurluk2= new JComboBox<KameraCozunurluk>(onKameraCozunurluk2ComboBoxModel);	
	private KameraCozunurluk selectedOnKameraCozunurluk2= new KameraCozunurluk();	
	//ön kamera2 diyafram
	private ComboBoxModel<Diyafram> onKameraDiyafram2ComboBoxModel = new DiyaframCombBoxModel();
	private JComboBox<Diyafram> fOnKameraDiyafram2= new JComboBox<Diyafram>(onKameraDiyafram2ComboBoxModel);	
	private Diyafram selectedOnKameraDiyafram2= new Diyafram();
	//arka kamera2 piksel büyüklüğü
	private ComboBoxModel<PikselBuyuklugu> onKameraPikselBuyuklugu2ComboBoxModel = new PikselBuyukluguCombBoxModel();
	private JComboBox<PikselBuyuklugu> fOnKameraPikselBuyuklugu2= new JComboBox<PikselBuyuklugu>(onKameraPikselBuyuklugu2ComboBoxModel);	
	private PikselBuyuklugu selectedOnKameraPikselBuyuklugu2= new PikselBuyuklugu();
	
	private JTextArea fOnKameraDiger = new JTextArea(2,30); 
	
	private JTextField fUyariTur = new JTextField();
	private JTextField fHoparlor = new JTextField();
	private JCheckBox fKulaklikGir = new JCheckBox();
	private JTextArea fSesDiger = new JTextArea(2,30); 

	//batarya kapasite
	private ComboBoxModel<BataryaKapasite> bataryaKapasiteComboBoxModel = new BataryaKapasiteCombBoxModel();
	private JComboBox<BataryaKapasite> fBataryaKapasite= new JComboBox<BataryaKapasite>(bataryaKapasiteComboBoxModel);	
	private BataryaKapasite selectedBataryaKapasite= new BataryaKapasite();
	//batarya teknoloji
	private ComboBoxModel<BataryaTeknoloji> bataryaTeknolojiComboBoxModel = new BataryaTeknolojiCombBoxModel();
	private JComboBox<BataryaTeknoloji> fBataryaTeknoloji= new JComboBox<BataryaTeknoloji>(bataryaTeknolojiComboBoxModel);	
	private BataryaTeknoloji selectedBataryaTeknoloji= new BataryaTeknoloji();

	//batarya değişir
	private ComboBoxModel<BataryaDegisir> bataryaDegisirComboBoxModel = new BataryaDegisirCombBoxModel();
	private JComboBox<BataryaDegisir> fBataryaDegisir= new JComboBox<BataryaDegisir>(bataryaDegisirComboBoxModel);	
	private BataryaDegisir selectedBataryaDegisir= new BataryaDegisir();
	private JTextField fBeklemeSur = new JTextField();
	private JTextField fKonusmaSur = new JTextField();
	
	private JTextField fWifi = new JTextField();
	private JTextField fBluetooth = new JTextField();
	private JTextField fNfc = new JTextField();
	private JTextField fGps = new JTextField();
	private JTextField fKizilOtesi = new JTextField();
	private JTextField fRadyo = new JTextField();
	private JTextField fUsb = new JTextField();
	
	// ağ
	private JTextField fIkiG = new JTextField();
	private JTextField fUcG = new JTextField();
	private JTextField fDortG = new JTextField();
	private JTextField fHiz = new JTextField();
	private JTextField fGprs = new JTextField();
	private JTextField fEdge = new JTextField();
	//sensör
	List<SensorJCheckBox> fSensorler = new ArrayList<SensorJCheckBox>();
	//diğer özellikler
	
	CihazView(JFrame aParent) {				    
		fEdit = Edit.ADD;		
		buildGui(aParent, "Cihaz Ekle");
		fStandardDialog.display();
	}
	
	CihazView(JFrame aParent, Cihaz selectedCihaz) {				    
		fEdit = Edit.CHANGE;		
		fId = selectedCihaz.getId();
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
			//hat sayısı
			ozellikAtama = ozellikAtamaDao.find(fId,9);
			if(ozellikAtama!=null)
				selectedSimSayisi = SimSayisiDAO.findBy(Integer.parseInt(ozellikAtama.getDeger()));
			//sim türü
			ozellikAtama = ozellikAtamaDao.find(fId,50);
			if(ozellikAtama!=null)
				selectedSim = SimDAO.findBy(Integer.parseInt(ozellikAtama.getDeger()));
			//seçilen os
			ozellikAtama = ozellikAtamaDao.find(fId,15);
			if(ozellikAtama!=null)
				selectedOS = OSDAO.findBy(Integer.parseInt(ozellikAtama.getDeger()));
			//seçilen os sürüm
			ozellikAtama = ozellikAtamaDao.find(fId,43);
			if(ozellikAtama!=null)
				selectedOSSurum = OSSurumDAO.findBy(Integer.parseInt(ozellikAtama.getDeger()));
			//seçilen yonga seti
			ozellikAtama = ozellikAtamaDao.find(fId,16);
			if(ozellikAtama!=null)
				selectedYongaSeti = YongaSetiDAO.findBy(Integer.parseInt(ozellikAtama.getDeger()));
			//gpu
			ozellikAtama = ozellikAtamaDao.find(fId,18);
			if(ozellikAtama!=null)
				selectedGpu = GpuDAO.findBy(Integer.parseInt(ozellikAtama.getDeger()));
			//cekirdek sayısı ve hızı
			cpuSayiHizAta = CpuSayiHizAtaDAO.findBy(selectedCihaz,0);
			if(cpuSayiHizAta!=null){
				selectedCekirdekSayi = CekirdekSayiDAO.findBy(cpuSayiHizAta.getSayiId());
				selectedCekirdekHiz = CekirdekHizDAO.findBy(cpuSayiHizAta.getHizId());
			}
			//cekirdek sayısı ve hızı 2
			cpuSayiHizAta2 = CpuSayiHizAtaDAO.findBy(selectedCihaz,1);
			if(cpuSayiHizAta2!=null){
				selectedCekirdekSayi2 = CekirdekSayiDAO.findBy(cpuSayiHizAta2.getSayiId());
				selectedCekirdekHiz2 = CekirdekHizDAO.findBy(cpuSayiHizAta2.getHizId());
			}
			//dahili hafıza
			dahiliHafizaAta = DahiliHafizaAtaDAO.findBy(selectedCihaz,0);
			if(dahiliHafizaAta!=null){
				DahiliHafiza dahiliHafiza = new DahiliHafiza();
				dahiliHafiza.setId(dahiliHafizaAta.getDahiliHafizaId());
				selectedDahiliHafiza = DahiliHafizaDAO.findById(dahiliHafiza);
			}
			//dahili hafıza 2
			dahiliHafizaAta2 = DahiliHafizaAtaDAO.findBy(selectedCihaz,1);
			if(dahiliHafizaAta2!=null){
				DahiliHafiza dahiliHafiza2 = new DahiliHafiza();
				dahiliHafiza2.setId(dahiliHafizaAta2.getDahiliHafizaId());
				selectedDahiliHafiza2 = DahiliHafizaDAO.findById(dahiliHafiza2);
			}
			//dahili hafıza 3
			dahiliHafizaAta3 = DahiliHafizaAtaDAO.findBy(selectedCihaz,2);
			if(dahiliHafizaAta3!=null){
				DahiliHafiza dahiliHafiza3 = new DahiliHafiza();
				dahiliHafiza3.setId(dahiliHafizaAta3.getDahiliHafizaId());
				selectedDahiliHafiza3 = DahiliHafizaDAO.findById(dahiliHafiza3);
			}
			//ram
			ramAta = RamAtaDAO.findBy(selectedCihaz);
			if(ramAta!=null){
				Ram ram = new Ram();
				ram.setId(ramAta.getRamId());
				selectedRam = RamDAO.findById(ram);
			}
			//seçilen harici hafıza tipi
			ozellikAtama = ozellikAtamaDao.find(fId,19);
			if(ozellikAtama!=null)
				selectedHariciHafizaTipi = HariciHafizaTipiDAO.findBy(Integer.parseInt(ozellikAtama.getDeger()));
			//seçilen harici hafıza büyüklüğü
			ozellikAtama = ozellikAtamaDao.find(fId,51);
			if(ozellikAtama!=null)
				selectedHariciHafizaBuyukluk = HariciHafizaBuyuklukDAO.findBy(Integer.parseInt(ozellikAtama.getDeger()));
			
			//birinci arka kamera çözünürlük 
			arkaKameraAta = ArkaKameraAtaDAO.findBy(selectedCihaz,0);
			if(arkaKameraAta!=null){
				KameraCozunurluk arkaKameraCozunurluk = new KameraCozunurluk();
				arkaKameraCozunurluk.setId(arkaKameraAta.getKameraCozunurlukId());
				selectedArkaKameraCozunurluk  = KameraCozunurlukDAO.findById(arkaKameraCozunurluk);
				
				if(arkaKameraAta.getDiyaframAcikligiIdId() != null){
					Diyafram arkaKameraDiyafram = new Diyafram();
					arkaKameraDiyafram.setId(arkaKameraAta.getDiyaframAcikligiIdId());
					selectedArkaKameraDiyafram = DiyaframDAO.findById(arkaKameraDiyafram);
				}
				if(arkaKameraAta.getPikselBuyukluguId()!= null){
					PikselBuyuklugu arkaKameraPikselBuyuklugu = new PikselBuyuklugu();
					arkaKameraPikselBuyuklugu.setId(arkaKameraAta.getPikselBuyukluguId());
					selectedArkaKameraPikselBuyuklugu = PikselBuyukluguDAO.findById(arkaKameraPikselBuyuklugu);
				}
			}
			
			//2.arka kamera çözünürlük 
			arkaKameraAta2 = ArkaKameraAtaDAO.findBy(selectedCihaz,1);
			if(arkaKameraAta2!=null){
				KameraCozunurluk arkaKameraCozunurluk2 = new KameraCozunurluk();
				arkaKameraCozunurluk2.setId(arkaKameraAta2.getKameraCozunurlukId());
				selectedArkaKameraCozunurluk2  = KameraCozunurlukDAO.findById(arkaKameraCozunurluk2);
				if(arkaKameraAta2.getDiyaframAcikligiIdId() != null){
					Diyafram arkaKameraDiyafram2 = new Diyafram();
					arkaKameraDiyafram2.setId(arkaKameraAta2.getDiyaframAcikligiIdId());
					selectedArkaKameraDiyafram2 = DiyaframDAO.findById(arkaKameraDiyafram2);
				}
				if(arkaKameraAta2.getPikselBuyukluguId()!= null){
					PikselBuyuklugu arkaKameraPikselBuyuklugu2 = new PikselBuyuklugu();
					arkaKameraPikselBuyuklugu2.setId(arkaKameraAta2.getPikselBuyukluguId());
					selectedArkaKameraPikselBuyuklugu2 = PikselBuyukluguDAO.findById(arkaKameraPikselBuyuklugu2);
				}
			}
			
			//birinci ön kamera çözünürlük 
			onKameraAta = OnKameraAtaDAO.findBy(selectedCihaz,0);
			if(onKameraAta!=null){
				KameraCozunurluk onKameraCozunurluk = new KameraCozunurluk();
				onKameraCozunurluk.setId(onKameraAta.getKameraCozunurlukId());
				selectedOnKameraCozunurluk  = KameraCozunurlukDAO.findById(onKameraCozunurluk);
				
				if(onKameraAta.getDiyaframAcikligiIdId() != null){
					Diyafram onKameraDiyafram = new Diyafram();
					onKameraDiyafram.setId(onKameraAta.getDiyaframAcikligiIdId());
					selectedOnKameraDiyafram = DiyaframDAO.findById(onKameraDiyafram);
				}
				if(onKameraAta.getPikselBuyukluguId()!= null){
					PikselBuyuklugu onKameraPikselBuyuklugu = new PikselBuyuklugu();
					onKameraPikselBuyuklugu.setId(onKameraAta.getPikselBuyukluguId());
					selectedOnKameraPikselBuyuklugu = PikselBuyukluguDAO.findById(onKameraPikselBuyuklugu);
				}
			}
			//2. ön kamera  
			onKameraAta2 = OnKameraAtaDAO.findBy(selectedCihaz,1);
			if(onKameraAta2!=null){
				KameraCozunurluk onKameraCozunurluk2 = new KameraCozunurluk();
				onKameraCozunurluk2.setId(onKameraAta2.getKameraCozunurlukId());
				selectedOnKameraCozunurluk2  = KameraCozunurlukDAO.findById(onKameraCozunurluk2);
				
				if(onKameraAta2.getDiyaframAcikligiIdId() != null){
					Diyafram onKameraDiyafram2 = new Diyafram();
					onKameraDiyafram2.setId(onKameraAta2.getDiyaframAcikligiIdId());
					selectedOnKameraDiyafram2 = DiyaframDAO.findById(onKameraDiyafram2);
				}
				if(onKameraAta2.getPikselBuyukluguId()!= null){
					PikselBuyuklugu onKameraPikselBuyuklugu2 = new PikselBuyuklugu();
					onKameraPikselBuyuklugu2.setId(onKameraAta2.getPikselBuyukluguId());
					selectedOnKameraPikselBuyuklugu2 = PikselBuyukluguDAO.findById(onKameraPikselBuyuklugu2);
				}
			}
			
			//uyarı tipleri
			ozellikAtama = ozellikAtamaDao.find(fId,25);
			if(ozellikAtama!=null)
				fUyariTur.setText(ozellikAtama.getDeger());		

			//hoparlör
			ozellikAtama = ozellikAtamaDao.find(fId,26);
			if(ozellikAtama!=null)
				fHoparlor.setText(ozellikAtama.getDeger());		

			ozellikAtama = ozellikAtamaDao.find(fId,27);
			if(ozellikAtama != null){
				if(ozellikAtama.getDeger().equals("Var"))
					fKulaklikGir.setSelected(true);
				else
					fKulaklikGir.setSelected(false);
			}
			
			//hoparlör
			ozellikAtama = ozellikAtamaDao.find(fId,28);
			if(ozellikAtama!=null)
				fSesDiger.setText(ozellikAtama.getDeger());	
			
			//batarya kapasite
			ozellikAtama = ozellikAtamaDao.find(fId,53);
			if(ozellikAtama!=null){
				BataryaKapasite bataryaKapasite = new BataryaKapasite();
				bataryaKapasite.setId(Integer.parseInt(ozellikAtama.getDeger()));
				selectedBataryaKapasite = BataryaKapasiteDAO.findById(bataryaKapasite);			
			}

			//batarya teknoloji
			ozellikAtama = ozellikAtamaDao.find(fId,54);
			if(ozellikAtama!=null){
				BataryaTeknoloji bataryaTeknoloji = new BataryaTeknoloji();
				bataryaTeknoloji.setId(Integer.parseInt(ozellikAtama.getDeger()));
				selectedBataryaTeknoloji = BataryaTeknolojiDAO.findById(bataryaTeknoloji);			
			}
			//batarya değişir
			ozellikAtama = ozellikAtamaDao.find(fId,55);
			if(ozellikAtama!=null){
				BataryaDegisir bataryaDegisir = new BataryaDegisir();
				bataryaDegisir.setId(Integer.parseInt(ozellikAtama.getDeger()));
				selectedBataryaDegisir = BataryaDegisirDAO.findById(bataryaDegisir);			
			}
			
			//bekleme süresi
			ozellikAtama = ozellikAtamaDao.find(fId,37);
			if(ozellikAtama!=null)
				fBeklemeSur.setText(ozellikAtama.getDeger());	
			//konuşma süresi
			ozellikAtama = ozellikAtamaDao.find(fId,38);
			if(ozellikAtama!=null)
				fKonusmaSur.setText(ozellikAtama.getDeger());	
			//wifi
			ozellikAtama = ozellikAtamaDao.find(fId,29);
			if(ozellikAtama!=null)
				fWifi.setText(ozellikAtama.getDeger());	
			//bluetooth
			ozellikAtama = ozellikAtamaDao.find(fId,30);
			if(ozellikAtama!=null)
				fBluetooth.setText(ozellikAtama.getDeger());	
			//nfc
			ozellikAtama = ozellikAtamaDao.find(fId,32);
			if(ozellikAtama!=null)
				fNfc.setText(ozellikAtama.getDeger());	
			//gps
			ozellikAtama = ozellikAtamaDao.find(fId,31);
			if(ozellikAtama!=null)
				fGps.setText(ozellikAtama.getDeger());	
			//kızıl ötesi
			ozellikAtama = ozellikAtamaDao.find(fId,33);
			if(ozellikAtama!=null)
				fKizilOtesi.setText(ozellikAtama.getDeger());	
			//radyo
			ozellikAtama = ozellikAtamaDao.find(fId,34);
			if(ozellikAtama!=null)
				fRadyo.setText(ozellikAtama.getDeger());	
			//usb
			ozellikAtama = ozellikAtamaDao.find(fId,35);
			if(ozellikAtama!=null)
				fUsb.setText(ozellikAtama.getDeger());	
			//2g
			ozellikAtama = ozellikAtamaDao.find(fId,1);
			if(ozellikAtama!=null)
				fIkiG.setText(ozellikAtama.getDeger());	
			//3g
			ozellikAtama = ozellikAtamaDao.find(fId,2);
			if(ozellikAtama!=null)
				fUcG.setText(ozellikAtama.getDeger());	
			//4g
			ozellikAtama = ozellikAtamaDao.find(fId,3);
			if(ozellikAtama!=null)
				fDortG.setText(ozellikAtama.getDeger());	
			//hız
			ozellikAtama = ozellikAtamaDao.find(fId,4);
			if(ozellikAtama!=null)
				fHiz.setText(ozellikAtama.getDeger());	
			//gprs
			ozellikAtama = ozellikAtamaDao.find(fId,5);
			if(ozellikAtama!=null)
				fGprs.setText(ozellikAtama.getDeger());	
			//edge
			ozellikAtama = ozellikAtamaDao.find(fId,6);
			if(ozellikAtama!=null)
				fEdge.setText(ozellikAtama.getDeger());	

		} catch (SQLException e) {
			e.printStackTrace();
		}
		buildGui(aParent, "Cihaz Güncelle");

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
	String getEkranDiger() {
	    return fEkranDiger.getText();
	}	
	//gövde
	String getBoyut() {
	    return fBoyut.getText();
	}
	String getAgirlik() {
	    return fAgirlik.getText();
	}	
	SimSayisi getSimSayisi() {
		return (SimSayisi) fSimSayisi.getModel().getSelectedItem();
	}
	Sim getSim() {
		return (Sim) fSim.getModel().getSelectedItem();
	}
	String getGovdeDiger() {
	    return fGovdeDiger.getText();
	}
	OS getOS() {
		return (OS) fOS.getModel().getSelectedItem();
	}	
	OSSurum getOSSurum() {
		return (OSSurum) fOSSurum.getModel().getSelectedItem();
	}	
	YongaSeti getYongaSeti() {
		return (YongaSeti) fYongaSeti.getModel().getSelectedItem();
	}	
	Gpu getGpu() {
		return (Gpu) fGpu.getModel().getSelectedItem();
	}
	CpuSayiHizAta getCpuSayiHizAta() {
		return cpuSayiHizAta;
	}
	CpuSayiHizAta getCpuSayiHizAta2() {
		return cpuSayiHizAta2;
	}
	CekirdekSayi getCekirdekSayi() {
		return (CekirdekSayi) fCekirdekSayi.getModel().getSelectedItem();
	}	
	CekirdekHiz getCekirdekHiz() {
		return (CekirdekHiz) fCekirdekHiz.getModel().getSelectedItem();
	}	
	CekirdekSayi getCekirdekSayi2() {
		return (CekirdekSayi) fCekirdekSayi2.getModel().getSelectedItem();
	}	
	CekirdekHiz getCekirdekHiz2() {
		return (CekirdekHiz) fCekirdekHiz2.getModel().getSelectedItem();
	}		
	DahiliHafiza getDahiliHafiza() {
		return (DahiliHafiza) fDahiliHafiza.getModel().getSelectedItem();
	}
	DahiliHafizaAta getDahiliHafizaAta() {
		return dahiliHafizaAta;
	}	
	DahiliHafiza getDahiliHafiza2() {
		return (DahiliHafiza) fDahiliHafiza2.getModel().getSelectedItem();
	}
	DahiliHafizaAta getDahiliHafizaAta2() {
		return dahiliHafizaAta2;
	}	
	DahiliHafiza getDahiliHafiza3() {
		return (DahiliHafiza) fDahiliHafiza3.getModel().getSelectedItem();
	}
	DahiliHafizaAta getDahiliHafizaAta3() {
		return dahiliHafizaAta3;
	}	
	Ram getRam() {
		return (Ram) fRam.getModel().getSelectedItem();
	}
	RamAta getRamAta() {
		return ramAta;
	}	
	HariciHafizaTipi getHariciHafizaTipi() {
		return (HariciHafizaTipi) fHariciHafizaTipi.getModel().getSelectedItem();
	}	
	
	HariciHafizaBuyukluk getHariciHafizaBuyukluk() {
		return (HariciHafizaBuyukluk) fHariciHafizaBuyukluk.getModel().getSelectedItem();
	}
	KameraCozunurluk getArkaKameraCozunurluk() {
		return (KameraCozunurluk) fArkaKameraCozunurluk.getModel().getSelectedItem();
	}
	ArkaKameraAta getArkaKameraAta() {
		return arkaKameraAta;
	}
	
	Diyafram getArkaKameraDiyafram() {
		return (Diyafram) fArkaKameraDiyafram.getModel().getSelectedItem();
	}
	PikselBuyuklugu getArkaKameraPikselBuyuklugu() {
		return (PikselBuyuklugu) fArkaKameraPikselBuyuklugu.getModel().getSelectedItem();
	}
	
	KameraCozunurluk getArkaKameraCozunurluk2() {
		return (KameraCozunurluk) fArkaKameraCozunurluk2.getModel().getSelectedItem();
	}
	ArkaKameraAta getArkaKameraAta2() {
		return arkaKameraAta2;
	}
	
	Diyafram getArkaKameraDiyafram2() {
		return (Diyafram) fArkaKameraDiyafram2.getModel().getSelectedItem();
	}
	PikselBuyuklugu getArkaKameraPikselBuyuklugu2() {
		return (PikselBuyuklugu) fArkaKameraPikselBuyuklugu2.getModel().getSelectedItem();
	}
	
	String getArkaKameraDiger() {
	    return fArkaKameraDiger.getText();
	}
	
	String getArkaKameraVideo() {
	    return fArkaKameraVideo.getText();
	}
	
	OnKameraAta getOnKameraAta() {
		return onKameraAta;
	}
	
	KameraCozunurluk getOnKameraCozunurluk() {
		return (KameraCozunurluk) fOnKameraCozunurluk.getModel().getSelectedItem();
	}
	
	Diyafram getOnKameraDiyafram() {
		return (Diyafram) fOnKameraDiyafram.getModel().getSelectedItem();
	}
	PikselBuyuklugu getOnKameraPikselBuyuklugu() {
		return (PikselBuyuklugu) fOnKameraPikselBuyuklugu.getModel().getSelectedItem();
	}
	OnKameraAta getOnKameraAta2() {
		return onKameraAta2;
	}
	
	KameraCozunurluk getOnKameraCozunurluk2() {
		return (KameraCozunurluk) fOnKameraCozunurluk2.getModel().getSelectedItem();
	}
	
	Diyafram getOnKameraDiyafram2() {
		return (Diyafram) fOnKameraDiyafram2.getModel().getSelectedItem();
	}
	PikselBuyuklugu getOnKameraPikselBuyuklugu2() {
		return (PikselBuyuklugu) fOnKameraPikselBuyuklugu2.getModel().getSelectedItem();
	}
	String getOnKameraDiger() {
	    return fOnKameraDiger.getText();
	}
	
	String getUyariTur() {
	    return fUyariTur.getText();
	}	
	String getHoparlor() {
	    return fHoparlor.getText();
	}
	
	public Boolean getKulaklikGir(){
		return this.fKulaklikGir.isSelected();
	}
	
	String getSesDiger() {
	    return fSesDiger.getText();
	}
	BataryaKapasite getBataryaKapasite() {
		return (BataryaKapasite) fBataryaKapasite.getModel().getSelectedItem();
	}	
	BataryaTeknoloji getBataryaTeknoloji() {
		return (BataryaTeknoloji) fBataryaTeknoloji.getModel().getSelectedItem();
	}	
	BataryaDegisir getBataryaDegisir() {
		return (BataryaDegisir) fBataryaDegisir.getModel().getSelectedItem();
	}	
	
	String getBeklemeSur() {
	    return fBeklemeSur.getText();
	}
	String getKonusmaSur() {
	    return fKonusmaSur.getText();
	}
	
	String getWifi() {
	    return fWifi.getText();
	}
	
	String getBluetooth() {
	    return fBluetooth.getText();
	}
	
	String getNfc() {
	    return fNfc.getText();
	}
	String getGps() {
	    return fGps.getText();
	}
	String getKizilOtesi() {
	    return fKizilOtesi.getText();
	}
	String getRadyo() {
	    return fRadyo.getText();
	}
	String getUsb() {
	    return fUsb.getText();
	}
	String getIkiG() {
	    return fIkiG.getText();
	}
	String getUcG() {
	    return fUcG.getText();
	}	
	String getDortG() {
	    return fDortG.getText();
	}
	String getHiz() {
	    return fHiz.getText();
	}
	String getGprs() {
	    return fGprs.getText();
	}
	String getEdge() {
	    return fEdge.getText();
	}	
	List<SensorJCheckBox> getSensorler() {
	    return fSensorler;
	}	
	private void buildGui(JFrame aParent, String aDialogTitle) {
		fStandardDialog = new StandardDialog(
		      aParent, aDialogTitle, true, OnClose.DISPOSE, getUserInputArea(), getButtons()
		);
		fStandardDialog.setDefaultButton(fEditButton);
	}	
	
	private JPanel getUserInputArea() {
	    mainPanel = new JPanel();
	    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
	    JPanel genelPanel = getGenelInputArea();	    
	    mainPanel.add(genelPanel); 
	    
	    JPanel ekranPanel = getEkranInputArea();
	    mainPanel.add(ekranPanel);
	    
	    JPanel govdePanel = getGovdeInputArea();
	    mainPanel.add(govdePanel);
	    
	    JPanel platformPanel = getPlatformInputArea();
	    mainPanel.add(platformPanel);
	    
	    JPanel hafizaPanel = getHafizaInputArea();
	    mainPanel.add(hafizaPanel);
	    
	    JPanel arkaKameraPanel = getArkaKameraInputArea();
	    mainPanel.add(arkaKameraPanel);

	    JPanel onKameraPanel = getOnKameraInputArea();
	    mainPanel.add(onKameraPanel);

	    JPanel sesPanel = getSesInputArea();
	    mainPanel.add(sesPanel);

	    JPanel bataryaPanel = getBataryaInputArea();
	    mainPanel.add(bataryaPanel);

	    JPanel baglantiPanel = getBaglantiInputArea();
	    mainPanel.add(baglantiPanel);
	    
	    JPanel agPanel = getAgInputArea();
	    mainPanel.add(agPanel);
	    
	    JPanel sensorPanel = getSensorInputArea();
	    mainPanel.add(sensorPanel);

	    UiUtil.alignAllX(mainPanel, UiUtil.AlignX.LEFT);
	    return mainPanel;	    
	}
	
	private JPanel getGenelInputArea(){
		JPanel result = new JPanel(new FlowLayout(FlowLayout.LEFT));	    
		result.setBorder(BorderFactory.createTitledBorder("GENEL"));
		addTextField(fAd, "Ad", result,20);
	    addTextField(fDigerAd, "Diğer Ad", result,20);
	    addUreticiComboField(fUretici, "Üretici", result);
	    addCihazTurComboField(fCihazTur, "Türü", result);
	    addDuyurulmaYilComboField(fDuyurulmaYil, "Duyurulma Yıl", result);
	    addDuyurulmaAyComboField(fDuyurulmaAy,"Ay",result);
	    addCheckField(fAnasayfa,"Anasayfa",result);
	    addCheckField(fAktif,"Aktif",result);
	    return result;
	}
	
	private JPanel getEkranInputArea(){
		JPanel ekranMainPanel = new JPanel();
		ekranMainPanel.setLayout(new BoxLayout(ekranMainPanel, BoxLayout.Y_AXIS));
		ekranMainPanel.setBorder(BorderFactory.createTitledBorder("EKRAN"));
		
		JPanel result = new JPanel(new FlowLayout(FlowLayout.LEFT));	    
	    addEkranTipComboField(fEkranTip, "Tipi", result);	    
	    addEkranRenkComboField(fEkranRenk, "Renk", result);	    
	    addEkranGenisligiComboField(fEkranGenisligi, "Genişlik", result);	    
	    addEkranCozunurlukComboField(fEkranCozunurluk, "Çözünürlük", result);	    
	    addEkranPPIComboField(fEkranPPI, "PPI", result);	    
	    addCokluDokunmatikComboField(fCokluDokunmatik, "ÇD", result);	    
		addTextField(fEkranKor, "EK", result,20);
		ekranMainPanel.add(result);
		
		JPanel ekranDigerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		addTextAreaField(fEkranDiger,"Diğer",ekranDigerPanel);
		ekranMainPanel.add(ekranDigerPanel);

		return ekranMainPanel;
	}	
	private JPanel getGovdeInputArea(){
		JPanel result = new JPanel(new FlowLayout(FlowLayout.LEFT));	    
		result.setBorder(BorderFactory.createTitledBorder("GÖVDE"));
		addTextField(fBoyut, "Boyutlar", result,20);
		addTextField(fAgirlik, "Ağırlık", result,20);
	    addSimSayisiComboField(fSimSayisi, "Hat Sayısı", result);	    
	    addSimComboField(fSim, "SIM türü", result);	    
		addTextAreaField(fGovdeDiger,"Diğer",result);
		return result;
	}	
	
	private JPanel getPlatformInputArea(){
		JPanel platformMainPanel = new JPanel();
		platformMainPanel.setLayout(new BoxLayout(platformMainPanel, BoxLayout.Y_AXIS));
		platformMainPanel.setBorder(BorderFactory.createTitledBorder("PLATFORM"));
		
		JPanel result = new JPanel(new FlowLayout(FlowLayout.LEFT));	    
	    addOSComboField(fOS, "OS", result);	    
	    addOSSurumComboField(fOSSurum, "Sürüm", result);	    
	    addYongaSetiComboField(fYongaSeti, "Yongaseti", result);	    
	    addGpuComboField(fGpu, "GPU", result);	    
	    platformMainPanel.add(result);
	    
		JPanel cpuPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		addCekirdekSayiComboField(fCekirdekSayi, cpuPanel);
		addCekirdekHizComboField(fCekirdekHiz, cpuPanel);
		addCekirdekSayi2ComboField(fCekirdekSayi2, cpuPanel);
		addCekirdekHiz2ComboField(fCekirdekHiz2, cpuPanel);
		platformMainPanel.add(cpuPanel);
	    
	    return platformMainPanel;
	}	
	
	private JPanel getHafizaInputArea(){
		JPanel result = new JPanel(new FlowLayout(FlowLayout.LEFT));	    
		result.setBorder(BorderFactory.createTitledBorder("HAFIZA"));
	    addDahiliHafizaComboField(fDahiliHafiza, result);	    
	    addDahiliHafiza2ComboField(fDahiliHafiza2, result);	    
	    addDahiliHafiza3ComboField(fDahiliHafiza3, result);	    
	    addRamComboField(fRam, result);	    
	    addHariciHafizaTipiComboField(fHariciHafizaTipi, result);	    
	    addHariciHafizaBuyuklukComboField(fHariciHafizaBuyukluk, result);	    
		return result;
	}	
	
	private JPanel getArkaKameraInputArea(){
		JPanel result = new JPanel(new FlowLayout(FlowLayout.LEFT));	    
		result.setBorder(BorderFactory.createTitledBorder("ARKA KAMERA"));
	    addArkaKameraCozunurlukComboField(fArkaKameraCozunurluk, result);	    
	    addArkaKameraDiyaframComboField(fArkaKameraDiyafram, result);	    
	    addArkaKameraPikselBuyukluguComboField(fArkaKameraPikselBuyuklugu, result);	    
	    addArkaKameraCozunurluk2ComboField(fArkaKameraCozunurluk2, result);	    
	    addArkaKameraDiyafram2ComboField(fArkaKameraDiyafram2, result);	    
	    addArkaKameraPikselBuyuklugu2ComboField(fArkaKameraPikselBuyuklugu2, result);	
		addTextAreaField(fArkaKameraDiger,"Diğer",result);
		addTextField(fArkaKameraVideo, "Video", result,20);
		return result;
	}
	
	private JPanel getOnKameraInputArea(){
		JPanel result = new JPanel(new FlowLayout(FlowLayout.LEFT));	    
		result.setBorder(BorderFactory.createTitledBorder("ÖN KAMERA"));
	    addOnKameraCozunurlukComboField(fOnKameraCozunurluk, result);	    
	    addOnKameraDiyaframComboField(fOnKameraDiyafram, result);	    
	    addOnKameraPikselBuyukluguComboField(fOnKameraPikselBuyuklugu, result);	    
	    addOnKameraCozunurluk2ComboField(fOnKameraCozunurluk2, result);	    
	    addOnKameraDiyafram2ComboField(fOnKameraDiyafram2, result);	    
	    addOnKameraPikselBuyuklugu2ComboField(fOnKameraPikselBuyuklugu2, result);	    
		addTextAreaField(fOnKameraDiger,"Diğer",result);
		return result;
	}
	
	private JPanel getSesInputArea(){
		JPanel result = new JPanel(new FlowLayout(FlowLayout.LEFT));	    
		result.setBorder(BorderFactory.createTitledBorder("SES"));
		addTextField(fUyariTur, "Uyarı türleri", result,20);
		addTextField(fHoparlor, "Hoparlör", result,20);
	    addCheckField(fKulaklikGir,"Kulaklık",result);
		addTextAreaField(fSesDiger,"Diğer",result);
	    return result;
	}	
	
	private JPanel getBataryaInputArea(){
		JPanel result = new JPanel(new FlowLayout(FlowLayout.LEFT));	    
		result.setBorder(BorderFactory.createTitledBorder("BATARYA"));
	    addBataryaKapasiteComboField(fBataryaKapasite, result);	    
	    addBataryaTeknolojiComboField(fBataryaTeknoloji, result);	    
	    addBataryaDegisirComboField(fBataryaDegisir, result);	    
		addTextField(fBeklemeSur, "Bekleme Süresi", result,20);
		addTextField(fKonusmaSur, "Konuşma Süresi", result,20);
	    return result;
	}	
	
	private JPanel getBaglantiInputArea(){
		
		JPanel baglantiMainPanel = new JPanel();
		baglantiMainPanel.setLayout(new BoxLayout(baglantiMainPanel, BoxLayout.Y_AXIS));
		baglantiMainPanel.setBorder(BorderFactory.createTitledBorder("BAĞLANTI"));
		
		JPanel baglanti1Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));	    
		addTextField(fWifi, "Wi-fi", baglanti1Panel,20);
		addTextField(fBluetooth, "Bluetooth", baglanti1Panel,20);
		addTextField(fNfc, "NFC", baglanti1Panel,20);
		addTextField(fGps, "GPS", baglanti1Panel,20);
		baglantiMainPanel.add(baglanti1Panel);
		
		JPanel baglanti2Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		addTextField(fKizilOtesi, "Kızıl Ötesi", baglanti2Panel,20);		
		addTextField(fRadyo, "Radyo", baglanti2Panel,20);		
		addTextField(fUsb, "USB", baglanti2Panel,20);		
		baglantiMainPanel.add(baglanti2Panel);
		
	    return baglantiMainPanel;
	}		
	
	private JPanel getAgInputArea(){
		JPanel result = new JPanel(new FlowLayout(FlowLayout.LEFT));	    
		result.setBorder(BorderFactory.createTitledBorder("AĞ"));
		addTextField(fIkiG, "2G", result,20);
		addTextField(fUcG, "3G", result,20);
		addTextField(fDortG, "4G", result,20);
		addTextField(fHiz, "Hız", result,20);
		addTextField(fGprs, "GPRS", result,10);
		addTextField(fEdge, "Edge", result,10);
	    return result;
	}		
	
	private JPanel getSensorInputArea(){
		JPanel result = new JPanel(new FlowLayout(FlowLayout.LEFT));	    
		result.setBorder(BorderFactory.createTitledBorder("SENSÖRLER"));
		try {
			for(Sensor sensor : SensorDAO.all()){
				SensorJCheckBox cb = new SensorJCheckBox(sensor.getAd(),sensor.getId());
				fSensorler.add(cb);
				if(fId != null) {
					for (SensorAta sensorAta : SensorAtaDAO.findByCihazId(fId)){
						if(sensor.getId() == sensorAta.getSensorId()){
							cb.setSelected(true);
						}
					}
				}
				addSensorCheckField(cb, result);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    return result;
	}		
	
	private void addTextAreaField(JTextArea aTextField, String aLabel, JPanel aPanel) {
		  JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		  JLabel label = new JLabel(aLabel);
		  panel.add(label);
		  panel.add(aTextField);
		  aPanel.add(panel);		  
	}
	
	private void addTextField(JTextField aTextField, String aLabel, JPanel aPanel, int width) {
		  JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		  JLabel label = new JLabel(aLabel);
		  panel.add(label);
		  panel.add(aTextField);
		  aTextField.setColumns(width);		  
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

	    aComboField.setSelectedItem("2017");
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
	
	private void addSensorCheckField(JCheckBox aCheckField, JPanel aPanel) {
  	    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
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
	
	private void addSimSayisiComboField(JComboBox<SimSayisi> aComboField, String aLabel, JPanel aPanel) {
  	    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		JLabel label = new JLabel(aLabel);
		panel.add(label);
		fSimSayisi.addItem(new SimSayisi(null, "Seçiniz"));  	  		
		try {
			for(SimSayisi simSayisi : SimSayisiDAO.all()){
				fSimSayisi.addItem(simSayisi);
				fSimSayisi.setRenderer(new SimSayisiComboBoxRenderer());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		

	    panel.add(aComboField);		 
		aPanel.add(panel);		  
	}	
	
	private void addSimComboField(JComboBox<Sim> aComboField, String aLabel, JPanel aPanel) {
  	    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		JLabel label = new JLabel(aLabel);
		panel.add(label);
		fSim.addItem(new Sim(null, "Seçiniz"));  	  		
		try {
			for(Sim sim : SimDAO.all()){
				fSim.addItem(sim);
				fSim.setRenderer(new SimComboBoxRenderer());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		

	    panel.add(aComboField);		 
		aPanel.add(panel);		  
	}
	
	private void addOSComboField(JComboBox<OS> aComboField, String aLabel, JPanel aPanel) {
  	    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		JLabel label = new JLabel(aLabel);
		panel.add(label);
		fOS.addItem(new OS(null, "Seçiniz"));  	  		
		try {
			for(OS os : OSDAO.all()){
				fOS.addItem(os);
				fOS.setRenderer(new OSComboBoxRenderer());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		fOS.addItemListener(new OSChangeListener(this));

	    panel.add(aComboField);		 
		aPanel.add(panel);		  
	}	
	
	private void addOSSurumComboField(JComboBox<OSSurum> aComboField, String aLabel, JPanel aPanel) {
  	    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		JLabel label = new JLabel(aLabel);
		panel.add(label);
		fOSSurum.addItem(new OSSurum(null,null, "Seçiniz"));  	  		
		if(selectedOS.getId() != null){
			try {
				for(OSSurum os : OSSurumDAO.all(selectedOS)){
					fOSSurum.addItem(os);
					fOSSurum.setRenderer(new OSSurumComboBoxRenderer());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
	    panel.add(aComboField);		 
		aPanel.add(panel);		  
	}
	
	public void refreshOSSurumComboField(OS aSelectedOS){
		fOSSurum.removeAllItems();
		fOSSurum.addItem(new OSSurum(null,null, "Seçiniz"));  	  		
		try {
			for(OSSurum os : OSSurumDAO.all(aSelectedOS)){
				fOSSurum.addItem(os);
				fOSSurum.setRenderer(new OSSurumComboBoxRenderer());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void addYongaSetiComboField(JComboBox<YongaSeti> aComboField, String aLabel, JPanel aPanel) {
  	    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		JLabel label = new JLabel(aLabel);
		panel.add(label);
		fYongaSeti.addItem(new YongaSeti(null, "Seçiniz"));  	  		
		try {
			for(YongaSeti yongaSeti : YongaSetiDAO.all()){
				fYongaSeti.addItem(yongaSeti);
				fYongaSeti.setRenderer(new YongaSetiComboBoxRenderer());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		aComboField.setPreferredSize(new Dimension(250, aComboField.getPreferredSize().height));

	    panel.add(aComboField);		 
		aPanel.add(panel);		  
	}	
	
	private void addGpuComboField(JComboBox<Gpu> aComboField, String aLabel, JPanel aPanel) {
  	    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		JLabel label = new JLabel(aLabel);
		panel.add(label);
		fGpu.addItem(new Gpu(null, "Seçiniz"));  	  		
		try {
			for(Gpu gpu : GpuDAO.all()){
				fGpu.addItem(gpu);
				fGpu.setRenderer(new GpuComboBoxRenderer());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		aComboField.setPreferredSize(new Dimension(250, aComboField.getPreferredSize().height));

	    panel.add(aComboField);		 
		aPanel.add(panel);		  
	}
	
	private void addCekirdekSayiComboField(JComboBox<CekirdekSayi> aComboField,  JPanel aPanel) {
  	    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		fCekirdekSayi.addItem(new CekirdekSayi(null, "Cekirdek Sayısı"));  	  		
		try {
			for(CekirdekSayi cekirdekSayi : CekirdekSayiDAO.all()){
				fCekirdekSayi.addItem(cekirdekSayi);
				fCekirdekSayi.setRenderer(new CekirdekSayiComboBoxRenderer());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		aComboField.setPreferredSize(new Dimension(200, aComboField.getPreferredSize().height));

	    panel.add(aComboField);		 
		aPanel.add(panel);		  
	}
	
	private void addCekirdekSayi2ComboField(JComboBox<CekirdekSayi> aComboField,  JPanel aPanel) {
  	    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		fCekirdekSayi2.addItem(new CekirdekSayi(null, "Cekirdek Sayısı"));  	  		
		try {
			for(CekirdekSayi cekirdekSayi2 : CekirdekSayiDAO.all()){
				fCekirdekSayi2.addItem(cekirdekSayi2);
				fCekirdekSayi2.setRenderer(new CekirdekSayiComboBoxRenderer());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		aComboField.setPreferredSize(new Dimension(200, aComboField.getPreferredSize().height));

	    panel.add(aComboField);		 
		aPanel.add(panel);		  
	}
	
	private void addCekirdekHizComboField(JComboBox<CekirdekHiz> aComboField,  JPanel aPanel) {
  	    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		fCekirdekHiz.addItem(new CekirdekHiz(null, "Cekirdek Hızı"));  	  		
		try {
			for(CekirdekHiz cekirdekHiz : CekirdekHizDAO.all()){
				fCekirdekHiz.addItem(cekirdekHiz);
				fCekirdekHiz.setRenderer(new CekirdekHizComboBoxRenderer());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		aComboField.setPreferredSize(new Dimension(200, aComboField.getPreferredSize().height));

	    panel.add(aComboField);		 
		aPanel.add(panel);		  
	}	
	
	private void addCekirdekHiz2ComboField(JComboBox<CekirdekHiz> aComboField,  JPanel aPanel) {
  	    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		fCekirdekHiz2.addItem(new CekirdekHiz(null, "Cekirdek Hızı"));  	  		
		try {
			for(CekirdekHiz cekirdekHiz2 : CekirdekHizDAO.all()){
				fCekirdekHiz2.addItem(cekirdekHiz2);
				fCekirdekHiz2.setRenderer(new CekirdekHizComboBoxRenderer());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		aComboField.setPreferredSize(new Dimension(200, aComboField.getPreferredSize().height));

	    panel.add(aComboField);		 
		aPanel.add(panel);		  
	}
	
	private void addDahiliHafizaComboField(JComboBox<DahiliHafiza> aComboField,  JPanel aPanel) {
  	    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		fDahiliHafiza.addItem(new DahiliHafiza(null, "Dahili Hafıza"));  	  		
		try {
			for(DahiliHafiza dahiliHafiza : DahiliHafizaDAO.all()){
				fDahiliHafiza.addItem(dahiliHafiza);
				fDahiliHafiza.setRenderer(new DahiliHafizaComboBoxRenderer());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		aComboField.setPreferredSize(new Dimension(200, aComboField.getPreferredSize().height));

	    panel.add(aComboField);		 
		aPanel.add(panel);		  
	}
	
	private void addDahiliHafiza2ComboField(JComboBox<DahiliHafiza> aComboField,  JPanel aPanel) {
  	    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		fDahiliHafiza2.addItem(new DahiliHafiza(null, "Dahili Hafıza"));  	  		
		try {
			for(DahiliHafiza dahiliHafiza : DahiliHafizaDAO.all()){
				fDahiliHafiza2.addItem(dahiliHafiza);
				fDahiliHafiza2.setRenderer(new DahiliHafizaComboBoxRenderer());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		aComboField.setPreferredSize(new Dimension(200, aComboField.getPreferredSize().height));

	    panel.add(aComboField);		 
		aPanel.add(panel);		  
	}
	
	private void addDahiliHafiza3ComboField(JComboBox<DahiliHafiza> aComboField,  JPanel aPanel) {
  	    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		fDahiliHafiza3.addItem(new DahiliHafiza(null, "Dahili Hafıza"));  	  		
		try {
			for(DahiliHafiza dahiliHafiza : DahiliHafizaDAO.all()){
				fDahiliHafiza3.addItem(dahiliHafiza);
				fDahiliHafiza3.setRenderer(new DahiliHafizaComboBoxRenderer());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		aComboField.setPreferredSize(new Dimension(200, aComboField.getPreferredSize().height));

	    panel.add(aComboField);		 
		aPanel.add(panel);		  
	}	
	
	private void addRamComboField(JComboBox<Ram> aComboField,  JPanel aPanel) {
  	    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		fRam.addItem(new Ram(null, "Ram bellek"));  	  		
		try {
			for(Ram ram : RamDAO.all()){
				fRam.addItem(ram);
				fRam.setRenderer(new RamComboBoxRenderer());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		aComboField.setPreferredSize(new Dimension(200, aComboField.getPreferredSize().height));

	    panel.add(aComboField);		 
		aPanel.add(panel);		  
	}	
	
	private void addHariciHafizaTipiComboField(JComboBox<HariciHafizaTipi> aComboField,  JPanel aPanel) {
  	    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		fHariciHafizaTipi.addItem(new HariciHafizaTipi(null, "Hafıza Kartı Tipi"));  	  		
		try {
			for(HariciHafizaTipi ram : HariciHafizaTipiDAO.all()){
				fHariciHafizaTipi.addItem(ram);
				fHariciHafizaTipi.setRenderer(new HariciHafizaTipiComboBoxRenderer());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		aComboField.setPreferredSize(new Dimension(200, aComboField.getPreferredSize().height));

	    panel.add(aComboField);		 
		aPanel.add(panel);		  
	}
	
	private void addHariciHafizaBuyuklukComboField(JComboBox<HariciHafizaBuyukluk> aComboField,  JPanel aPanel) {
  	    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		fHariciHafizaBuyukluk.addItem(new HariciHafizaBuyukluk(null, "Hafıza Kartı Tipi"));  	  		
		try {
			for(HariciHafizaBuyukluk hariciHafizaBuyukluk : HariciHafizaBuyuklukDAO.all()){
				fHariciHafizaBuyukluk.addItem(hariciHafizaBuyukluk);
				fHariciHafizaBuyukluk.setRenderer(new HariciHafizaBuyuklukComboBoxRenderer());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		aComboField.setPreferredSize(new Dimension(200, aComboField.getPreferredSize().height));

	    panel.add(aComboField);		 
		aPanel.add(panel);		  
	}	
	
	private void addArkaKameraCozunurlukComboField(JComboBox<KameraCozunurluk> aComboField,  JPanel aPanel) {
  	    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		fArkaKameraCozunurluk.addItem(new KameraCozunurluk(null, "Çözünürlük"));  	  		
		try {
			for(KameraCozunurluk arkaKameraCozunurluk : KameraCozunurlukDAO.all()){
				fArkaKameraCozunurluk.addItem(arkaKameraCozunurluk);
				fArkaKameraCozunurluk.setRenderer(new KameraCozunurlukComboBoxRenderer());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		aComboField.setPreferredSize(new Dimension(100, aComboField.getPreferredSize().height));

	    panel.add(aComboField);		 
		aPanel.add(panel);		  
	}	
	
	private void addArkaKameraDiyaframComboField(JComboBox<Diyafram> aComboField,  JPanel aPanel) {
  	    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

  	  fArkaKameraDiyafram.addItem(new Diyafram(null, "Diyafram"));  	  		
		try {
			for(Diyafram arkaKameraDiyafram : DiyaframDAO.all()){
				fArkaKameraDiyafram.addItem(arkaKameraDiyafram);
				fArkaKameraDiyafram.setRenderer(new DiyaframComboBoxRenderer());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		aComboField.setPreferredSize(new Dimension(100, aComboField.getPreferredSize().height));

	    panel.add(aComboField);		 
		aPanel.add(panel);		  
	}
	
	private void addArkaKameraPikselBuyukluguComboField(JComboBox<PikselBuyuklugu> aComboField,  JPanel aPanel) {
  	    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

  	  fArkaKameraPikselBuyuklugu.addItem(new PikselBuyuklugu(null, "Piksel Büyüklüğü"));  	  		
		try {
			for(PikselBuyuklugu arkaKameraPikselBuyuklugu : PikselBuyukluguDAO.all()){
				fArkaKameraPikselBuyuklugu.addItem(arkaKameraPikselBuyuklugu);
				fArkaKameraPikselBuyuklugu.setRenderer(new PikselBuyukluguComboBoxRenderer());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		aComboField.setPreferredSize(new Dimension(100, aComboField.getPreferredSize().height));

	    panel.add(aComboField);		 
		aPanel.add(panel);		  
	}
	
	private void addArkaKameraCozunurluk2ComboField(JComboBox<KameraCozunurluk> aComboField,  JPanel aPanel) {
  	    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		fArkaKameraCozunurluk2.addItem(new KameraCozunurluk(null, "Çözünürlük"));  	  		
		try {
			for(KameraCozunurluk arkaKameraCozunurluk : KameraCozunurlukDAO.all()){
				fArkaKameraCozunurluk2.addItem(arkaKameraCozunurluk);
				fArkaKameraCozunurluk2.setRenderer(new KameraCozunurlukComboBoxRenderer());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		aComboField.setPreferredSize(new Dimension(100, aComboField.getPreferredSize().height));

	    panel.add(aComboField);		 
		aPanel.add(panel);		  
	}
	
	private void addArkaKameraDiyafram2ComboField(JComboBox<Diyafram> aComboField,  JPanel aPanel) {
  	    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

  	  fArkaKameraDiyafram2.addItem(new Diyafram(null, "Diyafram"));  	  		
		try {
			for(Diyafram arkaKameraDiyafram : DiyaframDAO.all()){
				fArkaKameraDiyafram2.addItem(arkaKameraDiyafram);
				fArkaKameraDiyafram2.setRenderer(new DiyaframComboBoxRenderer());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		aComboField.setPreferredSize(new Dimension(100, aComboField.getPreferredSize().height));

	    panel.add(aComboField);		 
		aPanel.add(panel);		  
	}
	
	private void addArkaKameraPikselBuyuklugu2ComboField(JComboBox<PikselBuyuklugu> aComboField,  JPanel aPanel) {
  	    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

  	  fArkaKameraPikselBuyuklugu2.addItem(new PikselBuyuklugu(null, "Piksel Büyüklüğü"));  	  		
		try {
			for(PikselBuyuklugu arkaKameraPikselBuyuklugu : PikselBuyukluguDAO.all()){
				fArkaKameraPikselBuyuklugu2.addItem(arkaKameraPikselBuyuklugu);
				fArkaKameraPikselBuyuklugu2.setRenderer(new PikselBuyukluguComboBoxRenderer());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		aComboField.setPreferredSize(new Dimension(100, aComboField.getPreferredSize().height));

	    panel.add(aComboField);		 
		aPanel.add(panel);		  
	}	
	
	private void addOnKameraCozunurlukComboField(JComboBox<KameraCozunurluk> aComboField,  JPanel aPanel) {
  	    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		fOnKameraCozunurluk.addItem(new KameraCozunurluk(null, "Çözünürlük"));  	  		
		try {
			for(KameraCozunurluk onKameraCozunurluk : KameraCozunurlukDAO.all()){
				fOnKameraCozunurluk.addItem(onKameraCozunurluk);
				fOnKameraCozunurluk.setRenderer(new KameraCozunurlukComboBoxRenderer());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		aComboField.setPreferredSize(new Dimension(100, aComboField.getPreferredSize().height));

	    panel.add(aComboField);		 
		aPanel.add(panel);		  
	}		
	
	private void addOnKameraDiyaframComboField(JComboBox<Diyafram> aComboField,  JPanel aPanel) {
  	    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

  	  fOnKameraDiyafram.addItem(new Diyafram(null, "Diyafram"));  	  		
		try {
			for(Diyafram arkaKameraDiyafram : DiyaframDAO.all()){
				fOnKameraDiyafram.addItem(arkaKameraDiyafram);
				fOnKameraDiyafram.setRenderer(new DiyaframComboBoxRenderer());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		aComboField.setPreferredSize(new Dimension(100, aComboField.getPreferredSize().height));

	    panel.add(aComboField);		 
		aPanel.add(panel);		  
	}
	
	private void addOnKameraPikselBuyukluguComboField(JComboBox<PikselBuyuklugu> aComboField,  JPanel aPanel) {
  	    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

  	  fOnKameraPikselBuyuklugu.addItem(new PikselBuyuklugu(null, "Piksel Büyüklüğü"));  	  		
		try {
			for(PikselBuyuklugu arkaKameraPikselBuyuklugu : PikselBuyukluguDAO.all()){
				fOnKameraPikselBuyuklugu.addItem(arkaKameraPikselBuyuklugu);
				fOnKameraPikselBuyuklugu.setRenderer(new PikselBuyukluguComboBoxRenderer());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		aComboField.setPreferredSize(new Dimension(100, aComboField.getPreferredSize().height));

	    panel.add(aComboField);		 
		aPanel.add(panel);		  
	}
		
	private void addOnKameraCozunurluk2ComboField(JComboBox<KameraCozunurluk> aComboField,  JPanel aPanel) {
  	    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		fOnKameraCozunurluk2.addItem(new KameraCozunurluk(null, "Çözünürlük"));  	  		
		try {
			for(KameraCozunurluk onKameraCozunurluk : KameraCozunurlukDAO.all()){
				fOnKameraCozunurluk2.addItem(onKameraCozunurluk);
				fOnKameraCozunurluk2.setRenderer(new KameraCozunurlukComboBoxRenderer());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		aComboField.setPreferredSize(new Dimension(100, aComboField.getPreferredSize().height));

	    panel.add(aComboField);		 
		aPanel.add(panel);		  
	}		
	
	private void addOnKameraDiyafram2ComboField(JComboBox<Diyafram> aComboField,  JPanel aPanel) {
  	    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

  	  fOnKameraDiyafram2.addItem(new Diyafram(null, "Diyafram"));  	  		
		try {
			for(Diyafram arkaKameraDiyafram : DiyaframDAO.all()){
				fOnKameraDiyafram2.addItem(arkaKameraDiyafram);
				fOnKameraDiyafram2.setRenderer(new DiyaframComboBoxRenderer());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		aComboField.setPreferredSize(new Dimension(100, aComboField.getPreferredSize().height));

	    panel.add(aComboField);		 
		aPanel.add(panel);		  
	}
	
	private void addOnKameraPikselBuyuklugu2ComboField(JComboBox<PikselBuyuklugu> aComboField,  JPanel aPanel) {
  	    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

  	  fOnKameraPikselBuyuklugu2.addItem(new PikselBuyuklugu(null, "Piksel Büyüklüğü"));  	  		
		try {
			for(PikselBuyuklugu arkaKameraPikselBuyuklugu : PikselBuyukluguDAO.all()){
				fOnKameraPikselBuyuklugu2.addItem(arkaKameraPikselBuyuklugu);
				fOnKameraPikselBuyuklugu2.setRenderer(new PikselBuyukluguComboBoxRenderer());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		aComboField.setPreferredSize(new Dimension(100, aComboField.getPreferredSize().height));

	    panel.add(aComboField);		 
		aPanel.add(panel);		  
	}
	
	private void addBataryaKapasiteComboField(JComboBox<BataryaKapasite> aComboField,  JPanel aPanel) {
  	    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

  	  fBataryaKapasite.addItem(new BataryaKapasite(null, "Kapasite"));  	  		
		try {
			for(BataryaKapasite bataryaKapasite : BataryaKapasiteDAO.all()){
				fBataryaKapasite.addItem(bataryaKapasite);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		aComboField.setPreferredSize(new Dimension(100, aComboField.getPreferredSize().height));

	    panel.add(aComboField);		 
		aPanel.add(panel);		  
	}
	
	private void addBataryaTeknolojiComboField(JComboBox<BataryaTeknoloji> aComboField,  JPanel aPanel) {
  	    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

  	  fBataryaTeknoloji.addItem(new BataryaTeknoloji(null, "Teknoloji"));  	  		
		try {
			for(BataryaTeknoloji bataryaTeknoloji : BataryaTeknolojiDAO.all()){
				fBataryaTeknoloji.addItem(bataryaTeknoloji);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		aComboField.setPreferredSize(new Dimension(100, aComboField.getPreferredSize().height));

	    panel.add(aComboField);		 
		aPanel.add(panel);		  
	}	
	private void addBataryaDegisirComboField(JComboBox<BataryaDegisir> aComboField,  JPanel aPanel) {
  	    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

  	  fBataryaDegisir.addItem(new BataryaDegisir(null, "Değişir Batarya"));  	  		
		try {
			for(BataryaDegisir bataryaDegisir : BataryaDegisirDAO.all()){
				fBataryaDegisir.addItem(bataryaDegisir);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		aComboField.setPreferredSize(new Dimension(100, aComboField.getPreferredSize().height));

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
			ozellikAtama = ozellikAtamaDao.find(fId,44);
			if(ozellikAtama!=null)
				fEkranDiger.setText(ozellikAtama.getDeger());			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(selectedSimSayisi.getSayi()!=null)
			fSimSayisi.getModel().setSelectedItem(selectedSimSayisi);
		if(selectedSim.getAd()!=null)
			fSim.getModel().setSelectedItem(selectedSim);
	
		try {
			ozellikAtama = ozellikAtamaDao.find(fId,47);
			if(ozellikAtama!=null)
				fGovdeDiger.setText(ozellikAtama.getDeger());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(selectedOS.getAd()!=null)
			fOS.getModel().setSelectedItem(selectedOS);
		
		if(selectedOSSurum.getAd()!=null)
			fOSSurum.getModel().setSelectedItem(selectedOSSurum);
		
		if(selectedYongaSeti.getAd()!=null)
			fYongaSeti.getModel().setSelectedItem(selectedYongaSeti);		
		
		if(selectedGpu.getAd()!=null)
			fGpu.getModel().setSelectedItem(selectedGpu);
		
		if(selectedCekirdekSayi != null)
			fCekirdekSayi.getModel().setSelectedItem(selectedCekirdekSayi);
		if(selectedCekirdekHiz != null)
			fCekirdekHiz.getModel().setSelectedItem(selectedCekirdekHiz);
		if(selectedCekirdekSayi2 != null)
			fCekirdekSayi2.getModel().setSelectedItem(selectedCekirdekSayi2);
		if(selectedCekirdekHiz2 != null)
			fCekirdekHiz2.getModel().setSelectedItem(selectedCekirdekHiz2);
		
		if(selectedDahiliHafiza.getBuyukluk()!=null)
			fDahiliHafiza.getModel().setSelectedItem(selectedDahiliHafiza);
		
		if(selectedDahiliHafiza2.getBuyukluk()!=null)
			fDahiliHafiza2.getModel().setSelectedItem(selectedDahiliHafiza2);
		
		if(selectedDahiliHafiza3.getBuyukluk()!=null)
			fDahiliHafiza3.getModel().setSelectedItem(selectedDahiliHafiza3);
		
		if(selectedRam.getBuyukluk()!=null)
			fRam.getModel().setSelectedItem(selectedRam);
		
		if(selectedHariciHafizaTipi.getAd()!=null)
			fHariciHafizaTipi.getModel().setSelectedItem(selectedHariciHafizaTipi);
		if(selectedHariciHafizaBuyukluk.getBuyukluk()!=null)
			fHariciHafizaBuyukluk.getModel().setSelectedItem(selectedHariciHafizaBuyukluk);
		
		if(selectedArkaKameraCozunurluk.getId()!=null)
			fArkaKameraCozunurluk.getModel().setSelectedItem(selectedArkaKameraCozunurluk);
		if(selectedArkaKameraDiyafram.getId()!=null)
			fArkaKameraDiyafram.getModel().setSelectedItem(selectedArkaKameraDiyafram);
		if(selectedArkaKameraPikselBuyuklugu.getId()!=null)
			fArkaKameraPikselBuyuklugu.getModel().setSelectedItem(selectedArkaKameraPikselBuyuklugu);

		if(selectedArkaKameraCozunurluk2.getId()!=null)
			fArkaKameraCozunurluk2.getModel().setSelectedItem(selectedArkaKameraCozunurluk2);
		if(selectedArkaKameraDiyafram2.getId()!=null)
			fArkaKameraDiyafram2.getModel().setSelectedItem(selectedArkaKameraDiyafram2);
		if(selectedArkaKameraPikselBuyuklugu2.getId()!=null)
			fArkaKameraPikselBuyuklugu2.getModel().setSelectedItem(selectedArkaKameraPikselBuyuklugu2);
		
		try {
			ozellikAtama = ozellikAtamaDao.find(fId,22);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(ozellikAtama!=null)
			fArkaKameraDiger.setText(ozellikAtama.getDeger());
		try {
			ozellikAtama = ozellikAtamaDao.find(fId,23);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(ozellikAtama!=null){
			fArkaKameraVideo.setPreferredSize(new Dimension(400, fArkaKameraVideo.getPreferredSize().height));
			fArkaKameraVideo.setText(ozellikAtama.getDeger());
		}
		
		if(selectedOnKameraCozunurluk.getId()!=null)
			fOnKameraCozunurluk.getModel().setSelectedItem(selectedOnKameraCozunurluk);
		if(selectedOnKameraDiyafram.getId()!=null)
			fOnKameraDiyafram.getModel().setSelectedItem(selectedOnKameraDiyafram);
		if(selectedOnKameraPikselBuyuklugu.getId()!=null)
			fOnKameraPikselBuyuklugu.getModel().setSelectedItem(selectedOnKameraPikselBuyuklugu);
		
		if(selectedOnKameraCozunurluk2.getId()!=null)
			fOnKameraCozunurluk2.getModel().setSelectedItem(selectedOnKameraCozunurluk2);
		if(selectedOnKameraDiyafram2.getId()!=null)
			fOnKameraDiyafram2.getModel().setSelectedItem(selectedOnKameraDiyafram2);
		if(selectedOnKameraPikselBuyuklugu2.getId()!=null)
			fOnKameraPikselBuyuklugu2.getModel().setSelectedItem(selectedOnKameraPikselBuyuklugu2);
		try {
			ozellikAtama = ozellikAtamaDao.find(fId,52);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(ozellikAtama!=null)
			fOnKameraDiger.setText(ozellikAtama.getDeger());
		
		if(selectedBataryaKapasite.getKapasite()!=null)
			fBataryaKapasite.getModel().setSelectedItem(selectedBataryaKapasite);
		
		if(selectedBataryaTeknoloji.getId()!=null)
			fBataryaTeknoloji.getModel().setSelectedItem(selectedBataryaTeknoloji);
		
		if(selectedBataryaDegisir.getId()!=null)
			fBataryaDegisir.getModel().setSelectedItem(selectedBataryaDegisir);
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
