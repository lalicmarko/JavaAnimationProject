package main.buttons;

import java.awt.image.BufferedImage;

import main.MyGameFrame;
import rafgfxlib.GameFrame;

public class PlayButton extends ImageButton {
	
	MyGameFrame gf;

	public PlayButton(BufferedImage image, BufferedImage hoverImage, int x1, int x2, int y1, int y2, MyGameFrame gf	) {
		super(image, hoverImage, x1, x2, y1, y2);
		this.gf = gf;
	}
	
	@Override
	public void buttonAction(){
		gf.setMenu(false);
	}

	
	
}
