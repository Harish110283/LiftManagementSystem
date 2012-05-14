package com.lms.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.WritableRaster;
import java.util.Hashtable;

public class LiftImage extends Image{
	
	BufferedImage image;

	public LiftImage(BufferedImage image) {
		this.image=image;
	}
	

	

	@Override
	public Graphics getGraphics() {
		// TODO Auto-generated method stub
		return image.getGraphics();
	}


	@Override
	public int getHeight(ImageObserver observer) {
		// TODO Auto-generated method stub
		return image.getHeight();
	}


	@Override
	public Object getProperty(String name, ImageObserver observer) {
		// TODO Auto-generated method stub
		return image.getProperty(name, observer);
	}


	@Override
	public ImageProducer getSource() {
		// TODO Auto-generated method stub
		return image.getSource();
	}


	@Override
	public int getWidth(ImageObserver observer) {
		// TODO Auto-generated method stub
		return image.getHeight();
	}
	


}
