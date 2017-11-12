package main;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.buttons.ExitButton;
import main.buttons.ImageButton;
import main.buttons.PlayButton;
import rafgfxlib.GameFrame;
import rafgfxlib.Util;
import rafgfxlib.GameFrame.GFMouseButton;

public class MyGameFrame extends GameFrame {
	
	private boolean menu;
	
	private BufferedImage icon;
	private BufferedImage background;
	private BufferedImage text1;
	
	private Graphics2D graph;
	
	private ImageButton play;
	private ImageButton exit;
	
	private ArrayList<ImageButton> buttons;
	
	
	private int index = 0;
	private int indexP = 0;
	private int score = 0;
	private int sizeX, sizeY;
	private int startBackPosX = 0;
	
	private BufferedImage imgIcon = null;
	private BufferedImage backround1 = null;
	private BufferedImage backround2 = null;
	private BufferedImage backround3 = null;
	private BufferedImage back2 = null;
	private int flag = 0;
	private int flagCounter = 0;
	
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
		back2 = Util.loadImage("/res/back2.png");
		bobSheet = new SpriteSheet("/res/spongeSpriteDemo.png", 10, 2);
		bobSheet.setOffsets(300, 55);
		backupSheet = bobSheet;

		spongeBob = new AnimatedEntity(bobSheet, sizeX / 2 , sizeY / 2 + 200);
		
		setUpdateRate(updateRate);
		
		
		//Menu
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		
		menu = true;
		
		icon = Util.loadImage("/res/bobIcon.png");
		background = Util.loadImage("/res/menubackground.jpg");
		
		play = new PlayButton(Util.loadImage("/res/play.png"), Util.loadImage("/res/playHover.png"), 50, 100 ,100, 80, this);
		exit = new ExitButton(Util.loadImage("/res/exit.png"), Util.loadImage("/res/exitHover.png"), 50, 100 ,200, 80);
		
		buttons = new ArrayList<ImageButton>();
		
		buttons.add(play);
		buttons.add(exit);
		
		
		
		
		
		
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
		
		if (menu){
			AffineTransform transform = new AffineTransform();
			g.drawImage(background, 0, 0, null);
			
			for (ImageButton i : buttons){
				g.drawImage(i.getImage(), i.getX1(), i.getY1(), i.getX2(), i.getY2(), null);
			}
			
			graph = g;
		}
		
		else {
			AffineTransform transform = new AffineTransform();
			if(flag == 0)
			g.drawImage(backround1, startBackPosX, 0, null);
			else if(flag == 1) {
				g.drawImage(back2,  startBackPosX, 0, null);
			}
			
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
							if(!kp.getType().equals("Normal")) {
								if(kp.getType().equals("Contrast")) bobSheet.doContrast();
								else if(kp.getType().equals("Gray")) bobSheet.doGrayscale();
								else if(kp.getType().equals("Negative")) bobSheet.doNegative();
								else bobSheet.doPosterize();
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
				transform.translate(p.posX, p.posY);
				transform.rotate(p.angle);
				transform.translate(-180, -180);
				g.drawImage(patricks.get(p.getId()).img, transform, null);
				if(p.posX >= spongeBob.getPositionX()-300 && p.posX < spongeBob.getPositionX()-150) {
					if(p.posY >= spongeBob.getPositionY() - 100 && p.posY < spongeBob.getPositionY() + 200) {
						spongeBob.setMySheet(backupSheet);
					}
				}
			}
			g.drawString("SCORE: - [ " + score + " ]", 15, 20);
		}
		
		
		
	}

	@Override
	public void update() {
		/**
		 * @param flagCounter - broji koliko ce frejmova biti nameÅ¡ten background sa nekim izmenjenim detaljima na primer :
		 * znak krusty kraba je crveniji, zastavice imaju jaÄ�i kontrast itd.
		 * @param flag - sluzi kao flag kada da crta jednu a kada drugu pozadinu
		 */
		if (menu){
			int mouseX = 0;
			int mouseY = 0;
			try {
				mouseX = (int) getMousePosition().getX();
				mouseY = (int) getMousePosition().getY();
			} catch (NullPointerException ne){
				mouseX = 0;
				mouseY = 0;
			}
			
			catch (Exception e) {
				e.printStackTrace();
			}
			for (ImageButton i : buttons){
				i.setHover(i.isOnButton(mouseX, mouseY));
			}
		}
		else {
			flagCounter++;
			if(flagCounter >= 50 && flagCounter < 70) {
				flag = 1;
			}
			if(flagCounter >= 70) {
				flagCounter = 0;
				flag = 0;
			}
			
			/**
			 * kada dodje do leve ivice backgrounda, odnosno desnem sundjerova brzina postaje 0, dakle on se krece u mestu, da ne bi izasli iz okvira backgrounda.
			 */
			if(isKeyDown(KeyEvent.VK_LEFT)) {
				if(spongeBob.getPositionX() < 330) spongeBob.move(0, 0);
				else {
					spongeBob.move(-SPEED, 0);
					this.startBackPosX += 8;
//					System.out.println("sponge x="+spongeBob.getPositionX()+", y="+spongeBob.getPositionY());
				}
			}
			else if(isKeyDown(KeyEvent.VK_RIGHT)) {
				if(spongeBob.getPositionX() > 750) spongeBob.move(0, 0);
				else {
					spongeBob.move(SPEED, 0);
					this.startBackPosX -= 8;
					System.out.println("sponge x="+spongeBob.getPositionX()+", y="+spongeBob.getPositionY());
				}
			}
			
			spongeBob.update();
			/**
			 * U svakom frejmu pljeskavica ima 1,5% verovatnoce da se spawnuje na poziciji y = 0, x = random od sirine ekrana.
			 * @class KrabbyPatty u svom konstruktoru dalje ima logiku za odredjivanje tipa pljeskavice:
			 * @param "Normal" - bez efekata
			 * @param "Contrast" - efekat doContrast()
			 * @param "Negative" - efekat doNegative()
			 * @param "Gray"  - efekat doGrayscale()
			 * 
			 * Na slican nacin se generise i patrik sa verovatnocom od 0.6% - izvini PatriÄ�e
			 */
			if(Math.random() < 0.015) {
				KrabbyPatty kp = new KrabbyPatty();
				kp.setId(index);
				kp.setPosY(0);
				double x = Math.random()*(sizeX - 50) + 20 ;
				kp.setPosX((float)x);
				pljeskavice.add(kp);
				index++;
			}
			if(Math.random() < 0.006) {
				PatrickSalvation p = new PatrickSalvation();
				p.angle = (float)(Math.random() * Math.PI * 2.0);
				p.rot = (float)(Math.random() - 0.5) * 0.3f;
				
				if(Math.random() < 0.5){
					p.setType(0);
				}
				else{
					p.setType(1);
				}
				p.setId(indexP);
				p.setPosY(-200);
				double x = Math.random()*sizeX;
				p.setPosX((float)x);
				patricks.add(p);
				indexP++;
			}
			for(PatrickSalvation p : patricks) {
				p.posY += 5;
				if(p.getType() == 1){
					p.angle += p.rot;
					p.rot *= 0.99f;
				}
				else{
					p.angle -= p.rot;
					p.rot *= 0.99f;
				}
				
			}
			for(KrabbyPatty kp : pljeskavice) {
				kp.posY += 3;
			}
		}
		
	}

	@Override
	public void handleMouseDown(int x, int y, GFMouseButton button) {
		// TODO Auto-generated method stub

		if (menu){
			int mouseX = 0;
			int mouseY = 0;
			try {
				mouseX = (int) getMousePosition().getX();
				mouseY = (int) getMousePosition().getY();
			} catch (NullPointerException ne){
				mouseX = 0;
				mouseY = 0;
			}
			
			if (button == GFMouseButton.Left){

				for (ImageButton i : buttons){
					if (i.isOnButton(mouseX, mouseY)){
						i.buttonAction();
					}
				}
				
			}
		}
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
	
	public boolean isMenu() {
		return menu;
	}
	public void setMenu(boolean menu) {
		this.menu = menu;
	}

}
