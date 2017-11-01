package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import rafgfxlib.*;

public class SpriteSheet {

	private BufferedImage sheet;
	private WritableRaster raster;
	private int frameW, frameH;
	private int sheetW, sheetH;
	private int offsetX = 0, offsetY = 0;

	public SpriteSheet(String imageName, int columns, int rows) {

		this.sheet = Util.loadImage(imageName);
	//	this.raster = this.sheet.getRaster();
		if (imageName == null) {
			sheet = null;
			System.out.println("Error loading sprite sheet!");
			return;
		}
		sheetW = columns;
		sheetH = rows;
		frameW = sheet.getWidth() / sheetW;
		frameH = sheet.getHeight() / sheetH;
	}
	
	public int getColumnCount() {
		return sheetW;
	}
	
	public int getRowCount() {
		return sheetH;
	}
	
	public int getFrameWidth() {
		return frameW;
	}

	public int getFrameHeight() {
		return frameH;
	}
	
	public void drawTo(Graphics g, int posX, int posY, int frameX, int frameY) {
		
		if (sheet == null) return;
		if (frameX < 0 || frameY < 0 || frameX >= sheetW || frameY >= sheetH) return;
		
		g.drawImage(sheet, posX - offsetX, posY - offsetY,
				posX - offsetX + frameW, posY - offsetY + frameH,
				frameX * frameW, frameY * frameH,
				frameX * frameW + frameW, frameY * frameH + frameH,
				null);
	}

	public void setOffsets(int x, int y) {
		offsetX = x;
		offsetY = y;
	}

	/**
	 * Kada je pozadina prazna, metoda getPixel() ne radi za pozadinu.| Da bi smo
	 * resili taj problem, pozadina ce imati konkretnu vrednst u rgb-u npr:
	 * 199,244,88 i konkretno za sve piksele sa tom bojom cemo setovati alfa kanal na 1.
	 */
//	public void doNegative() {
//
//		WritableRaster target = Util.createRaster(sheet.getWidth(), sheet.getHeight(), true);
//		
//		int rgb[] = new int[3];
//		
//		float opacity = 0.0f;
//
//		for (int y = 0; y < sheet.getHeight(); y++) {
//			for (int x = 0; x < sheet.getWidth(); x++) {
//				try {
//					this.raster.getPixel(x, y, rgb);
////					System.out.println("ista");
//					
//						rgb[0] = 255 - rgb[0];
//						rgb[1] = 255 - rgb[1];
//						rgb[2] = 255 - rgb[2];
//					
//						target.setPixel(x, y, rgb);
//				} catch (Exception e) {
////					System.out.println("bla");
//					e.printStackTrace();
//				}
//				
//			
//			
//			}
//		}
//		this.sheet = Util.rasterToImage(target);
//	}

	public void setOffsetX(int x) {
		offsetX = x;
	}

	public void setOffsetY(int y) {
		offsetY = y;
	}

	public int getOffsetX() {
		return offsetX;
	}

	public int getOffsetY() {
		return offsetY;
	}
}
