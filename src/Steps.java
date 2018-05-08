import acm.graphics.GCompound;
import acm.graphics.GImage;

import java.util.Random;

public class Steps extends GCompound {

	private static String step = "Images/Background/Block.gif";
	private static final double stepWidth = 32;
	private static final int limitOfSteps = 8;

	public Steps(int amountOfSteps) {

		amountOfSteps = amountOfSteps;

		double dx = 0, dy = 0;

		reverse(amountOfSteps);

//		for (int i1 = 1; i1 < amountOfSteps * 2; i1++) {
//			for (int i2 = amountOfSteps; i2 != 0; i2--) {
//				add(
//						new GImage(step),
//						dx,
//						dy
//
//				);
//				dx += stepWidth;
//			}
//			amountOfSteps -= 1;
//			dy -= stepWidth;
//			dx = i1 * stepWidth;
//		}

	}

	private void reverse(int amountOfSteps) {
		double dx = 0, dy = 0;
		for (int i1 = amountOfSteps; i1 != 0; i1--) {
			for (int i2 = 0; i2 < amountOfSteps * 2; i2++) {
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
