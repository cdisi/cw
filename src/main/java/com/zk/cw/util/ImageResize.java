package com.zk.cw.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;



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
	
	
	public static byte[] reizeFromByte(byte[] aImg, int finalWidth, int finalHeight) {
	    
		BufferedImage originalImage = null;
		try {
			originalImage = ImageIO.read(new ByteArrayInputStream(aImg));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int originalWidth = originalImage.getWidth();
	    int originalHeight = originalImage.getHeight();

	    int newWidth;
	    int newHeight;
	    if (originalWidth == 0 || originalHeight == 0
	            || (originalWidth == finalWidth && originalHeight == finalHeight)) {
	        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

	        try {
				ImageIO.write(originalImage, "jpg", buffer);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        return buffer.toByteArray();
	    }

	    double aspectRatio = (double) originalWidth / (double) originalHeight;
	    double boundaryAspect = (double) finalWidth / (double) finalHeight;

	    if (aspectRatio > boundaryAspect) {
	        newWidth = finalWidth;
	        newHeight = (int) Math.round(newWidth / aspectRatio);
	    } else {
	        newHeight = finalHeight;
	        newWidth = (int) Math.round(aspectRatio * newHeight);
	    }

	    int xOffset = (finalWidth - newWidth) / 2;
	    int yOffset = (finalHeight - newHeight) / 2;


	    BufferedImage intermediateImage = new BufferedImage(finalWidth, finalHeight, BufferedImage.TYPE_INT_RGB);
	    Graphics2D gi = intermediateImage.createGraphics();
	    gi.setComposite(AlphaComposite.SrcOver);
	    gi.setColor(Color.WHITE);
	    gi.fillRect(0, 0, finalWidth, finalHeight);
	    gi.drawImage(originalImage, xOffset, yOffset, xOffset + newWidth, yOffset + newHeight, 0, 0, originalWidth, originalHeight, Color.WHITE, null);
	    gi.dispose();

	    //if image from db already had a transparent background, it becomes black when drawing it onto another
	    //even if we draw it onto a transparent image
	    //so we set it to a specific color, in this case white
	    //now we have to set that white background transparent
	    Image intermediateWithTransparentPixels = makeColorTransparent(intermediateImage, Color.WHITE);

	    //finalize the transparent image
	    BufferedImage finalImage = new BufferedImage(finalWidth, finalHeight, BufferedImage.TYPE_INT_RGB);
	    Graphics2D gf = finalImage.createGraphics();
	    gf.setComposite(AlphaComposite.SrcOver);
	    gf.setColor(new Color(255, 255, 255, 255));
	    gf.fillRect(0, 0, finalWidth, finalHeight);
	    gf.drawImage(intermediateWithTransparentPixels, 0, 0, finalWidth, finalHeight, new Color(0, 0, 0, 0), null);
	    gf.dispose();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        try {
			ImageIO.write(finalImage, "jpg", buffer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return buffer.toByteArray();
	}

	public static Image makeColorTransparent(Image im, final Color color) {
	    ImageFilter filter = new RGBImageFilter() {
	        // the color we are looking for... Alpha bits are set to opaque
	        public int markerRGB = color.getRGB() | 0xFF000000;

	        public final int filterRGB(int x, int y, int rgb) {
	            if ((rgb | 0xFF000000) == markerRGB) {
	                // Mark the alpha bits as zero - transparent
	                return 0x00FFFFFF & rgb;
	            } else {
	                // nothing to do
	                return rgb;
	            }
	        }
	    };

	    ImageProducer ip = new FilteredImageSource(im.getSource(), filter);
	    return Toolkit.getDefaultToolkit().createImage(ip);
	}	
	/*
	public static byte[] reizeFromByte(byte[] aImg, int aWidth, int aHeight) {
        try {
        	
        	
        	BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(aImg));

       	    BufferedImage image = Scalr.resize(originalImage,
        	                                       Method.ULTRA_QUALITY,
        	                                       Mode.AUTOMATIC,
        	                                       aWidth, aHeight,
        	                                       Scalr.OP_ANTIALIAS);
        	 
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            ImageIO.write(image, "jpg", buffer);

            return buffer.toByteArray();
            /*
        	
    		double h1 = aWidth * (img.getHeight()/(double)img.getWidth());
    	    double w2 = aHeight * (img.getWidth()/(double)img.getHeight());

    	    if( h1 <= aHeight) {
    	    	Imgproc.resize(input, output, new Size(dtsSizeWidth, h1) );
    	    	Image scaledImage = img.getScaledInstance(aWidth, aHeight, Image.SCALE_SMOOTH);
    	    } else {
    	        Imgproc.resize(input, output, new Size(w2, dtsSizeHeight) );
    	    	Image scaledImage = img.getScaledInstance(w2, aHeight, Image.SCALE_SMOOTH);
    	    }
        	
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
    	Imgcodecs.imencode(".jpg", output, matOfByte);
    	byte[] matOfByteArr = matOfByte.toArray();	
    	
	    return matOfByteArr;
	    
	}
	*/
}
