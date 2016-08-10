package com.zk.cw.yeni_cihaz;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class YeniCihazMainWindow {
	
	private static YeniCihazMainWindow INSTANCE = new YeniCihazMainWindow();
	private YeniCihazTableModel yeniCihazTableModel;
	private JTable yeniCihazTable;	
	
	private YeniCihazMainWindow() {  }
	
	public static YeniCihazMainWindow getInstance() {
	    return INSTANCE;
	}
	
	public void buildGui(JFrame mainFrame){
		yeniCihazTableModel = new YeniCihazTableModel();
		yeniCihazTable = new JTable(yeniCihazTableModel);
		buildContent(mainFrame);
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
    }	
}
