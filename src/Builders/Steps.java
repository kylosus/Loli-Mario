package Builders;

import acm.graphics.GCompound;
import acm.graphics.GImage;

class Steps extends GCompound {

	private static String step = Core.Step;
	private static final double stepWidth = 32;

	/**
	 *
	 * @param amountOfSteps Positive values face rightward, negative values face leftward
	 */
	Steps(int amountOfSteps) {

		if (amountOfSteps < 0) {
			reverse(-amountOfSteps);
		} else {
			construct(amountOfSteps);
		}
	}

	private void construct(int amountOfSteps) {
		double dx, dy = 0;
		int amountOfSteps2BecauseImLazyChangeThisNameLater = amountOfSteps;
		for (int i1 = 0; i1 < amountOfSteps; i1++) {
			dx = i1 * stepWidth;
			for (int i2 = 0; i2 < amountOfSteps2BecauseImLazyChangeThisNameLater; i2++) {
				add(new GImage(step), dx, dy);
				dx += stepWidth;
			}
			dy -= stepWidth;
			amountOfSteps2BecauseImLazyChangeThisNameLater--;
		}
	}

	private void reverse(int amountOfSteps) {
		double dx, dy = 0;
		int amountOfSteps2BecauseImLazyChangeThisNameLater = amountOfSteps;
		for (int i1 = 0; i1 < amountOfSteps; i1++) {
			dx = 0;
			for (int i2 = 0; i2 < amountOfSteps2BecauseImLazyChangeThisNameLater; i2++) {
				add(new GImage(step), dx, dy);
				dx += stepWidth;
			}
			dy -= stepWidth;
			amountOfSteps2BecauseImLazyChangeThisNameLater--;
		}
	}
}