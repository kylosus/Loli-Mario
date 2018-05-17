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
	private GLabel LIVES;
	private GLabel TIME;
	private static Font font;
	private ScoreItem score;
	private ScoreItem coins;
	private ScoreItem world;
	private ScoreItem lives;
	private ScoreItem time;

	static {
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File("lib/font.ttf"));
		} catch (java.io.IOException | FontFormatException e) {
			e.printStackTrace();
		}
		font = font.deriveFont(24f);
	}

	Stats(double canvasWidth, double canvasHeight) {
		setLabels(canvasWidth);
		setScoreItems();
		add(score);
		add(score, SCORE.getX() + (SCORE.getWidth() - score.getWidth()) / 2, SCORE.getY() + SCORE.getHeight());
		add(coins, COINS.getX(), COINS.getY() + COINS.getHeight());
		add(world, WORLD.getX(), WORLD.getY() + WORLD.getHeight());
		add(lives);
		add(time, TIME.getX(), TIME.getY() + TIME.getHeight());
		setLabel(SCORE, score);
		setLabel(COINS, coins);
		setLabel(WORLD, world);
		setLabel(LIVES, lives);
		setLabel(TIME, time);
	}


	private void setLabels(double canvasWidth) {
		double margin = canvasWidth / 5 + 20;
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
		LIVES = new GLabel("Lives", WORLD.getX() + WORLD.getWidth() + margin, 0);
		LIVES.setFont(font);
		LIVES.setLocation(LIVES.getX() - LIVES.getWidth(), 0);
		add(LIVES);
		TIME = new GLabel("Time", LIVES.getX() + LIVES.getWidth() + margin, 0);
		TIME.setFont(font);
		TIME.setLocation(TIME.getX() - TIME.getWidth(), 0);
		add(TIME);
	}

	private void setScoreItems() {
		score = new ScoreItem(font);
		coins = new ScoreItem(font);
		world = new ScoreItem(font, 1);
		lives = new ScoreItem(font, 3);
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
		setLabel(COINS, coins);
	}

	public void decreaseLives() {
		lives.iterateBy(-1);
		setLabel(LIVES, lives);
	}

	public void changeWorld(int world) {
		this.world.setValue(world);
		setLabel(WORLD, this.world);
	}

	public void increaseScoreBy(int value) {
		this.score.iterateBy(value);
		setLabel(SCORE, score);
	}


	void setWhite() {
		SCORE.setColor(Color.WHITE);
		COINS.setColor(Color.WHITE);
		WORLD.setColor(Color.WHITE);
		TIME.setColor(Color.WHITE);
		score.setColor(Color.WHITE);
		coins.setColor(Color.WHITE);
		world.setColor(Color.WHITE);
		lives.setColor(Color.WHITE);
		time.setColor(Color.WHITE);
	}
}
