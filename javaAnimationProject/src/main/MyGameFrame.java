package main;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import rafgfxlib.GameFrame;
import rafgfxlib.Util;

public class MyGameFrame extends GameFrame {
	
	private int index = 0;
	private int indexP = 0;
	private int score = 0;
	private int sizeX, sizeY;
	
	private BufferedImage imgIcon = null;
	private BufferedImage backround1 = null;
	private BufferedImage backround2 = null;
	private BufferedImage backround3 = null;
	
	private ArrayList<KrabbyPatty> pljeskavice = new ArrayList<KrabbyPatty>();
	private ArrayList<PatrickSalvation> patricks = new ArrayList<PatrickSalvation>();
	private int skor = 0;
	
	private SpriteSheet bobSheet;
	private SpriteSheet backupSheet;
	private AnimatedEntity spongeBob;
	
	private static final int ANIM_LEFT = 1;
	private static final int ANIM_RIGHT = 0;
	
	private static final int SPEED = 6;

	public MyGameFrame(String title, int sizeX, int sizeY, int updateRate) {
		
		super(title, sizeX, sizeY);
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		setHighQuality(true);
		
		imgIcon = Util.loadImage("/res/bobIcon.png");
		
		backround1 = Util.loadImage("/res/krusty krabs.png");
		backround2 = Util.loadImage("/res/kitchenKrusty.jpg");
		backround3 = Util.loadImage("/res/bikini bottom.jpg");
		
		bobSheet = new SpriteSheet("/res/spongeSpriteDemo.png", 10, 2);
		bobSheet.setOffsets(300, 55);
		backupSheet = bobSheet;

		spongeBob = new AnimatedEntity(bobSheet, sizeX / 2 , sizeY / 2 + 200);
		
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
		
		g.drawImage(backround1, 0, 0, null);
		
		spongeBob.draw(g);
		
		for(KrabbyPatty kp : pljeskavice) {
			transform.setToIdentity();
			transform.translate(kp.posX, kp.posY);
			if(kp.isAlive())
			g.drawImage(pljeskavice.get(kp.getId()).img, transform, null);
			
			if(kp.posX >= spongeBob.getPositionX()-300 && kp.posX < spongeBob.getPositionX()-150) {
				if(kp.posY >= spongeBob.getPositionY() - 100 && kp.posY < spongeBob.getPositionY() + 200) {
					if(kp.isAlive()){
						kp.setDead();
						System.out.println("Pojeo sam " + kp.getType());
						if(!kp.getType().equals("Normal")) {
							if(kp.getType().equals("Contrast")) bobSheet.doContrast();
							else if(kp.getType().equals("Gray")) bobSheet.doGrayscale();
							else bobSheet.doNegative();
						}else {
							bobSheet.setBackup();
						}
					}
					score = KrabbyPatty.getScore();
				}
			}
		}
		for(PatrickSalvation p : patricks) {
			transform.setToIdentity();
			transform.rotate(Math.cos(-52));
			transform.rotate(Math.cos(50));
//			transform.rotate(Math.cos(180));
			transform.translate(p.posX, p.posY);
			g.drawImage(patricks.get(p.getId()).img, transform, null);
			if(p.posX >= spongeBob.getPositionX()-300 && p.posX < spongeBob.getPositionX()-150) {
				if(p.posY >= spongeBob.getPositionY() - 100 && p.posY < spongeBob.getPositionY() + 200) {
					spongeBob.setMySheet(backupSheet);
				}
			}
		}
		g.drawString("SCORE: - [ " + score + " ]", 15, 20);
	}

	@Override
	public void update() {
		
		if(isKeyDown(KeyEvent.VK_LEFT))
			spongeBob.move(-SPEED, 0);
		else if(isKeyDown(KeyEvent.VK_RIGHT))
			spongeBob.move(SPEED, 0);
		
		spongeBob.update();
		
		if(Math.random() < 0.04) {
			KrabbyPatty kp = new KrabbyPatty();
			kp.setId(index);
			kp.setPosY(0);
			double x = Math.random()*sizeX;
			kp.setPosX((float)x);
			pljeskavice.add(kp);
			index++;
		}
		if(Math.random() < 0.02) {
			PatrickSalvation p = new PatrickSalvation();
			p.setId(indexP);
			p.setPosY(0);
			double x = Math.random()*sizeX;
			p.setPosX((float)x);
			patricks.add(p);
			indexP++;
		}
		for(PatrickSalvation p : patricks) {
			p.posY += 4;
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
		
		if(keyCode == KeyEvent.VK_LEFT)
		{
			spongeBob.setAnimation(ANIM_LEFT);
			spongeBob.play();
		}
		else if(keyCode == KeyEvent.VK_RIGHT)
		{
			spongeBob.setAnimation(ANIM_RIGHT);
			spongeBob.play();
		}

	}

	@Override
	public void handleKeyUp(int keyCode) {
		
		if(keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT)
		{
			spongeBob.stop();
			spongeBob.setFrame(5);
		}

	}

}
