package Objects;

import Builders.Foreground;
import acm.graphics.GCompound;
import acm.graphics.GImage;

public class Foe extends GCompound {
	public GImage Onscreen;
	public GImage alive;
	public GImage dead;
	public GImage points;
	public boolean isInterractible = true;
	public Runner runner;

	public Foe(GImage alive, GImage dead, GImage points) {
		this.alive = alive;
		this.dead = dead;
		this.points = points;
		this.Onscreen = new GImage(alive.getImage());
	}

	public boolean die() { // Returns true if killed
		if (isInterractible) {
			isInterractible = false;
			Onscreen.setImage(dead.getImage());
			runner.kill();
			add(points);
			new Thread(() -> {
				for (int i = 0; i < 100; i++) {
					points.move(0, -1);
					pause(5);
				}
				remove(Onscreen);
				remove(points);
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