package com.zk.cw.main;

import com.zk.cw.LaunchApplication;
import com.zk.cw.about.AboutAction;
import com.zk.cw.cihaz.CihazAction;
import com.zk.cw.cihaz_url.CihazAraAction;
import com.zk.cw.edit.MovieActionAdd;
import com.zk.cw.edit.MovieActionChange;
import com.zk.cw.edit.MovieActionDelete;
import com.zk.cw.exception.InvalidInputException;
import com.zk.cw.uretici.*;
import com.zk.cw.yeni_cihaz.*;
import com.zk.cw.exit.ExitAction;
import com.zk.cw.util.Util;
import com.zk.cw.util.ui.UiUtil;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Locale;
import java.util.logging.Logger;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.commons.io.FilenameUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public final class MainWindow {
  // PRIVATE 
  
  /** The single instance of this class. */
  private static MainWindow INSTANCE = new MainWindow();
  
  /** Empty constructor prevents the caller from creating an object. */
  private MainWindow() {  }
  
  private UreticiTableModel ureticiTableModel;
  private UreticiDAO ureticiDao = new UreticiDAO();
  private JTable ureticiTable;
  private Action fChangeMovieAction;
  private Action fDeleteMovieAction;
  private String fUserName;
  private static final Logger fLogger = Util.getLogger(MainWindow.class); 
  private JScrollPane mainPanel = new JScrollPane();

  public static MainWindow getInstance() {
    return INSTANCE;
  }
  
  public void buildAndShow(String aUserName){
    fUserName = aUserName;
    fLogger.fine("Building GUI for user : " + aUserName);
    buildGui();
  }
  
  /** Refresh the display in response to changing database content. */
  public void refreshView(){
    fMovieTableModel.refreshView();
  }
  
  public String getUserName(){
    return fUserName;
  }  
  
  /** Build the user interface. */
  private void buildGui(){
    JFrame frame = new JFrame(
      LaunchApplication.APP_NAME + 
      " - " + fUserName.toUpperCase(Locale.ENGLISH)
    ); 
    ureticiBul();
    
    ureticiTableModel = new UreticiTableModel();
    ureticiTable = new JTable(ureticiTableModel);
    
    buildActionsAndMenu(frame);
    buildContent(frame);
    
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    placeInMiddlePartOfTheScreen(frame);
    addApplicationIcon(frame);
    UiUtil.centerAndShow(frame);
  }
  
  private void ureticiBul() {
	Document doc;
	try {
		doc = Jsoup.connect("http://www.gsmarena.com/makers.php3").get();
		Elements links = doc.select("a[href*=phones]");
		for (Element link : links) {
			System.out.println("link : " + link.attr("href"));
			System.out.println("text : " + link.text());
			Elements imgElm = link.select("img");
			System.out.println(imgElm.attr("src"));
			String ad = link.text().trim();
			String logoUrl = null;
			String gsmArenaUrl = "http://www.gsmarena.com/"+ link.attr("href").trim();
			Uretici uretici = new Uretici(null,ad,null,gsmArenaUrl,0);
			String dosya =  (ad.replaceAll("[^\\w.-]", "_") + "." + (FilenameUtils.getExtension(imgElm.attr("src")))).toLowerCase();
			if(!UreticiDAO.bul(uretici)){
				//Util.downloadImage(imgElm.attr("src"), "resources/logo", dosya);
				//uretici.logoUrlVer("/img/logo/"+dosya);
				UreticiDAO.ekle(uretici);
			}
		}
	} catch (IOException e) {
		e.printStackTrace();
	} catch (InvalidInputException e) {
		e.printStackTrace();
	}
  }

  /** Sort the table.  Listens for clicks on the JTableHeader. */
  private final class SortMovieTable extends MouseAdapter {
    @Override public void mouseClicked(MouseEvent aEvent) {
      fLogger.config("Sorting the table.");
      int columnIdx = fMovieTable.getColumnModel().getColumnIndexAtX(aEvent.getX());
      fMovieTableModel.sortByColumn(columnIdx);
    }
  }
  
  /** Show a dialog to edit a movie.  Listens for double-clicks on the JTable. */
  private final class LaunchEditMovieDialog extends MouseAdapter {
    @Override public void mouseClicked(MouseEvent aEvent) {
      if( aEvent.getClickCount() == 2) {
        fLogger.config("Editing a movie.");
        ActionEvent event = new ActionEvent(this, 0, "");
        fChangeMovieAction.actionPerformed(event);
      }
    }
  }
  
  /** Enable edit and delete actions only when something is selected in the table. */
  private final class EnableEditActions implements ListSelectionListener {
    @Override public void valueChanged(ListSelectionEvent aEvent) {
      fLogger.fine(
        "List selection changed. First:" + aEvent.getFirstIndex() 
        + " Last " + aEvent.getLastIndex()
      );
      if( aEvent.getFirstIndex() != -1) {
        fDeleteMovieAction.setEnabled(true);
        fChangeMovieAction.setEnabled(true);
      }
      else {
        fDeleteMovieAction.setEnabled(false);
        fChangeMovieAction.setEnabled(false);
      }
    }
  }
  
  /** Build the menu bar. */
  private void buildActionsAndMenu(JFrame aFrame) {
    JMenuBar menuBar = new JMenuBar();

    JMenu fileMenu = new JMenu("File");
    fileMenu.setMnemonic('F'); 

    Action ureticilerAction = new UreticilerAction(aFrame);
    fileMenu.add(new JMenuItem(ureticilerAction));

    Action cihazAraAction = new CihazAraAction(aFrame);
    fileMenu.add(new JMenuItem(cihazAraAction));
    
    Action yeniCihazAction = new YeniCihazAction(aFrame, menuBar);
    fileMenu.add(new JMenuItem(yeniCihazAction));
    
    Action tumCihazlarAction = new CihazAction(aFrame, menuBar);
    fileMenu.add(new JMenuItem(tumCihazlarAction));
    
    Action exitAction = new ExitAction();
    fileMenu.add(new JMenuItem(exitAction));
    menuBar.add(fileMenu);
    
    JMenu editMenu = new JMenu("Edit");
    editMenu.setMnemonic('E');
    
    JMenu helpMenu = new JMenu("Help");
    helpMenu.setMnemonic('H');
    helpMenu.add(new JMenuItem(new AboutAction(aFrame)));
    menuBar.add(helpMenu);    
    
    aFrame.setJMenuBar(menuBar);
  }
  
  /** Expand the frame to fill the middle part of the screen. */
  private void placeInMiddlePartOfTheScreen(JFrame aFrame) {
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension halfScreen = new Dimension(2*screen.width/3, screen.height/2);
    aFrame.setPreferredSize(halfScreen);
  }

  /**
    Custom icon for the upper left corner of the frame.
    Not that a path relative to this class is used. 
   */
  private void addApplicationIcon(JFrame aFrame) {
    ImageIcon icon =  UiUtil.createImageIcon("app_icon.png", "Application icon", this.getClass());
    aFrame.setIconImage(icon.getImage());
  }

  /** Build the main content of the frame. */
  private void buildContent(JFrame aFrame) {
    ureticiTable.setBackground(Color.LIGHT_GRAY);
    
    //relative column widths
    ureticiTable.getColumnModel().getColumn(0).setPreferredWidth(100);
    ureticiTable.getColumnModel().getColumn(1).setPreferredWidth(20);
    ureticiTable.getColumnModel().getColumn(2).setPreferredWidth(20);
    ureticiTable.getColumnModel().getColumn(3).setPreferredWidth(200);

    clickOnHeaderSortsTable();
    doubleClickShowsEditDialog();
    rowSelectionEnablesActions();
    
    JScrollPane panel = new JScrollPane(ureticiTable);
    aFrame.getContentPane().add(panel);  
    
  }

  private void clickOnHeaderSortsTable() {
	  ureticiTable.getTableHeader().addMouseListener(new SortMovieTable());
  }
  
  private void doubleClickShowsEditDialog() {
	  ureticiTable.addMouseListener( new LaunchEditMovieDialog() );
  }

  private void rowSelectionEnablesActions() {
	  ureticiTable.getSelectionModel().addListSelectionListener(new EnableEditActions());
  }
}
      