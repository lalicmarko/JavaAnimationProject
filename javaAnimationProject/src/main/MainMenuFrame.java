package main;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import main.buttons.ImageButton;
import main.buttons.PlayButton;
import rafgfxlib.GameFrame;
import rafgfxlib.Util;

public class MainMenuFrame extends GameFrame{

	private BufferedImage icon;
	private BufferedImage background;
	private BufferedImage text1;
	private GameFrame gf;
	
	private ImageButton play;
	
	private int sizeX;
	private int sizeY;
	
	
	public MainMenuFrame(String title, int sizeX, int sizeY, GameFrame gf) {
		super(title, sizeX, sizeY);
		
		this.gf = gf;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		
		icon = Util.loadImage("/res/bobIcon.png");
		background = Util.loadImage("/res/krusty krabs.png");
		
		play = new PlayButton(Util.loadImage("/res/patty.png"), Util.loadImage("/res/krusty krabs.png"), 50, 150 ,50, 200);
		
		startThread();
	}

	@Override
	public void handleWindowInit() {
		// TODO Auto-generated method stub
		setIcon(icon);
		
	}

	@Override
	public void handleWindowDestroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics2D g, int sw, int sh) {
		
		AffineTransform transform = new AffineTransform();
		g.drawImage(background, 0, 0, null);
		g.drawImage(play.getImage(), play.getX1(), play.getX2(), play.getY1(), play.getY2(), null);
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
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
		if (play.isOnButton(mouseX, mouseY)){
			play.buttonAction();
		}
		else{
			
		}
		
	}

	@Override
	public void handleMouseDown(int x, int y, GFMouseButton button) {
		// TODO Auto-generated method stub
		
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

//			if (play.isOnButton(mouseX, mouseY)){
				System.out.println(mouseX +" "+ mouseY);
//			}
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleKeyUp(int keyCode) {
		// TODO Auto-generated method stub
		
	}
	
	

}
