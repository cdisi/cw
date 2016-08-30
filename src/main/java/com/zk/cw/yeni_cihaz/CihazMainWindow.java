package com.zk.cw.yeni_cihaz;

import java.awt.Color;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.zk.cw.about.AboutAction;
import com.zk.cw.cihaz_url.CihazAraAction;
import com.zk.cw.exit.ExitAction;
import com.zk.cw.uretici.UreticilerAction;

public class CihazMainWindow {
	
	private static CihazMainWindow INSTANCE = new CihazMainWindow();
	private YeniCihazTableModel yeniCihazTableModel;
	private JTable yeniCihazTable;	
	private CihazActionEkle cihazActionEkle;
	private CihazMainWindow() {  }
	
	public static CihazMainWindow getInstance() {
	    return INSTANCE;
	}
	
	public void buildGui(JFrame mainFrame, JMenuBar menuBar){
		yeniCihazTableModel = new YeniCihazTableModel();
		yeniCihazTable = new JTable(yeniCihazTableModel);
		buildActionsAndMenu(mainFrame, menuBar);
		buildContent(mainFrame);
	}	
	
	  /** Build the menu bar. */
	  
	private void buildActionsAndMenu(JFrame aFrame, JMenuBar menuBar) {
		menuBar.removeAll();
		menuBar.revalidate(); 
		menuBar.repaint();
		
		JMenu fileMenu = new JMenu("File");
	    fileMenu.setMnemonic('F'); 

	    Action ureticilerAction = new UreticilerAction(aFrame);
	    fileMenu.add(new JMenuItem(ureticilerAction));

	    Action cihazAraAction = new CihazAraAction(aFrame);
	    fileMenu.add(new JMenuItem(cihazAraAction));
		    
	    Action yeniCihazAction = new YeniCihazAction(aFrame, menuBar);
	    fileMenu.add(new JMenuItem(yeniCihazAction));
		    
	    Action exitAction = new ExitAction();
	    fileMenu.add(new JMenuItem(exitAction));
	    menuBar.add(fileMenu);
		    
	    JMenu editMenu = new JMenu("Edit");
	    editMenu.setMnemonic('E');
	    cihazActionEkle = new CihazActionEkle(aFrame,yeniCihazTable,yeniCihazTableModel);
	    editMenu.add(new JMenuItem(cihazActionEkle));
	    
	    menuBar.add(editMenu);
	    
	    JMenu helpMenu = new JMenu("Help");
	    helpMenu.setMnemonic('H');
	    helpMenu.add(new JMenuItem(new AboutAction(aFrame)));
	    menuBar.add(helpMenu);    
		    
	    aFrame.setJMenuBar(menuBar);
	
	  }	
	
	private void buildContent(JFrame mainFrame) {	    
		yeniCihazTable.setBackground(Color.LIGHT_GRAY);
		yeniCihazTable.getColumnModel().getColumn(0).setPreferredWidth(20);
		yeniCihazTable.getColumnModel().getColumn(1).setPreferredWidth(100);
		yeniCihazTable.getColumnModel().getColumn(2).setPreferredWidth(400);
		yeniCihazTable.getColumnModel().getColumn(3).setPreferredWidth(200);
		mainFrame.getContentPane().removeAll();
		
		JScrollPane panel = new JScrollPane(yeniCihazTable);
	    mainFrame.getContentPane().add(panel); 
	    mainFrame.revalidate(); 
	    mainFrame.repaint();
		rowSelectionEnablesActions();
    }	
	
	  /** Enable edit and delete actions only when something is selected in the table. */
	  private final class EnableEditActions implements ListSelectionListener {
	    @Override public void valueChanged(ListSelectionEvent aEvent) {
	      if( aEvent.getFirstIndex() != -1) {
	    	  cihazActionEkle.setEnabled(true);
	      }
	      else {
	    	  cihazActionEkle.setEnabled(false);
	      }
	    }
	  }	
	  private void rowSelectionEnablesActions() {
			yeniCihazTable.getSelectionModel().addListSelectionListener(new EnableEditActions());
	  }	  
}
