package Foes;

import Builders.Foreground;
import acm.graphics.GCompound;
import acm.graphics.GImage;

public class Foe extends GCompound {
	public GImage Onscreen;
	public GImage alive;
	public GImage dead;
	public Runner runner;

	public Foe(GImage alive, GImage dead) {
		this.alive = alive;
		this.dead = dead;
		this.Onscreen = new GImage(alive.getImage());
	}


	public void init(Foreground foreground, double x, double HEIGHT) {
//		add(this, foreground.getLocalPoint(x, foreground.REFERENCE_POINT - this.getHeight()));
		runner = new Runner(this, foreground);
		new Thread(runner).start();
	}
}