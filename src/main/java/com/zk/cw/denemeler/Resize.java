package com.zk.cw.denemeler;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class Resize {
	
	public static void main(String[] args) {
		try {
	         int zoomingFactor = 2;
	         System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
	         
	         Mat source = Highgui.imread("image.jpg", Highgui.CV_LOAD_IMAGE_GRAYSCALE);
	         Mat destination = new Mat(source.rows() * zoomingFactor, source.cols()*  zoomingFactor,source.type());  
	         
	         Imgproc.resize(source, destination, destination.size(),  zoomingFactor,zoomingFactor,Imgproc.INTER_NEAREST);
	         Highgui.imwrite("zoomed.jpg", destination);
	         
	      } catch (Exception e) {
	         System.out.println("Error: "+e.getMessage());
	      }

	}

}
