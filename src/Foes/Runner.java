package Foes;

import Builders.Foreground;
import Foes.Foe;
import acm.program.GraphicsProgram;
import sun.applet.Main;

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
		while (!isDead) {
			while (!isRightCollision() && right) {
				foe.move(5, 0);
				pause(10);
			}
			while (!isLeftCollision() && left) {
				foe.move(-5, 0);
				pause(10);
			}
		}
	}

	private boolean isLeftCollision() {
		if (foreground.contains(foe.getX(), foe.getY())) {
//		if (foreground.contains(foreground.getLocalPoint(foe.getX() - 5, foe.getY()))) {
//			System.out.println("Left collision");
			System.out.println(foe.getX() + " " + foe.getY());
			return true;
		} else {
			return false;
		}
	}

	private boolean isRightCollision() {
		if (foreground.contains(foe.getX() + foe.getWidth(), foe.getY())) {
//		if (foreground.contains(foreground.getLocalPoint(foe.getX() + foe.getWidth() + 5, foe.getY()))) {
//			System.out.println("Right collision");
			System.out.println(foe.getX() + " " + foe.getY());
			return true;
		} else {
			return false;
		}
	}

	public void setDead() {
		isDead = true;
	}
}