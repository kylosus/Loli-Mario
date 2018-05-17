package Objects;

import acm.graphics.GCompound;
import acm.graphics.GImage;

import java.util.HashMap;
import java.util.Random;

public class Score extends GCompound {
	private GImage image;
	int score;

	@SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
	@Deprecated
	private static HashMap<Integer, String> Scores = new HashMap<>();

	Score(int score) {
		this.score = score;
		this.image = new GImage("Images/Scores/" + Integer.toString(score) + ".png");
		add(this.image);
	}

	Score() {
		this.score = new Random().nextInt(9) + 1;
		this.image = new GImage("Images/Scores/" + Integer.toString(score * 100) + ".png");
		this.score = this.score * 100;
		add(this.image);
	}

	@Deprecated
	public static void initMap() {
		for (int i = 1; i <= 10; i++) {
			Scores.put(i * 100, "Images/Scores/" + Integer.toString(i * 100) + ".png");
		}
	}
}