package com.zk.cw.cihaz;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
import com.zk.cw.yeni_cihaz.YeniCihazAction;



public class CihazMainWindow {
	
	private static CihazMainWindow INSTANCE = new CihazMainWindow();
	private CihazTableModel cihazTableModel;
	private JTable cihazTable;	
	private CihazActionChange cihazActionChange;
	private CihazActionAdd cihazActionAdd;
	
	private CihazMainWindow() {  }
	
	public static CihazMainWindow getInstance() {
	    return INSTANCE;
	}
	
	public void refreshView(){
		cihazTableModel.refreshView();
	}	
	public void buildGui(JFrame mainFrame, JMenuBar menuBar){
		cihazTableModel = new CihazTableModel();
		cihazTable = new JTable(cihazTableModel);
		buildActionsAndMenu(mainFrame, menuBar);
		buildContent(mainFrame);
	}	
	
	private final class SortCihazTable extends MouseAdapter {
		    @Override public void mouseClicked(MouseEvent aEvent) {
		      int columnIdx = cihazTable.getColumnModel().getColumnIndexAtX(aEvent.getX());
		      //cihazTableModel.sortByColumn(columnIdx);
		    }
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
	    cihazActionAdd = new CihazActionAdd(aFrame,cihazTable,cihazTableModel);
	    editMenu.add(new JMenuItem(cihazActionAdd));
	    cihazActionChange = new CihazActionChange(aFrame,cihazTable,cihazTableModel);
	    editMenu.add(new JMenuItem(cihazActionChange));
	    
	    menuBar.add(editMenu);
	    
	    JMenu helpMenu = new JMenu("Help");
	    helpMenu.setMnemonic('H');
	    helpMenu.add(new JMenuItem(new AboutAction(aFrame)));
	    menuBar.add(helpMenu);    
		    
	    aFrame.setJMenuBar(menuBar);
	
	  }		
	
	private void buildContent(JFrame mainFrame) {	    
		cihazTable.setBackground(Color.LIGHT_GRAY);
		cihazTable.getColumnModel().getColumn(0).setPreferredWidth(50);
		cihazTable.getColumnModel().getColumn(1).setPreferredWidth(200);
		cihazTable.getColumnModel().getColumn(2).setPreferredWidth(200);
		cihazTable.getColumnModel().getColumn(3).setPreferredWidth(100);
		cihazTable.getColumnModel().getColumn(4).setPreferredWidth(100);
		cihazTable.getColumnModel().getColumn(5).setPreferredWidth(100);
		mainFrame.getContentPane().removeAll();
		
		JScrollPane panel = new JScrollPane(cihazTable);
	    mainFrame.getContentPane().add(panel); 
	    mainFrame.revalidate(); 
	    mainFrame.repaint();
	    //clickOnHeaderSortsTable();
		rowSelectionEnablesActions();
		doubleClickShowsEditDialog();
    }	
	
	  /** Enable edit and delete actions only when something is selected in the table. */
	  private final class EnableEditActions implements ListSelectionListener {
	    @Override public void valueChanged(ListSelectionEvent aEvent) {
	      if( aEvent.getFirstIndex() != -1) {
	    	  cihazActionChange.setEnabled(true);
	      }
	      else {
	    	  cihazActionChange.setEnabled(false);
	      }
	    }
	  }	
	  private void rowSelectionEnablesActions() {
			cihazTable.getSelectionModel().addListSelectionListener(new EnableEditActions());
	  }	 
	  private void doubleClickShowsEditDialog() {
		  cihazTable.addMouseListener( new LaunchEditCihazDialog() );
	  }	  
	  
	  private void clickOnHeaderSortsTable() {
		  cihazTable.getTableHeader().addMouseListener(new SortCihazTable());
	  }	  
	  private final class LaunchEditCihazDialog extends MouseAdapter {
		    @Override public void mouseClicked(MouseEvent aEvent) {
		      if( aEvent.getClickCount() == 2) {
		        ActionEvent event = new ActionEvent(this, 0, "");
		        cihazActionChange.actionPerformed(event);
		      }
		    }
	  }	
}
