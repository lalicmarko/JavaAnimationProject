package main;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import rafgfxlib.GameFrame;
import rafgfxlib.Util;

public class MyGameFrame extends GameFrame {
	private int index = 0;
	private int score = 0;
	private int sizeX,sizeY;
	private BufferedImage imgIcon = null;
	private BufferedImage backround1 = null;
	private BufferedImage backround2 = null;
	private BufferedImage backround3 = null;
	
	private ArrayList<KrabbyPatty> pljeskavice = new ArrayList<KrabbyPatty>();
	
	private int skor = 0;

	private SpriteSheet bobSheet;
	private AnimatedEntity spongeBob;

	public MyGameFrame(String title, int sizeX, int sizeY, int updateRate) {
		super(title, sizeX, sizeY);
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		setHighQuality(true);

		
		imgIcon = Util.loadImage("/res/bobIcon.png");
		
		backround1 = Util.loadImage("/res/krusty krabs.png");
		backround2 = Util.loadImage("/res/kitchenKrusty.jpg");
		backround3 = Util.loadImage("/res/bikini bottom.jpg");
		
		bobSheet = new SpriteSheet("/res/spongeSpriteDemo.png", 5, 1);
		bobSheet.setOffsets(100, 100);

		spongeBob = new AnimatedEntity(bobSheet, sizeX / 2, sizeY / 2 + 150);
		
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
		AffineTransform transform = new AffineTransform();
		
		g.drawImage(backround3, 0, 0, null);
		spongeBob.draw(g);
		
		for(KrabbyPatty kp : pljeskavice) {
			transform.setToIdentity();
			transform.translate(kp.posX, kp.posY);
			if(kp.isAlive())
			g.drawImage(pljeskavice.get(kp.getId()).img, transform, null);
			
			if(kp.posX >= spongeBob.getPositionX() - 150 &&
				kp.posX < spongeBob.getPositionX() + 50) {
				if(kp.posY >= spongeBob.getPositionY() - 100) {
					kp.setAlive(false);
//					pljeskavice.remove(kp.getId());
					score++;

				}
			}
		}
		g.drawString("SCORE: - " + score, 20, 10);
	}

	@Override
	public void update() {

		spongeBob.play();
		spongeBob.move(5, 0);
		spongeBob.update();
		
		if(Math.random() < 0.06) {
			KrabbyPatty kp = new KrabbyPatty();
			kp.setId(index);
			kp.setPosY(0);
			double x = Math.random()*sizeX;
			kp.setPosX((float)x);
			pljeskavice.add(kp);
			index++;
		}
		
		for(KrabbyPatty kp : pljeskavice) {
			kp.posY += 3;
		}
		
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
