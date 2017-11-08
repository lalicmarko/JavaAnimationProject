package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import rafgfxlib.*;

public class SpriteSheet implements ImageModifications {

	private BufferedImage sheet;
	private BufferedImage backupSheet;
	private int frameW, frameH;
	private int sheetW, sheetH;
	private int offsetX = 0, offsetY = 0;

	public SpriteSheet(String imageName, int columns, int rows) {

		this.sheet = Util.loadImage(imageName);
		backupSheet = sheet;
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

	@Override
	public void doContrast() {
		WritableRaster source = sheet.getRaster();
		WritableRaster target = Util.createRaster(source.getWidth(), source.getHeight(), true);
		int rgb[] = new int[4];
		double contrast = 2;
		for(int y = 0; y < source.getHeight(); y++){
			for(int x = 0; x < source.getWidth(); x++){
				source.getPixel(x, y, rgb);
				rgb[0] = saturate((int)((rgb[0] - 128) * contrast + 128));
				rgb[1] = saturate((int)((rgb[1] - 128) * contrast + 128));
				rgb[2] = saturate((int)((rgb[2] - 128) * contrast + 128));
				target.setPixel(x, y, rgb);
			}
		}
		BufferedImage newImage = Util.rasterToImage(target);
		this.sheet = newImage;
	}
	
	public void doNegative() {
		WritableRaster source = sheet.getRaster();
		WritableRaster target = Util.createRaster(source.getWidth(), source.getHeight(), true);
		int rgb[] = new int[4];
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
		this.sheet = newImage;
		
	}
	@Override
	public void doGrayscale() {
		WritableRaster source = sheet.getRaster();
		WritableRaster target = Util.createRaster(source.getWidth(), source.getHeight(), true);
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
		this.sheet = newImage;
	}
	public static int clamp(int value, int min, int max)
	{
		if(value < min) return min;
		if(value > max) return max;
		return value;
	}
	public void setBackup() {
		this.sheet = backupSheet;
	}
	
	public static int saturate(int value)
	{
		return clamp(value, 0, 255);
	}
}
