package Game;

import Game.Main;
import acm.graphics.GImage;
import acm.graphics.GPoint;
import acm.program.GraphicsProgram;

public class WeaponShooter extends GraphicsProgram implements Runnable {

	private static GImage weapon;

	public WeaponShooter(GImage weapon) {
		this.weapon = weapon;
	}

	@Override
	public void run() {
		Main.canShoot = false;
		while (!hasCrashed()) {
			Main.character.weapon.forward.move(10, 0);
			pause(20);
		}
		Main.Weapon_Crash_Point = new GPoint(weapon.getX() + weapon.getWidth() + 10, weapon.getY());
		Main.canShoot = true;
		Main.hasWeaponCrashed = true;
	}

	private static boolean hasCrashed() {
		return (Main.foreground.contains(weapon.getX() + weapon.getWidth(), weapon.getY()));
	}
}
