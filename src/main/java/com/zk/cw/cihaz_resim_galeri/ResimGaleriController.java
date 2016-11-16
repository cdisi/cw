package com.zk.cw.cihaz_resim_galeri;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import com.zk.cw.util.ImageResize;

public class ResimGaleriController implements ActionListener  {
	JFileChooser fResimChooser= new JFileChooser();
	ResimGaleriView fView;
	JPanel fMainPanel;
	
	public ResimGaleriController(ResimGaleriView aView, JPanel aMainPanel){
		fView = aView;
		fMainPanel = aMainPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
        int returnVal = fResimChooser.showOpenDialog(fMainPanel);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
           File file = fResimChooser.getSelectedFile();
       		try {
       			byte[] fKucukResim;
       			byte[] fOrtaResim;
       			byte[] fBuyukResim;
       			
    			fKucukResim = ImageResize.reize(file, 40, 0);
    			fOrtaResim = ImageResize.reize(file, 160, 0);
    			BufferedImage originalImage = ImageIO.read(file);
    			ByteArrayOutputStream baos = new ByteArrayOutputStream();
    			ImageIO.write( originalImage, "jpg", baos );
    			fBuyukResim = baos.toByteArray();
    			
    		} catch (Exception ex) {
    			ex.printStackTrace();
    		}
        }
		
	}
	
}
