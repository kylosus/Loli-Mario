import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.graphics.GPoint;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Foreground extends GCompound {
	public static final int AMOUNT_OF_SQUARES = 300;
	public static final int SQUARE_WIDTH = 32;
	public static final double GROUND_SQUARE_RATIO = 0.75;
	public static double REFERENCE_POINT;
	private static Random random = new Random();
//	private static ArrayList<Double> GroundGaps = new ArrayList<>(){{
//			add(2240),
//			add(2784),
//			add(5248)
//	}};

	public Foreground(double WIDTH, double HEIGHT) {

		REFERENCE_POINT = HEIGHT - (2 * SQUARE_WIDTH);

		add(Core.SakuraTree);

		/* Upper floor */
		double x = 200;
		for (int i = 0; i <= 10; i++) {
			add(new GImage(
					"Images/Background/Ground2.gif",
					x,
					250
			));
			x += SQUARE_WIDTH;
		}

		add(new QuestionBlock(), 300, 300);

		/* Ground */
		for (int i1 = 0; i1 < 2; i1++) {
			for (int i2 = 0; i2 <= AMOUNT_OF_SQUARES; i2++) {
//				if (i2 * SQUARE_WIDTH : GroundGaps) {
					add(new GImage(
							"Images/Background/Ground2.gif",
							i2 * SQUARE_WIDTH,
							REFERENCE_POINT
					));
//				}
			}
			REFERENCE_POINT += SQUARE_WIDTH;
		}
		REFERENCE_POINT -= SQUARE_WIDTH * 2;

		/* Steps */
		add(
				new Steps(),
				random.nextInt(9400) + 1000,
				REFERENCE_POINT - SQUARE_WIDTH
		);
		add(
				new Steps(),
				random.nextInt(9400) + 1000,
				REFERENCE_POINT - SQUARE_WIDTH
		);
		add(
				new Steps(),
				random.nextInt(9400) + 1000,
				REFERENCE_POINT - SQUARE_WIDTH
		);
		add(
				new Steps(),
				random.nextInt(9400) + 1000,
				REFERENCE_POINT - SQUARE_WIDTH
		);

		/* Add tubes */
		add(
				Core.Tube,
				896,
				REFERENCE_POINT - Core.Tube.getHeight()
		);
		add(
				Core.Tube_long,
				1184,
				REFERENCE_POINT - Core.Tube_long.getHeight()

		);
		add(
				new GImage(Core.Tube_long.getImage()),
				1472,
				REFERENCE_POINT - Core.Tube_long.getHeight()

		);
		add(
				new GImage(Core.Tube_long.getImage()),
				1856,
				REFERENCE_POINT - Core.Tube_long.getHeight()

		);
	}

	public void jump(GPoint point) {
		this.getElementAt(point).move(0, -5);
	}
}