package Builders;

import Levels.Level;
import Levels.Level_1;
import Objects.QuestionBlock;
import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.graphics.GPoint;
import java.util.Map;
import java.util.Random;

public class Foreground extends GCompound {
	private static final int BRICK_WIDTH = 32;
	public static double REFERENCE_POINT;
	private Level level;

	public Foreground(double WIDTH, double HEIGHT) {

		REFERENCE_POINT = HEIGHT - (2 * BRICK_WIDTH);

//		add(Core.SakuraTree, 9000, 100 - Core.SakuraTree.getHeight());
		for (int i = 0; i < 5; i++) {
			add(new GImage(Core.Ground_High), i * BRICK_WIDTH + 9000, 100);
		}

		level = new Level_1(REFERENCE_POINT, WIDTH, HEIGHT);

		{
			double x, y, dy = 0;

			for (int i1 = 0; i1 < 2; i1++) {
				for (Map.Entry<GPoint, Integer> entry : level.Ground_Low.entrySet()) {
					x = entry.getKey().getX();
					y = entry.getKey().getY() + dy;
					for (int i2 = 0; i2 < entry.getValue(); i2++) {
						add(new GImage(Core.Ground_Low), x, y);
						x += BRICK_WIDTH;
					}
				}
				dy += Level.Brick_Width;
			}

			for (Map.Entry<GPoint, Integer> entry : level.Ground_High.entrySet()) {
				x = entry.getKey().getX();
				y = entry.getKey().getY();
				for (int i = 0; i < entry.getValue(); i++) {
					add(new GImage(Core.Ground_High), x, y);
					x += BRICK_WIDTH;
				}
			}

			for (GPoint point : level.QuestionBlock) {
				if (getElementAt(point) == null) {
					add(new QuestionBlock(), point);
				} else {
					remove(getElementAt(point));
					add(new QuestionBlock(), point);
				}
			}
		}

		for (Map.Entry<GImage, Double> entry : level.Tube.entrySet()) {
			add(
					entry.getKey(),
					entry.getValue(),
					REFERENCE_POINT - entry.getKey().getHeight()
			);
		}

		for (Map.Entry<GPoint, Integer> entry : level.Steps.entrySet()) {
			add(
					new Steps(entry.getValue()),
					entry.getKey()
			);
		}
	}

	/**
	 *
	 * @return random Question Block
	 */
	public QuestionBlock getRandomQuestionBlock() {
		return (QuestionBlock) getElementAt(level.QuestionBlock.get(new Random().nextInt(level.QuestionBlock.size())).getLocation());
	}

	// Initiates secret level
	public void initiateSecret(GPoint point) {
		add(new GImage("Images/Special/waitwhat.png"), 14000, 200);
		point = getLocalPoint(point);
		for (int i = 0; i < 300; i++) {
			add(new GImage(Core.Ground_High), point.getX() + i * 32 + 64, Foreground.REFERENCE_POINT);
			add(new QuestionBlock(), point.getX() + i * 32 + 96, Foreground.REFERENCE_POINT - 100);
		}
	}
}