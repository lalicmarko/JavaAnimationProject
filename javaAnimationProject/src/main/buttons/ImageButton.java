package main.buttons;

import java.awt.image.BufferedImage;

public class ImageButton implements ButtonAction {
	
	BufferedImage image;
	BufferedImage hoverImage;
	int x1, x2, y1, y2;
	
	public ImageButton(BufferedImage image, BufferedImage hoverImage, int x1, int x2 ,int y1, int y2) {
		this.image = image;
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		this.hoverImage = hoverImage;
	}

	@Override
	public void buttonAction() {
		// TODO Auto-generated method stub
		
	}

	public boolean isOnButton(int x, int y){
		
		if (x > x1 && x < x2 && y > y1 && y < y2){
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

	public BufferedImage getHoverImage() {
		return hoverImage;
	}
	
	public void setHoverImage(BufferedImage hoverImage) {
		this.hoverImage = hoverImage;
	}
	
}
