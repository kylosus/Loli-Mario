package Levels;

import Builders.Core;
import acm.graphics.GImage;
import acm.graphics.GPoint;

import java.io.Serializable;

public class Level_1 extends Level {

	public Level_1(double REFERENCE_POINT, double WIDTH, double HEIGHT) {
		super(REFERENCE_POINT, WIDTH, HEIGHT);
		initiate_Ground_low();
		initiate_Ground_High();
		initiate_Tube();
		initiate_Steps();
		initiate_QuestionBlock();
	}

	private void initiate_Ground_low() {
		Ground_Low.put(new GPoint(0, REFERENCE_POINT), 70);
		Ground_Low.put(new GPoint(2336, REFERENCE_POINT), 15);
		Ground_Low.put(new GPoint(2912, REFERENCE_POINT), 67);
		Ground_Low.put(new GPoint(5120, REFERENCE_POINT), 58);
	}


	private void initiate_Ground_High() {
		Ground_High.put(new GPoint(21 * Level.Brick_Width, HEIGHT - 5 * Level.Brick_Width - 2 * Level.Brick_Width), 5);
		Ground_High.put(new GPoint(82 * Level.Brick_Width, HEIGHT - 5 * Level.Brick_Width - 2 * Level.Brick_Width), 3);
		Ground_High.put(new GPoint(85 * Level.Brick_Width, HEIGHT - 10 * Level.Brick_Width - 2 * Level.Brick_Width), 9);
		Ground_High.put(new GPoint(96 * Level.Brick_Width, HEIGHT - 10 * Level.Brick_Width - 2 * Level.Brick_Width), 4);
		Ground_High.put(new GPoint(99 * Level.Brick_Width, HEIGHT - 5 * Level.Brick_Width - 2 * Level.Brick_Width), 1);
		Ground_High.put(new GPoint(105 * Level.Brick_Width, HEIGHT - 5 * Level.Brick_Width - 2 * Level.Brick_Width), 2);
		Ground_High.put(new GPoint(123 * Level.Brick_Width, HEIGHT - 5 * Level.Brick_Width - 2 * Level.Brick_Width), 1);
		Ground_High.put(new GPoint(126 * Level.Brick_Width, HEIGHT - 10 * Level.Brick_Width - 2 * Level.Brick_Width), 3);
		Ground_High.put(new GPoint(133 * Level.Brick_Width, HEIGHT - 10 * Level.Brick_Width - 2 * Level.Brick_Width), 4);
		Ground_High.put(new GPoint(134 * Level.Brick_Width, HEIGHT - 5 * Level.Brick_Width - 2 * Level.Brick_Width), 2);
		Ground_High.put(new GPoint(175 * Level.Brick_Width, HEIGHT - 5 * Level.Brick_Width - 2 * Level.Brick_Width), 5);
	}

	private void initiate_Tube() {
		Tube.put(new GImage(Core.Tube_short), Level.Brick_Width * 29);
		Tube.put(new GImage(Core.Tube_short), Level.Brick_Width * 40);
		Tube.put(new GImage(Core.Tube_long), Level.Brick_Width * 48);
		Tube.put(new GImage(Core.Tube_short), Level.Brick_Width * 60);
		Tube.put(new GImage(Core.Tube_short), Level.Brick_Width * 169);
		Tube.put(new GImage(Core.Tube_short), Level.Brick_Width * 186);
	}

	private void initiate_Steps() {
		Steps.put(new GPoint(139 * Level.Brick_Width, HEIGHT - 4 * Level.Brick_Width + 1 * Level.Brick_Width), 4);
		Steps.put(new GPoint(145 * Level.Brick_Width, HEIGHT - 4 * Level.Brick_Width + 1 * Level.Brick_Width), -4);
		Steps.put(new GPoint(153 * Level.Brick_Width, HEIGHT - 4 * Level.Brick_Width + 1 * Level.Brick_Width), 5);
		Steps.put(new GPoint(160 * Level.Brick_Width, HEIGHT - 4 * Level.Brick_Width + 1 * Level.Brick_Width), -4);
		Steps.put(new GPoint(188 * Level.Brick_Width, HEIGHT - 8 * Level.Brick_Width + 5 * Level.Brick_Width), 8);
	}

	private void initiate_QuestionBlock() {
		QuestionBlock.add(new GPoint(17 * Level.Brick_Width, HEIGHT - 5 * Level.Brick_Width - 2 * Level.Brick_Width));
		QuestionBlock.add(new GPoint(22 * Level.Brick_Width, HEIGHT - 5 * Level.Brick_Width - 2 * Level.Brick_Width));
		QuestionBlock.add(new GPoint(23 * Level.Brick_Width, HEIGHT - 10 * Level.Brick_Width - 2 * Level.Brick_Width));
		QuestionBlock.add(new GPoint(24 * Level.Brick_Width, HEIGHT - 5 * Level.Brick_Width - 2 * Level.Brick_Width));
		QuestionBlock.add(new GPoint(40 * Level.Brick_Width, HEIGHT - 10 * Level.Brick_Width - 2 * Level.Brick_Width));
		QuestionBlock.add(new GPoint(70 * Level.Brick_Width, HEIGHT - 10 * Level.Brick_Width - 2 * Level.Brick_Width));
		QuestionBlock.add(new GPoint(77 * Level.Brick_Width, HEIGHT - 5 * Level.Brick_Width - 2 * Level.Brick_Width));
		QuestionBlock.add(new GPoint(83 * Level.Brick_Width, HEIGHT - 5 * Level.Brick_Width - 2 * Level.Brick_Width));
		QuestionBlock.add(new GPoint(86 * Level.Brick_Width, HEIGHT - 5 * Level.Brick_Width - 2 * Level.Brick_Width));
		QuestionBlock.add(new GPoint(86 * Level.Brick_Width, HEIGHT - 10 * Level.Brick_Width - 2 * Level.Brick_Width));
		QuestionBlock.add(new GPoint(89 * Level.Brick_Width, HEIGHT - 5 * Level.Brick_Width - 2 * Level.Brick_Width));
		QuestionBlock.add(new GPoint(107 * Level.Brick_Width, HEIGHT - 10 * Level.Brick_Width - 2 * Level.Brick_Width));
		QuestionBlock.add(new GPoint(148 * Level.Brick_Width, HEIGHT - 10 * Level.Brick_Width - 2 * Level.Brick_Width));
	}
}
