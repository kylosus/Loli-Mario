package Objects;

import acm.graphics.GCompound;
import acm.graphics.GImage;

public class PowerUpObject extends GCompound {
	public GImage image;
	public Score score;
	public boolean isShroom = false;
	public boolean hasDied = false;
	public Runner runner;

	public PowerUpObject(String image, int score) {
		this.image = new GImage(image);
		this.score = new Score(score);
		add(this.image);
	}

	public PowerUpObject(String image) {
//		this.shroom = new Shroom();
		isShroom = true;
		this.score = new Score(1000);
//		add(this.shroom);
	}

	public int die() {
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
		return this.score.score;
	}
}
