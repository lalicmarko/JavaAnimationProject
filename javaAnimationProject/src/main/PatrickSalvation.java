package main;

import java.awt.image.BufferedImage;

import rafgfxlib.Util;

public class PatrickSalvation implements ImageModifications{

	public float posX;
	public float posY;
	public int id = 0;
	public boolean alive;
	public BufferedImage img = Util.loadImage("/res/Patrick_Star.png");
	
	public PatrickSalvation() {
		
	}
	
	@Override
	public void doContrast() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doNegative() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doGrayscale() {
		// TODO Auto-generated method stub
		
	}
	public int getId() {
		return id;
	}
	public float getPosX() {
		return posX;
	}
	public float getPosY() {
		return posY;
	}
	public void setPosX(float posX) {
		this.posX = posX;
	}
	public void setPosY(float posY) {
		this.posY = posY;
	}

}
