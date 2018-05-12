package Objects;

import Builders.Foreground;
import Game.Main;
import acm.program.GraphicsProgram;

public class Runner extends GraphicsProgram implements Runnable {
	private Foe foe;
	private Foreground foreground;
	private boolean isDead = false;
	private boolean right = true;
	private boolean left = false;


	public Runner(Foe foe, Foreground foreground) {
		this.foe = foe;
		this.foreground = foreground;
	}

	@Override
	public void run() {
		while (!isDead) {
			while (!isRightCollision() && right && !isDead && isOnGround()) {
				foe.move(1, 0);
				pause(4);
			}
			left = true;
			right = false;
			while (!isLeftCollision() && left && !isDead && isOnGround()) {
				foe.move(-1, 0);
				pause(4);
			}
			left = false;
			right = true;
			while (!isOnGround()) {
				foe.move(0, 1);
				pause(4);
			}
		}
	}

	private boolean isLeftCollision() {
		return  (Main.foreground.contains(foe.getX(), foe.getY()));
	}

	private boolean isRightCollision() {
		return  (Main.foreground.contains(foe.getX() + foe.getWidth() + 2, foe.getY()));
	}

	private boolean isOnGround() {
		return (Main.foreground.contains(foe.getX() + foe.getWidth() / 2, foe.getY() + foe.getHeight()));
	}

	public void kill() {
		isDead = true;
	}
}