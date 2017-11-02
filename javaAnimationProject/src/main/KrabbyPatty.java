package main;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import rafgfxlib.Util;
/**
 * 
 * @author Demiurg
 * Types of KrabbyPatty: "Normal", "Gray", "Contrast", "Negative"
 *
 */
public class KrabbyPatty implements ImageModifications {

	public float posX;
	public float posY;
	public int id = 0;
	public boolean alive;
	public static int score = 0;
	String type = "Normal";

	public BufferedImage img = Util.loadImage("/res/patty.png");
	
	public KrabbyPatty() { 
		alive = true;
		double random = Math.random();
		if(random < 0.30) {
			double randomType = Math.random();
			if(randomType <= 0.33) {
				img = Util.loadImage("/res/bwPoint.png");
				setType("Gray");
			}
			else if (randomType > 0.33 && randomType < 0.66 ) {
				img = Util.loadImage("/res/negativePoint.png");
				setType("Negative");
			}
			else {
				img = Util.loadImage("/res/contrastPoint.png");
				setType("Contrast");
			}
		}
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
	public void setDead() {
		if(alive==true) {
			this.alive = false;	
			score++;
		}
	}
	public static int getScore() {
		return score;
	}
	public void setType(String type) {
		if(type.equals("Gray")) {
			this.type = "Gray";	
			doGrayscale();
		}else if(type.equals("Contrast")) {
			this.type = "Contrast";
			doContrast();
		}else {
			this.type = "Negative";
			doNegative();
		}
	}
	@Override
	public void doContrast() {
		WritableRaster source = img.getRaster();
		WritableRaster target = Util.createRaster(source.getWidth(), source.getHeight(), false);
		int rgb[] = new int[4];
		double contrast = 2;
		for(int y = 0; y < source.getHeight(); y++){
			for(int x = 0; x < source.getWidth(); x++){
				source.getPixel(x, y, rgb);
				 rgb[0] = 255 - rgb[0];
				 rgb[1] = 255 - rgb[1];
				 rgb[2] = 255 - rgb[2];
				target.setPixel(x, y, rgb);
			}
		}
		BufferedImage newImage = Util.rasterToImage(target);
		this.img = newImage;
	}
	@Override
	public void doNegative() {
		WritableRaster source = img.getRaster();
		WritableRaster target = Util.createRaster(source.getWidth(), source.getHeight(), false);
		int rgb[] = new int[4];
		for(int y = 0; y < source.getHeight(); y++){
			for(int x = 0; x < source.getWidth(); x++){
				source.getPixel(x, y, rgb);
				int i = (rgb[0] + rgb[1] + rgb[2]) / 3; // konvertovanje rgb u nijanse sive
				rgb[0] = i;
				rgb[1] = i;
				rgb[2] = i;
				target.setPixel(x, y, rgb);
			}
		}
		BufferedImage newImage = Util.rasterToImage(target);
		this.img = newImage;
		
	}
	@Override
	public void doGrayscale() {
		WritableRaster source = img.getRaster();
		WritableRaster target = Util.createRaster(source.getWidth(), source.getHeight(), false);
		int rgb[] = new int[4];
		for(int y = 0; y < source.getHeight(); y++){
			for(int x = 0; x < source.getWidth(); x++){
				source.getPixel(x, y, rgb);
				int i = (rgb[0] + rgb[1] + rgb[2]) / 3; // konvertovanje rgb u nijanse sive
				rgb[0] = i;
				rgb[1] = i;
				rgb[2] = i;
				target.setPixel(x, y, rgb);
			}
		}
		BufferedImage newImage = Util.rasterToImage(target);
		this.img = newImage;
	}
	public static int clamp(int value, int min, int max)
	{
		if(value < min) return min;
		if(value > max) return max;
		return value;
	}
	

	public String getType() {
		return type;
	}
	
	public static int saturate(int value)
	{
		return clamp(value, 0, 255);
	}
}
