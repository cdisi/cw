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

import com.zk.cw.util.ImageResize;

public class ResimController implements ActionListener  {
	JFileChooser fResimChooser= new JFileChooser();
	ResimView fView;
	JPanel fMainPanel;
	Resim resimGaleri = new Resim();
	ResimDAO resimGaleriDAO;
	
	public ResimController(ResimView aView, JPanel aMainPanel){
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
       			resimGaleri.setCihazId(fView.getCihazId());
       			resimGaleri.setKucukResim(ImageResize.reize(file, 40, 0));
       			resimGaleri.setOrtaResim(ImageResize.reize(file, 160, 0));
    			BufferedImage originalImage = ImageIO.read(file);
    			ByteArrayOutputStream baos = new ByteArrayOutputStream();
    			ImageIO.write( originalImage, "jpg", baos );
    			resimGaleri.setBuyukResim(baos.toByteArray());
    			JPanel resimGaleriPanel = fView.getResimGaleriPanel();
    			resimGaleriPanel.removeAll();
    			resimGaleriDAO.add(resimGaleri);
    			resimGaleriPanel = fView.getGaleriInputArea();   			
    			resimGaleriPanel.revalidate();
    			//resimGaleriPanel.repaint();
    			fMainPanel.revalidate();
    			//fMainPanel.repaint();
    			fMainPanel.add(resimGaleriPanel);
    			
    		} catch (Exception ex) {
    			ex.printStackTrace();
    		}
        }
		
	}
	
}
