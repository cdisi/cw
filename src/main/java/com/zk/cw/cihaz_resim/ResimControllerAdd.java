package com.zk.cw.cihaz_resim;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import com.zk.cw.cihaz.CihazView;
import com.zk.cw.util.ImageResize;

public class ResimControllerAdd implements ActionListener  {
	JFileChooser fResimChooser= new JFileChooser();
	CihazView fView;
	JPanel fMainPanel;
	Resim resimGaleri = new Resim();
	ResimDAO resimGaleriDAO;
	
	public ResimControllerAdd(CihazView aView, JPanel aMainPanel){
		fView = aView;
		fMainPanel = aMainPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
        int returnVal = fResimChooser.showOpenDialog(fMainPanel);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
           File file = fResimChooser.getSelectedFile();
       		try {
    			BufferedImage originalImage = ImageIO.read(file);
    			ByteArrayOutputStream baos = new ByteArrayOutputStream();
    			ImageIO.write( originalImage, "jpg", baos );
    			fView.setBuyukResim(baos.toByteArray());
    			fView.setGoruntuIcon(originalImage);
    			fMainPanel.revalidate();
    			
    		} catch (Exception ex) {
    			ex.printStackTrace();
    		}
        }
		
	}
	
}
