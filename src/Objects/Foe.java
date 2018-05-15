package Objects;

import Builders.Foreground;
import Game.Sound;
import acm.graphics.GCompound;
import acm.graphics.GImage;

public class Foe extends GCompound {
	public GImage Onscreen;
	public GImage alive;
	public GImage dead;
	public Score score;
	public boolean isInterractible = true;
	public Runner runner;

	public Foe(GImage alive, GImage dead) {
		this.alive = alive;
		this.dead = dead;
		this.score = new Score();
		this.Onscreen = new GImage(alive.getImage());
	}

	public boolean die() { // Returns true if killed
		if (isInterractible) {
			Sound.playStomp();
			isInterractible = false;
			Onscreen.setImage(dead.getImage());
			runner.kill();
			add(score);
			new Thread(() -> {
				for (int i = 0; i < 100; i++) {
					score.move(0, -1);
					pause(5);
				}
				remove(Onscreen);
				remove(score);
			}).start();
			return true;
		}
		return false;
	}


	public Foe init(Foreground foreground) {
//		add(this, foreground.getLocalPoint(x, foreground.REFERENCE_POINT - this.getHeight()));
		runner = new Runner(this, foreground);
		new Thread(runner).start();
		return this;
	}
}