package Builders;

import Builders.Core;
import Levels.Level;
import Levels.Level_1;
import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.graphics.GPoint;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.Random;

public class Foreground extends GCompound implements Serializable {
//	public static final int AMOUNT_OF_SQUARES = 300;
	public static final int BRICK_WIDTH = 32;
//	public static final double GROUND_SQUARE_RATIO = 0.75
	public static double REFERENCE_POINT;
//	private static Random random = new Random();

	public Foreground(double WIDTH, double HEIGHT) {

		REFERENCE_POINT = HEIGHT - (2 * BRICK_WIDTH);

		add(Core.SakuraTree);

		Level_1 level = new Level_1(REFERENCE_POINT, WIDTH, HEIGHT);
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

			x = 0;
			y = 0;

			for (Map.Entry<GPoint, Integer> entry : level.Ground_High.entrySet()) {
				x = entry.getKey().getX();
				y = entry.getKey().getY();
				for (int i = 0; i < entry.getValue(); i++) {
					add(new GImage(Core.Ground_High), x, y);
					x += BRICK_WIDTH;
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

}