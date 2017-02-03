package com.zk.cw.util;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;


public class ImageResize {
	
	public static byte[] reize(File file, int width, int height) {
        try {
            BufferedImage img = ImageIO.read(file);
            if(height == 0) {
                height = (width * img.getHeight())/ img.getWidth(); 
            }
            if(width == 0) {
                width = (height * img.getWidth())/ img.getHeight();
            }
            Image scaledImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            imageBuff.getGraphics().drawImage(scaledImage, 0, 0, new Color(0,0,0), null);

            ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            ImageIO.write(imageBuff, "jpg", buffer);

            return buffer.toByteArray();
        } catch (IOException e) {
            System.out.println(e.getMessage());            
        }
        return null;
    }
	
	public static byte[] reizeFromUrl(URL url, int width, int height) {
        try {
            BufferedImage img = ImageIO.read(url);
            if(height == 0) {
                height = (width * img.getHeight())/ img.getWidth(); 
            }
            if(width == 0) {
                width = (height * img.getWidth())/ img.getHeight();
            }
            Image scaledImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            imageBuff.getGraphics().drawImage(scaledImage, 0, 0, new Color(0,0,0), null);

            ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            ImageIO.write(imageBuff, "jpg", buffer);

            return buffer.toByteArray();
        } catch (IOException e) {
            System.out.println(e.getMessage());            
        }
        return null;
    }
	
	public static byte[] reizeFromByte(byte[] aImg, int width, int height) {
        try {
        	InputStream in = new ByteArrayInputStream(aImg);
        	BufferedImage img = ImageIO.read(in);
            if(height == 0) {
                height = (width * img.getHeight())/ img.getWidth(); 
            }
            if(width == 0) {
                width = (height * img.getWidth())/ img.getHeight();
            }
            Image scaledImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            imageBuff.getGraphics().drawImage(scaledImage, 0, 0, new Color(0,0,0), null);

            ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            ImageIO.write(imageBuff, "jpg", buffer);

            return buffer.toByteArray();
        } catch (IOException e) {
            System.out.println(e.getMessage());            
        }
        return null;
    }
	
	public static byte[] resizeKeepAspectRatio(byte[] img, int dtsSizeWidth, int dtsSizeHeight)
	{
		System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
		Mat output = new Mat();

		Mat input = Imgcodecs.imdecode(new MatOfByte(img), Imgcodecs.CV_LOAD_IMAGE_UNCHANGED);
		
		double h1 = dtsSizeWidth * (input.rows()/(double)input.cols());
	    double w2 = dtsSizeHeight * (input.cols()/(double)input.rows());
	    if( h1 <= dtsSizeHeight) {
	    	Imgproc.resize(input, output, new Size(dtsSizeWidth, h1) );
	    } else {
	        Imgproc.resize(input, output, new Size(w2, dtsSizeHeight) );
	    }

	    int top = (dtsSizeHeight-output.rows()) / 2;
	    int down = (dtsSizeHeight-output.rows()+1) / 2;
	    int left = (dtsSizeWidth - output.cols()) / 2;
	    int right = (dtsSizeWidth - output.cols()+1) / 2;
		 
	    Core.copyMakeBorder(output, output, top, down, left, right, Core.BORDER_CONSTANT, new Scalar(255, 255, 255) );
    	
	    MatOfByte matOfByte = new MatOfByte();   	
    	Imgcodecs.imencode(".jpg", input, matOfByte);
    	byte[] matOfByteArr = matOfByte.toArray();	
    	
	    return matOfByteArr;
	    
	}
	
}
