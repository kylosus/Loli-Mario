package Objects;

import Builders.Foreground;
import Game.Sound;
import acm.graphics.GCompound;
import acm.graphics.GImage;

public class Foe extends GCompound {
	GImage Onscreen;
	private GImage dead;
	Score score;
	public boolean isInterractible = true;
	public Runner runner;

	Foe(GImage alive, GImage dead) {
		this.dead = dead;
		this.score = new Score();
		this.Onscreen = new GImage(alive.getImage());
	}

	public void die() {
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
		}
	}

	public Foe init(Foreground foreground) {
		runner = new Runner(this, foreground);
		new Thread(runner).start();
		return this;
	}
}