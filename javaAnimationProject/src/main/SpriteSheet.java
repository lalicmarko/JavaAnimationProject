package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import rafgfxlib.*;

public class SpriteSheet{
	
	private BufferedImage sheet;
	private int frameW, frameH;
	private int sheetW, sheetH;
	private int offsetX = 0, offsetY = 0;
	
	public SpriteSheet(String imageName, int columns, int rows){
		
		sheet = Util.loadImage(imageName);
		
		if(imageName == null){
			sheet = null;
			System.out.println("Error loading sprite sheet!");
			return;
		}
		sheetW = columns;
		sheetH = rows;
		frameW = sheet.getWidth() / sheetW;
		frameH = sheet.getHeight() / sheetH;
	}
	
	public int getColumnCount() { return sheetW; }
	public int getRowCount() { return sheetH; }
	public int getFrameWidth() { return frameW; }
	public int getFrameHeight() { return frameH; }
	
	public void drawTo(Graphics g, int posX, int posY, int frameX, int frameY) {
		if(sheet == null) return;
		if(frameX < 0 || frameY < 0 || frameX >= sheetW || frameY >= sheetH) return;
		
		g.drawImage(sheet,
				posX - offsetX, posY - offsetY, 
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
	 * Kada je pozadina prazna, metoda getPixel() ne radi za pozadinu.|
	 * Da bi smo resili taj problem, pozadina ce imati konkretnu vrednst u rgb-u npr: 199,244,88
	 * i konkretno sve piksele sa tom bojom cemo setovati na TRASPARENT.
	 */
	public void doNegative(){
		WritableRaster source = this.sheet.getRaster();
		WritableRaster target = Util.createRaster(source.getWidth(), source.getHeight(), false);
		
		int rgb[] = new int[3];
		
		for(int y = 0; y < source.getHeight(); y++){
			for(int x = 0; x < source.getWidth(); x++){
				
				source.getPixel(x, y, rgb);
				
				rgb[0] = 255 - rgb[0];
				rgb[1] = 255 - rgb[1];
				rgb[2] = 255 - rgb[2];
				
				target.setPixel(x, y, rgb);
			}
		}
		this.sheet = Util.rasterToImage(target);
	}
	
	public void setOffsetX(int x) { offsetX = x; }
	public void setOffsetY(int y) { offsetY = y; }
	public int getOffsetX() { return offsetX; }
	public int getOffsetY() { return offsetY; }
}
