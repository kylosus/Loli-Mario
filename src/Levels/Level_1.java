package Levels;

import Builders.Core;
import acm.graphics.GImage;
import acm.graphics.GPoint;

public class Level_1 extends Level {

	public Level_1(double REFERENCE_POINT, double WIDTH, double HEIGHT) {
		super(REFERENCE_POINT, WIDTH, HEIGHT);
		initiate_Ground_low();
		initiate_Ground_High();
		initiate_Tube();
		initiate_Steps();
	}

	private void initiate_Ground_low() {
		Ground_Low.put(new GPoint(0, REFERENCE_POINT), 70);
		Ground_Low.put(new GPoint(2336, REFERENCE_POINT), 15);
		Ground_Low.put(new GPoint(2908, REFERENCE_POINT), 64);
		Ground_Low.put(new GPoint(5052, REFERENCE_POINT), 47);
	}


	private void initiate_Ground_High() {
		Ground_High.put(new GPoint(21 * Level.Brick_Width, HEIGHT - 4 * Level.Brick_Width - 2 * Level.Brick_Width), 5);
		Ground_High.put(new GPoint(82 * Level.Brick_Width, HEIGHT - 4 * Level.Brick_Width - 2 * Level.Brick_Width), 3);
		Ground_High.put(new GPoint(85 * Level.Brick_Width, HEIGHT - 10 * Level.Brick_Width - 2 * Level.Brick_Width), 9);
		Ground_High.put(new GPoint(96 * Level.Brick_Width, HEIGHT - 10 * Level.Brick_Width - 2 * Level.Brick_Width), 4);
		Ground_High.put(new GPoint(99 * Level.Brick_Width, HEIGHT - 4 * Level.Brick_Width - 2 * Level.Brick_Width), 1);
		Ground_High.put(new GPoint(105 * Level.Brick_Width, HEIGHT - 4 * Level.Brick_Width - 2 * Level.Brick_Width), 2);
		Ground_High.put(new GPoint(123 * Level.Brick_Width, HEIGHT - 4 * Level.Brick_Width - 2 * Level.Brick_Width), 1);
		Ground_High.put(new GPoint(126 * Level.Brick_Width, HEIGHT - 10 * Level.Brick_Width - 2 * Level.Brick_Width), 3);
		Ground_High.put(new GPoint(133 * Level.Brick_Width, HEIGHT - 10 * Level.Brick_Width - 2 * Level.Brick_Width), 4);
		Ground_High.put(new GPoint(134 * Level.Brick_Width, HEIGHT - 4 * Level.Brick_Width - 2 * Level.Brick_Width), 2);
		Ground_High.put(new GPoint(175 * Level.Brick_Width, HEIGHT - 4 * Level.Brick_Width - 2 * Level.Brick_Width), 5);
	}

	private void initiate_Tube() {
		Tube.put(new GImage(Core.Tube_short), Level.Brick_Width * 29);
		Tube.put(new GImage(Core.Tube_short), Level.Brick_Width * 40);
		Tube.put(new GImage(Core.Tube_long), Brick_Width * 48);
		Tube.put(new GImage(Core.Tube_short), Level.Brick_Width * 60);
		Tube.put(new GImage(Core.Tube_short), Level.Brick_Width * 169);
		Tube.put(new GImage(Core.Tube_short), Level.Brick_Width * 186);
	}

	private void initiate_Steps() {
		Steps.put(new GPoint(139 * Level.Brick_Width, HEIGHT - 4 * Level.Brick_Width + 1 * Level.Brick_Width), 4);
		Steps.put(new GPoint(145 * Level.Brick_Width, HEIGHT - 4 * Level.Brick_Width + 1 * Level.Brick_Width), 4);
		Steps.put(new GPoint(154 * Level.Brick_Width, HEIGHT - 4 * Level.Brick_Width + 1 * Level.Brick_Width), 5);
		Steps.put(new GPoint(162 * Level.Brick_Width, HEIGHT - 4 * Level.Brick_Width + 1 * Level.Brick_Width), 4);
		Steps.put(new GPoint(188 * Level.Brick_Width, HEIGHT - 8 * Level.Brick_Width + 5 * Level.Brick_Width), 8);
	}
}
