
package com.zk.cw.yeni_cihaz;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class YeniCihazAction extends AbstractAction {
	
	private static final long serialVersionUID = 1L;
	private JFrame mFrame;	
	private JScrollPane mainPanel;
    private YeniCihazTableModel yeniCihazTableModel;
	private JTable yeniCihazTable;
	
	public YeniCihazAction(JFrame aFrame, JScrollPane mainPanel){
	    super("Yeni Cihazlar", null );
	    putValue(SHORT_DESCRIPTION, "Yeni Cihazlar"); 
	    putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_A) );
	    mFrame = aFrame;
	    this.mainPanel=mainPanel;
	}
	
	@Override 
	public void actionPerformed(ActionEvent aActionEvent) {
	    this.mainPanel.removeAll();
	    this.mainPanel.repaint();
	    yeniCihazTableModel = new YeniCihazTableModel();
	    yeniCihazTable = new JTable(yeniCihazTableModel);
	    yeniCihazTable.setBackground(Color.LIGHT_GRAY);
	    
	    //relative column widths
	    yeniCihazTable.getColumnModel().getColumn(0).setPreferredWidth(100);
	    yeniCihazTable.getColumnModel().getColumn(1).setPreferredWidth(20);
	    yeniCihazTable.getColumnModel().getColumn(2).setPreferredWidth(20);
	    yeniCihazTable.getColumnModel().getColumn(3).setPreferredWidth(200);
	    mainPanel.add(yeniCihazTable);
	    mFrame.getContentPane().add(mainPanel);
	    //this.mainPanel.repaint();
	    //YeniCihazDAO = new YeniCihazDAO();
	}	
	
}
