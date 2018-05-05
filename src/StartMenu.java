import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.program.GraphicsProgram;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class StartMenu extends GCompound {
	private double WIDTH;
	private double HEIGHT;
	private GImage background = Core.Menu_Background;
	private GImage Left_Bar = Core.Menu_Left_Bar;
	private GImage Right_Bar = Core.Menu_Right_Bar;
	private GLabel name;
	private GLabel age;
	private GLabel weapon;

	public StartMenu(double WIDTH, double HEIGHT) {
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		add(background);
		add(Left_Bar, -Left_Bar.getWidth() * 2 / 3, 0);
		add(Right_Bar, WIDTH * 2 / 3, 0);
		add(
				Main.character.Large,
				(WIDTH - Main.character.Large.getWidth()) / 2,
				0
		);
		setGLabels();
		add(name);
		add(age);
		add(weapon);
		add(
				Main.character.weapon.forward,
				weapon.getX(),
				weapon.getY() + weapon.getHeight()
		);

	}

	private void setGLabels() {
		name = new GLabel(
				"Name: " + Main.character.name,
				WIDTH * 2 / 3 + 260,
				75
		);
		name.setFont("sans-24");

		age = new GLabel(
				"Age: " + Main.character.age,
				name.getX(),
				name.getY() + name.getHeight()
		);
		age.setFont(name.getFont());

		weapon = new GLabel(
				"Weapon: " + Main.character.weapon.name,
				age.getX(),
				age.getY() + age.getHeight()
		);
		weapon.setFont(age.getFont());
	}
}
