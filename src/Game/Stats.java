package Game;

import acm.graphics.GCompound;
import acm.graphics.GLabel;
import com.sun.istack.internal.NotNull;

import java.awt.*;
import java.io.File;

public class Stats extends GCompound {
	private GLabel SCORE;
	private GLabel COINS;
	private GLabel WORLD;
	private GLabel TIME;
	private static Font font;
	private ScoreItem score;
	private ScoreItem coins;
	private ScoreItem world;
	private ScoreItem time;

	static {
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File("lib/font.ttf"));
		} catch (java.io.IOException | FontFormatException e) {
			e.printStackTrace();
		}
		font = font.deriveFont(24f);
	}

	public Stats(double canvasWidth, double canvasHeight) {
		setLabels(canvasWidth);
		setScoreItems();
		add(score);
		add(score, SCORE.getX() + (SCORE.getWidth() - score.getWidth()) / 2, SCORE.getY() + SCORE.getHeight());
		add(coins, COINS.getX(), COINS.getY() + COINS.getHeight());
		add(world, WORLD.getX(), WORLD.getY() + WORLD.getHeight());
		add(time, TIME.getX(), TIME.getY() + TIME.getHeight());
		setLabel(SCORE, score);
		setLabel(COINS, coins);
		setLabel(WORLD, world);
		setLabel(TIME, time);
	}


	private void setLabels(double canvasWidth) {
		double margin = canvasWidth / 4 + 20;
		SCORE = new GLabel("Score", 20, 0);
		SCORE.setFont(font);
		add(SCORE);
		COINS = new GLabel("Coins", SCORE.getX() + SCORE.getWidth() + margin, 0);
		COINS.setFont(font);
		COINS.setLocation(COINS.getX() - COINS.getWidth(), 0);
		add(COINS);
		WORLD = new GLabel("World", COINS.getX() + COINS.getWidth() + margin, 0);
		WORLD.setFont(font);
		WORLD.setLocation(WORLD.getX() - WORLD.getWidth(), 0);
		add(WORLD);
		TIME = new GLabel("Time", WORLD.getX() + WORLD.getWidth() + margin, 0);
		TIME.setFont(font);
		TIME.setLocation(TIME.getX() - TIME.getWidth(), 0);
		add(TIME);
	}

	private void setScoreItems() {
		score = new ScoreItem(font);
		coins = new ScoreItem(font);
		world = new ScoreItem(font, 1);
		time = new ScoreItem(font, 300);
		time.startTimer();
	}

	private void setLabel(GLabel label, ScoreItem item) {
		item.setLocation(label.getX() + (label.getWidth() - item.getWidth()) / 2, label.getY() + label.getHeight());
	}

	public void getCoin() {
		coins.iterateOnce();
		score.iterateBy(500);
		setLabel(SCORE, score);
//		setLabel(COINS, coins);
	}


	public void setWhite() {
		SCORE.setColor(Color.WHITE);
		COINS.setColor(Color.WHITE);
		WORLD.setColor(Color.WHITE);
		TIME.setColor(Color.WHITE);
		score.setColor(Color.WHITE);
		coins.setColor(Color.WHITE);
		world.setColor(Color.WHITE);
		time.setColor(Color.WHITE);
	}
}
