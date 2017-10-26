package main;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import rafgfxlib.GameFrame;
import rafgfxlib.Util;

public class MyGameFrame extends GameFrame{
	private BufferedImage imgIcon = null;
	private BufferedImage imgBob = null;
	

	
	public MyGameFrame(String title, int sizeX, int sizeY) {
		super(title, sizeX, sizeY);
		setHighQuality(true);
		
		imgIcon = Util.loadImage("/res/sponge.png");
		
	
		setUpdateRate(60);
		startThread();
		
	}

	@Override
	public void handleWindowInit() {
		setIcon(imgIcon);
		
	}

	@Override
	public void handleWindowDestroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics2D g, int sw, int sh) {
		// TODO Auto-generated method stub
//		imgBob = Util.loadImage("/red/spongebobSmall.png");
		g.drawImage(imgIcon, 0, 0, null);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleMouseDown(int x, int y, GFMouseButton button) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleMouseUp(int x, int y, GFMouseButton button) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleMouseMove(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleKeyDown(int keyCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleKeyUp(int keyCode) {
		// TODO Auto-generated method stub
		
	}

}
