package main;

import java.awt.image.BufferedImage;

import rafgfxlib.Util;

public class KrabbyPatty {

	public float posX;
	public float posY;
	public int id = 0;
	public boolean alive;
	public BufferedImage img = Util.loadImage("/res/patty.png");
	
	public KrabbyPatty() { alive = true;
	}
	public void setPosX(float posX) {
		this.posX = posX;
	}
	public void setPosY(float posY) {
		this.posY = posY;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isAlive() {
		return alive;
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
}
