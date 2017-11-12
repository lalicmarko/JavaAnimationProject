package main.buttons;

import java.awt.image.BufferedImage;

public class ExitButton extends ImageButton {

	public ExitButton(BufferedImage image, BufferedImage hoverImage, int x1, int x2, int y1, int y2) {
		super(image, hoverImage, x1, x2, y1, y2);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void buttonAction(){
		System.exit(1);
	}

}
