package Builders;

import acm.graphics.GCompound;
import acm.graphics.GImage;
import java.util.ArrayList;
import java.util.Random;

public class Background extends GCompound {
	private static final double GROUND_SQUARE_RATIO = 0.75;
	private static final int AMOUNT_OF_CLOUDS = 15;
	private double HEIGHT;
	public static GImage gameLostScreen = new GImage("Images/DontGiveUp.gif");
	private static ArrayList<GImage> Clouds = new ArrayList<>();
	private static ArrayList<String> Bushes = new ArrayList<>();
	private static final double bushHeight = 32;

	public Background(double HEIGHT) {

		// For randomizing Cloud and Bush locations
		Clouds.add(Core.Cloud_single);
		Clouds.add(Core.Cloud_double);
		Clouds.add(Core.Cloud_triple);
		Bushes.add("Images/Bush - Single.gif");
		Bushes.add("Images/Bush - Double.gif");
		Bushes.add("Images/Bush - Triple.gif");
		this.HEIGHT = HEIGHT;

		// Ads clouds
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

		// Adds bush
		for (int i = 1; i < AMOUNT_OF_CLOUDS; i++) {
			add(
					new GImage(Bushes.get(random.nextInt(3))),
					random.nextInt(9600),
					Foreground.REFERENCE_POINT - bushHeight

			);
		}
	}

	@Deprecated
	public double getStartY() {
		return HEIGHT * GROUND_SQUARE_RATIO;
	}
}