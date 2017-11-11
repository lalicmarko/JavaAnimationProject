package main;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import rafgfxlib.GameFrame;
import rafgfxlib.Util;

public class MainMenuFrame extends GameFrame{

	private BufferedImage icon;
	private BufferedImage background;
	private BufferedImage text1;
	
	private int sizeX;
	private int sizeY;
	
	
	public MainMenuFrame(String title, int sizeX, int sizeY) {
		super(title, sizeX, sizeY);
		
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		
		icon = Util.loadImage("/res/bobIcon.png");
		background = Util.loadImage("/res/krusty krabs.png");
		
		startThread();
	}

	@Override
	public void handleWindowInit() {
		// TODO Auto-generated method stub
		setIcon(icon);
		
	}

	@Override
	public void handleWindowDestroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics2D g, int sw, int sh) {
		
		AffineTransform transform = new AffineTransform();
		g.drawImage(background, 0, 0, null);
		
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
