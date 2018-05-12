package Objects;

import acm.graphics.GCompound;
import acm.graphics.GImage;

public class PowerUpObject extends GCompound {
	public GImage image;
	public Score score;
	public Shroom shroom; // Optional
	public boolean isShroom = false;

	public PowerUpObject(String image, int score) {
		this.image = new GImage(image);
		this.score = new Score(score);
		add(this.image);
	}

	public PowerUpObject(String image) {
		this.shroom = new Shroom();
		isShroom = true;
		this.score = new Score(1000);
		add(this.shroom);
	}
}
