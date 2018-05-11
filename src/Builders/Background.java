package Builders;

import Builders.Core;
import acm.graphics.*;

import java.util.ArrayList;
import java.util.Random;

public class Background extends GCompound {
	public static final int AMOUNT_OF_SQUARES = 50;
	public static final int GROUND_SQUARE_WIDTH = 32;
	public static final double GROUND_SQUARE_RATIO = 0.75;
	public static final int AMOUNT_OF_CLOUDS = 15;
	private static double WIDTH;
	private static double HEIGHT;
	public static GImage gameLostScreen = new GImage("Images/DontGiveUp.gif");
	private static ArrayList<GImage> Clouds = new ArrayList<>();
	private static ArrayList<String> Bushes = new ArrayList<>();
	private static final double bushHeight = 32;

	public Background(double WIDTH, double HEIGHT) {
		Clouds.add(Core.Cloud_single);
		Clouds.add(Core.Cloud_double);
		Clouds.add(Core.Cloud_triple);
		Bushes.add("Images/Bush - Single.gif");
		Bushes.add("Images/Bush - Double.gif");
		Bushes.add("Images/Bush - Triple.gif");
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;

//		for (int i = 0; i <= AMOUNT_OF_SQUARES; i++) {
//			add(new GImage(
//					"Images/Builders.Background/Ground2.gif",
//					i * BRICK_WIDTH,
//					HEIGHT * GROUND_SQUARE_RATIO
//			));
//		}

		/* Adds clouds */
		Random random = new Random();
		int randomY;
		for (int i = 0; i < AMOUNT_OF_CLOUDS; i++) {
			randomY = random.nextInt((int) (HEIGHT * GROUND_SQUARE_RATIO - 120));
			add(
					new GImage(Clouds.get(random.nextInt(3)).getImage()),
					i * 280,
					randomY

			);
		}

		/* Add bush */
		for (int i = 1; i < AMOUNT_OF_CLOUDS; i++) {
			add(
					new GImage(Bushes.get(random.nextInt(3))),
					random.nextInt(9600),
					Foreground.REFERENCE_POINT - bushHeight

			);
		}
	}


	public static double getStartY() {
		return HEIGHT * GROUND_SQUARE_RATIO;
	}
}