package Objects;

import acm.graphics.GCompound;
import acm.graphics.GImage;

public class PowerUpObject extends GCompound {
	GImage image;
	Score score;
	boolean isShroom = false;
	private boolean hasDied = false;
	public Runner runner;

	PowerUpObject(String image, int score) {
		this.image = new GImage(image);
		this.score = new Score(score);
		add(this.image);
	}

	PowerUpObject(String image) {
		this.image = new GImage(image);
		isShroom = true;
		this.score = new Score(1000);
	}

	public void die() {
		if (!hasDied) {
			remove(image);
			hasDied = true;
			add(score);
			new Thread(() -> {
				for (int i = 0; i < 100; i++) {
					score.move(0, -1);
					pause(5);
				}
				remove(score);
			}).start();
		}
	}
}
