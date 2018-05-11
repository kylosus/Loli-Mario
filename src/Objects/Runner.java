package Objects;

import Builders.Foreground;
import acm.program.GraphicsProgram;

public class Runner extends GraphicsProgram implements Runnable {
	private Foe foe;
	private Foreground foreground;
	private boolean isDead = false;
	private boolean right = true;
	private boolean left = true;


	public Runner(Foe foe, Foreground foreground) {
		this.foe = foe;
		this.foreground = foreground;
	}

	@Override
	public void run() {
		double dx = 0, dy = 2;
		while (!isDead) {
			while (!isRightCollision() && right && !isDead && isOnGround()) {
				foe.move(5, 0);
				pause(20);
			}
			while (!isLeftCollision() && left && !isDead && isOnGround()) {
				foe.move(-5, 0);
				pause(20);
			}
			while (!isOnGround()) {
				foe.move(dx, dy);
				pause(10);
				dy++;
			}
			dy = 2;
		}
	}

	private boolean isLeftCollision() {
		return  (foreground.contains(foe.getX(), foe.getY()));
	}

	private boolean isRightCollision() {
		return  (foreground.contains(foe.getX() + foe.getWidth(), foe.getY()));
	}

	private boolean isOnGround() {
		return (foreground.contains(foe.getX() + foe.getWidth() / 2, foe.getY() + foe.getHeight()));
	}

	public void kill() {
		isDead = true;
	}
}