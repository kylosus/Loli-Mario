import acm.graphics.GCompound;
import acm.graphics.GImage;

import java.util.Random;

public class Steps extends GCompound {

	private static String step = "Images/Background/Block.gif";
	private static final double stepWidth = 32;
	private static final int limitOfSteps = 8;
	private int amountOfSteps;

	public Steps() {

		amountOfSteps = new Random().nextInt(limitOfSteps * 2) + 1;

		double dx = 0, dy = 0;
		for (int i1 = 1; i1 < amountOfSteps; i1++) {
			for (int i2 = amountOfSteps; i2 != 0; i2--) {
				add(
						new GImage(step),
						dx,
						dy

				);
				dx += stepWidth;
			}
			amountOfSteps -= 1;
			dy -= stepWidth;
			dx = i1 * stepWidth;
		}
	}

}
