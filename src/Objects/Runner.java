package Objects;

import Builders.Foreground;
import Game.Main;
import acm.graphics.GObject;
import acm.program.GraphicsProgram;

public class Runner extends GraphicsProgram implements Runnable {
	private GObject object;
	private Foreground ground;
	private boolean isDead = false;
	private boolean right = true;
	private boolean left = false;


	public Runner(GObject object, Foreground ground) {
		this.object = object;
		this.ground = ground;
	}

	@Override
	public void run() {
		while (!isDead) {
			while (!isRightCollision() && right && !isDead && isOnGround()) {
				object.move(1, 0);
				pause(4);
			}
			left = true;
			right = false;
			while (!isLeftCollision() && left && !isDead && isOnGround()) {
				object.move(-1, 0);
				pause(4);
			}
			left = false;
			right = true;
			while (!isOnGround()) {
				object.move(0, 1);
				pause(4);
			}
		}
	}

	private boolean isLeftCollision() {
		return  (ground.contains(object.getX() - 1, object.getY()));
	}

	private boolean isRightCollision() {
		return  (ground.contains(object.getX() + object.getWidth() + 2, object.getY()));
	}

	private boolean isOnGround() {
		return (ground.contains(object.getX() + object.getWidth() / 2, object.getY() + object.getHeight() + 1));
	}

	public void kill() {
		isDead = true;
	}
}