package Objects;

import Builders.Core;
import acm.graphics.GCompound;
import acm.graphics.GImage;

import java.util.HashMap;
import java.util.Random;

public class Score extends GCompound {
	public GImage image;
	public int score;
	private static HashMap<Integer, String> Scores = new HashMap<>();

	public Score(int score) {
		if (Scores.isEmpty()) {
//			initMap();
		}
		this.score = score;
		this.image = new GImage("Images/Scores/" + Integer.toString(score) + ".png");
		add(this.image);
	}

	public Score() {
		if (Scores.isEmpty()) {
//			initMap();
		}
		this.score = new Random().nextInt(9) + 1;
		this.image = new GImage("Images/Scores/" + Integer.toString(score * 100) + ".png");
		add(this.image);
	}

	public static void initMap() {
		for (int i = 1; i <= 10; i++) {
			Scores.put(i * 100, "Images/Scores/" + Integer.toString(i * 100) + ".png");
		}
	}
}