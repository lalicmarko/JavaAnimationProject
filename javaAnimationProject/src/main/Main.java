package main;

import rafgfxlib.GameFrame;

/**
 * Repository url: https://github.com/lalicmarko/JavaAnimationProject
 * @author Demiurg
 *
 */
public class Main {

	public static void main(String[] args) {
		/**
		 * Konstruktor prima:
		 * @String title
		 * @int width
		 * @int height
		 * @int updateRate
		 */
		
		GameFrame gf = new MyGameFrame("Sponge-Anime", 650, 650, 30);
		GameFrame mf = new MainMenuFrame("Sponge-Menu", 650, 650, gf);

		mf.initGameWindow();
		
	}
}
