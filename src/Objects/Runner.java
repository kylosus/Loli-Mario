package Objects;

import Builders.Foreground;
import Game.Main;
import acm.graphics.GObject;
import acm.program.GraphicsProgram;

public class Runner extends GraphicsProgram implements Runnable {
	private GObject object;
	private Foe foe;
	private PowerUpObject puo;
	private Foreground ground;
	private boolean isDead = false;
	private boolean right = true;
	private boolean left = false;


	Runner(Foe object, Foreground ground) {
		this.object = object;
		this.ground = ground;
		foe = object;
	}

	public Runner(PowerUpObject object, Foreground ground) {
		this.object = object;
		this.ground = ground;
		puo = object;
	}

	@Override
	public void run() {
		pause(1000);
		while (!isDead) {
			while (!isRightCollision() && right && !isDead && isOnGround()) {
				if (isDownCollision()) {
					break;
				}
				object.move(1, 0);
				pause(8);
			}
			left = true;
			right = false;
			while (!isLeftCollision() && left && !isDead && isOnGround()) {
				if (isDownCollision()) {
					break;
				}
				object.move(-1, 0);
				pause(8);
			}
			left = false;
			right = true;
			while (!isOnGround()) {
				object.move(0, 1);
				pause(8);
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

	private boolean isDownCollision() {
		return (ground.contains(object.getX() + object.getWidth() / 2, object.getY() + object.getHeight() / 2));
	}

	public void kill() {
		isDead = true;
		if (foe != null) {
			Main.stats.increaseScoreBy(foe.score.score);
		} else if (puo != null) {
			Main.stats.increaseScoreBy(puo.score.score);
		}
	}
}