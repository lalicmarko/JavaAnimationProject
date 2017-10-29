package main;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import rafgfxlib.GameFrame;
import rafgfxlib.Util;


public class MyGameFrame extends GameFrame{
	
	private BufferedImage imgIcon = null;
	private BufferedImage backround1 = null;
	private BufferedImage backround2 = null;
	private BufferedImage backround3 = null; 
	
	private SpriteSheet bobSheet;
	private AnimatedEntity spongeBob;
	
	
	public MyGameFrame(String title, int sizeX, int sizeY, int updateRate) {
		super(title, sizeX, sizeY);
		setHighQuality(true);
		
		imgIcon = Util.loadImage("/res/sponge.png");
		backround1 = Util.loadImage("/res/krusty krabs.png");
		backround2 = Util.loadImage("/res/kitchenKrusty.jpg");
		backround3 = Util.loadImage("/res/bikini bottom.jpg");
		
		
		bobSheet = new SpriteSheet("/res/bobSaPozadinom.jpg", 5, 1);
		bobSheet.setOffsets(100, 100);
		bobSheet.doNegative();
		spongeBob = new AnimatedEntity(bobSheet, sizeX/2, sizeY/2 + 150);
		
		setUpdateRate(updateRate);
		
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
		g.drawImage(backround3,0,0,null);
		spongeBob.draw(g);
//		g.drawImage(imgIcon, 0, 0, null);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

		spongeBob.play();
		spongeBob.move(5,0);
		spongeBob.update();
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
